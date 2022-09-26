package com.example.mensajesespias.infrastructure.network.rest;

import ar.edu.ungs.communications.application.CommunicationResponse;
import ar.edu.ungs.networks.domain.CommunicationNetwork;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class CommunicationNetworkResponse {
	private final Set<CommunicationResponse> communications;

	public CommunicationNetworkResponse(Set<CommunicationResponse> communications) {
		this.communications = communications;
	}

	public static CommunicationNetworkResponse map(CommunicationNetwork aggregate) {
		Set<CommunicationResponse> communications = aggregate.communications()
															 .stream()
				 											 .map(CommunicationResponse::map)
															 .collect(Collectors.toSet());

		return new CommunicationNetworkResponse(communications);
	}

	public Set<CommunicationResponse> communications() {
		return communications;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CommunicationNetworkResponse that = (CommunicationNetworkResponse) o;
		return Objects.equals(communications, that.communications);
	}

	@Override
	public int hashCode() {
		return Objects.hash(communications);
	}

	@Override
	public String toString() {
		return "CommunicationNetworkResponse{" + "communications=" + communications + '}';
	}
}
