package com.example.mensajesespias.infrastructure.communication.database.mapper;

import com.example.mensajesespias.domain.communication.Communication;
import com.example.mensajesespias.infrastructure.communication.database.entity.CommunicationEntity;
import org.springframework.stereotype.Component;

@Component
public class CommunicationEntityMapper {


  public CommunicationEntity toEntity(Communication communication) {
    if(communication == null) {
      return null;
    }
    CommunicationEntity entity = new CommunicationEntity();
    entity.setProbability(communication.probability().value());
    entity.setFirstSpy(communication.firstSpy().name());
    entity.setSecondSpy(communication.secondSpy().name());
    return entity;
  }

}
