package com.example.mensajesespias.infrastructure.communication.database.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COMMUNICATIONS")
public class CommunicationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstSpy;
  private String secondSpy;

  private Double probability;


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommunicationEntity that = (CommunicationEntity) o;

    boolean a = Objects.equals(firstSpy, that.firstSpy) && Objects.equals(secondSpy, that.secondSpy);
    boolean b = Objects.equals(firstSpy, that.secondSpy) && Objects.equals(secondSpy, that.firstSpy);
    boolean w = probability.doubleValue() == that.getProbability().doubleValue();

    return (a || b) && w;
  }

}
