package com.keyin.SprintCRUD.AccessingDataMySQL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Results")
public class Results {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @NotBlank
  private Integer tournament_id;
  @NotBlank
  private Integer member_id;
  @NotBlank
  @NotNull
  private Integer result;

  public Integer getId() {
    return id;
  }
  public Integer getMember_id() {
    return member_id;
  }
  public Integer getTournament_id() {
    return tournament_id;
  }
  public Integer getResult() {
    return result;
  }

  public void setMember(Integer member_id) {
    this.member_id = member_id;
  }
  public void setTournament(Integer tournament_id) {
    this.tournament_id = tournament_id;
  }
  public void setResult(Integer result) {
    this.result = result;
  }
}
