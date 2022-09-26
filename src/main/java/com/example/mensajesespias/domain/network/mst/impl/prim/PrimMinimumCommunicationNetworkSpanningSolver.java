package com.example.mensajesespias.domain.network.mst.impl.prim;

import com.example.mensajesespias.domain.communication.Communication;
import com.example.mensajesespias.domain.communication.Probability;
import com.example.mensajesespias.domain.network.CommunicationNetwork;
import com.example.mensajesespias.domain.network.mst.IMinimumCommunicationNetworkSpanningSolver;
import com.example.mensajesespias.domain.spy.Spy;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class PrimIMinimumCommunicationNetworkSpanningSolver implements
		IMinimumCommunicationNetworkSpanningSolver {
	private Set<Spy> spies;
	private Set<Communication> communications;

	public PrimIMinimumCommunicationNetworkSpanningSolver() {
		this.spies = new HashSet<>();
		this.communications = new HashSet<>();
	}

	@Override
	public CommunicationNetwork solve(CommunicationNetwork network) {
		spies.add(network.firstSpy());
		int counter = 1;

		while (counter < network.size()) {
			Communication communication = findMinimumCommunication(network);

			communications.add(communication);
			spies.add(communication.secondSpy());
			counter++;
		}

		CommunicationNetwork result = buildCommunicationNetwork();
		this.cleanAttributes();
		return result;
	}

	private Communication findMinimumCommunication(CommunicationNetwork network) {
		return searchCommunicationsWhereOnlyOneVisitedSpy(network)
				.stream()
				.min(Communication::compareProbability)
				.orElseThrow(() -> new IllegalArgumentException("can not find a minimum communication"));
	}

	private Set<Communication> searchCommunicationsWhereOnlyOneVisitedSpy(CommunicationNetwork network) {
		Set<Communication> communicationsWhereOnlyOneVisitedSpy = new HashSet<>();

		for (Spy spy : this.spies) {
			Set<Spy> neighbours = network.neighbours(spy);

			for (Spy neighbour : neighbours) {
				Optional<Probability> probability = network.probability(spy, neighbour);

				if (probability.isPresent() && isOnlyOneVisitedSpy(spy, neighbour)) {
					Communication communication = new Communication(spy, neighbour, probability.get());
					communicationsWhereOnlyOneVisitedSpy.add(communication);
				}
			}
		}

		return communicationsWhereOnlyOneVisitedSpy;
	}

	private boolean isOnlyOneVisitedSpy(Spy spy, Spy neighbour) {
		return (this.spies.contains(spy) && !this.spies.contains(neighbour)) ||
		       (this.spies.contains(neighbour) && !this.spies.contains(spy));
	}

	private CommunicationNetwork buildCommunicationNetwork() {
		CommunicationNetwork result = new CommunicationNetwork(this.spies.size());
		communications.forEach(communication -> result.add(communication.firstSpy(),
		                                                   communication.secondSpy(),
		                                                   communication.probability()));
		return result;
	}

	private void cleanAttributes() {
		this.spies = new HashSet<>();
		this.communications = new HashSet<>();
	}
}
