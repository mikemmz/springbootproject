package com.arms.domain.service;

import com.arms.domain.entity.User;

public abstract interface UserAccountService
{
  public abstract User findOneByEmail(String paramString);
}
