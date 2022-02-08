package by.tc.elective.service;

import by.tc.elective.service.impl.*;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final CourseService courseService = new CourseServiceImpl();
    private final CategoryService categoryService = new CategoryServiceImpl();
    private final CourseRunUserInfoService courseRunUserInfoService = new CourseRunUserInfoServiceImpl();
    private final CourseRunService courseRunService = new CourseRunServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public CourseService getCourseService() {
        return courseService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public CourseRunUserInfoService getCourseRunUserInfoService() {
        return courseRunUserInfoService;
    }

    public CourseRunService getCourseRunService() {
        return courseRunService;
    }
}
