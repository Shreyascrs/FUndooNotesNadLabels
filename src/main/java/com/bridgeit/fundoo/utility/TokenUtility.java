package com.bridgeit.fundoo.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class TokenUtility {

	public String verifyToken(String token) 
	{
		String secretPin="##9738672744";
//		String id;
//		Verification verification=JWT.require(Algorithm.HMAC256(secretPin));
//		JWTVerifier jwtVerifier=verification.build();
//		DecodedJWT decodedJWT=jwtVerifier.verify(token);
//		Claim claim=decodedJWT.getClaim("ID");
//		id=claim.asString();
		 Jws<Claims> claims = Jwts.parser()
		          .setSigningKey(secretPin)
		          .parseClaimsJws(token);
		        String userId = claims.getBody().getId();
		        return userId;
				
	}
}
