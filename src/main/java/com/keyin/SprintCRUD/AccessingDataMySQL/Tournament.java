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
  private String start_date;
  @NotBlank
  @NotNull
  private String end_date;
  @NotBlank
  @NotNull
  private Integer entry_fee;
  @NotBlank
  @NotNull
  private Integer total_cash_prize;

  public void setLocation(String location) {
    this.location = location;
  }
  public void setStart_date(String start_date) {
    this.start_date = start_date;
  }
  public void setEnd_date(String end_date) {
    this.end_date = end_date;
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
  public String getStart_date() {
    return start_date;
  }
  public String getEnd_date() {
    return end_date;
  }
  public Integer getEntry_fee() {
    return entry_fee;
  }
  public Integer getTotal_cash_prize() {
    return total_cash_prize;
  }

}
