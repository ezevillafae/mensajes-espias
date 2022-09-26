package com.example.mensajesespias.infrastructure.communication.rest.mapper;

import com.example.mensajesespias.domain.communication.Communication;
import com.example.mensajesespias.infrastructure.communication.rest.response.CommunicationResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateCommunicationMapper {

  public CommunicationResponse toResponse(Communication communication) {
    return new CommunicationResponse(
        communication.firstSpy().name(),
        communication.secondSpy().name(),
        communication.probability().value());
  }

}
