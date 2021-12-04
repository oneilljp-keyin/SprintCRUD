package com.keyin.SprintCRUD.AccessingDataMySQL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Tournaments")
public class Tournament {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="id")
  private Integer Id;
  @NotBlank
  @NotNull
  private String location;
  @NotBlank
  @NotNull
  private String startDate;
  @NotBlank
  @NotNull
  private String endDate;
  @NotBlank
  @NotNull
  private Integer entry_fee;
  @NotBlank
  @NotNull
  private Integer total_cash_prize;

  public void setLocation(String location) {
    this.location = location;
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
  public void setEntry_fee(Integer entry_fee) {
    this.entry_fee = entry_fee;
  }
  public void setTotal_cash_prize(Integer total_cash_prize) {
    this.total_cash_prize = total_cash_prize;
  }

  public Integer getId() {
    return Id;
  }
  public String getLocation() {
    return location;
  }
  public String getStartDate() {
    return startDate;
  }
  public String getEndDate() {
    return endDate;
  }
  public Integer getEntry_fee() {
    return entry_fee;
  }
  public Integer getTotal_cash_prize() {
    return total_cash_prize;
  }

}
