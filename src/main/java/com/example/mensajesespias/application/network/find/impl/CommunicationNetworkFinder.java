package com.example.mensajesespias.application.network.find.impl;

import com.example.mensajesespias.application.network.find.ICommunicationNetworkFinder;
import com.example.mensajesespias.domain.network.CommunicationNetwork;
import com.example.mensajesespias.domain.network.CommunicationNetworkNotExists;
import com.example.mensajesespias.domain.network.ICommunicationNetworkRepository;

public class CommunicationNetworkFinder implements ICommunicationNetworkFinder {

  private final ICommunicationNetworkRepository ICommunicationNetworkRepository;

  public CommunicationNetworkFinder(ICommunicationNetworkRepository ICommunicationNetworkRepository) {
    this.ICommunicationNetworkRepository = ICommunicationNetworkRepository;
  }

  @Override
  public CommunicationNetwork find(Long id) {
    return ICommunicationNetworkRepository.find(id).orElseThrow(CommunicationNetworkNotExists::new);
  }
}
