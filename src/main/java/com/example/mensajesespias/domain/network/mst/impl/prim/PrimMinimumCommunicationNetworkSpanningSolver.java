package com.example.mensajesespias.domain.network.mst.impl.prim;

import com.example.mensajesespias.domain.communication.Communication;
import com.example.mensajesespias.domain.communication.Probability;
import com.example.mensajesespias.domain.network.CommunicationNetwork;
import com.example.mensajesespias.domain.network.mst.IMinimumCommunicationNetworkSpanningSolver;
import com.example.mensajesespias.domain.spy.Spy;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class PrimMinimumCommunicationNetworkSpanningSolver implements
		IMinimumCommunicationNetworkSpanningSolver {
	private Set<Spy> spies;
	private Set<Communication> communications;

	public PrimMinimumCommunicationNetworkSpanningSolver() {
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

				probability.ifPresent(
						value -> addIfOnlyOneVisitedSpy(communicationsWhereOnlyOneVisitedSpy, spy, neighbour, value));
			}
		}

		return communicationsWhereOnlyOneVisitedSpy;
	}

	private void addIfOnlyOneVisitedSpy(Set<Communication> communicationsWhereOnlyOneVisitedSpy, Spy spy,
			Spy neighbour, Probability probability) {
		if (isOnlyOneVisitedSpy(spy, neighbour)) {
			Communication communication = new Communication(spy, neighbour, probability);
			communicationsWhereOnlyOneVisitedSpy.add(communication);
		}
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
