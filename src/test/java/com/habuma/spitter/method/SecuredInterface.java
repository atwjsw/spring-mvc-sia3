package com.habuma.spitter.method;

import org.springframework.security.access.prepost.PostAuthorize;

import com.habuma.spitter.domain.Spittle;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface SecuredInterface {
  @PostAuthorize("returnObject.spitter.username == principal.username")
  Spittle postAuthorizedSpittleMethod(Spittle spittle);
  
  public void unsecuredMethod();
  
  public void securedMethod();
  
  public void preAuthorizeSecuredMethod(int amount);
  
  public String preAndPostAuthorizedMethod(String input);
  
  @Sensitive
  void topSecretMethod(String x);

  @PreAuthorize("hasRole('ROLE_SPITTER')")
  @PostFilter("filterObject.spitter.username == principal.username")
  List<Spittle> getABunchOfSpittles();

}
