package gameController;

import items.interfaces.IItemManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import monster.Interfaces.IMonster;
import player.IPlayerMax;
import saveGame.IsaveManagement;

public class ButtonHandler implements ActionListener {

	private IMonster compMonster;
	private IPlayerMax compPlayer;
	private IItemManagement compItemManagement;
	private IsaveManagement svg;
	private JButton novaPartida, carregarPartida;
	
	public ButtonHandler(JButton np, JButton cp){
		this.novaPartida = np;
		this.carregarPartida = cp;
	}
	
	public void connect(IMonster compMonster, IPlayerMax compPlayer, IItemManagement compItemManagement, IsaveManagement svg /*, IMapVisual compMapVisual*/) {
		this.compMonster = compMonster;
		this.compPlayer = compPlayer;
		this.compItemManagement = compItemManagement;
		this.svg = svg;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == novaPartida) {
			GameController.getSharedInstance().conectar(compMonster, compPlayer, compItemManagement, svg, 1/*, compMapVisual*/);
		}
		
		if(e.getSource() == carregarPartida) {
			GameController.getSharedInstance().loadFromDeserialization(svg);
		}

	}

}
