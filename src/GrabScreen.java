import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.lament.visual.Image;
import com.lament.visual.ImageButton;
import com.lament.visual.Resolution;
import com.lament.window.State;
import com.lament.window.StateManager;
import com.lament.window.Window;

public class GrabScreen extends State
{
	
	public static Window window = new Window("Untitled", true, new Resolution(), new StateManager(new GrabScreen()));
	
	private static Image screenshot;
	
	private static Robot robot;
	private static Dimension dim = new Dimension();
	
	private Point start = new Point();
	private Point current = new Point();
	private Point end = new Point();
	
	private boolean isDown = false;
	private boolean isDone = false;
	
	private static ImageButton btnGood = new ImageButton(	new Image("/btn_g_normal.png"),
															new Image("/btn_g_hover.png"),
															new Image("/btn_g_pressed.png"));
	private static ImageButton btnBad = new ImageButton(	new Image("/btn_b_normal.png"),
															new Image("/btn_b_hover.png"),
															new Image("/btn_b_pressed.png"));
	
	public static void main(String[] args)
	{
		
		try { robot = new Robot(); }
		catch(AWTException e) { e.printStackTrace(); }
		
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		screenshot = new Image(robot.createScreenCapture(new Rectangle((int) dim.getWidth(), (int) dim.getHeight())));
		
		btnGood.setVisible(false);
		btnBad.setVisible(false);
		
		window.run();
	}
	
	public void init()
	{}
	
	public void tick()
	{}
	
	@Override
	public void render(Graphics2D graphics)
	{
		
		screenshot.render(graphics);
		
		if(!isDown && !isDone) drawFill(graphics);
		if(isDown) drawBox(start, current, graphics);
		if(isDone) drawBox(start, end, graphics);
		
		btnGood.render(graphics);
		btnBad.render(graphics);
	}

	public void keyReleased(int i)
	{
		
		if(i == KeyEvent.VK_ESCAPE) System.exit(0);
	}
	
	public void mousePressed(MouseEvent e)
	{
		
		btnGood.mousePressed(e);
		btnBad.mousePressed(e);
		
		if(e.getButton() == MouseEvent.BUTTON1 && !isDone)
		{
			
			isDown = true;
			start = current;
		}
	}
	
	public void mouseReleased(MouseEvent e)
	{
		
		if(e.getButton() == MouseEvent.BUTTON1 && !isDone)
		{
			
			isDown = false;
			isDone = true;
			
			end = current;

			checkPoints();
			
			btnGood.setLocation(end.x - 64 - 8, end.y + 8);
			btnBad.setLocation(btnGood.getX() - 64 - 8, end.y + 8);
			
			if(end.y + 8 + 64 >= dim.getHeight())
			{
				
				btnGood.setLocation(btnGood.getX(), end.y - 64 - 8);
				btnBad.setLocation(btnBad.getX(), end.y - 64 - 8);
			}
			
			btnGood.setVisible(true);
			btnBad.setVisible(true);
		}
		
		if(btnGood.mouseReleased(e)) onClick(btnGood.ID);
		if(btnBad.mouseReleased(e)) onClick(btnBad.ID);
	}
	
	public void mouseMoved(MouseEvent e)
	{
		
		current = e.getPoint();
		
		btnGood.mouseMoved(e);
		btnBad.mouseMoved(e);
	}
	
	public void mouseDragged(MouseEvent e)
	{
		
		mouseMoved(e);
	}
	
	public void onClick(int i)
	{
		
		if(i == btnGood.ID)
		{
			
			saveImage();
			
			OCRProcess.main(new String[0]);
		}
		
		if(i == btnBad.ID)
		{
			
			btnGood.setVisible(false);
			btnBad.setVisible(false);
			
			isDown = false;
			isDone = false;
			
			start = new Point();
			current = new Point();
			end = new Point();
		}
	}
	
	private void saveImage()
	{
		
		Rectangle rectangle = new Rectangle(start, new Dimension(end.x - start.x, end.y - start.y));
		BufferedImage output = robot.createScreenCapture(rectangle);
		String dir = System.getProperty("java.io.tmpdir") + "/ocr_hackathon2016.jpg";
		
		try { ImageIO.write(output, "jpg", new File(dir)); }
		catch(IOException e) { e.printStackTrace(); }
	}
	
	private void checkPoints()
	{
		
		if(end.x < start.x && end.y < start.y)
		{
			
			Point temp = start;
			start = end;
			end = temp;
		}
		
		if(start.x < end.x && end.y < start.y)
		{
			
			Point temp = start;
			start = new Point(start.x, end.y);
			end = new Point(end.x, temp.y);
		}
		
		if(end.x < start.x && start.y < end.y)
		{
			
			Point temp = start;
			start = new Point(end.x, start.y);
			end = new Point(temp.x, end.y);
		}
	}
	
	private void drawBox(Point a, Point b, Graphics2D graphics)
	{
		
		int top = a.y < b.y ? a.y : b.y;
		int left = a.x < b.x ? a.x : b.x;
		int right = b.x > a.x ? b.x : a.x;
		int bottom = b.y > a.y ? b.y : a.y;
		
		graphics.fillRect(0, 0, dim.width, top);						//top
		graphics.fillRect(0, top, left, bottom - top);					//left
		graphics.fillRect(right, top, dim.width - right, bottom - top);	//right
		graphics.fillRect(0, bottom, dim.width, dim.height - bottom);	//bottom
	}
	
	private void drawFill(Graphics2D graphics)
	{
		
		graphics.setColor(new Color(0, 0, 0, 150));
		graphics.fillRect(0, 0, dim.width, dim.height);
	}
}