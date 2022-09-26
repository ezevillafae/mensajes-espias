package com.example.mensajesespias.domain.network.mst.kruskal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.mensajesespias.domain.network.CommunicationNetwork;
import com.example.mensajesespias.domain.network.CommunicationNetworkMother;
import com.example.mensajesespias.domain.network.mst.impl.kruskal.KruskalMinimumCommunicationNetworkSpanningSolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class KruskalMinimumCommunicationNetworkSpanningSolverShould {
	private KruskalMinimumCommunicationNetworkSpanningSolver solver;

	@BeforeEach
	void setUp() {
		this.solver = new KruskalMinimumCommunicationNetworkSpanningSolver();
	}

	@Test
	void solve_minimum_communication_network_spanning() {
		CommunicationNetwork network = CommunicationNetworkMother.undirectedRandom();
		CommunicationNetwork expected = CommunicationNetworkMother.minimumRandom();

		CommunicationNetwork actual = this.solver.solve(network);

		assertEquals(expected, actual);
	}
}