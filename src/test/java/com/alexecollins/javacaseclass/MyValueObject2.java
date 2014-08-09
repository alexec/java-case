package com.alexecollins.javacaseclass;

class MyValueObject2 implements MyValueObject {
    private final int i;

    MyValueObject2(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyValueObject2 that = (MyValueObject2) o;

        if (i != that.i) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return i;
    }
}