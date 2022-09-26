package com.example.mensajesespias.infrastructure.network.rest;

import ar.edu.ungs.communications.application.CommunicationRequest;
import ar.edu.ungs.communications.domain.Probability;
import ar.edu.ungs.networks.domain.CommunicationNetwork;
import ar.edu.ungs.spies.domain.Spy;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class CommunicationNetworkRequest {
	private final Set<CommunicationRequest> network;

	public CommunicationNetworkRequest(Set<CommunicationRequest> network) {
		this.network = network;
	}

	public static CommunicationNetwork map(CommunicationNetworkRequest request) {
		Map<String, Spy> spies = new HashMap<>();

		Integer id = 0;
		for (CommunicationRequest communicationRequest : request.network()) {
			if (!spies.containsKey(communicationRequest.firstSpy())) {
				String name = communicationRequest.firstSpy();
				spies.put(name, new Spy(id, name));
				id++;
			}

			if (!spies.containsKey(communicationRequest.secondSpy())) {
				String name = communicationRequest.secondSpy();
				spies.put(name, new Spy(id, name));
				id++;
			}
		}

		CommunicationNetwork network = new CommunicationNetwork(spies.size());

		for (CommunicationRequest communicationRequest : request.network()) {
			network.add(spies.get(communicationRequest.firstSpy()),
			            spies.get(communicationRequest.secondSpy()),
			            new Probability(communicationRequest.probability()));
		}

		return network;
	}

	public Set<CommunicationRequest> network() {
		return network;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CommunicationNetworkRequest that = (CommunicationNetworkRequest) o;
		return Objects.equals(network, that.network);
	}

	@Override
	public int hashCode() {
		return Objects.hash(network);
	}

	@Override
	public String toString() {
		return "CommunicationNetworkRequest{" + "network=" + network + '}';
	}
}
