

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class WindowPanel extends JPanel implements Runnable, KeyListener, MouseInputListener
{

	private static final long serialVersionUID = 1L;
	
	private final Resolution resPanel;
	
	
	//THREAD
	private Thread thread;
	private boolean isRunning;
	private int fps = 120;
	private long targetTime = 1000 / fps;
	public static long BEGIN = System.currentTimeMillis();
	
	
	//IMAGE
	private BufferedImage image;
	private Graphics2D g2d;
	
	
	//StateManager
	private StateManager stateManager;
	
	
	//CONSTRUCTOR
	public WindowPanel(Resolution res, StateManager manager)
	{
		
		super();
		
		this.resPanel = res;
		this.stateManager = manager;
		
		this.setPreferredSize(res.getDimensionScaled());
		this.setFocusable(true);
		this.requestFocus();
	}
	
	public void addNotify()
	{
		
		super.addNotify();
		
		if(thread == null)
		{
			
			thread = new Thread(this);
			
			this.addKeyListener(this);
			this.addMouseListener(this);
			this.addMouseMotionListener(this);
			
			thread.start();
		}
	}
	
	
	//INIT
	private void init()
	{
		
		image	= new BufferedImage(resPanel.WIDTH, resPanel.HEIGHT, BufferedImage.TYPE_INT_RGB);
		g2d		= (Graphics2D) image.getGraphics();
		
		isRunning = true;
	}
	
	
	//RUN
	public void run()
	{
		
		this.init();
		
		long start;
		long elapsed;
		long wait;
		
		
		//RUNTIME LOOP
		while(isRunning)
		{
			
			start = System.nanoTime();
			
			this.tick();
			this.render();
			this.renderToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - (elapsed / 1000000);
			wait = (wait < 0 ? 5 : wait);
			
			try
			{
				
				Thread.sleep(wait);
			}
			
			catch(Exception e)
			{
				
				e.printStackTrace();
			}
		}
	}
	
	
	//TICKS
	private void tick()
	{
		
		stateManager.tick();
	}
	
	private void render()
	{
		
		stateManager.render(g2d);
	}
	
	private void renderToScreen()
	{
		
		Graphics g = this.getGraphics();
		
		g.drawImage(image, 0, 0, resPanel.getWidthScaled(), resPanel.getHeightScaled(), null);
		g.dispose();
	}
	
	
	//KEY LISTENER
	public void keyTyped(KeyEvent e)
	{
		
		stateManager.keyTyped(e.getKeyCode());
	}
	
	public void keyPressed(KeyEvent e)
	{
		
		stateManager.keyPressed(e.getKeyCode());
	}
	
	public void keyReleased(KeyEvent e)
	{
		
		stateManager.keyReleased(e.getKeyCode());
	}
	
	
	//MOUSE LISTENER
	public void mouseClicked(MouseEvent e)
	{
		
		stateManager.mouseClicked(e);
	}
	
	public void mousePressed(MouseEvent e)
	{
		
		stateManager.mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e)
	{
		
		stateManager.mouseReleased(e);
	}
	
	public void mouseEntered(MouseEvent e)
	{
		
		stateManager.mouseEntered(e);
	}
	
	public void mouseExited(MouseEvent e)
	{
		
		stateManager.mouseExited(e);
	}
	
	public void mouseDragged(MouseEvent e)
	{
		
		stateManager.mouseDragged(e);
	}
	
	public void mouseMoved(MouseEvent e)
	{
		
		stateManager.mouseMoved(e);
	}
}