package ar.edu.ungs.domain;

import com.github.javafaker.Faker;

public final class WordMother {
    public static String random(){
        return new Faker().funnyName().name();
    }
}
