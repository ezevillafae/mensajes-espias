package com.example.mensajesespias.domain.communication;

import com.example.mensajesespias.domain.spy.Spy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class CommunicationMother {
	public static Communication build(Spy firstSpy, Spy secondSpy, Probability probability) {
		return new Communication(firstSpy, secondSpy, probability);
	}

	public static Communication[] builds(Spy firstSpy, Set<Spy> secondSpies) {
		return builds(firstSpy, Arrays.copyOf(secondSpies.toArray(), secondSpies.size(), Spy[].class));
	}

	public static Communication[] builds(Spy firstSpy, Spy ...secondSpies) {
		Set<Communication> communications = new HashSet<>();
		for (Spy secondSpy: secondSpies) {
			Communication communication = CommunicationMother.build(firstSpy, secondSpy, ProbabilityMother.random());
			communications.add(communication);
		}

		return Arrays.copyOf(communications.toArray(), communications.size(), Communication[].class);
	}
}
