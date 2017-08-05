package com.habuma.spitter.method;

import com.habuma.spitter.domain.Spittle;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.memory.InMemoryDaoImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by wenda on 8/5/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-method-security.xml")
public class MethodSecurityTest {

    @Autowired
    private SecuredInterface securedInterface;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private UserDetailsService userService;

    @Test
    public void testUnsecuredMethod() {
        Authentication token = new UsernamePasswordAuthenticationToken("habuma", "123456");
        System.out.println("token: " + token);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("authentication: " + authentication);
        securedInterface.unsecuredMethod();
        securedInterface.securedMethod();
    }

//    @Test
//    public void testValidRole()
//    {
//        //Get the user by username from configured user details service
//        UserDetails userDetails = userService.loadUserByUsername ("habuma");
//        System.out.println(userDetails);
//        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authToken);
////        SecuredInterface securedInterface = applicationContext.getBean(SecuredInterface.class);
//        securedInterface.securedMethod();
//    }

    @Test
    public void testPreAuthorizeSecureMethod() {
        try {
            Authentication token = new UsernamePasswordAuthenticationToken("spitter", "123456");
            System.out.println("token: " + token);
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("authentication: " + authentication);
            securedInterface.preAuthorizeSecuredMethod(11);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testPostAuthorizeSpittleMethod() {
        Authentication token = new UsernamePasswordAuthenticationToken("habuma", "123456");
        System.out.println("token: " + token);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("authentication: " + authentication);
        Spittle spittle = new Spittle();
        spittle.setId(1L);
        spittle.setText("This is a spittle");
        spittle.setWhen(new Date());
        spittle.getSpitter().setUsername("habuma");
        Spittle returnSpittle = securedInterface.postAuthorizedSpittleMethod(spittle);
        System.out.println("returnObject: " + returnSpittle);
    }

    @Test
    public void testPreAndPostAuthorizedMethod() {
        Authentication token = new UsernamePasswordAuthenticationToken("habuma", "123456");
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        securedInterface.preAndPostAuthorizedMethod("foo1");
//        securedInterface.preAndPostAuthorizedMethod("bb");
          securedInterface.preAndPostAuthorizedMethod("bar");
    }


    @Test
    public void testgetABunchOfSpittles() {
//        Authentication token = new UsernamePasswordAuthenticationToken("spitter", "123456");
        Authentication token = new UsernamePasswordAuthenticationToken("habuma", "123456");
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        List<Spittle> spittles = securedInterface.getABunchOfSpittles();
    }

    @Test
    public void testTopSecretMethod() {
//        Authentication token = new UsernamePasswordAuthenticationToken("spitter", "123456");
        Authentication token = new UsernamePasswordAuthenticationToken("habuma", "123456");
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        securedInterface.topSecretMethod("top secret");
        securedInterface.securedMethod();
    }


}
