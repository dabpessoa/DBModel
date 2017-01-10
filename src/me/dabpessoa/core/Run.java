package me.dabpessoa.core;

import javax.swing.SwingUtilities;

/**
 * 
 * Classe Respons�vel por inicializar a aplica��o.
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
