package feature;

public class Main {

    public static void main(String[] args) {
        try {
            UISystem uiSystem = new UISystem();
            uiSystem.run();
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}