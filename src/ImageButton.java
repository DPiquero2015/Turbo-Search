

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class ImageButton
{
	
	private static int index = 0;
	
	private final Image icon;
	private final Image hover;
	private final Image pressed;
	
	public final int ID;
	
	private PressState state;
	
	private boolean isVisible;
	private boolean isDown;
	
	private int x;
	private int y;

	public ImageButton(Image icon, Image hover, Image pressed)
	{
		
		this.icon = icon;
		this.hover = hover;
		this.pressed = pressed;
		
		this.ID = index++;
		
		this.isVisible = true;
		this.isDown = false;

		this.state = PressState.NORMAL;
	}
	
	public void render(Graphics2D graphics)
	{
		
		if(isVisible) getCurrentIcon().render(graphics, x, y);
	}
	
	public void mouseMoved(MouseEvent e)
	{
		
		if(isInBounds(e) && !isDown) state = PressState.HOVER;
		else if(isDown) state = PressState.CLICKED;
		else state = PressState.NORMAL;
	}
	
	public void mousePressed(MouseEvent e)
	{
		
		if(isInBounds(e) && e.getButton() == MouseEvent.BUTTON1)
		{
			
			isDown = true;
			state = PressState.CLICKED;
		}
		
		else
		{
			
			state = PressState.NORMAL;
		}
	}
	
	public boolean mouseReleased(MouseEvent e)
	{
		
		if(isInBounds(e) && e.getButton() == MouseEvent.BUTTON1)
		{
			
			isDown = false;
			state = PressState.HOVER;
			return true;
		}
		
		else
		{
			
			state = PressState.NORMAL;
		}
		
		return false;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setVisible(boolean visible)
	{
		
		this.isVisible = visible;
	}
	
	private Image getCurrentIcon()
	{
		
		switch(state)
		{
			
			case NORMAL:
				return icon;
			case HOVER:
				return hover;
			case CLICKED:
				return pressed;
		}
		
		return icon;
	}
	
	private boolean isInBounds(MouseEvent e)
	{
		
		int buffer = 5;
		
		int xBound = x + getCurrentIcon().retrieve().getWidth();
		int yBound = y + getCurrentIcon().retrieve().getHeight();
		
		boolean inX = x + buffer <= e.getX() && e.getX() <= xBound - buffer;
		boolean inY = y + buffer <= e.getY() && e.getY() <= yBound - buffer;
		
		return inX && inY;
	}
	
	private static enum PressState
	{
		
		NORMAL,
		HOVER,
		CLICKED
	}
}