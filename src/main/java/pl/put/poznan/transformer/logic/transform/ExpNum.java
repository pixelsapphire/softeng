package pl.put.poznan.transformer.logic.transform;

import org.jetbrains.annotations.NotNull;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerDecorator;
import pl.put.poznan.transformer.util.DictionaryBuilder;

import java.util.Map;

public class ExpNum extends TextTransformerDecorator {

    private static final Map<Integer, String> DIGITS
            = new DictionaryBuilder<Integer, String>(10).with(0, "zero").with(1, "jeden").with(2, "dwa").with(3, "trzy")
                                                        .with(4, "cztery").with(5, "pięć").with(6, "sześć").with(7, "siedem")
                                                        .with(8, "osiem").with(9, "dziewięć").build();
    private static final Map<Integer, String> TEENS
            = new DictionaryBuilder<Integer, String>(0).with(10, "dziesięć").with(1, "jedenaście").with(2, "dwanaście")
                                                       .with(3, "trzynaście").with(4, "czternaście").with(5, "piętnaście")
                                                       .with(6, "szesnaście").with(7, "siedemnaście").with(8, "osiemnaście")
                                                       .with(9, "dziewiętnaście").build();
    private static final Map<Integer, String> TENS
            = new DictionaryBuilder<Integer, String>(10).with(2, "dwadzieścia").with(3, "trzydzieści").with(4, "czterdzieści")
                                                        .with(5, "pięćdziesiąt").with(6, "sześćdziesiąt").with(7, "siedemdziesiąt")
                                                        .with(8, "osiemdziesiąt").with(9, "dziewięćdziesiąt").build();
    private static final Map<Integer, String> HUNDREDS
            = new DictionaryBuilder<Integer, String>(10).with(1, "sto").with(2, "dwieście").with(3, "trzysta")
                                                        .with(4, "czterysta").with(5, "pięćset").with(6, "sześćset")
                                                        .with(7, "siedemset").with(8, "osiemset").with(9, "dziewięćset")
                                                        .build();
    private final boolean numberExpandAllowed;

    public ExpNum(@NotNull TextTransformer textToTransform, boolean numberExpandAllowed) {
        super(textToTransform);
        this.numberExpandAllowed = numberExpandAllowed;
    }

    @Override
    public @NotNull String transform() {
        return function(textToTransform.transform());
    }

    public @NotNull String function(@NotNull String s) {
        if (numberExpandAllowed) {
            final StringBuilder result = new StringBuilder();
            for (String part : s.split("\\D+")) {
                try {
                    int numericValue = Integer.parseInt(part);
                    result.append(numberInWords(numericValue)).append(" ");
                } catch (NumberFormatException e) {
                    result.append(part).append(" ");
                }
            }

            return result.toString().trim();
        } else {
            return s;
        }
    }

    private @NotNull String numberInWords(int number) {
        if (number < 0 || number > 999) {
            return String.valueOf(number);
        }

        final StringBuilder result = new StringBuilder();

        int hundreds = number / 100;
        if (hundreds > 0) {
            result.append(HUNDREDS.get(hundreds));
        }

        int remainder = number % 100;
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
}
