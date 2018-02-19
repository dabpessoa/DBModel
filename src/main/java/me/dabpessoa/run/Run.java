package me.dabpessoa.run;

import me.dabpessoa.business.Controller;

import javax.swing.*;

/**
 * 
 * Classe Responsável por inicializar a aplicação.
 * 
 * @author Diego Augusto
 * @since 29.05.2010
 *
 */
public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Controller().run());
	}

}
