package com.example.mensajesespias.domain.network.mst;

import com.example.mensajesespias.domain.network.CommunicationNetwork;

public interface IMinimumCommunicationNetworkSpanningSolver {
	CommunicationNetwork solve(CommunicationNetwork network);
}
