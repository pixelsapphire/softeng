package pl.put.poznan.transformer.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public interface PluralVariant {

    @NotNull String getVariant(long quantity);

    @NotNull String getVariant(double quantity);

    @NotNull
    default String getQuantityName(long quantity) {
        return getQuantityName(quantity, (Function<Long, String>) null);
    }

    @NotNull String getQuantityName(long quantity, @Nullable Function<Long, String> numberNameProvider);

    @NotNull
    default String getQuantityName(double quantity) {
        return getQuantityName(quantity, null);
    }

    @NotNull String getQuantityName(double quantity, @Nullable Function<Double, String> numberNameProvider);
}
