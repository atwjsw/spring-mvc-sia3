package com.habuma.spitter.method;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.memory.InMemoryDaoImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wenda on 8/5/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-method-security.xml")
public class SecurityTest {

    static ApplicationContext applicationContext = null;
    static InMemoryDaoImpl userDetailsService = null;

    /**
     * Initialize the application context to re-use in all test cases
     * */
    @BeforeClass
    public static void setup()
    {
        //Create application context instance
        applicationContext = new ClassPathXmlApplicationContext("classpath:test-method-security.xml");
        //Get user details service configured in configuration
        userDetailsService = applicationContext.getBean(InMemoryDaoImpl.class);
    }

    /**
     * Test the valid user with valid role
     * */
    @Test
    public void testValidRole()
    {
        //Get the user by username from configured user details service
        UserDetails userDetails = userDetailsService.loadUserByUsername ("lokesh");
        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        SecuredInterface securedInterface = applicationContext.getBean(SecuredInterface.class);
        securedInterface.securedMethod();
    }
}
