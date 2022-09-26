package com.example.mensajesespias.domain.network.mst;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.mensajesespias.domain.network.CommunicationNetwork;
import com.example.mensajesespias.domain.network.CommunicationNetworkMother;
import com.example.mensajesespias.domain.network.mst.impl.prim.PrimMinimumCommunicationNetworkSpanningSolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class PrimMinimumCommunicationNetworkSpanningSolverShould {
  private PrimMinimumCommunicationNetworkSpanningSolver solver;

  @BeforeEach
  void setUp() {
    this.solver = new PrimMinimumCommunicationNetworkSpanningSolver();
  }

  @Test
  void solve_minimum_communication_network_spanning() {
    CommunicationNetwork network = CommunicationNetworkMother.undirectedRandom();
    CommunicationNetwork expected = CommunicationNetworkMother.minimumRandom();

    CommunicationNetwork actual = this.solver.solve(network);

    assertEquals(expected, actual);
  }
}
