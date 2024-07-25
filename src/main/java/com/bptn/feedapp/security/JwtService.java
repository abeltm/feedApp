package com.bptn.feedapp.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bptn.feedapp.provider.ResourceProvider;

@Component
public class JwtService {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ResourceProvider provider;

	public String generateJwtToken(String username, long expiration) {

		return JWT.create().withIssuer(this.provider.getJwtIssuer()).withAudience(this.provider.getJwtAudience())
				.withIssuedAt(new Date()).withSubject(username)
				.withExpiresAt(new Date(System.currentTimeMillis() + expiration))
				.sign(HMAC512(this.provider.getJwtSecret()));
	}

	public DecodedJWT verifyJwtToken(String token) {
		
//		JWT.require() method creates a verifier object that requires a signature algorithm and a secret key to verify the token
		return JWT.require(HMAC512(this.provider.getJwtSecret()))
				.withIssuer(this.provider.getJwtIssuer())
//				.build() method builds the verifier object with the specified requirements
				.build()
//				verify() method of the verifier object is called with the token parameter to verify the JWT.
				.verify(token);
	}

	public String getSubject(String token) {

		return JWT.require(HMAC512(this.provider.getJwtSecret()))
				.withIssuer(this.provider.getJwtIssuer())
				.build()
				.verify(token).getSubject();
	}

}