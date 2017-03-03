package main.java.me.dabpessoa.run;

import main.java.me.dabpessoa.business.Controller;

import javax.swing.SwingUtilities;

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
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Controller().run();
			}
		});
	}

}
