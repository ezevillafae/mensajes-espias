package com.example.mensajesespias.domain.network;


import com.example.mensajesespias.domain.communication.Communication;
import com.example.mensajesespias.domain.communication.ProbabilityMother;
import com.example.mensajesespias.domain.spy.Spy;
import com.example.mensajesespias.domain.spy.SpyMother;

public final class CommunicationNetworkMother {
	public static CommunicationNetwork preset() {
		CommunicationNetwork network = new CommunicationNetwork(9);

		Spy one = SpyMother.build(0, "spy 1");
		Spy two = SpyMother.build(1, "spy 2");
		Spy three = SpyMother.build(2, "spy 3");
		Spy four = SpyMother.build(3, "spy 4");
		Spy five = SpyMother.build(4, "spy 5");
		Spy six = SpyMother.build(5, "spy 6");
		Spy seven = SpyMother.build(6, "spy 7");
		Spy eight = SpyMother.build(7, "spy 8");
		Spy nine = SpyMother.build(8, "spy 9");

		network.add(one, two, ProbabilityMother.build(0.2));
		network.add(one, three, ProbabilityMother.build(1D));

		network.add(two, five, ProbabilityMother.build(0D));
		network.add(two, three, ProbabilityMother.build(0.1));
		network.add(two, four, ProbabilityMother.build(0.3));

		network.add(three, two, ProbabilityMother.build(0.1));
		network.add(three, four, ProbabilityMother.build(0.5));
		network.add(three, six, ProbabilityMother.build(0.5));

		network.add(four, seven, ProbabilityMother.build(0.3));

		network.add(five, eight, ProbabilityMother.build(0.2));

		network.add(six, nine, ProbabilityMother.build(0.7));

		network.add(eight, seven, ProbabilityMother.build(0.4));

		network.add(nine, seven, ProbabilityMother.build(0D));

		return network;
	}


	public static CommunicationNetwork undirectedRandom() {
		CommunicationNetwork network = new CommunicationNetwork(4);

		network.add(SpyMother.build(0, "spy 0"), SpyMother.build(1, "spy 1"), ProbabilityMother.build(0.1D));
		network.add(SpyMother.build(0, "spy 0"), SpyMother.build(2, "spy 2"), ProbabilityMother.build(0.3D));
		network.add(SpyMother.build(1, "spy 1"), SpyMother.build(3, "spy 3"), ProbabilityMother.build(0.4D));
		network.add(SpyMother.build(3, "spy 3"), SpyMother.build(2, "spy 2"), ProbabilityMother.build(0.3D));

		return network;
	}

	public static CommunicationNetwork minimumRandom() {
		CommunicationNetwork network = new CommunicationNetwork(4);

		network.add(SpyMother.build(0, "spy 0"), SpyMother.build(1, "spy 1"), ProbabilityMother.build(0.1D));
		network.add(SpyMother.build(0, "spy 0"), SpyMother.build(2, "spy 2"), ProbabilityMother.build(0.3D));
		network.add(SpyMother.build(3, "spy 3"), SpyMother.build(2, "spy 2"), ProbabilityMother.build(0.3D));

		return network;
	}

	public static CommunicationNetwork random() {
		CommunicationNetwork network = new CommunicationNetwork(3);

		network.add(SpyMother.build(0, "spy 0"), SpyMother.build(1, "spy 1"), ProbabilityMother.build(0.8D));
		network.add(SpyMother.build(1, "spy 1"),SpyMother.build(2, "spy 2"), ProbabilityMother.build(0.4D));
		network.add(SpyMother.build(2, "spy 2"),SpyMother.build(0, "spy 0"), ProbabilityMother.build(0.5D));

		return network;
	}

	public  static CommunicationNetwork withoutEdges(int vertexes) {
		return new CommunicationNetwork(vertexes);
	}

	public  static CommunicationNetwork build(int numberOfSpies, Communication ...communications) {
		CommunicationNetwork network = new CommunicationNetwork(numberOfSpies);

		for (Communication communication : communications) {
			network.add(communication.firstSpy(), communication.secondSpy(), communication.probability());
		}

		return network;
	}
}
