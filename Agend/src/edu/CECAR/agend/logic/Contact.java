/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.CECAR.agend.logic;

/**
 *
 * @author Yesid Lazaro Mayoriano
 */
public class Contact {
  private String name;
  private String lastName;
  private String phone;
  private String mobile;
  private String email;
  private String twitterUsername;

    public Contact(String name, String lastName, String phone, String mobile, String email, String twitterUsername) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
        this.twitterUsername = twitterUsername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public void setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
    }

    public String toString() {
        return "Contact{" + "name=" + name + ", lastName=" + lastName + ", phone=" + phone + ", mobile=" + mobile + ", email=" + email + ", twitterUsername=" + twitterUsername + '}';
    }
  
  
}
