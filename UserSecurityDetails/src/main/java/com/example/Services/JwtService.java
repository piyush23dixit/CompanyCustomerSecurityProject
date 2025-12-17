package com.example.Services;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
@Component
public class JwtService {
	
//private String secretKey="";
//	
//	public JwtService() {
//		
//		KeyGenerator keyGen;
//		try {
//			keyGen = KeyGenerator.getInstance("HmacSHA256");
//		SecretKey sk=keyGen.generateKey();
//		secretKey= Base64.getEncoder().encodeToString(sk.getEncoded());		
//		} catch (NoSuchAlgorithmException e) {
//		e.printStackTrace();
//	}
//	}
//	private SecretKey getKey() {
//	    byte[] keyBytes = Base64.getDecoder().decode(secretKey);
//	    return Keys.hmacShaKeyFor(keyBytes); 
//	}
	
	
	@Value("${jwt.secret}")
	private String secretKey;

	private SecretKey getKey() {
	    byte[] keyBytes = Base64.getDecoder().decode(secretKey);
	    return Keys.hmacShaKeyFor(keyBytes); 
	}

	public String generateToken(String username, String role) {
		
		Map<String, Object> claims= new HashMap<>();
		claims.put("role", role);
		
		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+60*60*180000))
				.and()
				.signWith(getKey())
				.compact();
	}
	
	public String extractUserName(String token) {
	    return extractClaim(token, Claims::getSubject);
	}
	
	 public String extractUserRole(String token) {
	        return extractClaim(token, claims -> claims.get("role", String.class));
	    }

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	    final Claims claims = extractAllClaims(token);
	    return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
	    return Jwts.parser()
	               .verifyWith(getKey()).build()
	               .parseSignedClaims(token)
	               .getPayload();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	 public boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }
	 
	 private Date extractExpiration(String token) {
		 return extractClaim(token, Claims::getExpiration);
	 }

	    

}
