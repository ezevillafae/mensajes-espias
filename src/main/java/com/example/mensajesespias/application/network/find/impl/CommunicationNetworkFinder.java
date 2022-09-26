package com.example.mensajesespias.application.network.find;

import com.example.mensajesespias.domain.network.CommunicationNetwork;
import com.example.mensajesespias.domain.network.CommunicationNetworkNotExists;
import com.example.mensajesespias.domain.network.CommunicationNetworkRepository;

public class CommunicationNetworkFinder implements ICommunicationNetworkFinder {

  private final CommunicationNetworkRepository communicationNetworkRepository;

  public CommunicationNetworkFinder(CommunicationNetworkRepository communicationNetworkRepository) {
    this.communicationNetworkRepository = communicationNetworkRepository;
  }

  @Override
  public CommunicationNetwork find() {
    return communicationNetworkRepository.find().orElseThrow(CommunicationNetworkNotExists::new);
  }
}
