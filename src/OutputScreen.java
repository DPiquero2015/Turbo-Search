import java.awt.Desktop;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class OutputScreen extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel gridMain;
	
	private TextArea textArea;
	
	private JButton btnGoogle;
	private JButton btnCopy;
	private JButton btnSave;
	
	public static void run(String input)
	{
		
		try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) { e.printStackTrace(); }
		
		new OutputScreen(input).setVisible(true);
	}
	
	public OutputScreen(String input)
	{
		
		setTitle("OCR Output");
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 480, 360);
		
		gridMain = new JPanel();
		gridMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(gridMain);
		
		gridMain.setLayout(null);
		
		textArea = new TextArea();
		textArea.setFont(new Font("Arial", Font.PLAIN, 18));
		textArea.setText(input);
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 474, 260);
		gridMain.add(textArea);
		
		btnGoogle = new JButton("Google Search");
		btnGoogle.addMouseListener(new MouseAdapter()
		{
			
			public void mouseClicked(MouseEvent e)
			{
				
				String google = "http://www.google.com/search?q=";
				String search = textArea.getText();
				
				try
				{
					URL url = new URL(google + URLEncoder.encode(search, "UTF-8"));
				
					if(Desktop.isDesktopSupported())
					{
						try { Desktop.getDesktop().browse(url.toURI()); }
						catch(IOException | URISyntaxException exception) { exception.printStackTrace(); }
					}
				}
				catch(MalformedURLException | UnsupportedEncodingException exception) { exception.printStackTrace(); }
				
				
			}
		});
		btnGoogle.setFont(new Font("Arial", Font.PLAIN, 14));
		btnGoogle.setBounds(10, 270, 130, 50);
		gridMain.add(btnGoogle);
		
		btnCopy = new JButton("Copy to Clipboard");
		btnCopy.addMouseListener(new MouseAdapter()
		{
			
			public void mouseClicked(MouseEvent e)
			{
				
				StringSelection selection = new StringSelection(textArea.getText());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(selection, null);
			}
		});
		btnCopy.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCopy.setBounds(177, 270, 150, 50);
		gridMain.add(btnCopy);
		
		btnSave = new JButton("Save Text");
		btnSave.addMouseListener(new MouseAdapter()
		{
			
			public void mouseClicked(MouseEvent e)
			{
				
				JFileChooser fileChooser = new JFileChooser();
				if(fileChooser.showSaveDialog(gridMain) == JFileChooser.APPROVE_OPTION)
				{
					
					File file = fileChooser.getSelectedFile();
					try
					{
						PrintWriter writer = new PrintWriter(file);
						writer.print(textArea.getText());
						writer.close();
					}
					catch(FileNotFoundException exception) { exception.printStackTrace(); }
				}
			}
		});
		btnSave.setFont(new Font("Arial", Font.PLAIN, 14));
		btnSave.setBounds(365, 270, 100, 50);
		gridMain.add(btnSave);
	}
}