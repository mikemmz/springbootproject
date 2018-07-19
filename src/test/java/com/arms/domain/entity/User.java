package com.arms.domain.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User
{
  @Id
  @GeneratedValue
  private int id;
  @Column(nullable=false, unique=true)
  private String email;
  @Column(nullable=false)
  private String password;
  
  public int getId()
  {
    return this.id;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }

  @ManyToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.EAGER)
  @JoinTable(name="users_roles", joinColumns={@javax.persistence.JoinColumn(name="user_id")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="role_id")})
  private Set<Role> roles = new HashSet();
  



public Set<Role> getRoles()
{
  return this.roles;
}

public void setRoles(Set<Role> roles)
{
  this.roles = roles;
}
}