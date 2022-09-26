package ar.edu.ungs.networks.domain;

public final class CommunicationNetworkNotExists extends RuntimeException {
	public CommunicationNetworkNotExists() {
		super("the network not exists");
	}
}
