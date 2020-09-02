/**
 * Entry point of program
 *
 * @author Matveev Alexander
 */
public class Main {
    private static final String INN = "1234567894";

    /**
     * Main method of program
     * @param args arguments of program
     */
    public static void main(String[] args) {
        Validator validator = new Validator();
        try {
            validator.validate(INN);
            System.out.println("Inn correct");
        } catch (IllegalStateException e) {
            System.out.println("Error:\n" + e.getMessage());
        }
    }
}
