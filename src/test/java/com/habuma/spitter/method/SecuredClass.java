package com.habuma.spitter.method;

import com.habuma.spitter.domain.Spitter;
import org.junit.Test;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.habuma.spitter.domain.Spittle;

import java.util.Arrays;
import java.util.List;

@Component
public class SecuredClass implements SecuredInterface {

  public void unsecuredMethod() {
    System.out.println("unsecuredMethod can be run by everyone.");
  }
  
  @Secured("ROLE_SPITTER")
  public void securedMethod() {
    System.out.println("This securedMethod can be run by ROLE_SPITTER.");
  }

  @PreAuthorize("hasRole('ROLE_SPITTER') and #amount > 10")
  public void preAuthorizeSecuredMethod(int amount) {
    System.out.println("This preAuthorizeSecuredMethod(int amount) can be run by ROLE_SPITTER and amount > 10");
    System.out.println("param amount: " + amount);
  }
  
  @PreAuthorize("#input.length() <= 3")
  @PostAuthorize("returnObject.length() == #input.length()")
  public String preAndPostAuthorizedMethod(String input) {
    System.out.println("@PreAuthorize(\"#input.length() <= 3\")");
    System.out.println("@PostAuthorize(\"returnObject.length() == #input.length()\")");
    System.out.println("input: " + input);
    return "foo";
  }
  
  public Spittle postAuthorizedSpittleMethod(Spittle spittle) {
    System.out.println("@PostAuthorize(\"returnObject.spitter.username == principal.username\")");
    System.out.println(spittle);
    return spittle;
  }
  
  public Spittle getSpittleById(long id) {
    return null;
  }
  
  @Sensitive
  public void topSecretMethod(String x) {
    System.out.println("topSecretMethod: " + x);
  }


//  @PostAuthorize("returnObject.spitter.username == principal.username")
  public List<Spittle> getABunchOfSpittles(){
    Spitter habuma = new Spitter();
    habuma.setUsername("habuma");
    Spitter spitter = new Spitter();
    spitter.setUsername("spitter");
    Spittle spitt1e1 = new Spittle();
    spitt1e1.setSpitter(habuma);
    Spittle spittle2 = new Spittle();
    spittle2.setSpitter(spitter);
    Spittle spittle3 = new Spittle();
    spittle3.setSpitter(habuma);
    Spittle spittle4 = new Spittle();
    spittle4.setSpitter(spitter);
    Spittle spittle5 = new Spittle();
    spittle5.setSpitter(habuma);

    List<Spittle> spittles = Arrays.asList(spitt1e1,spittle2,spittle3,spittle4,spittle5);

    for (Spittle s: spittles) {
      System.out.println(s.getSpitter().getUsername());
    }
    return spittles;
  }
}
