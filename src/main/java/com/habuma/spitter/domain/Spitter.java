package com.habuma.spitter.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class Spitter {
  private Long id;
  @Size(min=3, max=20, message= "Username must be between 3 and 20 character' slong.") //Enforce size
  @Pattern(regexp="^[a-zA-Z0-9]+$", message="Username must be alphanumeric with no spaces") //Ensure alphanumeric and no spaces
  @NotNull(message="User name can not be empty.")
  private String username;
  @Size(min=6, max=20, message="The password must be at least 6 characters long.") //Enforce size
  private String password;
  @Size(min=3, max=50, message="Your fullname must be between 3 and 50 characters long.") //Enforce size
  private String fullName;
  private List<com.habuma.spitter.domain.Spittle> spittles;
  @Pattern(regexp="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}", message="Invalid email address.") //Match email pattern
  private String email;
  private boolean updateByEmail;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getFullName() {
    return fullName;
  }

  public void setEmail(String email) {
      this.email = email;
  }

  public String getEmail() {
      return email;
  }

  public void setSpittles(List<com.habuma.spitter.domain.Spittle> spittles) {
    this.spittles = spittles;
  }

  public List<Spittle> getSpittles() {
    return spittles;
  }
  
  public void setUpdateByEmail(boolean updateByEmail) {
      this.updateByEmail = updateByEmail;
  }
  
  public boolean isUpdateByEmail() {
    return updateByEmail;
  }
  
  
//  @Override
//  public boolean equals(Object obj) {
//    Spitter other = (Spitter) obj;
//    return other.fullName.equals(fullName) && other.username.equals(username) && other.password.equals(password);
//  }

  @Override
  public String toString() {
    return "Spitter{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", fullName='" + fullName + '\'' +
            ", spittles=" + spittles +
            ", email='" + email + '\'' +
            ", updateByEmail=" + updateByEmail +
            '}';
  }
}
