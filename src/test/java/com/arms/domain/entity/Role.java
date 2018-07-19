package com.arms.domain.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role
{
  @Id
  @GeneratedValue
  private int id;
  @Column(name="role_name")
  private String roleName;
  
  public int getId()
  {
    return this.id;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public String getRoleName()
  {
    return this.roleName;
  }
  
  public void setRoleName(String roleName)
  {
    this.roleName = roleName;
  }
  
  @ManyToMany(mappedBy="roles")
  private Set<User> users = new HashSet();
  
  public Set<User> getUsers()
  {
    return this.users;
  }
  
  public void setUsers(Set<User> users)
  {
    this.users = users;
  }
}
