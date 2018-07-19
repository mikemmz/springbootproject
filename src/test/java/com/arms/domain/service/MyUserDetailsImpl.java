package com.arms.domain.service;

import com.arms.domain.entity.Role;
import com.arms.domain.entity.User;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetailsImpl implements UserDetails {
  
	
	private static final long serialVersionUID = 1L;
  private User user;
  
  public MyUserDetailsImpl(User user)
  {
    this.user = user;
  }
  
  public Collection<? extends GrantedAuthority> getAuthorities()
  {
    Set<GrantedAuthority> authorities = new HashSet();
    Set<Role> userRoles = this.user.getRoles();
    for (Role role : userRoles) {
      authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
    }
    return authorities;
  }
  
  public String getPassword()
  {
    return this.user.getPassword();
  }
  
  public String getUsername()
  {
    return this.user.getEmail();
  }
  
  public boolean isAccountNonExpired()
  {
    return true;
  }
  
  public boolean isAccountNonLocked()
  {
    return true;
  }
  
  public boolean isCredentialsNonExpired()
  {
    return true;
  }
  
  public boolean isEnabled()
  {
    return true;
  }
}
