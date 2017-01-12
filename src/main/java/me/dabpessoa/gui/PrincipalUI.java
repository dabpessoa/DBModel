package main.java.me.dabpessoa.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import main.java.me.dabpessoa.business.Controller;
import main.java.me.dabpessoa.business.listeners.RelationShipListener;
import main.java.me.dabpessoa.business.listeners.TablesListener;
import main.java.me.dabpessoa.business.xml.DBModelXMLParser;
import main.java.me.dabpessoa.gui.components.About;
import main.java.me.dabpessoa.util.ImageUtils;

public class PrincipalUI implements ActionListener, RelationShipListener, MouseListener {
	
	private static FundoUI fundo;
	private List<TabelaUI> listaTabelas = new ArrayList<TabelaUI>();
	private static JScrollPane scrollPane;
	private List<TablesListener> listeners;
	private JFrame frame;
	public static int count = 0;
	
	
	public List<TabelaUI> getListaTabelas() {
		return listaTabelas;
	}
	
	public PrincipalUI() {
		this.listeners = new ArrayList<TablesListener>();
	}
	
    public JMenuBar createMenuBar() {
    	
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.PAGE_AXIS));
        menuBar.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.BLACK));
        
        menuBarAddComponents(menuBar);
        
        return menuBar;
        
    }
    
    public void readModel(StringBuilder xml) {
    	
    	count = 0;
    	List<TabelaUI> xmlTablesUI = new ArrayList<TabelaUI>();
    	
    	DBModelXMLParser parser = new DBModelXMLParser();
    	xmlTablesUI = parser.loadXML(xml);
    	
    	fundo.removeAll();
    	fundo.updateUI();
    	
    	for (int i = 0 ; i < xmlTablesUI.size() ; i++) {
    		
    		xmlTablesUI.get(i).setPrincipalUI(this);
    		
    		Point point = xmlTablesUI.get(i).getLocation();
    		
    		JPanel tablePanel = this.createTablePanel(point);
    		tablePanel.add(xmlTablesUI.get(i));
    		
    		this.setTableUI_ID(xmlTablesUI.get(i));
    		this.addTablePanelToMainPanel(tablePanel);
    		
    	}
    	
    	
    }
    
    public void addListener(TablesListener listener) {
    	listeners.add(listener);
    }
    
    public void removeListener(TablesListener listener) {
    	listeners.remove(listener);
    }
    
    public void updateListeners(Object obj, int acao) {
    	for (int i = 0 ; i < listeners.size() ; i++) {
    		listeners.get(i).updateTables(obj, acao);
    	}
    }
    
    public static JPanel getFundo() {
		return fundo;
	} 
    
    public void menuBarAddComponents(JMenuBar menuBar) {
    	
//    	menuBar.add(createMenu("Menu 1"));
//        menuBar.add(createMenu("Menu 2"));
//        menuBar.add(createMenu("Menu 3"));
    	
		JButton button = new JButton();
		button.setToolTipText("Adicionar Tabela");
		Image im = ImageUtils.redimensiona("src"+File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator+"addTable.png", 18, 18);
		ImageIcon icon = new ImageIcon(im);
		button.setIcon(icon);
		button.addActionListener(this);
		button.setActionCommand("button");

	    JButton button2 = new JButton();
	    button2.setToolTipText("Adicionar Restri��es");
		Image im2 = ImageUtils.redimensiona("src"+File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator+"relationship.png", 18, 18);
		ImageIcon icon2 = new ImageIcon(im2);
		button2.setIcon(icon2);
	    button2.addActionListener(this);
	    button2.setActionCommand("button2");
        
	    JButton button3 = new JButton();
	    button3.setToolTipText("Gerar SQL");
		Image im3 = ImageUtils.redimensiona("src"+File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator+"gerar.png", 18, 18);
		ImageIcon icon3 = new ImageIcon(im3);
		button3.setIcon(icon3);
	    button3.addActionListener(this);
	    button3.setActionCommand("button3");
        
	    menuBar.add(button);
        menuBar.add(button2);
        menuBar.add(button3);
    	
    }
    
    public void posicao(int x, int y, JPanel painel) {
		painel.setLocation(x + painel.getX(), y+painel.getY());
	}
    
    public Point calculateCoordinates() {
    	
    	Point location = null;
    	
    	for (int i = 0 ; i < listaTabelas.size() ; i++) {
    		
    		location = listaTabelas.get(i).getParent().getLocation();
//        	int width = listaTabelas.get(i).getWidth();
//        	int height = listaTabelas.get(i).getHeight();
        	
        	location.x += 10;
        	location.y += 10;
        	
    	}
    	
    	if (location == null) return new Point(0,0);
    	return location;
    	
    }
    
    public JMenu createMenu(String title) {
    	
        JMenu m = new HorizontalMenu(title);
        
        m.add("Menu item #1 in " + title);
        m.add("Menu item #2 in " + title);
        m.add("Menu item #3 in " + title);

        JMenu submenu = new HorizontalMenu("Submenu");
        submenu.add("Submenu item #1");
        submenu.add("Submenu item #2");
        m.add(submenu);

        return m;
        
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
        final JFrame frame = new JFrame("DBModel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(frame.getToolkit().getImage(getClass().getResource("/main/resources/images/logo.png")));

        //Create and set up the content pane.
//        PrincipalUI demo = new PrincipalUI();
        Container contentPane = frame.getContentPane();
        contentPane.setBackground(Color.WHITE); //contrasting bg
        contentPane.add(this.createMenuBar(), BorderLayout.LINE_START);

        JMenuBar mb = new JMenuBar();
        JMenu m = new JMenu("Menu");
        
        JMenuItem submenu = new JMenuItem("Sair");
        submenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				System.exit(0);
				
			}
		});
        
        m.add(submenu);
        
        JMenu m1 = new JMenu("Editar");
        
        final PrincipalUI p = this;
        JMenuItem submenu3 = new JMenuItem("Salvar Modelo");
        submenu3.addActionListener(new ActionListener() {
        	
        	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				p.updateListeners(null, Controller.SALVAR_MODELO);
				
			}
		});
        
        JMenuItem submenu4 = new JMenuItem("Carregar Modelo");
        submenu4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				p.updateListeners(null, Controller.CARREGAR_MODELO);
				
			}
		});
        
        JMenuItem submenu5 = new JMenuItem("Gerar SQL");
        submenu5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
