package view;

import java.sql.SQLException;

public class Manager {

    protected static boolean run = true , LoginBool = true , SignUpBool = false , HomeBool = false;

    public static void start () throws SQLException {
        while (run) {
            if (LoginBool) Login.login();
            if (SignUpBool) SignUp.signup();
            if (HomeBool) Home.home();
        }
    }


}
