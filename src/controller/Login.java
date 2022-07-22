package controller;

import model.User;

import java.sql.SQLException;

public class Login {

    public static String login (String username,String password) throws SQLException {
       int ID = User.getIDByUserName(username);
       if (ID == -1) return "username doesn't exist";
       if (!User.getPasswordByID(ID).equals(password)) return "password is wrong";
       else return  "login successfully";
    }

    public static int showSecurityQuestion (String username) throws SQLException {
        int ID = User.getIDByUserName(username);
        if (ID==-1) return -1;
        return User.getSQNumberByID(ID);
    }

    public static boolean checkAnswer (String username,String answer) throws SQLException {
        int ID = User.getIDByUserName(username);
        return User.getAnswerByID(ID).equals(answer);
    }

    public static String changePasswordByID (String username,String newPassword) throws SQLException {
        int ID = User.getIDByUserName(username);
        String checkedPassword = SignUp.checkPassword(newPassword);
        if (checkedPassword.isEmpty()) {
            User.setNewPasswordByID(ID, newPassword);
            return "password changed successfully";
        }
        else
            return checkedPassword;
    }

}
