package view;

import java.sql.SQLException;
import java.util.Scanner;

public class SignUp {

    static Scanner scanner = new Scanner(System.in);

    public static void signup () throws SQLException {
        System.out.println("***************************** signup page *****************************");
        System.out.println("enter your username if you have account enter login");
        String username = scanner.next();
        if (username.equals("login")) {
            Manager.SignUpBool = false;
            Manager.LoginBool = true;
            return;
        }
        System.out.println("enter your password (password must be at least 8 characters and include uppercase,lowercase,number)");
        String password = scanner.next();
        System.out.println("reenter your password");
        String password2 = scanner.next();
        System.out.println("enter your gender (male or female)");
        String gender = scanner.next();
        System.out.println("enter your email");
        String email = scanner.next();
        System.out.println("enter your BirthYear");
        int BirthYear = scanner.nextInt();
        System.out.println("choose one if this security questions\n1.What city were you born in?\n2.What is your oldest sibling’s first name?\n3.What is the name of your first school?\n4.What were the last four digits of your childhood telephone number?");
        int SQNumber = scanner.nextInt();
        System.out.println("enter your answer");
        String answer = scanner.next();
        System.out.println("choose your account type (personal or business)");
        String accountType = scanner.next();
        String result = controller.SignUp.signup(username,password,password2,gender,email,BirthYear,SQNumber,answer,accountType);
        System.out.println(result);
    }
}