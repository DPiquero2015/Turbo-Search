

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image
{

	private BufferedImage buffered;
	
	public Image(Image image)
	{
		
		this(image.buffered);
	}
	
	public Image(BufferedImage image)
	{
		
		this.buffered = image;
	}

	public Image(String dir)
	{
		
		try
		{
			
			this.buffered = ImageIO.read(getClass().getResourceAsStream(dir));
		}
		
		catch(IOException e)
		{
			
			e.printStackTrace();
		}
	}
	
	public Image(int width, int height)
	{
		
		this.buffered = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	
	public BufferedImage retrieve()
	{
		
		return buffered;
	}
	
	public void render(Graphics graphics)
	{
		
		render(graphics, 0, 0);
	}
	
	public void render(Graphics graphics, int x, int y)
	{
		
		graphics.drawImage(buffered, x, y, null);
	}
	
	public void render(Graphics graphics, int x, int y, int u, int v, int width, int height)
	{
		
		render(graphics, x, y, u + width, v + height, u, v, width, height);
	}
	
	public void render(Graphics graphics, int x, int y, int imgWidth, int imgHeight, int u, int v, int width, int height)
	{
		
		graphics.drawImage(buffered, x, y, imgWidth, imgHeight, u, v, u + width, v + height, null);
	}
	
	public int getRGB(int x, int y)
	{
		
		return buffered.getRGB(x, y);
	}
	
	public void setRGB(int x, int y, int numerical)
	{
		
		buffered.setRGB(x, y, numerical);
	}
	
	public Image clone()
	{
		
		ColorModel colorModel = this.buffered.getColorModel();
		WritableRaster writableRaster = this.buffered.copyData(null);
		boolean isRasterPremultiplied = this.buffered.isAlphaPremultiplied();
		
		return new Image(new BufferedImage(colorModel, writableRaster, isRasterPremultiplied, null));
	}
}