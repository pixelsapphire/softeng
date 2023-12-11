package pl.put.poznan.transformer.logic.functionality;

import java.util.HashMap;
import java.util.Map;

import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerDecorator;

public class ExpNum extends TextTransformerDecorator {
    private static final Map<Character, String> DIGIT_MAP = new HashMap<>();
    private static final Map<String, String> TEENS_MAP = new HashMap<>();
    private static final Map<Character, String> TENS_MAP = new HashMap<>();
    private static final Map<Character, String> HUNDRED = new HashMap<>();

    static {
        DIGIT_MAP.put('0', "zero");
        DIGIT_MAP.put('1', "jeden");
        DIGIT_MAP.put('2', "dwa");
        DIGIT_MAP.put('3', "trzy");
        DIGIT_MAP.put('4', "cztery");
        DIGIT_MAP.put('5', "pięć");
        DIGIT_MAP.put('6', "sześć");
        DIGIT_MAP.put('7', "siedem");
        DIGIT_MAP.put('8', "osiem");
        DIGIT_MAP.put('9', "dziewięć");
    }

    static {
        TEENS_MAP.put("10", "dziesięć");
        TEENS_MAP.put("11", "jedenaście");
        TEENS_MAP.put("12", "dwanaście");
        TEENS_MAP.put("13", "trzynaście");
        TEENS_MAP.put("14", "czternaście");
        TEENS_MAP.put("15", "piętnaście");
        TEENS_MAP.put("16", "szesnaście");
        TEENS_MAP.put("17", "siedemnaście");
        TEENS_MAP.put("18", "osiemnaście");
        TEENS_MAP.put("19", "dziewiętnaście");
    }

    static {
        TENS_MAP.put('2', "dwadzieścia");
        TENS_MAP.put('3', "trzydzieści");
        TENS_MAP.put('4', "czterdzieści");
        TENS_MAP.put('5', "pięćdziesiąt");
        TENS_MAP.put('6', "sześćdziesiąt");
        TENS_MAP.put('7', "siedemdziesiąt");
        TENS_MAP.put('8', "osiemdziesiąt");
        TENS_MAP.put('9', "dziewięćdziesiąt");
    }

    static {
        HUNDRED.put('1', "sto");
        HUNDRED.put('2', "dwieście");
        HUNDRED.put('3', "trzysta");
        HUNDRED.put('4', "czterysta");
        HUNDRED.put('5', "pięćset");
        HUNDRED.put('6', "sześćset");
        HUNDRED.put('7', "siedemset");
        HUNDRED.put('8', "osiemset");
        HUNDRED.put('9', "dziewięćset");
    }

    private boolean numberExpandAllow;

    public ExpNum(TextTransformer textToTransform, boolean numberExpandAllow) {
        super(textToTransform);
        this.numberExpandAllow = numberExpandAllow;
    }

    public String function(String s) {
        if (numberExpandAllow) {
            StringBuilder result = new StringBuilder();

            String[] parts = s.split("\\D+");

            for (String part : parts) {
                try {
                    int numericValue = Integer.parseInt(part);
                    result.append(expandNumber(numericValue)).append(" ");
                } catch (NumberFormatException e) {
                    result.append(part).append(" ");
                }
            }

            return result.toString().trim();
        } else {
            return s;
        }
    }

    private String expandNumber(int number) {
        if (number < 0 || number > 999) {
            return String.valueOf(number);
        }

        StringBuilder result = new StringBuilder();

        int hundreds = number / 100;
        if (hundreds > 0) {
            result.append(HUNDRED.get((char) (hundreds + '0')));
        }

        int remainder = number % 100;
        if (remainder > 0) {
            if (result.length() > 0) {
                result.append(" ");
            }

            if (remainder < 10) {
                result.append(DIGIT_MAP.get((char) (remainder + '0')));
            } else if (remainder < 20) {
                result.append(TEENS_MAP.get(String.valueOf(remainder)));
            } else {
                int tens = remainder / 10;
                int units = remainder % 10;

                result.append(TENS_MAP.get((char) (tens + '0')));

                if (units > 0) {
                    result.append(" ").append(DIGIT_MAP.get((char) (units + '0')));
                }
            }
        }

        return result.toString();
    }

    @Override
    public String transform() {
        return function(textToTransform.transform());
    }
}
