package com.example.mensajesespias.infrastructure.communication.rest;

import ar.edu.ungs.communications.domain.Communication;
import java.util.Objects;

public final class CommunicationResponse {
	private final String firstSpy;
	private final String secondSpy;
	private final Double probability;

	public CommunicationResponse(String firstSpy, String secondSpy, Double probability) {
		this.firstSpy = firstSpy;
		this.secondSpy = secondSpy;
		this.probability = probability;
	}

	public static CommunicationResponse map(Communication aggregate) {
		return new CommunicationResponse(aggregate.firstSpy().name(), aggregate.secondSpy().name(), aggregate.probability().value());
	}

    public String firstSpy() {
		return firstSpy;
	}

	public String secondSpy() {
		return secondSpy;
	}

	public Double probability() {
		return probability;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CommunicationResponse that = (CommunicationResponse) o;


		return Objects.equals(firstSpy, that.firstSpy) && Objects.equals(secondSpy, that.secondSpy) &&
		       Objects.equals(probability, that.probability);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstSpy, secondSpy, probability);
	}

	@Override
	public String toString() {
		return "CommunicationResponse{" + "firstSpy='" + firstSpy + '\'' + ", secondSpy='" + secondSpy + '\'' +
		       ", probability=" + probability + '}';
	}
}
