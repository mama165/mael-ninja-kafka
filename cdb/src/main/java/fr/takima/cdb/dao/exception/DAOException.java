package fr.takima.cdb.dao.exception;

public class DAOException extends RuntimeException {

    /**
     * Constructor.
     */
    public DAOException() {
        super();
    }

    /**
     * Constructor with message.
     * @param message a specific message for the exception
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Constructor with exception.
     * @param e the exception
     */
    public DAOException(Exception e) {
        super(e);
    }
}