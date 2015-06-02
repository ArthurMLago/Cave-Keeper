package items.inventory;

import visual.interfaces.IAction;
import visual.interfaces.IMapVisual;
import anima.component.IRequires;
import anima.component.ISupports;
import anima.component.InterfaceType;

public class Flash extends GeneralItems implements IAction{
	private int key;
	private IMapVisual map;
	
	public Flash(String name, int number) {
		super(name,number);
	}
	

	public int getKey() {
		return key;
	}

	
	public void setKey(int key) {
		this.key = key;
	}

	
	public void execute() {
		System.out.println("Execute Flare!!");
		map.flareVisual();
	}

	
	public void connect(IMapVisual map) {
		this.map = map;
	}


	@Override
	public int addRef() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String getInstanceId() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <T extends ISupports> T queryInterface(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <T extends ISupports> T queryInterface(String arg0,
			InterfaceType arg1) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <T extends ISupports> IRequires<T> queryReceptacle(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int release() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void effect() {
		map.flareVisual();

	}
}
