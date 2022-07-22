package controller;

import model.User;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp {



    public static String signup (String username,String password,String password2,String Gender,String Email,int BirthYear,int SQNumber,String answer,String accountType) throws SQLException {
        String result = "";
        int ID = User.getIDByUserName(username);
        if (ID!=-1) result += "username is already exists ";
        result += checkPassword(password);
        if (!password.equals(password2)) result += "passwords are not same ";
        result += checkEmail(Email);
        if (result.isEmpty()) {
            User.insertNewUser(username,password,Gender,Email,BirthYear,SQNumber,answer,accountType);
            result = "sign up successfully";
        }
        return result;
    }

    protected static String checkPassword (String password) {
        Pattern pattern ;
        Matcher matcher;
        String[] check = {"[A-Z]","[a-z]","[0-9]"};
        String[] message = {"password must include uppercase ","password must include lowercase ","password must include number ","password must be at least 8 characters "};
        StringBuilder result = new StringBuilder();
        for (int i=0;i<3;i++) {
            pattern = Pattern.compile(check[i]);
            matcher = pattern.matcher(password);
            if (!matcher.find()) {
                result.append(message[i]);
            }
        }
        if (password.length()<8) result.append(message[3]);
        return result.toString();
    }

    private static String checkEmail (String Email) {
        Pattern pattern = Pattern.compile("\\S+@(\\w+)\\.com");
        Matcher matcher = pattern.matcher(Email);
        if (!matcher.find()) return "invalid Email";
        else return "";
    }
}
