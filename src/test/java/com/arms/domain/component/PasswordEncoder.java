package com.arms.domain.component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder
{
  public String hashMD5(String original)
    throws NoSuchAlgorithmException
  {
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(original.getBytes());
    byte[] digest = md.digest();
    StringBuffer sb = new StringBuffer();
    byte[] arrayOfByte1;
    int j = (arrayOfByte1 = digest).length;
    for (int i = 0; i < j; i++)
    {
      byte b = arrayOfByte1[i];
      sb.append(String.format("%02x", new Object[] { Integer.valueOf(b & 0xFF) }));
    }
    return sb.toString();
  }
}
