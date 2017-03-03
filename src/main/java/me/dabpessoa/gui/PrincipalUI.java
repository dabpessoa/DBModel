package main.java.me.dabpessoa.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import main.java.me.dabpessoa.bean.Atributo;
import main.java.me.dabpessoa.bean.IntegritRestriction;
import main.java.me.dabpessoa.bean.Relationship;
import main.java.me.dabpessoa.bean.enums.UserAction;
import main.java.me.dabpessoa.business.listeners.RelationShipListener;
import main.java.me.dabpessoa.business.listeners.TablesListener;
import main.java.me.dabpessoa.business.xml.DBModelXMLParser;
import main.java.me.dabpessoa.gui.controller.MenuController;
import main.java.me.dabpessoa.gui.controller.TableController;
import main.java.me.dabpessoa.gui.panels.About;

public class PrincipalUI implements ActionListener, RelationShipListener, MouseListener {
	
	private static FundoUI fundo;
	private List<TabelaUI> listaTabelas = new ArrayList<TabelaUI>();
	private static JScrollPane scrollPane;
	private List<TablesListener> listeners;
	private JFrame frame;

	public PrincipalUI() {
		this.listeners = new ArrayList<TablesListener>();
	}

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(false);

        //Create and set up the window.
        frame = new JFrame("DBModel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(frame.getToolkit().getImage(getClass().getResource("/main/resources/images/logo.png")));

        //Create and set up the content pane.
        Container contentPane = frame.getContentPane();
        contentPane.setBackground(Color.WHITE); //contrasting bg

		// Add menus
        contentPane.add(MenuController.createSecondaryMenuBar(this), BorderLayout.LINE_START);
        contentPane.add(MenuController.createPrimaryMenuBar(this), BorderLayout.PAGE_START);
        
        fundo = FundoUI.getInstance();
        fundo.setLayout(null);
        fundo.setBackground(Color.WHITE);
        fundo.addMouseListener(this);
        
        scrollPane = new JScrollPane(fundo);
        frame.add(scrollPane);

        //Display the window.
        frame.setSize(700, 600);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }

	public List<TabelaUI> getListaTabelas() {
		return listaTabelas;
	}

	public void readModel(StringBuilder xml) {

		List<TabelaUI> xmlTablesUI = new ArrayList<TabelaUI>();

		DBModelXMLParser parser = new DBModelXMLParser();
		xmlTablesUI = parser.loadXML(xml);

		fundo.removeAll();
		fundo.updateUI();

		for (int i = 0 ; i < xmlTablesUI.size() ; i++) {

			xmlTablesUI.get(i).setPrincipalUI(this);

			Point point = xmlTablesUI.get(i).getLocation();

			TableController.createAndAddNewTableUI(point, this);

		}

	}

	public void addListener(TablesListener listener) {
		listeners.add(listener);
	}

	public void removeListener(TablesListener listener) {
		listeners.remove(listener);
	}

	public void updateListeners(Object obj, UserAction acao) {
		for (int i = 0 ; i < listeners.size() ; i++) {
			listeners.get(i).updateTables(obj, acao);
		}
	}

	public static JPanel getFundo() {
		return fundo;
	}

	public void posicao(int x, int y, JPanel painel) {
		painel.setLocation(x + painel.getX(), y+painel.getY());
	}

