package main.java.me.dabpessoa.gui.controller;

import main.java.me.dabpessoa.bean.Tabela;
import main.java.me.dabpessoa.gui.PrincipalUI;
import main.java.me.dabpessoa.gui.TabelaUI;

import javax.swing.*;
import java.awt.Point;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.util.List;

/**
 * Created by diego.pessoa on 03/03/2017.
 */
public class TableController {

    public static JPanel createAndAddNewTableUI (Point point, PrincipalUI principalUI) {
        JPanel tablePanel = createNewTableUI(point, principalUI);
        addTablePanelToMainPanel(tablePanel, PrincipalUI.getFundo());
        return tablePanel;
    }

    public static JPanel createNewTableUI(Point point, PrincipalUI principalUI) {
        JPanel tablePanel = createTablePanel(point);

        TabelaUI tabelaUI = new TabelaUI(principalUI);
        tabelaUI.setTitle("Tabela_"+(principalUI.getListaTabelas().size()+1)+"");
        principalUI.getListaTabelas().add(tabelaUI);

        tablePanel.add(tabelaUI);

        return tablePanel;
    }

    private static JPanel createTablePanel(Point point) {

        JPanel tablePanel = new JPanel();

        tablePanel.setLayout(new GridLayout());
        tablePanel.setSize(new Dimension(100, 100));
        tablePanel.setLocation(point);
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        return tablePanel;

    }

    private static void addTablePanelToMainPanel(JPanel tablePanel, JPanel tablesPanel) {
        tablesPanel.add(tablePanel);
        tablesPanel.setComponentZOrder(tablePanel, 0);
        tablesPanel.updateUI();
    }

}
