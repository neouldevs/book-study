package com.github.hyeyoom.module;

import org.junit.Before;
import org.junit.Test;

public class PositiveNumberHandlerTest {

    private PositiveNumberHandler handler;

    @Before
    public void setUp() {
        handler = new PositiveNumberHandler();
    }

    @Test(expected = NotPositiveNumberException.class)
    public void 값이_0인경우_익셉션_발생() throws NotPositiveNumberException {
        handler.multiply(0, 10);
    }

    @Test(expected = NotPositiveNumberException.class)
    public void 값이_음수인_경우_익셉션_발생() throws NotPositiveNumberException {
        handler.multiply(-10, 10);
    }
}