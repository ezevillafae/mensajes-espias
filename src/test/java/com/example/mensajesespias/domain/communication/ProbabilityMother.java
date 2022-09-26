package com.example.mensajesespias.domain.communication;

import ar.edu.ungs.domain.DoubleMother;

public final class ProbabilityMother {
	public static Probability random() {
		return build(DoubleMother.random(0D, 1D));
	}

	public static Probability build(Double value) {
		return new Probability(value);
	}

	private static Probability empty() {
		return new Probability();
	}
}
