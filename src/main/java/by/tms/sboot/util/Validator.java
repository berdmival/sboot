package by.tms.sboot.util;

import org.springframework.stereotype.Component;

@Component("validator")
public class Validator {

    public static boolean isValidAction(String input) {
        switch (input) {
            case ("sum"):
            case ("diff"):
            case ("mult"):
            case ("div"):
                return true;
            default:
                return false;
        }
    }

    public static boolean isValidDIV(Double num2, String actionType) {
        return !(actionType.equals("div") & num2 == 0);
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        str = str.replaceAll(",", ".");
        int dotCount = 0;
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                if (i == length - 1) {
                    return false;
                } else if (c == '.') {
                    if (++dotCount > 1) {
                        return false;
                    }
                } else if (i != 0 || c != '+' && c != '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validArgs(Double num1, Double num2, String actionType) {
        return isValidAction(actionType) && isValidDIV(num2, actionType);
    }
}
