package com.example.mensajesespias.infrastructure.network.database.entity;

import com.example.mensajesespias.infrastructure.communication.database.entity.CommunicationEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "COMMUNICATIONS_NETWORKS")
public class CommunicationNetworkEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<CommunicationEntity> network = new ArrayList<>();

  public void addCommunication(CommunicationEntity communicationEntity) {
    if(!network.contains(communicationEntity)){
      network.add(communicationEntity);
    }
  }

}
