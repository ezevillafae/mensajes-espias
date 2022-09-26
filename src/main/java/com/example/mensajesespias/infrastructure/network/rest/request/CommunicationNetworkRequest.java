package com.example.mensajesespias.infrastructure.network.rest.request;

import com.example.mensajesespias.infrastructure.communication.rest.request.CommunicationRequest;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class CommunicationNetworkRequest {

	private Set<CommunicationRequest> network;


}
