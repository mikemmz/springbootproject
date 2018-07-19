package com.arms.domain.service;

import com.arms.app.user.EditUser;
import com.arms.domain.component.PasswordEncoder;
import com.arms.domain.entity.User;
import com.arms.domain.repository.UserRepository;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
  @Autowired
  UserRepository userRepository;
  @Autowired
  PasswordEncoder passwordEncoder;
  
  public Page<User> getAllUsers(Pageable pageable)
  {
    return this.userRepository.findAll(pageable);
  }
  
  public User findOne(int userId)
  {
    return (User)this.userRepository.findOne(Integer.valueOf(userId));
  }
  
  public EditUser setUserEditForm(int userId)
  {
    User user = (User)this.userRepository.findOne(Integer.valueOf(userId));
    EditUser editUser = new EditUser();
    editUser.setId(user.getId());
    editUser.setEmail(user.getEmail());
    editUser.setPassword(user.getPassword());
    return editUser;
  }
  
  public void updateUser(EditUser userEditForm)
    throws NoSuchAlgorithmException
  {
    User user = (User)this.userRepository.findOne(Integer.valueOf(userEditForm.getId()));
    user.setPassword(this.passwordEncoder.hashMD5(userEditForm.getPassword()));
    this.userRepository.save(user);
  }
  
  public void deleteUser(int userId)
  {
    this.userRepository.delete(Integer.valueOf(userId));
  }

}

