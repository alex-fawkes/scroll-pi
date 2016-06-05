package net.ddns.adfawkes.scratchapp.extensions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Shortcuts for quick extension methods with no heavy dependencies.
 */
@SuppressWarnings({"WeakerAccess", "SameParameterValue"})
public final class X {
    private static final DoubleToSingle DOUBLE_TO_SINGLE = new DoubleToSingle();

    private X() {
    }

    public static String str(final int n) {
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
        return join(map(split(string, "'"), DOUBLE_TO_SINGLE), "\"");
    }

    public static List<String> split(String string, final String separator) {
        return Arrays.asList(string.split(separator));
    }

    public static String join(final List<String> strings, final String separator) {
        final StringBuilder builder = new StringBuilder();
        for (String s : strings) {
            builder.append(s);
            if (!last(strings, s)) {
                builder.append(separator);
            }
        }
        return builder.toString();
    }

    public static <E> List<E> map(final List<E> elements, Function.Return1<E, E> function) {
        final List<E> mapped = new ArrayList<>();
        for (E e : elements) {
            mapped.add(function.execute(e));
        }
        return mapped;
    }

    private static class DoubleToSingle implements Function.Return1<String, String> {
        @Override
        public String execute(String string) {
            return string.replace("\"", "'");
        }
    }
}
