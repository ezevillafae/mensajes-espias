package com.example.mensajesespias.domain.network.mst.impl.unionfind;

import ar.edu.ungs.communications.domain.Communication;

public final class UnionFindSolver {
    private final Integer[] parents;

    public UnionFindSolver(Integer totalNodes) {
        this.parents = new Integer[totalNodes];

        for (int i = 0; i < totalNodes; i++) {
            this.parents[i] = i;
        }
    }

    private Integer find(Integer value) {
        if (parents[value].equals(value)) {
            return value;
        }

        return find(parents[value]);
    }

    private void union(Integer x, Integer y) {
        parents[x] = y;
    }

    public boolean detectCycle(Communication communication) {
        Integer parentOfFirstSpy = find(communication.firstSpy().id());
        Integer parentOfSecondSpy = find(communication.secondSpy().id());

        if (parentOfFirstSpy.equals(parentOfSecondSpy)) {
            return true;
        }

        union(parentOfFirstSpy, parentOfSecondSpy);
        return false;
    }
}
