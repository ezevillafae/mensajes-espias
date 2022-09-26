package com.example.mensajesespias.infrastructure.communication.rest.response;

import com.example.mensajesespias.domain.communication.Communication;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class CommunicationResponse {
	private String firstSpy;
	private String secondSpy;
	private Double probability;
}
