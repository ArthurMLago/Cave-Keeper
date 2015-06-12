package ioComponent;

import org.newdawn.slick.Input;

import player.IPlayerMax;
import visual.MapVisual;
import visual.interfaces.IMapVisual;
import anima.component.base.ComponentBase;
import ioComponent.PlayerDownAction;

public class IoComponent extends ComponentBase {

	private IMapVisual compVisual;
	private IPlayerMax compPlayer;
	
	private IActionPlayerMapVisual playerFlare, playerSetLighter, playerShootDown, playerShootLeft, playerShootRight, playerShootUp;
	private IActionPlayer playerDown, playerLeft, playerRight, playerUp, playerStick, playerWait, playerLighter;
	private ActionHandler handler;
	
	public void connect(IMapVisual mv, IPlayerMax p) {
		this.compVisual = mv;
		this.compPlayer = p;
	}
	
	public void setActions() {
	
		playerDown = new PlayerDownAction();
		playerDown.setKey(Input.KEY_DOWN);
		playerDown.connect(compPlayer);

		playerLeft = new PlayerLeftAction();
		playerLeft.setKey(Input.KEY_LEFT);
		playerLeft.connect(compPlayer);

		playerRight = new PlayerRightAction();
		playerRight.setKey(Input.KEY_RIGHT);
		playerRight.connect(compPlayer);

		playerUp = new PlayerUpAction();
		playerUp.setKey(Input.KEY_UP);
		playerUp.connect(compPlayer);

		playerShootDown = new PlayerShootDownAction();
		playerShootDown.setKey(Input.KEY_S);
		playerShootDown.connect(compPlayer);
		playerShootDown.connect(compVisual);

		playerShootLeft = new PlayerShootLeftAction();
		playerShootLeft.setKey(Input.KEY_A);
		playerShootLeft.connect(compPlayer);
		playerShootLeft.connect(compVisual);

		playerShootRight = new PlayerShootRightAction();
		playerShootRight.setKey(Input.KEY_D);
		playerShootRight.connect(compPlayer);
		playerShootRight.connect(compVisual);

		playerShootUp = new PlayerShootUpAction();
		playerShootUp.setKey(Input.KEY_W);
		playerShootUp.connect(compPlayer);
		playerShootUp.connect(compVisual);

		playerFlare = new PlayerFlareAction();
		playerFlare.setKey(Input.KEY_R);
		playerFlare.connect(compPlayer);
		playerFlare.connect(compVisual);

		playerStick = new PlayerStickAction();
		playerStick.setKey(Input.KEY_E);
		playerStick.connect(compPlayer);

		playerWait = new PlayerWaitAction();
		playerWait.setKey(Input.KEY_G);
		playerWait.connect(compPlayer);
		
		playerLighter = new PlayerSetLighterAction();
		playerLighter.setKey(Input.KEY_L);
		playerLighter.connect(compPlayer);
		
		handler = new ActionHandler();
		handler.connect(playerDown);
		handler.connect(playerUp);
		handler.connect(playerLeft);
		handler.connect(playerRight);
		handler.connect(playerShootDown);
		handler.connect(playerShootUp);
		handler.connect(playerShootLeft);
		handler.connect(playerShootRight);
		handler.connect(playerFlare);
		handler.connect(playerStick);
		handler.connect(playerWait);
		handler.connect(playerLighter);

	}
}
