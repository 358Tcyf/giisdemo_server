package simple.project.giis.utils;

public class VerifyCodeUtil {

    private static final String[] num = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public static String createCode() {
        String code = "";
        for (int i = 0; i < 6; i++) {
            int n = (int) (Math.random() * 9);
            code = code + num[n];
        }
        return code;
    }

    public static void main(String[] args) {
        System.out.println(createCode());
    }
}
