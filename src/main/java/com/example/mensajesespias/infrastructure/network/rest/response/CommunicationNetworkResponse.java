package com.example.mensajesespias.infrastructure.network.rest.response;


import com.example.mensajesespias.infrastructure.communication.rest.response.CommunicationResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class CommunicationNetworkResponse {

	private List<CommunicationResponse> communications;

}
