package com.example.mensajesespias.application.network.save.impl;

import com.example.mensajesespias.application.network.save.ICommunicationNetworkSaver;
import com.example.mensajesespias.domain.network.CommunicationNetwork;
import com.example.mensajesespias.domain.network.ICommunicationNetworkRepository;

public class CommunicationNetworkSaver implements ICommunicationNetworkSaver {

  private final ICommunicationNetworkRepository communicationNetworkRepository;

  public CommunicationNetworkSaver(ICommunicationNetworkRepository communicationNetworkRepository) {
    this.communicationNetworkRepository = communicationNetworkRepository;
  }

  @Override
  public CommunicationNetwork save(CommunicationNetwork communicationNetwork) {
    return communicationNetworkRepository.save(communicationNetwork);
  }
}
