package visual;

import ioComponent.interfaces.IIoComponent;
import items.interfaces.IItemManagement;

import java.util.logging.Level;
import java.util.logging.Logger;

import map.interfaces.IGameMap;
import monster.Interfaces.IMonster;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import player.IPlayerMax;
import visual.interfaces.IAudioEffect;
import visual.interfaces.IMapVisual;
import anima.annotation.Component;
import anima.component.base.ComponentBase;

@Component(id = "<http://cave.com/visual.MapVisual>", provides = { "<http://cave.com/visual.IMapVisual>" })
public class MapVisual extends ComponentBase implements IMapVisual,
		IAudioEffect {
	private SlickMap compositeMap;
	private AppGameContainer agc;
	public static int SIZEIMAGE = 32;
	private IGameMap gameMap;
	private IPlayerMax player;
	private IMonster monsters;
	private IIoComponent ios;
	private IItemManagement items;
	
	public MapVisual(IGameMap gm, IPlayerMax pm, IMonster mon, IIoComponent io, IItemManagement item) {
		this.gameMap = gm;
		this.player = pm;
		this.monsters = mon;
		this.ios = io;
		this.items = item;
		compositeMap = new SlickMap("Cave's Keeper", gameMap, player, monsters, ios, items);
	}

	@Override
	public void flareVisual() {
		compositeMap.flare();
	}

	@Override
	public void playEffect(float gain, String type) {
		if ("footstep".compareTo(type) == 0)
			compositeMap.playFootstep(gain);
	}

	@Override
	public void shootDirection(char direction) {
		compositeMap.shootDirection(direction);
	}

	@Override
	public void changePlayerFacing(char facing) {
		compositeMap.faceSprite(facing);
	}

	@Override
	public void start() {
		try {
			agc = new AppGameContainer(compositeMap);
			agc.setDisplayMode(gameMap.getLimitX() * SIZEIMAGE,
					(gameMap.getLimitY()+2) * SIZEIMAGE, false);
			agc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void message(String messageTxt) {
		compositeMap.setMessage(messageTxt);
	}

	@Override
	public void end() {
		agc.exit();
	}
	
	
}
