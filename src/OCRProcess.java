import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCRProcess
{
	
	public static void main(String[] args)
	{
		
		File imageFile = new File(System.getProperty("java.io.tmpdir") + "/ocr_hackathon2016.jpg");
		ITesseract instance = new Tesseract();
		instance.setDatapath("C:\\");
		instance.setLanguage("eng");
		
		try { OutputScreen.run(instance.doOCR(imageFile)); }
		catch(TesseractException e) { e.printStackTrace(); }
		
		imageFile.delete();
	}
}
