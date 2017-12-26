package it.polito.tdp.bar.exception;

public class NoTableAvailableException extends BarManagementException{
	private static final long serialVersionUID = 441858249958301803L;

	public NoTableAvailableException(String message) {
		super(message);
	}

}
