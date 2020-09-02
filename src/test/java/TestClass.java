import org.junit.Assert;
import org.junit.Test;

public class TestClass {
    private Validator validator = new Validator();

    private final static String INCORRECT_INN_LENGTH_MESSAGE = "Inn length must be 10 or 12 symbols";
    private final static String INCORRECT_TEN_SYMBOLS_MESSAGE = "Inn must contain 10 digits, not anything else";
    private final static String INCORRECT_TWELVE_SYMBOLS_MESSAGE = "Inn must contain 12 digits, not anything else";
    private final static String INCORRECT_TEN_DIGITS_INN_MESSAGE = "10 digits inn is not valid";
    private final static String INCORRECT_TWELVE_DIGITS_INN_MESSAGE = "12 digits inn is not valid";

    @Test(expected = IllegalStateException.class)
    public void testEmptyInn() {
        validator.validate("");
    }

    @Test
    public void testValidTenDigitInn() {
        validator.validate("1234567894");
    }

    @Test
    public void testValidTwelveDigitInn() {
        validator.validate("124631235399");
    }

    @Test
    public void getCorrectMessageFromNonDigitInn(){
        try {
            validator.validate("qqqwwweeer");
            Assert.fail("IllegalStateException Expected");
        }catch (IllegalStateException e){
            Assert.assertEquals(e.getMessage(),INCORRECT_TEN_SYMBOLS_MESSAGE);
        }
    }
    @Test
    public void getCorrectMessageWhenInnLengthNotCorrect(){
        try {
            validator.validate("123");
            Assert.fail("IllegalStateException Expected");
        }catch (IllegalStateException e){
            Assert.assertEquals(e.getMessage(),INCORRECT_INN_LENGTH_MESSAGE);
        }
    }
    @Test
    public void getCorrectMessageWhenTenDigitsInnIsNotValid(){
        try {
            validator.validate("1234567890");
            Assert.fail("IllegalStateException Expected");
        }catch (IllegalStateException e){
            Assert.assertEquals(e.getMessage(),INCORRECT_TEN_DIGITS_INN_MESSAGE);
        }
    }
    @Test
    public void getCorrectMessageFromNonDigitInnAgain(){
        try {
            validator.validate("A12345678901");
            Assert.fail("IllegalStateException Expected");
        }catch (IllegalStateException e){
            Assert.assertEquals(e.getMessage(),INCORRECT_TWELVE_SYMBOLS_MESSAGE);
        }
    }
    @Test
    public void getCorrectMessageWhenTwelveDigitsInnIsNotValid(){
        try {
            validator.validate("000000000011");
            Assert.fail("IllegalStateException Expected");
        }catch (IllegalStateException e){
            Assert.assertEquals(e.getMessage(),INCORRECT_TWELVE_DIGITS_INN_MESSAGE);
        }
    }

}
