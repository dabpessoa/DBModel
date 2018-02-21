package me.dabpessoa.gui;

import me.dabpessoa.bean.Atributo;
import me.dabpessoa.bean.Tabela;
import me.dabpessoa.bean.TabelaModelo;
import me.dabpessoa.business.DBModelManager;
import me.dabpessoa.business.listeners.EditListener;
import me.dabpessoa.gui.components.*;
import me.dabpessoa.util.ImageUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TabelaUI extends JPanel implements MouseListener, MouseMotionListener, EditListener {
	
	private static final long serialVersionUID = 1L;
	private GridBagConstraints c = new GridBagConstraints();
	private Tabela tabela;
	private TabelaUITitulo titulo;
	private TabelaUIPKs corpoPK;
	private TabelaUIFKs corpoFK;
	private TabelaUINKs corpoNK;
	private TabelaUICorpo corpo;
	private PrincipalUI principalUI;
	private Point mousePressedPosition;
	
	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new TabelaUI(new PrincipalUI(new DBModelManager())));
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public TabelaUI(PrincipalUI principalUI) {
		super();
		this.principalUI = principalUI;
		this.tabela = new Tabela();
		setBackground(Color.WHITE);
		initGUI();
	}
	
	public TabelaUI() {this(null);}
	
	public void setPrincipalUI(PrincipalUI principalUI) {
		this.principalUI = principalUI;
	}

	public void atualizarObjetoTabela() {
		if (tabela == null) tabela = new Tabela();
		if (tabela.getModelo() == null) tabela.setModelo(new TabelaModelo());
		tabela.getModelo().setAltura((double)getParent().getHeight());
		tabela.getModelo().setLargura((double)getParent().getWidth());
		tabela.getModelo().setPosicaoX(getParent().getLocation().getX());
		tabela.getModelo().setPosicaoY(getParent().getLocation().getY());
	}

	public void initGUI() {
		try {
			setBorder(BorderFactory.createBevelBorder(0)); 
			GridBagLayout gbLayout = new GridBagLayout();
			setLayout(gbLayout);
			this.setBackground(Color.GRAY);
	
			// dividir tabelaUI em dois: titulo e corpo
			
			BufferedImage fundo = null;  
	        try { fundo = ImageIO.read(new File("src"+File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator+"titleBack.png")); }
	        catch (IOException e1){e1.printStackTrace();};

			titulo = new TabelaUITitulo(new ImageIcon(fundo).getImage());
			titulo.addMouseListener(this);
			titulo.addMouseMotionListener(this);
			this.setTitle("Tabela_"+((principalUI.getManager().getTabelas().size())+1));
			
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 0;
			c.fill = GridBagConstraints.HORIZONTAL;
			
			add(titulo, c);
			
			corpo = new TabelaUICorpo();
			
			corpoPK = new TabelaUIPKs();
			corpoNK = new TabelaUINKs();
			corpoFK = new TabelaUIFKs();
			
			
			GridBagConstraints gb = new GridBagConstraints();
			
			Insets is = new Insets(0, 3, 0, 3);
			
			gb.weightx = 0;
			gb.weighty = 0;
			gb.insets = is;
			gb.gridx = 0;
			gb.gridy = 0;
			gb.gridwidth = 1;
			gb.gridheight = 1;
			gb.fill = GridBagConstraints.BOTH;
			
			corpo.add(corpoPK, gb);
			
			gb.weightx = Tabela.LARGURA_PADRAO;
			gb.weighty = Tabela.ALTURA_PADRAO;
			gb.insets = is;
			gb.gridx = 0;
			gb.gridy = 1;
			gb.gridwidth = 1;
			gb.gridheight = 1;
			gb.fill = GridBagConstraints.BOTH;
			
			corpo.add(corpoNK, gb);
			
			gb.weightx = 0;
			gb.weighty = 0;
			gb.insets = is;
			gb.gridx = 0;
			gb.gridy = 2;
			gb.gridwidth = 1;
			gb.gridheight = 1;
			gb.fill = GridBagConstraints.BOTH;
			
			corpo.add(corpoFK, gb);
			
			
			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 1;
			c.ipady = 0;
			
			add(corpo, c);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} 
	
	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getClickCount() == 2) {
			TableEditUI tableEdit = TableEditUI.getInstance(tabela);
			tableEdit.setListener(this);
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			
			final TabelaUI t = this;
			
			 JPopupMenu menu = new JPopupMenu();  
	            JMenuItem cliqueme = new JMenuItem("Remove");
	            JMenuItem editTable = new JMenuItem("Editar");
	  
	            cliqueme.addActionListener(new ActionListener() {  
	                public void actionPerformed(ActionEvent ae) {  
	                	
	                	JPanel panel = (JPanel) t.getParent().getParent();
	                	panel.remove(0);
	                	panel.updateUI();
	                	principalUI.getListaTabelas().remove(t);
	                	
	                	removerRelationships(t, panel);
	                	
	                }  
	            });
	            
	            editTable.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
						TableEditUI tableEdit = TableEditUI.getInstance(tabela);
						tableEdit.setListener(t);
						
					}
				});
	  
	            menu.add(editTable);
	            menu.add(cliqueme);
	  
	            menu.show(this, e.getX(), e.getY());
		}
	}
	
	public void removerRelationships(TabelaUI t, JPanel panel) {
		
		int index = -1;
		
		while (((FundoUI)panel).getT1s().indexOf(t) != -1) {
			index = ((FundoUI)panel).getT1s().indexOf(t);
    		((FundoUI)panel).getT1s().remove(index);
    		((FundoUI)panel).getT2s().remove(index);
		}
		while (((FundoUI)panel).getT2s().indexOf(t) != -1) {
			index = ((FundoUI)panel).getT2s().indexOf(t);
    		((FundoUI)panel).getT1s().remove(index);
    		((FundoUI)panel).getT2s().remove(index);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		((JPanel)getParent()).setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		((JPanel)getParent()).setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	}

	@Override
	public void mousePressed(MouseEvent e) {

		// Marcando a posição do mouse no momento do click.
		if (mousePressedPosition == null) mousePressedPosition = new Point(e.getX(), e.getY());
		mousePressedPosition.setLocation(e.getX(), e.getY());

		getParent().getParent().setComponentZOrder(getParent(), 0);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		int w,h;
		boolean changed = false;
		
		w = getParent().getParent().getWidth();
		h = getParent().getParent().getHeight();
		Dimension originalArea = new Dimension(w, h);
		
		w = this.getParent().getX() + this.getWidth();
		h = this.getParent().getY() + this.getHeight();
		Dimension newArea = new Dimension(w, h);
		
		Dimension auxOriginal = new Dimension();
		auxOriginal.width = originalArea.width;
		auxOriginal.height = originalArea.height;
		
		if (newArea.width > originalArea.width || newArea.height > originalArea.height) {

			if (newArea.width > originalArea.width) {
				originalArea.width = newArea.width; changed=true;
			}

			if (newArea.height > originalArea.height) {
				originalArea.height = newArea.height; changed=true;
			}
			
		}
		
		Rectangle rect = new Rectangle( this.getParent().getX(), this.getParent().getY(), this.getParent().getWidth(), this.getParent().getHeight());
		((JPanel)getParent().getParent()).scrollRectToVisible(rect);
		
		if (changed) {
			//Update client's preferred size because
			//the area taken up by the graphics has
			//gotten larger or smaller (if cleared).
			((JPanel)getParent().getParent()).setPreferredSize(originalArea);			
			
			//Let the scroll pane know to update itself
			//and its scrollbars.
			((JPanel)getParent().getParent()).revalidate();
			
		}
		
		((JPanel)getParent()).repaint();
		
	}

	@Override
	public void updateTable(Tabela tabela) {
		tabela.setId(this.tabela.getId());
		/*
		 *  Aqui j� tenho o objeto tabela preenchido, s� falta ent�o
		 *  coloc�-lo dentro do panel e atualizar este panel.
		 */
		
		ImageIcon iconT = new ImageIcon(ImageUtils.redimensiona("src"+File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator+"titulo1.png", 16, 16));
		JLabel label = new JLabel(tabela.getTitulo(), iconT, JLabel.LEFT);
		
		if (label.getText().length() != 0) {
			titulo.remove(0);
			titulo.add(label);
		}
		
		corpoNK.removeAll();
		corpoPK.removeAll();
		corpoFK.removeAll();
		for (int i = 0 ; i < tabela.getAtributos().size() ; i++) {
			
			ImageIcon icon;
			if (tabela.getAtributos().get(i).isChavePrimaria()) {
				icon = new ImageIcon(ImageUtils.redimensiona("src"+File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator+"pk.png", 8, 8));
				JLabel jl = new JLabel(tabela.getAtributos().get(i).getNome() + ": " + tabela.getAtributos().get(i).getTipo().getDescricao(), icon, JLabel.LEFT);
				corpoPK.add(jl);
			} else if (tabela.getAtributos().get(i).isUniqueKey()){
				icon = new ImageIcon(ImageUtils.redimensiona("src"+File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator+"unique.png", 8, 8));
				JLabel jl = new JLabel(tabela.getAtributos().get(i).getNome() + ": " + tabela.getAtributos().get(i).getTipo().getDescricao(), icon, JLabel.LEFT);
				corpoNK.add(jl);
			} else if (tabela.getAtributos().get(i).getIntegritRestriction() != null){
				icon = new ImageIcon(ImageUtils.redimensiona("src"+File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator+"fk.png", 8, 8));
				JLabel jl = new JLabel(tabela.getAtributos().get(i).getNome() + ": " + tabela.getAtributos().get(i).getTipo().getDescricao(), icon, JLabel.LEFT);
				corpoFK.add(jl);
			} else {
				icon = new ImageIcon(ImageUtils.redimensiona("src"+File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator+"atributoIcon.png", 8, 8));
				JLabel jl = new JLabel(tabela.getAtributos().get(i).getNome() + ": " + tabela.getAtributos().get(i).getTipo().getDescricao(), icon, JLabel.LEFT);
				corpoNK.add(jl);
			}
			
		}
		
		titulo.updateUI();
		corpoNK.updateUI();
		
		this.tabela = tabela;
		this.principalUI.getManager().atualizarTabela(tabela);
		
		this.getParent().setSize(this.getParent().getPreferredSize());
		
	}
	
	public void setTitle(String t) {
		
		if (tabela.getTitulo() != null && tabela.getTitulo().length() != 0) {
			titulo.remove(0);
		}

		ImageIcon icon = new ImageIcon(ImageUtils.redimensiona("src"+File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator+"titulo1.png", 16, 16));
		JLabel label = new JLabel(t, icon, JLabel.LEFT);
		titulo.add(label);
		
		this.tabela.setTitulo(t);
		
	}
	
	public void setForeingKeys(List<Atributo> atributos) {
		
//		tabela.setAtributos(atributos);
		for (int i = 0 ; i < atributos.size() ; i++) {
			tabela.getAtributos().add(atributos.get(i));
		}
		
		ImageIcon icon;
		for (int i = 0 ; i < atributos.size() ; i++) {
			icon = new ImageIcon(ImageUtils.redimensiona("src"+File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator+"fk.png", 8, 8));
			JLabel jl = new JLabel(atributos.get(i).getNome() + ": " + atributos.get(i).getTipo().getDescricao(), icon, JLabel.LEFT);
			corpoFK.add(jl);
			
//			JLabel jl = new JLabel(atributos.get(i).getNome() + ": " + atributos.get(i).getTipo().getNome(), icon, JLabel.LEFT);
//			corpoFK.add(jl);
		}
		
		this.updateUI();
		
	}
	
	public void setPrimaryKeys(List<Atributo> atributos) {
		
//		tabela.setAtributos(atributos);
		for (int i = 0 ; i < atributos.size() ; i++) {
			tabela.getAtributos().add(atributos.get(i));
		}
		
		ImageIcon icon;
		for (int i = 0 ; i < atributos.size() ; i++) {
			icon = new ImageIcon(ImageUtils.redimensiona("src"+File.separator+"main"+File.separator+"resources"+File.separator+"images"+File.separator+"pk.png", 8, 8));
			JLabel jl = new JLabel(atributos.get(i).getNome() + ": " + atributos.get(i).getTipo().getDescricao(), icon, JLabel.LEFT);
			corpoPK.add(jl);

//			JLabel jl = new JLabel(atributos.get(i).getNome() + ": " + atributos.get(i).getTipo().getNome(), icon, JLabel.LEFT);
//			corpoPK.add(jl);
		}
		
		this.updateUI();
		
	}
	
	public void setNotKeys(List<Atributo> atributos) {
		
//		tabela.setAtributos(atributos);
		for (int i = 0 ; i < atributos.size() ; i++) {
			tabela.getAtributos().add(atributos.get(i));
		}
		
		for (int i = 0 ; i < atributos.size() ; i++) {
			JLabel jl = new JLabel(atributos.get(i).getNome() + ": " + atributos.get(i).getTipo().getDescricao());
			corpoNK.add(jl);
		}
		
		this.updateUI();
		
	}

	public Tabela getTabela() {
		return tabela;
	}

	public TabelaUITitulo getTitulo() {
		return titulo;
	}

	public void setTitulo(TabelaUITitulo titulo) {
		this.titulo = titulo;
	}

	public TabelaUICorpo getCorpo() {
		return corpo;
	}

	public void setCorpo(TabelaUICorpo corpo) {
		this.corpo = corpo;
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		int mouseOffsetX = Math.abs((int) (mousePressedPosition.getX() - getParent().getX()));
		int mouseOffsetY = Math.abs((int) (mousePressedPosition.getY() - getParent().getY()));

		getParent().setLocation(e.getX() + mouseOffsetX, e.getY() + mouseOffsetY);
		FundoUI temp = (FundoUI) getParent().getParent();
		temp.updateUI();

	}

	@Override
	public void mouseMoved(MouseEvent e) {}
	
}
