package by.tc.elective.controller;

import by.tc.elective.controller.impl.*;
import by.tc.elective.controller.impl.add.AddNewCategoryCommand;
import by.tc.elective.controller.impl.change.ChangeLangCommand;
import by.tc.elective.controller.impl.delete.DeleteCategoryCommand;
import by.tc.elective.controller.impl.go_to.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandFactory {
    private final Map<String, Command> commands = new HashMap<String, Command>();

    public CommandFactory() {
        commands.put("Sign in", new SignInCommand());
        commands.put("Registration", new RegistrationCommand());
        commands.put("Change", new ChangeLangCommand());
        commands.put("ShowAllCourses", new ShowAllCoursesCommand());
        commands.put("ShowCategories", new ShowCategoriesCommand());
        commands.put("ShowCoursesByCategory", new ShowCoursesByCategoryCommand());
        commands.put("ShowCourseInfo", new ShowCourseInfoCommand());
        commands.put("AddNewCategory", new AddNewCategoryCommand());
        commands.put("DeleteCategory", new DeleteCategoryCommand());
        commands.put("ProfileEditing", new ProfileEditingCommand());
        commands.put("UpdateCategory", new UpdateCategoryCommand());
        commands.put("ShowMyCourses", new ShowMyCoursesCommand());
        commands.put("ShowTeacherCourses", new ShowAllTeacherCoursesCommand());
        commands.put("ShowUserCourseProgress", new ShowUserCourseProgressCommand());
        commands.put("JoinCourse", new JoinCourseCommand());

        commands.put("GO_TO_SIGN_IN_PAGE", new GoToSignInPageCommand());
        commands.put("GO_TO_REGISTRATION_PAGE", new GoToRegistrationPageCommand());
        commands.put("GO_TO_INDEX_PAGE", new GoToIndexPageCommand());
        commands.put("GO_TO_PROFILE_PAGE", new GoToProfilePageCommand());
        commands.put("GO_TO_SIGN_OUT_PAGE", new GoToSignOutPageCommand());
        commands.put("GO_TO_UPDATE_CATEGORY_PAGE", new GoToUpdateCategoryPageCommand());
        commands.put("GO_TO_UPDATE_PROFILE_PAGE", new GoToUpdateProfilePageCommand());
        commands.put("GO_TO_MY_COURSES_PAGE", new GoToUpdateProfilePageCommand());
    }

    public Command getCommand(String commandName) {
        Command command = commands.get(commandName);
        return command;
    }
}
