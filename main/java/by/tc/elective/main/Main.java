package by.tc.elective.main;
import by.tc.elective.bean.Category;
import by.tc.elective.bean.Course;
import by.tc.elective.bean.User;
import by.tc.elective.dao.*;
import by.tc.elective.service.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws DAOException {
        DAOFactory factory = DAOFactory.getInstance();
        CourseRunDAO courseRunDAO = factory.getCourseRunDAO();
        List<Integer> integers = new ArrayList<>();
        List<Integer> integersCourseRun = new ArrayList<>();
        integers.add(1);
        integers.add(3);

        integers.add(2);
        
        System.out.println(courseRunDAO.showCourseRunCourse(integers));
    }
}
