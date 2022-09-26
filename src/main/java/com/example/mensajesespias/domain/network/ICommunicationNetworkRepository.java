package com.example.mensajesespias.domain.network;

import java.util.Optional;

public interface CommunicationNetworkRepository {
	void save(CommunicationNetwork network);
	Optional<CommunicationNetwork> find();
}
