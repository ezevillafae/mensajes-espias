package com.example.mensajesespias.domain.spy;

import com.example.mensajesespias.common.WordMother;
import java.util.Set;

public final class SpyMother {

	public static Set<Spy> randoms() {
		return randoms(0);
	}
	public static Set<Spy> randoms(Integer fromId) {
		var first = random(fromId);
		fromId++;
		var second = random(fromId);
		fromId++;
		var third = random(fromId);

		return Set.of(first, second, third);
	}

	public static Spy random(Integer id) {
		return build(id, WordMother.random());
	}

	public static Spy build(Integer id, String name) {
		return new Spy(id, name);
	}
}
