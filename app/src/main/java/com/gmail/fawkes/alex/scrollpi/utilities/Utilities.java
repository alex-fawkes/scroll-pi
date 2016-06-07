package com.gmail.fawkes.alex.scrollpi.utilities;

import com.gmail.fawkes.alex.scrollpi.functional.Function1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Shortcut for quick utility methods with no heavy dependencies.
 */
@SuppressWarnings({"SameParameterValue", "WeakerAccess", "unused"}) // for future use
public final class Utilities {
    private static final ConvertDoubleQuotes converter = new ConvertDoubleQuotes();

    private Utilities() {
    }

    public static String string(final int n) {
        return String.valueOf(n);
    }

    /**
     * Explicitly check reference equality.
     */
    public static <E> boolean same(final E left, final E right) {
        return left == right;
    }

    public static <E> boolean first(final List<E> elements, E element) {
        return at(elements, element, 0);
    }

    public static <E> boolean last(final List<E> elements, final E element) {
        return at(elements, element, elements.size() - 1);
    }

    public static <E> boolean at(final List<E> elements, final E element, final int index) {
        return !elements.isEmpty() && index < elements.size() && same(elements.get(index), element);
    }

    /**
     * Swap single and double quotes.
     */
    public static String quote(final String string) {
        return join(map(split(string, "'"), converter), "\"");
    }

    public static List<String> split(final String string, final String separator) {
        return Arrays.asList(string.split(separator));
    }

    public static String join(final List<String> strings, final String separator) {
        final StringBuilder builder = new StringBuilder();
        for (final String string : strings) {
            builder.append(string);
            if (!last(strings, string)) {
                builder.append(separator);
            }
        }
        return builder.toString();
    }

    public static <E, F> List<F> map(
            final List<E> elements, final Function1<F, E> function) {
        final List<F> mapped = new ArrayList<>();
        for (final E element : elements) {
            mapped.add(function.execute(element));
        }
        return mapped;
    }
}
