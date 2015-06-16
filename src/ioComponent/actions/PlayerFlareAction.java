package ioComponent.actions;

import ioComponent.interfaces.IActionPlayerMapVisual;
import player.IPlayerMax;
import visual.interfaces.IMapVisual;

public class PlayerFlareAction implements IActionPlayerMapVisual {
	private int key;
	private IPlayerMax player;
	private IMapVisual map;

	@Override
	public int getKey() {
		return key;
	}

	@Override
	public void setKey(int key) {
		this.key = key;
	}

	@Override
	public void execute() {
		if(player.useFlare()){
			map.flareVisual();
			player.checkLighter();
		}
		
	}

	@Override
	public void connect(IPlayerMax player) {
		this.player = player;
	}
	
	@Override
	public void connect(IMapVisual map) {
		this.map = map;
	}


}