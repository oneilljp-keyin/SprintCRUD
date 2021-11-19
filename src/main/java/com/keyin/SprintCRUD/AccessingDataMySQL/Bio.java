package com.keyin.SprintCRUD.AccessingDataMySQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Bio {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @NotBlank
  @NotNull
  private String real_name;

  private Double height_cm;
  private Double weight_kg;
  private String dob;
  private String dod = "0000-00-00";
  private String status;

  public Integer getId() {
    return id;
  }
  public String getReal_name() {
    return real_name;
  }
  public Double getHeight_cm() {
    return height_cm;
  }
  public Double getWeight_kg() {
    return weight_kg;
  }
  public String getDob() {
    return dob;
  }
  public String getDod() {
    return dod;
  }
  public String getStatus() {
    return status;
  }

  public void setReal_name(String real_name) {
    this.real_name = real_name;
  }
  public void setHeight_cm(Double height_cm) {
    this.height_cm = height_cm;
  }
  public void setWeight_kg(Double weight_kg) {
    this.weight_kg = weight_kg;
  }
  public void setDob(String dob) {
    this.dob = dob;
  }
  public void setDod(String dod) {
    this.dod = dod;
  }
  public void setStatus(String status) {
    this.status = status;
  }

}
