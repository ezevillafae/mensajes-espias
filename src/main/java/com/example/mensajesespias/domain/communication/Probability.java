package ar.edu.ungs.communications.domain;

import java.util.Objects;

public final class Probability implements Comparable<Probability> {
	private final Double value;

	public Probability() {
		this.value = Double.POSITIVE_INFINITY;
	}

	public Probability(Double value) {
		this.value = value;

		ensureProbabilityValid(value);
	}

	public Double value() {
		return value;
	}

	private void ensureProbabilityValid(Double value) {
		if (value < 0 || value > 1) {
			throw new InvalidProbability(value);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Probability that = (Probability) o;
		return Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return "Probability{" + "value=" + value + '}';
	}

	@Override
	public int compareTo(Probability o) {
		return this.value().compareTo(o.value());
	}
}
