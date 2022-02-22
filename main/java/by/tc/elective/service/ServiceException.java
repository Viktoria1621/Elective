package by.tc.elective.service;

public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }

    public ServiceException(String massage) {
        super(massage);
    }

    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String massage, Exception e) {
        super(massage, e);
    }
}
