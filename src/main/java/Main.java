public class Main {
    private static final String INN = "000000000b";
    public static void main(String[] args){
        Validator validator = new Validator();
        try{
            validator.validate(INN);
            System.out.println("Inn correct");
        }catch (IllegalStateException e){
            System.out.println("Error:\n"+e.getMessage());
        }
    }
}
