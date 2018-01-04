

import javax.swing.JFrame;

public class Window
{
	
	public final JFrame window = new JFrame();
	
	private String TITLE;
	private boolean FULLSCREEN;
	private Resolution RESOLUTION;
	private StateManager MANAGER;
	
	
	//CONSTRUCTOR
	public Window()
	{
		this("Untitled", false, new Resolution(), new StateManager());
	}
	
	public Window(String title, boolean isFullScreen, Resolution resolution, StateManager manager)
	{
	
		this.TITLE = title;
		this.FULLSCREEN = isFullScreen;
		this.RESOLUTION = resolution;
		this.MANAGER = manager;
	}
	
	public void update()
	{
		
		window.setTitle(this.TITLE);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if(this.FULLSCREEN)
		{
			
			window.setExtendedState(JFrame.MAXIMIZED_BOTH);
			window.setUndecorated(true);
			
			this.RESOLUTION = Resolution.getFullScreen(Resolution.RESOLUTION_16x9);
		}
		
		window.add(new WindowPanel(this.RESOLUTION, this.MANAGER));
		
		window.setResizable(false);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
	}
	
	public void run()
	{
		
		update();
		
		window.setVisible(true);
	}
	
	public void setTitle(String title)
	{
		
		this.TITLE = title;
	}
	
	public void setFullScreen(boolean isFullScreen)
	{
		
		this.FULLSCREEN = isFullScreen;
	}
	
	public StateManager getManager()
	{
		
		return this.MANAGER;
	}
	
	public int getWidth()
	{
		
		return RESOLUTION.getWidthScaled();
	}
	
	public int getHeight()
	{
		
		return RESOLUTION.getHeightScaled();
	}
}