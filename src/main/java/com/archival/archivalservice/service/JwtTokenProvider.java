package com.archival.archivalservice.service;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.archival.archivalservice.dto.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtTokenProvider {

	private final String secretKey = "YourSecretKey"; 
	private final long tokenValidity = 3600000;

	public String generateToken(UserDetails userDetails, List<Role> roles) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + tokenValidity);

		Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
		claims.put("roles", roles);

		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException
				| IllegalArgumentException ex) {
			return false;
		}
	}
	
	public String getUsernameFromToken(String token) {
	    Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	    return claims.getSubject();
	}
}
