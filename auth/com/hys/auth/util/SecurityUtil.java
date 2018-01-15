package com.hys.auth.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

public class SecurityUtil {

	public static String getUserName() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return userDetails.getUsername();
	}

	public static List<String> getAuthoritiesList() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		GrantedAuthority[] authorities = userDetails.getAuthorities();
		List<String> auths = new ArrayList<String>(authorities.length);
		for (GrantedAuthority auth : authorities) {
			auths.add(auth.getAuthority());
		}
		return auths;
	}
}
