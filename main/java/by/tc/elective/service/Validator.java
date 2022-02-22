package by.tc.elective.service;

import by.tc.elective.bean.Category;
import by.tc.elective.bean.User;
import by.tc.elective.dao.CategoryDAO;
import by.tc.elective.dao.DAOFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean registrationValidator(User newUser) {
        String name = newUser.getName();
        String login = newUser.getLogin();
        String password = newUser.getPassword();

        Pattern namePattern = Pattern.compile("[A-ZА-Я][a-zа-я]+");
        Pattern loginPattern = Pattern.compile("\\w{4,15}");
        Pattern passwordPattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{4,10}");

        Matcher nameMatcher = namePattern.matcher(name);
        Matcher loginMatcher = loginPattern.matcher(login);
        Matcher passwordMatcher = passwordPattern.matcher(password);

        return (!(name.isEmpty() && login.isEmpty() && password.isEmpty())) && nameMatcher.matches() && loginMatcher.matches() && passwordMatcher.matches();

    }

    public static boolean signInValidator(String login, String password) {
        Pattern loginPattern = Pattern.compile("\\w{4,15}");
        Pattern passwordPattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{4,10}");

        Matcher loginMatcher = loginPattern.matcher(login);
        Matcher passwordMatcher = passwordPattern.matcher(password);

        return loginMatcher.matches() && passwordMatcher.matches();
    }

    public static boolean categoryValidator(String title) {
        Pattern titlePattern = Pattern.compile("[A-ZА-Я][a-zа-я]*.*");
        Matcher titleMatcher = titlePattern.matcher(title);

        return !title.isEmpty() && titleMatcher.matches();
    }
}
