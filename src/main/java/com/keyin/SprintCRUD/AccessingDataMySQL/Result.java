package com.keyin.SprintCRUD.AccessingDataMySQL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="results")
public class Result {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="id")
  private Integer Id;
  @Column(columnDefinition = "integer default 0")
  private Integer result;
  @Column(columnDefinition = "integer default 0")
  private Integer cashPrize;
  @ManyToOne
  @NotNull
  @JoinColumn(name="tournamentId", referencedColumnName = "id", updatable=false, insertable=false)
  private Tournament tournament;
  @ManyToOne
  @NotNull
  @JoinColumn(name="memberId", referencedColumnName = "id", updatable=false, insertable=false)
  private Member member;

  public Integer getId() {
    return Id;
  }
  public Integer getResult() {
    return result;
  }
  public Integer getCashPrize() {
    return cashPrize;
  }
  public Tournament getTournament() {
    return tournament;
  }
  public Member getMember() {
    return member;
  }

  public void setId(Integer id) {
    Id = id;
  }
  public void setResult(Integer result) {
    this.result = result;
  }
  public void setCashPrize(Integer cashPrize) {
    this.cashPrize = cashPrize;
  }
  public void setTournament(Tournament tournament) {
    this.tournament = tournament;
  }
  public void setMember(Member member) {
    this.member = member;
  }
}
