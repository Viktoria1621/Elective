package by.tc.elective.service;

import by.tc.elective.bean.Category;
import by.tc.elective.bean.User;
import by.tc.elective.dao.CategoryDAO;
import by.tc.elective.dao.DAOFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean registrationValidator(User newUser) {
        String name;
        String login;
        String password;

        name = newUser.getName();
        login = newUser.getLogin();
        password = newUser.getPassword();

        Pattern namePattern = Pattern.compile("[A-ZА-Я][a-zа-я]+");
        Pattern loginPattern = Pattern.compile("\\w{4,15}");
        Pattern passwordPattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{4,10}");

        Matcher nameMatcher = namePattern.matcher(name);
        Matcher loginMatcher = loginPattern.matcher(login);
        Matcher passwordMatcher = passwordPattern.matcher(password);

        if((!(name.isEmpty()&&login.isEmpty()&&password.isEmpty()))&&nameMatcher.matches()&&loginMatcher.matches()&&passwordMatcher.matches()) {
            return true;
        }
        else return false;
    }

    public static boolean signInValidator(String login, String password) {

        Pattern loginPattern = Pattern.compile("\\w{4,15}");
        Pattern passwordPattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{4,10}");

        Matcher loginMatcher = loginPattern.matcher(login);
        Matcher passwordMatcher = passwordPattern.matcher(password);

        if (loginMatcher.matches()&&passwordMatcher.matches()) {
            return true;
        }
        else return false;
    }

    public static boolean categoryValidator(String title) {
        Pattern titlePattern = Pattern.compile("[A-ZА-Я][a-zа-я]*.*");
        Matcher titleMatcher = titlePattern.matcher(title);

        if(!title.isEmpty()&&titleMatcher.matches()) {
            return true;
        }
        else return false;
    }
}
