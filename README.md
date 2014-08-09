I really like mixing Scala case classes and matchers. They make for easy to read code. I wondered if we could do something similar in pure Java?

Java 8 lambdas allow us to start writing code that feels like new language constructs. It's great for DSLs for example.

How about this?

~~~java
int x = match(
        new MyValueObject1(0),
        when(equalTo(new MyValueObject1(0)), () -> 0),
        when(not(new MyValueObject1(0)), () -> 1)
);
~~~

The `match` method here takes two things, and object and a `Branch`, and tries each branch in turn to see if it matches, if so, return the branch's value:

~~~java
public static <T,U extends T,O> O match(U o, Branch<T,O>... branches) {
    for (Branch<T,O> b : branches) {
        if (b.matcher.matches(o)) {
            return b.block.get();
        }
    }
    throw new IllegalStateException("no matching branch");
}
~~~

A `Branch` is a simple tuple of a Hamcrest `Matcher` and a Java 8 `Supplier`.
