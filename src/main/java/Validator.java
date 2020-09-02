/**
 * Class to validate Russian federation INN
 */
public class Validator {
    private final int[] weightTenDigits = {3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8, 0};

    private final static String INCORRECT_INN_LENGTH_MESSAGE = "Inn length must be 10 or 12 symbols";
    private final static String INCORRECT_TEN_SYMBOLS_MESSAGE = "Inn must contain 10 digits, not anything else";
    private final static String INCORRECT_TWELVE_SYMBOLS_MESSAGE = "Inn must contain 12 digits, not anything else";
    private final static String INCORRECT_TEN_DIGITS_INN_MESSAGE = "10 digits inn is not valid";
    private final static String INCORRECT_TWELVE_DIGITS_INN_MESSAGE = "12 digits inn is not valid";

    /**
     * Method to validate Inn
     *
     * @param inn inn to validate
     * @throws IllegalStateException thrown when inn is not correct
     */
    public void validate(String inn) throws IllegalStateException {
        if (inn.length() == 10) {
            containsTenDigits(inn);
            validateTenDigits(inn);
        } else {
            if (inn.length() == 12) {
                containsTwelveDigits(inn);
                validateTwelveDigits(inn);
            } else
                throw new IllegalStateException(INCORRECT_INN_LENGTH_MESSAGE);
        }
    }

    private void containsTenDigits(String inn) throws IllegalStateException {
        if (!inn.matches("\\d{10}")) {
            throw new IllegalStateException(INCORRECT_TEN_SYMBOLS_MESSAGE);
        }
    }

    private void containsTwelveDigits(String inn) throws IllegalStateException {
        if (!inn.matches("\\d{12}")) {
            throw new IllegalStateException(INCORRECT_TWELVE_SYMBOLS_MESSAGE);
        }
    }

    private void validateTenDigits(String inn) throws IllegalStateException {
        int controlSum = getControlSumFromTenDigitsInn(inn);
        int controlNumber = getControlNumber(controlSum);
        if (controlNumber != Integer.parseInt(inn.substring(9)))
            throw new IllegalStateException(INCORRECT_TEN_DIGITS_INN_MESSAGE);
    }

    private void validateTwelveDigits(String inn) throws IllegalStateException {
        int controlSum1 = getControlSum1FromTwelveDigitsInn(inn);
        int controlNumber1 = getControlNumber(controlSum1);
        int controlSum2 = getControlSum2FromTwelveDigitsInn(inn);
        int controlNumber2 = getControlNumber(controlSum2);
        if ((controlNumber1 != Integer.parseInt(inn.substring(10, 11)))
                || (controlNumber2 != Integer.parseInt(inn.substring(11))))
            throw new IllegalStateException(INCORRECT_TWELVE_DIGITS_INN_MESSAGE);
    }

    private int getControlNumber(int controlSum) {
        int controlNumber = controlSum % 11;
        if (controlNumber > 9)
            controlNumber %= 10;
        return controlNumber;
    }

    private int getControlSumFromTenDigitsInn(String inn) {
        int sum = 0;
        for (int i = 0; i < inn.length(); i++) {
            sum += Character.getNumericValue(inn.charAt(i)) * weightTenDigits[i + 2];
        }
        return sum;
    }

    private int getControlSum1FromTwelveDigitsInn(String inn) {
        int sum = 0;
        for (int i = 0; i < inn.length() - 1; i++) {
            sum += Character.getNumericValue(inn.charAt(i)) * weightTenDigits[i + 1];
        }
        return sum;
    }

    private int getControlSum2FromTwelveDigitsInn(String inn) {
        int sum = 0;
        for (int i = 0; i < inn.length(); i++) {
            sum += Character.getNumericValue(inn.charAt(i)) * weightTenDigits[i];
        }
        return sum;
    }
}
