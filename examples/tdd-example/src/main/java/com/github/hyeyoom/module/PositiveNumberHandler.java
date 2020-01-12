package com.github.hyeyoom.module;

public class PositiveNumberHandler {

    public int multiply(int a, int b) throws NotPositiveNumberException {
        if (a <= 0 || b <= 0) {
            throw new NotPositiveNumberException("Wrong Value");
        }

        return a * b;
    }
}