	public Point calculateCoordinates() {

		Point location = null;

		for (int i = 0 ; i < listaTabelas.size() ; i++) {

			location = listaTabelas.get(i).getParent().getLocation();

			location.x += 10;
			location.y += 10;

		}

		if (location == null) return new Point(0,0);
		return location;

	}

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PrincipalUI().createAndShowGUI();
            }
        });
    }

    class HorizontalMenu extends JMenu {
		private static final long serialVersionUID = 1L;

		HorizontalMenu(String label) {
            super(label);
            JPopupMenu pm = getPopupMenu();
            pm.setLayout(new BoxLayout(pm, BoxLayout.LINE_AXIS));
        }

        public Dimension getMinimumSize() {
            return getPreferredSize();
        }

        public Dimension getMaximumSize() {
            return getPreferredSize();
        }

        public void setPopupMenuVisible(boolean b) {
            boolean isVisible = isPopupMenuVisible();
            if (b != isVisible) {
                if ((b==true) && isShowing()) {
                    //Set location of popupMenu (pulldown or pullright).
                    //Perhaps this should be dictated by L&F.
                    int x = 0;
                    int y = 0;
                    Container parent = getParent();
                    if (parent instanceof JPopupMenu) {
                        x = 0;
                        y = getHeight();
                    } else {
                        x = getWidth();
                        y = 0;
                    }
                    getPopupMenu().show(this, x, y);
                } else {
                    getPopupMenu().setVisible(false);
                }
            }
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		if (command.equalsIgnoreCase("button")) {
			
			Point point = calculateCoordinates();
			JPanel tablePanel = TableController.createAndAddNewTableUI(point, this);
			TabelaUI tabelaUI = (TabelaUI)tablePanel.getComponent(0);

			this.updateListeners(tabelaUI.getTabela(), UserAction.ADICIONAR);
			
		} else if (command.equalsIgnoreCase("button2")) {
			
			// Pegar os duas tabelas escolhidas e criar a linha entre elas.
			this.updateListeners(null, UserAction.RELACAO_TABELAS);
			
		} else if (command.equalsIgnoreCase("button3")) {
		
			this.updateListeners(null, UserAction.GERAR_SQL);
			
		}
		
	}

	@Override
	public void drawLines(String objectName, String t1, String t2, String cardinalidade, List<String> primaryAtributoName, List<String> foreignAtributoName) {
		
		/*
		 * Procuro pelas tabelas cujos nomes são iguais aos parâmetros passados
		 * e em seguida desenho uma linha ligando-as.
		 */

		TabelaUI tabela1 = null;
		TabelaUI tabela2 = null;
		
		for (int i = 0 ; i < listaTabelas.size() ; i++) {
			if (listaTabelas.get(i).getTabela().getTitulo() == t1) {
				tabela1 = listaTabelas.get(i);
			} else if (listaTabelas.get(i).getTabela().getTitulo() == t2) {
				tabela2 = listaTabelas.get(i);
			}
		}

		
		Relationship rs = new Relationship();
		rs.setNome(objectName);
		rs.setLeftTable(tabela1.getTabela());
		rs.setRightTable(tabela2.getTabela());
		rs.setCardinalidade(cardinalidade);
		
		
		
		if (cardinalidade.equalsIgnoreCase("N:N")) {
			
			if (tabela1.getTabela().getChavesPrimaria().isEmpty() || tabela2.getTabela().getChavesPrimaria().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Nas relaçõeses N:N é obrigatório que as duas tabelas tenham um atributo definido como chave primária");
			} else {
			
				JPanel tablePanel = TableController.createAndAddNewTableUI(new Point(0,0), this);
				TabelaUI temp = (TabelaUI) tablePanel.getComponent(0);
				temp.setTitle(tabela1.getTabela().getTitulo().substring(0, 3)+"_"+tabela2.getTabela().getTitulo().subSequence(0, 3)+"_table");
				
				List<Atributo> atribs = new ArrayList<Atributo>();
				for (int i = 0 ; i < tabela1.getTabela().getChavesPrimaria().size() ; i++) {
					Atributo atrib = tabela1.getTabela().getChavesPrimaria().get(i);
					Atributo temp1 = new Atributo();
					
					IntegritRestriction fk = new IntegritRestriction();
					fk.setTabelaNomeFK(tabela1.getTabela().getTitulo());
					fk.setAtributoFK(atrib.getNome());
					
					temp1.setChavePrimaria(true);
					temp1.setIntegritRestriction(fk);
					temp1.setNome(atrib.getNome());
					temp1.setRestrictNull(atrib.isRestrictNull());
					temp1.setType(atrib.getType());
					temp1.setUniqueKey(atrib.isUniqueKey());
					
					atribs.add(temp1);
				}
				for (int i = 0 ; i < tabela2.getTabela().getChavesPrimaria().size() ; i++) {
					Atributo atrib = tabela2.getTabela().getChavesPrimaria().get(i);
					Atributo temp1 = new Atributo();					
					
					IntegritRestriction fk = new IntegritRestriction();
					fk.setTabelaNomeFK(tabela2.getTabela().getTitulo());
					fk.setAtributoFK(atrib.getNome());
					
					temp1.setChavePrimaria(true);
					temp1.setIntegritRestriction(fk);
					temp1.setNome(atrib.getNome());
					temp1.setRestrictNull(atrib.isRestrictNull());
					temp1.setType(atrib.getType());
					temp1.setUniqueKey(atrib.isUniqueKey());
					
					atribs.add(temp1);
				}
				
				temp.setPrimaryKeys(atribs);
				
				fundo.setT1(tabela1);
				fundo.setT2(temp);
				fundo.addCardinalidade("1:N");
				
				fundo.setT1(temp);
				fundo.setT2(tabela2);
				fundo.addCardinalidade("N:1");
				
				rs = new Relationship();
				rs.setNome(objectName);
				rs.setLeftTable(tabela1.getTabela());
				rs.setRightTable(temp.getTabela());
				rs.setCardinalidade("1:N");
				
				temp.getParent().setSize(temp.getParent().getPreferredSize());
				
			}
			
		} else if (cardinalidade.equalsIgnoreCase("N:1")) {
			
			if (tabela1.getTabela().getChavesPrimaria().isEmpty() || tabela2.getTabela().getChavesPrimaria().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Para adicionar um relacionamento ao menos uma das tabelas deve conter um atributo definido como chave primária.");
			} else {
				
				List<Atributo> atribs = new ArrayList<Atributo>();
				for (int i = 0 ; i < tabela2.getTabela().getChavesPrimaria().size() ; i++) {
					
					Atributo atrib = tabela2.getTabela().getChavesPrimaria().get(i);
					Atributo temp = new Atributo();
					
					IntegritRestriction fk = new IntegritRestriction();
					fk.setTabelaNomeFK(tabela2.getTabela().getTitulo());
					fk.setAtributoFK(atrib.getNome());
										
					temp.setChavePrimaria(false);
					temp.setIntegritRestriction(fk);
					temp.setNome(atrib.getNome());
					temp.setRestrictNull(atrib.isRestrictNull());
					temp.setType(atrib.getType());
					temp.setUniqueKey(atrib.isUniqueKey());
					
					atribs.add(temp);
				}
				
				tabela1.setForeingKeys(atribs);
				tabela1.getParent().setSize(tabela1.getParent().getPreferredSize());
				
				fundo.setT1(tabela1);
				fundo.setT2(tabela2);
				fundo.addCardinalidade(cardinalidade);
			}
			
		} else if (cardinalidade.equalsIgnoreCase("1:N")) {
			
			if (tabela1.getTabela().getChavesPrimaria().isEmpty() || tabela2.getTabela().getChavesPrimaria().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Para adicionar um relacionamento ao menos uma das tabelas deve conter um atributo definido como chave primária.");
			} else {
				
				List<Atributo> atribs = new ArrayList<Atributo>();
				for (int i = 0 ; i < tabela1.getTabela().getChavesPrimaria().size() ; i++) {
					Atributo atrib = tabela1.getTabela().getChavesPrimaria().get(i);
					Atributo temp1 = new Atributo();
					
					IntegritRestriction fk = new IntegritRestriction();
					fk.setTabelaNomeFK(tabela1.getTabela().getTitulo());
					fk.setAtributoFK(atrib.getNome());
					
					temp1.setChavePrimaria(false);
					temp1.setIntegritRestriction(fk);
					temp1.setNome(atrib.getNome());
					temp1.setRestrictNull(atrib.isRestrictNull());
					temp1.setType(atrib.getType());
					temp1.setUniqueKey(atrib.isUniqueKey());
					
					atribs.add(temp1);
				}
				tabela2.setForeingKeys(atribs);
				tabela2.getParent().setSize(tabela2.getParent().getPreferredSize());
				
				fundo.setT1(tabela1);
				fundo.setT2(tabela2);
				fundo.addCardinalidade(cardinalidade);
			}
			
		} else if (cardinalidade.equalsIgnoreCase("1:1")) {
			
			if (tabela1.getTabela().getChavesPrimaria().isEmpty() || tabela2.getTabela().getChavesPrimaria().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Para adicionar um relacionamento ao menos uma das tabelas deve conter um atributo definido como chave primária.");
			} else {
				
				List<Atributo> atribs = new ArrayList<Atributo>();
				for (int i = 0 ; i < tabela1.getTabela().getChavesPrimaria().size() ; i++) {
					
					Atributo atrib = tabela1.getTabela().getChavesPrimaria().get(i);
					Atributo temp1 = new Atributo();
					
					IntegritRestriction fk = new IntegritRestriction();
					fk.setTabelaNomeFK(tabela1.getTabela().getTitulo());
					fk.setAtributoFK(atrib.getNome());
					
					temp1.setChavePrimaria(false);
					temp1.setIntegritRestriction(fk);
					temp1.setNome(atrib.getNome());
					temp1.setRestrictNull(atrib.isRestrictNull());
					temp1.setType(atrib.getType());
					temp1.setUniqueKey(atrib.isUniqueKey());

					atribs.add(temp1);
				}
				tabela2.setForeingKeys(atribs);
				tabela2.getParent().setSize(tabela2.getParent().getPreferredSize());
				
				fundo.setT1(tabela1);
				fundo.setT2(tabela2);
				fundo.addCardinalidade(cardinalidade);
			}
			
		}
		
		fundo.updateUI();
		
		this.updateListeners(rs, UserAction.ADD_RELATIONSHIP);
		
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			TableController.createAndAddNewTableUI(new Point(e.getX(), e.getY()), this);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}