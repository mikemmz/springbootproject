package com.arms.app.user;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class EditUser
{
  @NotNull
  private int id;
  @NotEmpty
  private String email;
  @NotEmpty
  private String password;
  
  public String toString()
  {
    return "EditUser(id=" + getId() + ", email=" + getEmail() + ", password=" + getPassword() + ")";
  }
  
  public int hashCode()
  {
    int PRIME = 59;int result = 1;result = result * 59 + getId();Object $email = getEmail();result = result * 59 + ($email == null ? 43 : $email.hashCode());Object $password = getPassword();result = result * 59 + ($password == null ? 43 : $password.hashCode());return result;
  }
  
  protected boolean canEqual(Object other)
  {
    return other instanceof EditUser;
  }
  
  public boolean equals(Object o)
  {
    if (o == this) {
      return true;
    }
    if (!(o instanceof EditUser)) {
      return false;
    }
    EditUser other = (EditUser)o;
    if (!other.canEqual(this)) {
      return false;
    }
    if (getId() != other.getId()) {
      return false;
    }
    Object this$email = getEmail();Object other$email = other.getEmail();
    if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
      return false;
    }
    Object this$password = getPassword();Object other$password = other.getPassword();return this$password == null ? other$password == null : this$password.equals(other$password);
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public int getId()
  {
    return this.id;
  }
}
