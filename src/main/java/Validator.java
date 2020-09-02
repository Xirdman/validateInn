/**
 * Class to validate Russian federation INN
 */
public class Validator {

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
        int controlSum = Integer.parseInt(inn.substring(0, 1)) * 2 +
                Integer.parseInt(inn.substring(1, 2)) * 4 +
                Integer.parseInt(inn.substring(2, 3)) * 10 +
                Integer.parseInt(inn.substring(3, 4)) * 3 +
                Integer.parseInt(inn.substring(4, 5)) * 5 +
                Integer.parseInt(inn.substring(5, 6)) * 9 +
                Integer.parseInt(inn.substring(6, 7)) * 4 +
                Integer.parseInt(inn.substring(7, 8)) * 6 +
                Integer.parseInt(inn.substring(8, 9)) * 8;
        int controlNumber = getControlNumber(controlSum);
        if (controlNumber != Integer.parseInt(inn.substring(9)))
            throw new IllegalStateException(INCORRECT_TEN_DIGITS_INN_MESSAGE);
    }

    private void validateTwelveDigits(String inn) throws IllegalStateException {
        int controlSum1 = getControlSum1FromTwelveDigitsInn(inn.substring(0, 11));
        int controlNumber1 = getControlNumber(controlSum1);
        int controlSum2 = getControlSum2FromTwelveDigitsInn(inn);
        int controlNumber2 = getControlNumber(controlSum2);
        if ((controlNumber1 != Integer.parseInt(inn.substring(10, 11)))
                || (controlNumber2 != Integer.parseInt(inn.substring(11))))
            throw new IllegalStateException(INCORRECT_TWELVE_DIGITS_INN_MESSAGE);
    }

    private int getControlSum1FromTwelveDigitsInn(String inn) {
        return Integer.parseInt(inn.substring(0, 1)) * 7 +
                Integer.parseInt(inn.substring(1, 2)) * 2 +
                Integer.parseInt(inn.substring(2, 3)) * 4 +
                Integer.parseInt(inn.substring(3, 4)) * 10 +
                Integer.parseInt(inn.substring(4, 5)) * 3 +
                Integer.parseInt(inn.substring(5, 6)) * 5 +
                Integer.parseInt(inn.substring(6, 7)) * 9 +
                Integer.parseInt(inn.substring(7, 8)) * 4 +
                Integer.parseInt(inn.substring(8, 9)) * 6 +
                Integer.parseInt(inn.substring(9, 10)) * 8;
    }

    private int getControlSum2FromTwelveDigitsInn(String inn) {
        return getControlSum1FromTwelveDigitsInn(inn.substring(1, 12))
                + (Integer.parseInt(inn.substring(0, 1)) * 3);
    }

    private int getControlNumber(int controlSum) {
        int controlNumber = controlSum % 11;
        if (controlNumber > 9)
            controlNumber %= 10;
        return controlNumber;
    }
}
