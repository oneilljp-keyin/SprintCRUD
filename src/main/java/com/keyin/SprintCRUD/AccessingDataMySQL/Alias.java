package com.keyin.SprintCRUD.AccessingDataMySQL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Alias {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @NotBlank
  @NotNull
  private Integer wrestler_id;
  @NotBlank
  @NotNull
  private String alias;
  private Integer current;

  public Integer getId() {
    return id;
  }
  public Integer getWrestler_id() {
    return wrestler_id;
  }
  public String getAlias() {
    return alias;
  }
  public Integer getCurrent() {
    return current;
  }

  public void setWrestler_id(Integer wrestler_id) {
    this.wrestler_id = wrestler_id;
  }
  public void setAlias(String alias) {
    this.alias = alias;
  }
  public void setCurrent(Integer current) {
    this.current = current;
  }

}