//				JOptionPane.showMessageDialog(null, "Gerar SQL");
				p.updateListeners(null, Controller.GERAR_SQL);
				
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
				
				About.getInstance(frame);
				
			}
		});
        
        m2.add(submenu2);
        
        mb.add(m);
        mb.add(m1);
        mb.add(m2);
        contentPane.add(mb, BorderLayout.PAGE_START);
        
        fundo = FundoUI.getInstance();
        fundo.setLayout(null);
        fundo.setBackground(Color.WHITE);
        fundo.addMouseListener(this);
        
        scrollPane = new JScrollPane(fundo);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        frame.add(scrollPane);

        //Display the window.
        frame.setSize(700, 600);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    
    public TabelaUI createNewTableUI (Point point) {
        
    	JPanel tablePanel = createTablePanel(point);
	    
	    TabelaUI tabelaUI = new TabelaUI(this);
	    tablePanel.add(tabelaUI);
	    
	    this.setTableUI_ID(tabelaUI);
        
        this.addTablePanelToMainPanel(tablePanel);
        
        return tabelaUI;
    	
    }
    
    public void setTableUI_ID(TabelaUI tabelaUI) {
    	count++;
	    tabelaUI.getTabela().setId(count+"");
        listaTabelas.add(tabelaUI);
    }
    
    public JPanel createTablePanel(Point point) {
    	
    	JPanel tablePanel = new JPanel();
    	
    	tablePanel.setLayout(new GridLayout());
    	tablePanel.setSize(new Dimension(100, 100));
    	tablePanel.setLocation(point);
    	tablePanel.setBackground(Color.WHITE);
    	tablePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    	
    	return tablePanel;
    	
    }
    
    public void addTablePanelToMainPanel(JPanel tablePanel) {
    	fundo.add(tablePanel);
        fundo.setComponentZOrder(tablePanel, 0);
        fundo.updateUI();
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
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
			createNewTableUI(point);

			this.updateListeners(listaTabelas.get(listaTabelas.size()-1).getTabela(), Controller.ADICIONAR);
			
		} else if (command.equalsIgnoreCase("button2")) {
			
			// Pegar os duas tabelas escolhidas e criar a linha entre elas.
			this.updateListeners(null, Controller.RELACAO_TABELAS);
			
		} else if (command.equalsIgnoreCase("button3")) {
		
			this.updateListeners(null, Controller.GERAR_SQL);
			
		}
		
	}

	@Override
	public void drawLines(String objectName, String t1, String t2, String cardinalidade, List<String> primaryAtributoName, List<String> foreignAtributoName) {
		
		/*
		 * Procuro pelas tabelas cujos nomes s�o iguais aos par�metros passados
		 * e em seguida desenho uma linha ligando-as.
		 */
		
		
		// IMPORTANTE, FALTA CONSIDERAR OS NOMES DOS ATRIBUTOS SELECIONADOS: String primaryAtributoName, String foreignAtributoName
		
		TabelaUI tabela1 = null;
		TabelaUI tabela2 = null;
		
		for (int i = 0 ; i < listaTabelas.size() ; i++) {
			if (listaTabelas.get(i).getTabela().getTitulo() == t1) {
				tabela1 = listaTabelas.get(i);
			} else if (listaTabelas.get(i).getTabela().getTitulo() == t2) {
				tabela2 = listaTabelas.get(i);
			}
		}
		
		// J� tenho tudo que preciso, agora � s� desenhar!	
		
		Relationship rs = new Relationship();
		rs.setNome(objectName);
		rs.setLeftTable(tabela1.getTabela());
		rs.setRightTable(tabela2.getTabela());
		rs.setCardinalidade(cardinalidade);
		
		
		
		if (cardinalidade.equalsIgnoreCase("N:N")) {
			
			if (tabela1.getTabela().getChavesPrimaria().isEmpty() || tabela2.getTabela().getChavesPrimaria().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Nas rela��es N:N � obrigat�rio que as duas tabelas tenham um atributo definido como chave prim�ria");
			} else {
			
				TabelaUI temp = this.createNewTableUI(new Point(0,0));
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
				JOptionPane.showMessageDialog(null, "Para adicionar um relacionamento ao menos uma das tabelas deve conter um atributo definido como chave prim�ria.");
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
				JOptionPane.showMessageDialog(null, "Para adicionar um relacionamento ao menos uma das tabelas deve conter um atributo definido como chave prim�ria.");
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
				JOptionPane.showMessageDialog(null, "Para adicionar um relacionamento ao menos uma das tabelas deve conter um atributo definido como chave prim�ria.");
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
		
		this.updateListeners(rs, Controller.ADD_RELATIONSHIP);
		
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
			this.createNewTableUI(new Point(e.getX(), e.getY()));
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