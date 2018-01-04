

import java.awt.Dimension;
import java.awt.Toolkit;

public class Resolution
{

	public static final double	RESOLUTION_04x3	= ((double) 04 / 3);
	public static final double	RESOLUTION_16x9	= ((double) 16 / 9);
	
	public double RESOLUTION;
	public double SCALE;
	public int WIDTH;
	public int HEIGHT;
	
	public Resolution()
	{
		
		this(RESOLUTION_04x3, 1, 480);
	}
	
	public Resolution(double res, double scale, int width)
	{
		
		this.RESOLUTION = res;
		this.SCALE = scale;
		this.WIDTH = width;
		this.HEIGHT = (int) ((1 / res) * width);
	}
	
	public Dimension getDimension()
	{
		
		return new Dimension((int) this.WIDTH, (int) this.HEIGHT);
	}
	
	public Dimension getDimensionScaled()
	{
		
		return new Dimension((int) (this.WIDTH * this.SCALE), (int) (this.HEIGHT * this.SCALE));
	}
	
	public int getWidthScaled()
	{
		
		return (int) (this.WIDTH * this.SCALE);
	}
	
	public int getHeightScaled()
	{
		
		return (int) (this.HEIGHT * this.SCALE);
	}
	
	public static Resolution getFullScreen(double res)
	{
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		
		return new Resolution(res, 1, kit.getScreenSize().width);
	}
	
	public void setResolution(double res)
	{
		
		this.RESOLUTION = res;
	}
	
	public void setScale(double scale)
	{
		
		this.SCALE = scale;
	}
	
	public void setWidth(int width)
	{
		
		this.WIDTH = width;
	}
	
	public void setHeight(int height)
	{
		
		this.HEIGHT = height;
	}
}