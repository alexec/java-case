package com.alexecollins.javacaseclass;

import org.hamcrest.Matcher;

import java.util.function.Supplier;

public final class Switch {
    private Switch() {
    }

    public abstract static class AbstractBranch<T> {
        final Matcher<T> matcher;

        private AbstractBranch(Matcher<T> matcher) {
            this.matcher = matcher;
        }
    }

    public static final class VoidBranch<T> extends AbstractBranch<T> {
        private final Runnable block;

        private VoidBranch(Matcher<T> matcher, Runnable block) {
            super(matcher);
            this.block = block;
        }
    }

    public static final class Branch<T,O> extends AbstractBranch<T> {
        private final Supplier<O> block;

        private Branch(Matcher<T> matcher, Supplier<O> block) {
            super(matcher);
            this.block = block;
        }
    }

    @SafeVarargs
    public static <T,U extends T> void match(U o, VoidBranch<T>... branches) {
        for (VoidBranch<T> b : branches) {
            if (b.matcher.matches(o)) {
                b.block.run();
                return;
            }
        }
        throw new IllegalStateException("no matching branch");
    }

    @SafeVarargs
    public static <T,U extends T,O> O match(U o, Branch<T,O>... branches) {
        for (Branch<T,O> b : branches) {
            if (b.matcher.matches(o)) {
                return b.block.get();
            }
        }
        throw new IllegalStateException("no matching branch");
    }

    public static <T> VoidBranch<T> when(Matcher<T> matcher, Runnable block) {
        return new VoidBranch<T>(matcher, block);
    }

    public static <T,O> Branch<T,O> when(Matcher<T> matcher, Supplier<O> block) {
        return new Branch<>(matcher, block);
    }
}
