package ar.edu.ungs.networks.domain;

import ar.edu.ungs.spies.domain.Spy;
import ar.edu.ungs.communications.domain.Communication;
import ar.edu.ungs.communications.domain.Probability;

import java.util.*;
import java.util.stream.Collectors;

public final class CommunicationNetwork {
	private final Probability[][] probabilities;
	private final Map<Integer, Spy> spies;

	public CommunicationNetwork(Integer numberOfSpies) {
		ensureThatTheNumberOfSpiesIsValid(numberOfSpies);

		this.probabilities = new Probability[numberOfSpies][numberOfSpies];
		this.spies = new HashMap<>();
	}

	private void ensureThatTheNumberOfSpiesIsValid(Integer quantityOfSpies) {
		if (quantityOfSpies < 0) {
			throw new IllegalArgumentException();
		}
	}

	public void add(Spy firstSpy, Spy secondSpy, Probability probability) {
		ensureSpyExists(firstSpy);
		ensureSpyExists(secondSpy);
		ensureSpiesAreDifferent(firstSpy, secondSpy);

		this.probabilities[firstSpy.id()][secondSpy.id()] = probability;
		this.probabilities[secondSpy.id()][firstSpy.id()] = probability;

		this.spies.put(firstSpy.id(), firstSpy);
		this.spies.put(secondSpy.id(), secondSpy);
	}

	public Optional<Probability> probability(Spy firstSpy, Spy secondSpy) {
		if (!exists(firstSpy, secondSpy)) {
			return Optional.empty();
		}

		return Optional.ofNullable(this.probabilities[firstSpy.id()][secondSpy.id()]);
	}

	public Set<Spy> neighbours(Spy spy) {
		ensureSpyExists(spy);

		Set<Spy> result = new HashSet<>();
		for (int neighbourId = 0; neighbourId < this.probabilities.length; ++neighbourId) {
			Spy possibleNeighbour = this.spies.get(neighbourId);
			if (possibleNeighbour != null && neighbourId != spy.id() && this.exists(spy, possibleNeighbour)) {
				result.add(possibleNeighbour);
			}
		}

		return result;
	}

	public Set<Communication> communications() {
		Set<Communication> communications = new TreeSet<>();

		for (int i = 0; i < this.probabilities.length; i++) {
			for (int j = 0; j < this.probabilities[i].length; j++) {
				Optional<Probability> probability = this.probability(this.spies.get(i), this.spies.get(j));

				if (probability.isPresent()) {
					Communication possibleCommunication = new Communication(this.spies.get(i), this.spies.get(j), probability.get());
					communications.add(possibleCommunication);
				}
			}
		}

		return communications;
	}

	private boolean exists(Spy firstSpy, Spy secondSpy) {
		return this.probabilities[firstSpy.id()][secondSpy.id()] != null
		       && this.probabilities[secondSpy.id()][firstSpy.id()] != null;
	}

	private void ensureSpiesAreDifferent(Spy firstSpy, Spy secondSpy) {
		if (firstSpy.equals(secondSpy)) {
			throw new IllegalArgumentException(String.format("first spy <%s> and second <%s> are equals", firstSpy.id(), secondSpy.id()));
		}
	}

	private void ensureSpyExists(Spy spy) {
		if (spy.id() < 0) {
			throw new IllegalArgumentException(String.format("the spy id <%s> can not be less than zero", spy.id()));
		}

		if (spy.id() >= this.probabilities.length) {
			throw new IllegalArgumentException(String.format("the spy id <%s> can not be greater than or equal to <%s>", spy.id(), this.probabilities.length));
		}
	}

	public Spy firstSpy() {
		return this.spies.get(0);
	}

	public Set<Spy> spies() {
		return spies.keySet().stream().map(spies::get).collect(Collectors.toSet());
	}

	public int size() {
		return this.probabilities.length;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		for (var spy: spies.values()) {
			stringBuilder.append(String.format("spy <%s> : ", spy.id())).append(neighbours(spy)).append("\n");
		}

		return stringBuilder.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CommunicationNetwork network = (CommunicationNetwork) o;

		boolean allEquals = true;

		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < size(); j++) {
				allEquals = allEquals && Objects.equals(this.probabilities[i][j], network.probabilities[i][j]);
			}
		}

		return allEquals;
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(spies);
		result = 31 * result + Arrays.hashCode(probabilities);
		return result;
	}
}
