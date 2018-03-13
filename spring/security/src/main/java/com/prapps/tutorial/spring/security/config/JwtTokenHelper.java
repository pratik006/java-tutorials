package com.prapps.tutorial.spring.security.config;

import com.prapps.tutorial.spring.security.exception.SecurityException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;

public class JwtTokenHelper {
	private static final String ISSUER = "apps-pratiks application";
	private static final String SIGNING_KEY = "LongAndHardToGuessValueWithSpecialCharacters";

	private static long durationInDays = 5000000l;

	/**
	 * Creates a JSON Web Token which is digitally signed token and contains a
	 * payload (e.g. userId to identify the user). The signing key is secret
	 * which ensures that the token is authentic and has not been modified.
	 * Using a JWT eliminates the need to store authentication session
	 * information in a database.
	 *
	 * @param userId
	 * @param durationInDays
	 * @return
	 */

	public static String createJsonWebToken(Authentication auth) {
		//The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);

	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SIGNING_KEY);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	    Map<String, Object> claimItems = new HashMap<String, Object>();
	    claimItems.put("username", auth.getPrincipal());
	    claimItems.put("authorities", auth.getAuthorities());
	    Claims claims = new DefaultClaims(claimItems);

	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder().setId(UUID.randomUUID().toString())
	                                .setIssuedAt(now)
	                                .setClaims(claims)
	                                .setIssuer(ISSUER)
	                                .signWith(signatureAlgorithm, signingKey);

	    //if it has been specified, let's add the expiration
	    if (durationInDays >= 0) {
	    long expMillis = nowMillis + durationInDays;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }

	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}

	public static UserDetails verifyToken(String token) throws SecurityException {
		Claims claims;
		try {
			claims = Jwts.parser()
		       .setSigningKey(DatatypeConverter.parseBase64Binary(SIGNING_KEY))
		       .parseClaimsJws(token).getBody();
		}catch(io.jsonwebtoken.MalformedJwtException ex) {
			throw new SecurityException(ex, "Invalid token");
		}

	    final String username = claims.get("username", String.class);
	    Collection<LinkedHashMap<String, String>> auths = claims.get("authorities", Collection.class);

	    List<SimpleGrantedAuthority> list = new ArrayList<>(auths.size());
		for (LinkedHashMap<String, String> auth : auths) {
			list.add(new SimpleGrantedAuthority(auth.get("authority")));
		}

	    UserDetails userDetails = new UserDetails() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isEnabled() {
				return true;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return false;
			}

			@Override
			public boolean isAccountNonLocked() {
				return false;
			}

			@Override
			public boolean isAccountNonExpired() {
				return false;
			}

			@Override
			public String getUsername() {
				return username;
			}

			@Override
			public String getPassword() {
				return null;
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return list;
			}
		};
	    return userDetails;
	}

	public static void main(String args[]) throws SecurityException {
		GrantedAuthority g = new GrantedAuthority() {
			private static final long serialVersionUID = 1L;
			@Override
			public String getAuthority() {
				return "ROLE_ADMIN";
			}
		};
		String token = createJsonWebToken(new UsernamePasswordAuthenticationToken("yogi", "password", Arrays.asList(g)));
		System.out.println(token);
		UserDetails user = verifyToken(token);
		System.out.println(user.getUsername());
	}

}