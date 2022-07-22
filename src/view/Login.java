package view;
import model.User;

import java.sql.SQLException;
import java.util.Scanner;
public class Login {

    static Scanner scanner = new Scanner(System.in);
    private static final String[] SecurityQuestions = {"What city were you born in?","What is your oldest sibling’s first name?","What is the name of your first school?","What were the last four digits of your childhood telephone number?"};

    protected static void login() throws SQLException {
        System.out.println("***************************** login page *****************************");
        System.out.println("enter your username if you dont have account enter signup");
        String username = scanner.next();
        if (username.equals("signup")) {Manager.LoginBool=false; Manager.SignUpBool = true; return;}
        System.out.println("enter your password if you forget your password enter forget");
        String password = scanner.next();
        if (password.equals("forget")) {
           int SQNumber = controller.Login.showSecurityQuestion(username);
           if (SQNumber == -1) {
               System.out.println("username doesn't exist");
               return;
           }
            System.out.println(SecurityQuestions[SQNumber-1]);
            String answer = scanner.next();
            if (controller.Login.checkAnswer(username,answer)) {
                System.out.println("enter your new password (password must be at least 8 characters and include uppercase,lowercase,number)");
                String newPassword = scanner.next();
                String result = controller.Login.changePasswordByID(username,newPassword);
                System.out.println(result);
            }
            else System.out.println("incorrect answer");
            return;
        }
        String result = controller.Login.login(username, password);
        System.out.println(result);
        if (result.equals("login successfully")) {
            Home.myUserName = username;
            Home.myID = User.getIDByUserName(username);
            Home.businessAccount = User.getBoolADByID(Home.myID);
            Manager.LoginBool=false;
            Manager.HomeBool = true;
        }
    }

}
