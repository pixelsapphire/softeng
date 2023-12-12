package pl.put.poznan.transformer.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

/**
 * This interface represents a word plural conjugation variant. Specific implementations are used to conjugate words
 * in different languages. The conjugation is based on the quantity of the word, which is passed as a parameter to the
 * {@link #getVariant(long)} and {@link #getVariant(double)} methods. {@link #getQuantityName} methods are used to
 * conjugate the word with a number name, e.g. "one apple" or "two apples", in which case a function that returns the
 * number in words may be passed as a parameter.
 */
public interface PluralVariant {

    /**
     * Returns the plural variant of the word based on the inegral quantity.
     *
     * @param quantity the quantity of the word
     * @return the plural variant of the word
     */
    @NotNull String getVariant(long quantity);

    /**
     * Returns the plural variant of the word based on the fractional quantity.
     *
     * @param quantity the quantity of the word
     * @return the plural variant of the word
     */
    @NotNull String getVariant(double quantity);

    /**
     * Returns the quantity of the word with the number name, e.g. "1 apple" or "2 apples".
     *
     * @param quantity the quantity of the word
     * @return the quantity of the word with the number name
     */
    @NotNull
    default String getQuantityName(long quantity) {
        return getQuantityName(quantity, (Function<Long, String>) null);
    }

    /**
     * Returns the quantity of the word with the number name, e.g. "one apple" or "two apples". If the number name
     * provider is null, the number itself is appended to the quantity name, e.g. "1 apple" or "2 apples".
     *
     * @param quantity           the quantity of the word
     * @param numberNameProvider the function that returns the number in words
     * @return the quantity of the word with the number name
     */
    @NotNull String getQuantityName(long quantity, @Nullable Function<Long, String> numberNameProvider);

    /**
     * Returns the quantity of the word with the number name, e.g. "1 apple" or "2 apples".
     *
     * @param quantity the quantity of the word
     * @return the quantity of the word with the number name
     */
    @NotNull
    default String getQuantityName(double quantity) {
        return getQuantityName(quantity, null);
    }

    /**
     * Returns the quantity of the word with the number name, e.g. "one apple" or "two apples". If the number name
     * provider is null, the number itself is appended to the quantity name, e.g. "1 apple" or "2 apples".
     *
     * @param quantity           the quantity of the word
     * @param numberNameProvider the function that returns the number in words
     * @return the quantity of the word with the number name
     */
    @NotNull String getQuantityName(double quantity, @Nullable Function<Double, String> numberNameProvider);
}
