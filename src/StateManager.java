

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

public class StateManager
{

	public int currentState;
	public List<State> stateArray;
	
	public StateManager()
	{
		
		this(State.instance);
	}
	
	public StateManager(State... states)
	{
		
		this(0, states);
	}
	
	public StateManager(List<State> states)
	{
		
		this(0, states);
	}
	
	public StateManager(int start, State... states)
	{
		
		this(start, Arrays.asList(states));
	}
	
	public StateManager(int start, List<State> states)
	{
		
		stateArray = states;
		
		currentState = start;
	}
	
	public void setState(int state)
	{
		
		currentState = state;
		
		stateArray.get(currentState).init();
	}
	
	public void tick()
	{
		
		stateArray.get(currentState).tick();
	}
	
	public void render(Graphics2D graphics)
	{
		
		stateArray.get(currentState).render(graphics);
	}
	
	
	//KEY LISTENER
	public void keyTyped(int i)
	{
		
		stateArray.get(currentState).keyTyped(i);
	}
	
	public void keyPressed(int i)
	{
		
		stateArray.get(currentState).keyPressed(i);
	}
	
	public void keyReleased(int i)
	{
		
		stateArray.get(currentState).keyReleased(i);
	}
	
	
	//MOUSE LISTENER
	public void mouseClicked(MouseEvent e)
	{
		
		stateArray.get(currentState).mouseClicked(e);
	}
	
	public void mousePressed(MouseEvent e)
	{
		
		stateArray.get(currentState).mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e)
	{
		
		stateArray.get(currentState).mouseReleased(e);
	}
	
	public void mouseEntered(MouseEvent e)
	{
		
		stateArray.get(currentState).mouseEntered(e);
	}
	
	public void mouseExited(MouseEvent e)
	{
		
		stateArray.get(currentState).mouseExited(e);
	}
	
	public void mouseDragged(MouseEvent e)
	{
		
		stateArray.get(currentState).mouseDragged(e);
	}
	
	public void mouseMoved(MouseEvent e)
	{
		
		stateArray.get(currentState).mouseMoved(e);
	}
}