package com.spring.facebooklogin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;

@Component
public class RestFB {
  
  public static String FACEBOOK_APP_ID = "2675363909194077";
  public static String FACEBOOK_APP_SECRET = "a3ad24da833d5981a602e85629bf3dfe";
  //public static String FACEBOOK_REDIRECT_URL = "https://localhost:8080/SpringMvc/login-facebook";
  public static String FACEBOOK_REDIRECT_URL = "https://localhost:8443/SpringMvc/login-facebook";

  public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";
  public String getToken(final String code) throws ClientProtocolException, IOException {
    String link = String.format(FACEBOOK_LINK_GET_TOKEN, FACEBOOK_APP_ID, FACEBOOK_APP_SECRET, FACEBOOK_REDIRECT_URL, code);
    System.out.println(" link "+ link);
    String response = Request.Get(link).execute().returnContent().asString();
     ObjectMapper mapper = new ObjectMapper();
    JsonNode node = mapper.readTree(response).get("access_token");
    return node.textValue();
  }
  
  public com.restfb.types.User getUserInfo(final String accessToken) {
    FacebookClient facebookClient = new DefaultFacebookClient(accessToken, FACEBOOK_APP_SECRET, Version.LATEST);
    JsonObject fetchObjectsResults =
    		  facebookClient.fetchObjects(Arrays.asList("me", "123456789"), 
    		           JsonObject.class, Parameter.with("fields","email,id, birthday"));
    System.out.println("fetchObjectsResults " + fetchObjectsResults.toString());
    
    
    return facebookClient.fetchObject("me", com.restfb.types.User.class);
  }

  public UserDetails buildUser(com.restfb.types.User user) {
    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    UserDetails userDetail = new User(user.getId(), "", enabled, accountNonExpired, credentialsNonExpired,
        accountNonLocked, authorities);
    System.out.println(user.getEmail());
    System.out.println(user.getFirstName());
    System.out.println(user.getLastName());
    System.out.println(user.getBirthday());
    System.out.println("-------------");
    System.out.println(user.toString());
    System.out.println("-------------");    
    return userDetail;
  }
  
}









