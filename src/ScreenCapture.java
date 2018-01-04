

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ScreenCapture
{
    
    private static final String CONSOLE_HEADER = "[SCREENCAPTURE]" + "\t";
    
    public static void screenshot(EXTENSIONS extension)
    {
        
        screenshot("./", extension);
    }
    
    public static void screenshot(String dir, EXTENSIONS extension)
    {
        
        screenshot(dir, "screenshot_" + System.nanoTime(), extension);
    }
    
    public static void screenshot(String dir, String fileName, EXTENSIONS extension)
    {
	
        if(extension.meta == 0)
        {
            
            File path = new File(dir + fileName + "." + extension.value);
            path.mkdirs();
            
            Dimension screenDimen = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle screenRect = new Rectangle(screenDimen);
            
            try
            {
                
                BufferedImage image = new Robot().createScreenCapture(screenRect);
            
                ImageIO.write(image, extension.value, path);
            }
            
            catch(IOException | AWTException e)
            {
                
                e.printStackTrace();
            }
        }
        
        else System.out.println(CONSOLE_HEADER + "Extension " + "\"." + extension.value + "\"" + " Is Not A Valid Extension.");
    }
    
    public static void record(String dir, String fileName, EXTENSIONS extension)
    {
        
        if(extension.meta == 1)
        {
            
            File path = new File(dir + fileName + "." + extension.value);
            path.mkdirs();
        }
        
        else System.out.println(CONSOLE_HEADER + "Extension " + "\"." + extension.value + "\"" + " Is Not A Valid Extension.");
    }
    
    enum EXTENSIONS
    {
        
        IMAGE_BMP("bmp", 0),
        IMAGE_JPG("jpg", 0),
        IMAGE_PNG("png", 0),
        
        VIDEO_MOV("mov", 1),
        VIDEO_MP4("mp4", 1),
        VIDEO_WMV("wmv", 1);
        
        public final String value;
        public final int meta;
        
        EXTENSIONS(String ext, int meta)
        {
            
            this.value = ext;
            this.meta = meta;
        }
    }
}