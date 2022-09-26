package com.example.mensajesespias.domain.network;

import java.util.Optional;

public interface ICommunicationNetworkRepository {
	CommunicationNetwork save(CommunicationNetwork network);
	Optional<CommunicationNetwork> find(Long id);
}
