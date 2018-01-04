

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public abstract class State
{
	
	public static final State instance = new State()
	{
		
		public void init() {}
		public void tick() {}
		public void render(Graphics2D graphics) {}
	};

	protected StateManager stateManager;
	
	public State() {}
	
	public abstract void init();
	public abstract void tick();
	public abstract void render(Graphics2D graphics);
	
	
	//KEY
	public void keyTyped(int i) {}
	public void keyPressed(int i) {}
	public void keyReleased(int i) {}
	
	
	//MOUSE
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
}