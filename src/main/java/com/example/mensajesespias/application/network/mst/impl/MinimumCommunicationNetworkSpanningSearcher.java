package com.example.mensajesespias.application.network.mst.impl;

import com.example.mensajesespias.application.network.find.ICommunicationNetworkFinder;
import com.example.mensajesespias.application.network.mst.IMinimumCommunicationNetworkSpanningSearcher;
import com.example.mensajesespias.domain.network.CommunicationNetwork;
import com.example.mensajesespias.domain.network.mst.IMinimumCommunicationNetworkSpanningSolver;

public class MinimumCommunicationNetworkSpanningSearcher implements
    IMinimumCommunicationNetworkSpanningSearcher {

  private final ICommunicationNetworkFinder communicationNetworkFinder;
  private final IMinimumCommunicationNetworkSpanningSolver minimumCommunicationNetworkSpanningSolver;

  public MinimumCommunicationNetworkSpanningSearcher(
      ICommunicationNetworkFinder communicationNetworkFinder,
      IMinimumCommunicationNetworkSpanningSolver minimumCommunicationNetworkSpanningSolver) {
    this.communicationNetworkFinder = communicationNetworkFinder;
    this.minimumCommunicationNetworkSpanningSolver = minimumCommunicationNetworkSpanningSolver;
  }

  @Override
  public CommunicationNetwork search(Long id) {
    CommunicationNetwork communicationNetwork = communicationNetworkFinder.find(id);

    return minimumCommunicationNetworkSpanningSolver.solve(communicationNetwork);
  }
}
