package com.keyin.SprintCRUD.AccessingDataMySQL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name="Memberships")
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int member_id;
  @NotBlank
  @NotNull
  private String name;
  @NotBlank
  @NotNull
  private String address;
  @NotBlank
  @NotNull
  private String email;
//  @NotBlank
  @NotNull
  private String phone;
  @NotBlank
  @NotNull
  private LocalDate membershipStartDate;
  @NotBlank
  @NotNull
  private int membershipLength;
  @NotBlank
  @NotNull
  private String membershipType;

  public Member() {}

  public Integer getId() {
    return member_id;
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
  public LocalDate getMembershipStartDate() {
    return membershipStartDate;
  }
  public int getMembershipLength() {
    return membershipLength;
  }
  public String getMembershipType() {
    return membershipType;
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
  public void setMembershipStartDate(LocalDate membershipStartDate) {
    this.membershipStartDate = membershipStartDate;
  }
  public void setMembershipLength(Integer membershipLength) {
    this.membershipLength = membershipLength;
  }
  public void setMembershipType(String membershipType) {
    this.membershipType = membershipType;
  }

  @Override
  public String toString() {
    return "Member{" +
        "member_id=" + member_id +
        ", name='" + name + '\'' +
        ", address='" + address + '\'' +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        ", membershipStartDate=" + membershipStartDate +
        ", membershipLength=" + membershipLength +
        ", membershipType='" + membershipType + '\'' +
        '}';
  }
}
