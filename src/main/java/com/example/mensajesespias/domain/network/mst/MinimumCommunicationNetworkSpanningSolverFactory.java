package com.example.mensajesespias.domain.network.mst;

import com.example.mensajesespias.application.network.find.ICommunicationNetworkFinder;
import com.example.mensajesespias.application.network.mst.IMinimumCommunicationNetworkSpanningSearcher;
import com.example.mensajesespias.application.network.mst.impl.MinimumCommunicationNetworkSpanningSearcher;
import com.example.mensajesespias.domain.network.mst.impl.kruskal.KruskalMinimumCommunicationNetworkSpanningSolver;
import com.example.mensajesespias.domain.network.mst.impl.prim.PrimMinimumCommunicationNetworkSpanningSolver;


public final class MinimumCommunicationNetworkSpanningSolverFactory {

  private final ICommunicationNetworkFinder communicationNetworkFinder;

  public MinimumCommunicationNetworkSpanningSolverFactory(
      ICommunicationNetworkFinder communicationNetworkFinder) {
    this.communicationNetworkFinder = communicationNetworkFinder;
  }

  public IMinimumCommunicationNetworkSpanningSearcher getMinimumCommunicationNetworkSpanningSolver(
      String solver) {
    if(solver == null) {
      return new MinimumCommunicationNetworkSpanningSearcher(communicationNetworkFinder,
          new PrimMinimumCommunicationNetworkSpanningSolver());
    }

    if (solver.equalsIgnoreCase(MinimumCommunicationNetworkSpanningStrategy.PRIM.name())) {
      return new MinimumCommunicationNetworkSpanningSearcher(communicationNetworkFinder,
          new PrimMinimumCommunicationNetworkSpanningSolver());
    }
    if (solver.equalsIgnoreCase(MinimumCommunicationNetworkSpanningStrategy.KRUSKAL.name())) {
      return new MinimumCommunicationNetworkSpanningSearcher(communicationNetworkFinder,
          new KruskalMinimumCommunicationNetworkSpanningSolver());
    }

    return new MinimumCommunicationNetworkSpanningSearcher(communicationNetworkFinder,
        new PrimMinimumCommunicationNetworkSpanningSolver());
  }
}
