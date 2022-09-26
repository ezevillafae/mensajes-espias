package com.example.mensajesespias.domain.network.mst;

import com.example.mensajesespias.domain.network.CommunicationNetwork;

public interface MinimumCommunicationNetworkSpanningSolver {
	CommunicationNetwork solve(CommunicationNetwork network);
}
