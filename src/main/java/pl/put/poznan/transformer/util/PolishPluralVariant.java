package pl.put.poznan.transformer.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class PolishPluralVariant implements PluralVariant {

    private final String single, pluralDefault, pluralAlt, fraction;

    public PolishPluralVariant(@NotNull String single, @NotNull String pluralDefault, @NotNull String pluralAlt, @NotNull String fraction) {
        this.single = single;
        this.pluralDefault = pluralDefault;
        this.pluralAlt = pluralAlt;
        this.fraction = fraction;
    }

    @Override
    public @NotNull String getVariant(long quantity) {
        if (quantity == 1) return single;
        else if (quantity % 10 >= 2 && quantity % 10 <= 4 && (quantity % 100 < 10 || quantity % 100 >= 20)) return pluralAlt;
        else return pluralDefault;
    }

    @Override
    public @NotNull String getVariant(double quantity) {
        if (quantity % 1 != 0) return fraction;
        else return getVariant((int) quantity);
    }

    @Override
    public @NotNull String getQuantityName(long quantity, @Nullable Function<Long, String> numberNameProvider) {
        if (numberNameProvider == null) return quantity + " " + getQuantityName(quantity);
        return (quantity != 1 ? numberNameProvider.apply(quantity) + " " : "") + getVariant(quantity);
    }

    @Override
    public @NotNull String getQuantityName(double quantity, @Nullable Function<Double, String> numberNameProvider) {
        if (numberNameProvider == null) return quantity + " " + getQuantityName(quantity);
        return (quantity != 1 ? numberNameProvider.apply(quantity) + " " : "") + getVariant(quantity);
    }
}
