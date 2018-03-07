package template.r3tech.com.utils;


public class Constant {

    private static Constant constant = null;

    private Constant() {
    }

    public static Constant getInstance() {
        if (constant == null)
            constant = new Constant();
        return constant;
    }

    public final String CURRENT_POSITION = "CURRENT_POSITION";
}
