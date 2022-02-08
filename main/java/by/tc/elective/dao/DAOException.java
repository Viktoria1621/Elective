package by.tc.elective.dao;

public class DAOException extends Exception {
    public DAOException() {
        super();
    }

    public DAOException(String massage) {
        super(massage);
    }

    public DAOException(Exception e) {
        super(e);
    }

    public DAOException(String massage, Exception e) {
        super(massage, e);
    }
}
