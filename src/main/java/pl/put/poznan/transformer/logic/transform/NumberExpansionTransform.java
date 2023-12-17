package pl.put.poznan.transformer.logic.transform;

import org.jetbrains.annotations.NotNull;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerDecorator;
import pl.put.poznan.transformer.util.DictionaryBuilder;
import pl.put.poznan.transformer.util.PluralVariant;
import pl.put.poznan.transformer.util.PolishPluralVariant;

import java.util.List;
import java.util.Map;

public class NumberExpansionTransform extends TextTransformerDecorator {

    private static final Map<Integer, String> DIGITS
            = new DictionaryBuilder<Integer, String>(10).with(0, "zero").with(1, "jeden").with(2, "dwa").with(3, "trzy")
                                                        .with(4, "cztery").with(5, "pięć").with(6, "sześć").with(7, "siedem")
                                                        .with(8, "osiem").with(9, "dziewięć").build();
    private static final Map<Integer, String> TEENS
            = new DictionaryBuilder<Integer, String>(10).with(0, "dziesięć").with(1, "jedenaście").with(2, "dwanaście")
                                                        .with(3, "trzynaście").with(4, "czternaście").with(5, "piętnaście")
                                                        .with(6, "szesnaście").with(7, "siedemnaście").with(8, "osiemnaście")
                                                        .with(9, "dziewiętnaście").build();
    private static final Map<Integer, String> TENS
            = new DictionaryBuilder<Integer, String>(8).with(2, "dwadzieścia").with(3, "trzydzieści").with(4, "czterdzieści")
                                                       .with(5, "pięćdziesiąt").with(6, "sześćdziesiąt").with(7, "siedemdziesiąt")
                                                       .with(8, "osiemdziesiąt").with(9, "dziewięćdziesiąt").build();
    private static final Map<Integer, String> HUNDREDS
            = new DictionaryBuilder<Integer, String>(9).with(1, "sto").with(2, "dwieście").with(3, "trzysta")
                                                       .with(4, "czterysta").with(5, "pięćset").with(6, "sześćset")
                                                       .with(7, "siedemset").with(8, "osiemset").with(9, "dziewięćset")
                                                       .build();
    private static final PluralVariant thousands = new PolishPluralVariant("tysiąc", "tysiące", "tysięcy", "tysiąca");
    private static final PluralVariant millions = new PolishPluralVariant("milion", "miliony", "milionów", "miliona");
    private static final PluralVariant billions = new PolishPluralVariant("miliard", "miliardy", "miliardów", "miliarda");
    private static final PluralVariant trillions = new PolishPluralVariant("bilion", "biliony", "bilionów", "biliona");
    private static final PluralVariant quadrillions = new PolishPluralVariant("biliard", "biliardy", "biliardów", "biliarda");
    private static final PluralVariant quintillions = new PolishPluralVariant("trylion", "tryliony", "trylionów", "tryliona");

    private final boolean numberExpandAllowed;

    public NumberExpansionTransform(@NotNull TextTransformer textToTransform, boolean numberExpandAllowed) {
        super(textToTransform);
        this.numberExpandAllowed = numberExpandAllowed;
    }

    private static @NotNull String numberInWords(long number) {

        final StringBuilder result = new StringBuilder();

        if (number < 0) {
            result.append("minus ");
            number = -number;
        }

        final List<PluralVariant> variants = List.of(quintillions, quadrillions, trillions, billions, millions, thousands);
        for (long powOf10 = 1_000_000_000_000_000_000L, variant = 0; powOf10 >= 1_000L; powOf10 /= 1_000L, variant++) {
            final long triplet = number / powOf10;
            if (triplet > 0) {
                result.append(variants.get((int) variant).getQuantityName(triplet, NumberExpansionTransform::numberInWords)).append(" ");
                number %= powOf10;
            }
        }

        final int hundreds = (int) (number / 100);
        if (hundreds > 0) result.append(HUNDREDS.get(hundreds));

        final int remainder = (int) (number % 100);
        if (remainder > 0) {
            if (result.length() > 0) result.append(" ");
            if (remainder < 10) result.append(DIGITS.get(remainder));
            else if (remainder < 20) result.append(TEENS.get(remainder - 10));
            else {
                final int tens = remainder / 10;
                final int units = remainder % 10;
                result.append(TENS.get(tens));
                if (units > 0) result.append(" ").append(DIGITS.get(units));
            }
        }

        return result.toString();
    }

    @Override
    public @NotNull String transform() {
        return expandNumbers(textToTransform.transform());
    }

    @Override
    public @NotNull String description() {
        return "Number expansion. Expands numbers in the input text to their word form. Example: '123' -> 'sto dwadzieścia trzy'";
    }

    private @NotNull String expandNumbers(@NotNull String s) {
        if (numberExpandAllowed) {
            final StringBuilder result = new StringBuilder();
            for (String word : s.split(" ")) {
                try {
                    final long number = Long.parseLong(word);
                    result.append(numberInWords(number)).append(" ");
                } catch (NumberFormatException e) {
                    result.append(word).append(" ");
                }
            }

            return result.toString().trim();
        } else {
            return s;
        }
    }
}
