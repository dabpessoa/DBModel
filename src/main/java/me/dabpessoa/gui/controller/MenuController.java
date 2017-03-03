package main.java.me.dabpessoa.gui.controller;

import main.java.me.dabpessoa.bean.enums.UserAction;
import main.java.me.dabpessoa.business.listeners.TablesListener;
import main.java.me.dabpessoa.gui.PrincipalUI;
import main.java.me.dabpessoa.gui.panels.About;
import main.java.me.dabpessoa.util.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by diego.pessoa on 03/03/2017.
 */
public class MenuController {

    public static JMenuBar createPrimaryMenuBar(PrincipalUI principalUI) {

        JFrame principalFrame = principalUI.getFrame();

        JMenuBar mb = new JMenuBar();
        JMenu m = new JMenu("Menu");

        JMenuItem submenu = new JMenuItem("Sair");
        submenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                principalFrame.dispose();
                System.exit(0);
            }
        });

        m.add(submenu);

        JMenu m1 = new JMenu("Editar");

        JMenuItem submenu3 = new JMenuItem("Salvar Modelo");
        submenu3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                principalUI.updateListeners(null, UserAction.SALVAR_MODELO);
            }
        });

        JMenuItem submenu4 = new JMenuItem("Carregar Modelo");
        submenu4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                principalUI.updateListeners(null, UserAction.CARREGAR_MODELO);
            }
        });

        JMenuItem submenu5 = new JMenuItem("Gerar SQL");
        submenu5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                principalUI.updateListeners(null, UserAction.GERAR_SQL);
            }
        });

        m1.add(submenu3);
        m1.add(submenu4);
        m1.add(submenu5);

        JMenu m2 = new JMenu("Ajuda");

        JMenuItem submenu2 = new JMenuItem("Sobre DBModel");
        submenu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                About.getInstance(principalFrame);

            }
        });

        m2.add(submenu2);

        mb.add(m);
        mb.add(m1);
        mb.add(m2);

        return mb;

    }

    public static JMenuBar createSecondaryMenuBar(ActionListener actionListener) {

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.PAGE_AXIS));
        menuBar.setBorder(BorderFactory.createMatteBorder(0,0,0,1, Color.BLACK));

        menuBarAddComponents(menuBar, actionListener);

        return menuBar;

    }

    private static void menuBarAddComponents(JMenuBar menuBar, ActionListener actionListener) {

        JButton button = new JButton();
        button.setToolTipText("Adicionar Tabela");
        Image im = ImageUtils.redimensiona("src"+ File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator+"addTable.png", 18, 18);
        ImageIcon icon = new ImageIcon(im);
        button.setIcon(icon);
        button.addActionListener(actionListener);
        button.setActionCommand("button");

        JButton button2 = new JButton();
        button2.setToolTipText("Adicionar Restrições");
        Image im2 = ImageUtils.redimensiona("src"+File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator+"relationship.png", 18, 18);
        ImageIcon icon2 = new ImageIcon(im2);
        button2.setIcon(icon2);
        button2.addActionListener(actionListener);
        button2.setActionCommand("button2");

        JButton button3 = new JButton();
        button3.setToolTipText("Gerar SQL");
        Image im3 = ImageUtils.redimensiona("src"+File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator+"gerar.png", 18, 18);
        ImageIcon icon3 = new ImageIcon(im3);
        button3.setIcon(icon3);
        button3.addActionListener(actionListener);
        button3.setActionCommand("button3");

        menuBar.add(button);
        menuBar.add(button2);
        menuBar.add(button3);

    }

}
