package com.msdt.carrental.model.dao.api;

/**
 * Custom Exception to be more specific from where we have throwing that exception
 * 
 * @author Ahmad Alrefai
 * 
 */
public class DaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -481271377780096016L;

	public DaoException() {
		super();
	}

	public DaoException(final String message) {
		super(message);
	}

	public DaoException(final Throwable cause) {
		super(cause);
	}

	public DaoException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
