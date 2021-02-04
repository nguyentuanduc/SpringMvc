package com.spring.facebooklogin;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.spring.facebooklogin.RestFB;

@Controller
public class BaseFaceController {

  @Autowired
  private RestFB restFB;

  @RequestMapping("/login-facebook")
  public String loginFacebook(HttpServletRequest request) {
    String code = request.getParameter("code");
    System.out.println(" code "+ code);
    String accessToken = "";

    try {
      accessToken = restFB.getToken(code);
    } catch (IOException e) {
      return "login?facebook=error";
    }

    com.restfb.types.User user = restFB.getUserInfo(accessToken);
    UserDetails userDetail = restFB.buildUser(user);
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
        userDetail.getAuthorities());
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    
    System.out.println(userDetail.getUsername());
    
    return "redirect:/face/user";
  }

  @RequestMapping("/face/user")
  public String user() {
    return "face/user";
  }

  @RequestMapping("/face/admin")
  public String admin() {
    return "face/admin";
  }

  @RequestMapping("/face/403")
  public String accessDenied() {
    return "face/403";
  }

}