package gameController;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import items.interfaces.IItemManagement;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import monster.Interfaces.IMonster;
import player.IPlayerMax;
import saveGame.IsaveManagement;

public class TelaInicial extends JFrame implements ActionListener {

	private IMonster compMonster;
	private IPlayerMax compPlayer;
	private IItemManagement compItemManagement;
	private IsaveManagement svg;
	private JLabel imagemFundo;
	private JButton novaPartida, carregarPartida;
	JPanel painel;
	
	public TelaInicial(IMonster compMonster, IPlayerMax compPlayer, IItemManagement compItemManagement, IsaveManagement svg) {
		super("Cave Keeper");
		this.compMonster = compMonster;
		this.compPlayer = compPlayer;
		this.compItemManagement = compItemManagement;
		this.svg = svg;
		
		Icon imagem = new ImageIcon("resources/fundo/fundo.png");
		imagemFundo = new JLabel(imagem);
		imagemFundo.setBounds(0, 0, 640, 730);
		
		Icon novoJogoB = new ImageIcon("resources/fundo/start.png");
		novaPartida = new JButton(novoJogoB);
		novaPartida.setBounds(250, 750, 149, 50);
		
		Icon carregarJogoB = new ImageIcon("resources/fundo/cont.png");
		carregarPartida = new JButton(carregarJogoB);
		carregarPartida.setBounds(250, 810, 149, 50);
		
		ButtonHandler handler = new ButtonHandler(novaPartida, carregarPartida);
		handler.connect(compMonster, compPlayer, compItemManagement, svg);
		
		novaPartida.addActionListener(this);
		carregarPartida.addActionListener(this);
		
		this.setLayout(null);
		add(imagemFundo);
		add(novaPartida);
		add(carregarPartida);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640,880);
		setVisible(true);
		setResizable(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == novaPartida) {
			GameController.getSharedInstance().conectar(compMonster, compPlayer, compItemManagement, svg, 1/*, compMapVisual*/);

		}
		
		if(e.getSource() == carregarPartida) {
			GameController.getSharedInstance().loadFromDeserialization(svg);
			setVisible(false);
		}

	}
}