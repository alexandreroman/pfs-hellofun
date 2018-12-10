package fr.alexandreroman.demos.hellofun;

import java.util.function.Function;

/**
 * {@link Function} implementation returning classic greeting.
 */
public class Hello implements Function<String, String> {
    // At runtime, PFS will call this method using a Java service invoker.

    public String apply(String name) {
        final String n;
        if (name == null || name.isEmpty()) {
            n = "world";
        } else {
            n = name;
        }
        return "Hello " + n;
    }
}
