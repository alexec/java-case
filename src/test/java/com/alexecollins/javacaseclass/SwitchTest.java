package com.alexecollins.javacaseclass;

import org.junit.Test;

import static com.alexecollins.javacaseclass.Switch.match;
import static com.alexecollins.javacaseclass.Switch.when;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.any;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SwitchTest {

    @Test
    public void matchVoid() throws Exception {
        match(
                new MyValueObject1(0),
                when(equalTo(new MyValueObject1(0)), () -> System.out.println("zero!")),
                when(not(new MyValueObject1(0)), () -> fail())
        );
    }

    @Test
    public void matchNormal() throws Exception {
        int x = match(
                new MyValueObject1(0),
                when(instanceOf(MyValueObject.class), () -> 0),
                when(any(MyValueObject.class), () -> {fail();return 0;})
        );

        assertEquals(0, x);
    }
}