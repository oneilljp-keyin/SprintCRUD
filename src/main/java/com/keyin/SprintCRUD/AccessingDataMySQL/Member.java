package com.keyin.SprintCRUD.AccessingDataMySQL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="memberships")
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="id")
  private Integer Id;
  @NotBlank
  @NotNull
  private String name;
  @NotBlank
  @NotNull
  private String address;
  @NotBlank
  @NotNull
  private String email;
  @NotBlank
  @NotNull
  private String phone;
  @NotBlank
  @NotNull
  private String membership_start_date;
  @NotBlank
  @NotNull
  private Integer membership_length;
  @NotBlank
  @NotNull
  private String membership_type;

  public Integer getId() {
    return Id;
  }
  public String getName() {
    return name;
  }
  public String getAddress() {
    return address;
  }
  public String getEmail() {
    return email;
  }
  public String getPhone() {
    return phone;
  }
  public String getMembershipStartDate() {
    return membership_start_date;
  }
  public Integer getMembershipLength() {
    return membership_length;
  }
  public String getMembershipType() {
    return membership_type;
  }

  public void setName(String name) {
    this.name = name;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public void setMembershipStartDate(String membershipStartDate) {
    this.membership_start_date = membershipStartDate;
  }
  public void setMembershipLength(Integer membershipLength) {
    this.membership_length = membershipLength;
  }
  public void setMembershipType(String membershipType) {
    this.membership_type = membershipType;
  }

  @Override
  public String toString() {
    return "Member{" +
        "id=" + Id +
        ", name='" + name + '\'' +
        ", address='" + address + '\'' +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        ", membershipStartDate=" + membership_start_date +
        ", membershipLength=" + membership_length +
        ", membershipType='" + membership_type + '\'' +
        '}';
  }
}
