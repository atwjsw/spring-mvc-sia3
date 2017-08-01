package com.habuma.spitter.domain.hibernate;

import static java.util.Arrays.*;
import static javax.persistence.GenerationType.*;
//import static org.apache.commons.lang.builder.EqualsBuilder.*;
//import static org.apache.commons.lang.builder.HashCodeBuilder.*;
//import static org.apache.commons.lang.builder.ToStringBuilder.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="spitter")
@XmlRootElement
public class Spitter implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;

  @Size(min=3, max=20, message="Username must be between 3 and 20 characters long.")
  @Pattern(regexp="^[a-zA-Z0-9]+$", message="Username must be alphanumeric with no spaces")
  private String username;  
  
  @Size(min=6, max=20,message="The password must be at least 6 characters long.")
  private String password;

  @Size(min=3, max=50, message="Your full name must be between 3 and 50 characters long.")
  private String fullName;
  
  @Pattern(regexp="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}",message="Invalid email address.")
  private String email;

  private boolean updateByEmail;
  
  @Id
  @GeneratedValue(strategy = AUTO)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name="username", unique=true)
  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Column(name="password")
  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Column(name="fullname")
  public String getFullName() {
    return fullName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name="email")
  public String getEmail() {
    return email;
  }

  public void setUpdateByEmail(boolean updateByEmail) {
    this.updateByEmail = updateByEmail;
  }

  @Column(name="update_by_email")
  public boolean isUpdateByEmail() {
    return updateByEmail;
  }

  @Override
  public String toString() {
    return "Spitter{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", fullName='" + fullName + '\'' +
            ", email='" + email + '\'' +
            ", updateByEmail=" + updateByEmail +
            '}';
  }
}
