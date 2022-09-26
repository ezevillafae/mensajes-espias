package com.example.mensajesespias.domain.network;

import ar.edu.ungs.communications.domain.Communication;
import ar.edu.ungs.communications.domain.CommunicationMother;
import ar.edu.ungs.communications.domain.ProbabilityMother;
import ar.edu.ungs.spies.domain.Spy;
import ar.edu.ungs.spies.domain.SpyMother;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommunicationNetworkShould {

    @Test
    public void throw_exception_negative_number_of_spies() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CommunicationNetwork(-1));
    }

    @Test
    public void throw_exception_when_any_id_spy_id_negative() {
        CommunicationNetwork communicationNetwork = CommunicationNetworkMother.random();
        Spy firstSpy = SpyMother.build(-1, "test");
        Spy secondSpy = SpyMother.build(2, "test");

        Assertions.assertThrows(IllegalArgumentException.class, () -> communicationNetwork.add(firstSpy, secondSpy, ProbabilityMother.build(0.5d)));
    }

    @Test
    public void throw_exception_when_spies_are_equals() {
        CommunicationNetwork communicationNetwork = CommunicationNetworkMother.random();
        Spy firstSpy = SpyMother.build(1, "test");
        Spy secondSpy = SpyMother.build(1, "test");

        Assertions.assertThrows(IllegalArgumentException.class, () -> communicationNetwork.add(firstSpy, secondSpy, ProbabilityMother.build(0.5d)));
    }

    @Test
    public void get_spy_neighbors() {
        Spy firstSpy = SpyMother.random(0);
        Set<Spy> expected = SpyMother.randoms(1);
        int numberOfSpies = expected.size() + 1;

        CommunicationNetwork communicationNetwork = CommunicationNetworkMother.build(numberOfSpies, CommunicationMother.builds(firstSpy, expected));

        Set<Spy> actual = communicationNetwork.neighbours(firstSpy);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void get_communications() {
        Spy firstSpy = SpyMother.random(0);
        Set<Spy> others = SpyMother.randoms(1);
        int numberOfSpies = others.size() + 1;
        Communication[] communications = CommunicationMother.builds(firstSpy, others);

        Set<Communication> expected = Set.of(communications);

        CommunicationNetwork communicationNetwork = CommunicationNetworkMother.build(numberOfSpies, communications);

        Set<Communication> actual = communicationNetwork.communications();

        Assertions.assertEquals(expected, actual);
    }

}
