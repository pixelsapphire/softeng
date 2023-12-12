package pl.put.poznan.transformer.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

/**
 * An implementation of {@link PluralVariant} for Polish language.
 */
public class PolishPluralVariant implements PluralVariant {

    private final String single, plural2, plural1, fraction;

    /**
     * Creates a new instance of {@link PolishPluralVariant} with the given variants.
     *
     * @param single   the singular variant (for 1)
     * @param plural1  the plural variant for numbers ending with 2, 3 or 4, except for numbers ending with 12, 13 or 14
     * @param plural2  the plural variant for all other numbers
     * @param fraction the plural variant for fractional numbers
     */
    public PolishPluralVariant(@NotNull String single, @NotNull String plural1, @NotNull String plural2, @NotNull String fraction) {
        this.single = single;
        this.plural1 = plural1;
        this.plural2 = plural2;
        this.fraction = fraction;
    }

    // see PluralVariant#getVariant for documentation
    @Override
    public @NotNull String getVariant(long quantity) {
        if (quantity == 1) return single;
        else if (quantity % 10 >= 2 && quantity % 10 <= 4 && (quantity % 100 < 10 || quantity % 100 >= 20)) return plural1;
        else return plural2;
    }

    // see PluralVariant#getVariant for documentation
    @Override
    public @NotNull String getVariant(double quantity) {
        if (quantity % 1 != 0) return fraction;
        else return getVariant((int) quantity);
    }

    // see PluralVariant#getQuantityName for documentation
    @Override
    public @NotNull String getQuantityName(long quantity, @Nullable Function<Long, String> numberNameProvider) {
        if (numberNameProvider == null) return quantity + " " + getQuantityName(quantity);
        return (quantity != 1 ? numberNameProvider.apply(quantity) + " " : "") + getVariant(quantity);
    }

    // see PluralVariant#getQuantityName for documentation
    @Override
    public @NotNull String getQuantityName(double quantity, @Nullable Function<Double, String> numberNameProvider) {
        if (numberNameProvider == null) return quantity + " " + getQuantityName(quantity);
        return (quantity != 1 ? numberNameProvider.apply(quantity) + " " : "") + getVariant(quantity);
    }
}
