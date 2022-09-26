package com.example.mensajesespias.application.network.find;

import com.example.mensajesespias.domain.network.CommunicationNetwork;

public interface ICommunicationNetworkFinder {

  CommunicationNetwork find(Long id);

}
