package me.dabpessoa.exception;

import javax.swing.JOptionPane;

public class EmptyColumnException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public EmptyColumnException(String message) {
		super(message);
		JOptionPane.showMessageDialog(null, message);
	}

}
