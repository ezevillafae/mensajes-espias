package com.example.mensajesespias.domain.network;

public final class CommunicationNetworkNotExists extends RuntimeException {
	public CommunicationNetworkNotExists() {
		super("the network not exists");
	}
}
