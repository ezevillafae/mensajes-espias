package com.example.mensajesespias.infrastructure.network.database.mapper;

import com.example.mensajesespias.domain.communication.Communication;
import com.example.mensajesespias.domain.communication.Probability;
import com.example.mensajesespias.domain.network.CommunicationNetwork;
import com.example.mensajesespias.domain.spy.Spy;
import com.example.mensajesespias.infrastructure.communication.database.entity.CommunicationEntity;
import com.example.mensajesespias.infrastructure.communication.database.mapper.CommunicationEntityMapper;
import com.example.mensajesespias.infrastructure.network.database.entity.CommunicationNetworkEntity;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommunicationNetworkEntityMapper {

  private final CommunicationEntityMapper communicationEntityMapper;

  public CommunicationNetworkEntity toEntity(CommunicationNetwork network) {
    if(network == null) {
      return null;
    }

    CommunicationNetworkEntity entity = new CommunicationNetworkEntity();
    Set<Communication> communications = network.communications();
    for (Communication communication: communications) {
      CommunicationEntity communicationEntity = communicationEntityMapper.toEntity(communication);
      entity.addCommunication(communicationEntity);
    }

    return entity;
  }

  public CommunicationNetwork toDomain(CommunicationNetworkEntity communicationNetworkEntity) {
    Map<String, Spy> spies = new HashMap<>();

    Integer id = 0;
    for (CommunicationEntity communicationEntity : communicationNetworkEntity.getNetwork()) {
      if (!spies.containsKey(communicationEntity.getFirstSpy())) {
        String name = communicationEntity.getFirstSpy();
        spies.put(name, new Spy(id, name));
        id++;
      }

      if (!spies.containsKey(communicationEntity.getSecondSpy())) {
        String name = communicationEntity.getSecondSpy();
        spies.put(name, new Spy(id, name));
        id++;
      }
    }

    CommunicationNetwork network = new CommunicationNetwork(spies.size());

    for (CommunicationEntity communicationEntity : communicationNetworkEntity.getNetwork()) {
      network.add(spies.get(communicationEntity.getFirstSpy()),
          spies.get(communicationEntity.getSecondSpy()),
          new Probability(communicationEntity.getProbability()));
    }

    return network;

  }
}
