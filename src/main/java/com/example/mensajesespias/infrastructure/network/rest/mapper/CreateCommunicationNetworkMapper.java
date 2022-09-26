package com.example.mensajesespias.infrastructure.network.rest.mapper;

import com.example.mensajesespias.domain.communication.Probability;
import com.example.mensajesespias.domain.network.CommunicationNetwork;
import com.example.mensajesespias.domain.spy.Spy;
import com.example.mensajesespias.infrastructure.communication.rest.mapper.CreateCommunicationMapper;
import com.example.mensajesespias.infrastructure.communication.rest.request.CommunicationRequest;
import com.example.mensajesespias.infrastructure.communication.rest.response.CommunicationResponse;
import com.example.mensajesespias.infrastructure.network.rest.request.CommunicationNetworkRequest;
import com.example.mensajesespias.infrastructure.network.rest.response.CommunicationNetworkResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCommunicationNetworkMapper {

  private final CreateCommunicationMapper communicationMapper;

  public CommunicationNetwork toDomain(CommunicationNetworkRequest request) {
    Map<String, Spy> spies = new HashMap<>();

    Integer id = 0;
    for (CommunicationRequest communicationRequest : request.getNetwork()) {
      if (!spies.containsKey(communicationRequest.firstSpy())) {
        String name = communicationRequest.firstSpy();
        spies.put(name, new Spy(id, name));
        id++;
      }

      if (!spies.containsKey(communicationRequest.secondSpy())) {
        String name = communicationRequest.secondSpy();
        spies.put(name, new Spy(id, name));
        id++;
      }
    }

    CommunicationNetwork network = new CommunicationNetwork(spies.size());

    for (CommunicationRequest communicationRequest : request.getNetwork()) {
      network.add(spies.get(communicationRequest.firstSpy()),
          spies.get(communicationRequest.secondSpy()),
          new Probability(communicationRequest.probability()));
    }

    return network;
  }

  public CommunicationNetworkResponse toResponse(CommunicationNetwork communication) {
    List<CommunicationResponse> communications = communication.communications()
        .stream()
        .map(communicationMapper::toResponse)
        .collect(Collectors.toList());

    return new CommunicationNetworkResponse(communications);
  }

}
