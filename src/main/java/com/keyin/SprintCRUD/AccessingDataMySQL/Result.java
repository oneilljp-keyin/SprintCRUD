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
  private Integer result;
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
  public Tournament getTournamentId() {
    return tournament;
  }
  public Member getMemberId() {
    return member;
  }

  public void setId(Integer id) {
    Id = id;
  }
  public void setResult(Integer result) {
    this.result = result;
  }
  public void setTournament(Tournament tournament) {
    this.tournament = tournament;
  }
  public void setMember(Member member) {
    this.member = member;
  }
}
