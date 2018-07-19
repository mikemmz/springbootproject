package com.arms.app.user;

import com.arms.domain.component.PageWrapper;
import com.arms.domain.entity.User;
import com.arms.domain.service.UserService;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController
{
  @Autowired
  UserService userService;
  
  @RequestMapping({"/list"})
  public String list(Model model, Pageable pageable)
  {
    Page<User> pageUserList = this.userService.getAllUsers(pageable);
    PageWrapper<User> page = new PageWrapper<>(pageUserList, "/list");
    model.addAttribute("page", page);
    model.addAttribute("users", page.getContent());
    return "user/list";
  }


  
  @Secured({"ROLE_USER"})
  @ResponseBody
  @RequestMapping({"/user/user"})
  public String user()
  {
    return "Only ROLE_USER can access this page";
  }
  
  @Secured({"ROLE_ADMIN"})
  @ResponseBody
  @RequestMapping({"/admin/user"})
  public String admin()
  {
    return "Only ROLE_AMIN can access this page";
  }
  
  @Secured({"ROLE_ADMIN"})
  @ResponseBody
  @RequestMapping(value={"/user/edit/{userId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView edit(@PathVariable int userId, ModelAndView modelAndView, Pageable pageable)
  {
    modelAndView.addObject("user", this.userService.findOne(userId));
    modelAndView.addObject("editUser", this.userService.setUserEditForm(userId));
    modelAndView.setViewName("user/edit");
    return modelAndView;
  }
  
  @Secured({"ROLE_ADMIN"})
  @ResponseBody
  @RequestMapping(value={"/user/edit"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public Object edit(@Validated EditUser editUser, BindingResult bindingResult, Model model, Pageable pageable, ModelAndView modelAndView, Principal principal)
    throws NoSuchAlgorithmException
  {
    if (bindingResult.hasErrors())
    {
      modelAndView.addObject("user", this.userService.findOne(editUser.getId()));
      modelAndView.setViewName("user/edit");
      return modelAndView;
    }
    this.userService.updateUser(editUser);
    Page<User> pageUserList = this.userService.getAllUsers(pageable);
    PageWrapper<User> page = new PageWrapper<User>(pageUserList, "/list");
    model.addAttribute("page", page);
    model.addAttribute("users", page.getContent());
    modelAndView.setViewName("user/list");
    return modelAndView;
  }
  
  @Secured({"ROLE_ADMIN"})
  @RequestMapping({"/user/delete/{userId}"})
  public String delete(@PathVariable int userId, RedirectAttributes redirectAttributes)
  {
    this.userService.deleteUser (userId);
    redirectAttributes.addFlashAttribute("message", "User was deleted.");
    return "redirect:/";
  }
}
