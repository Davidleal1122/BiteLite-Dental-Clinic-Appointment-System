package swingComponents;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class CustomizedFonts  extends Font{

	Font poppinsBold;
	Font poppinsThin;
	int fontSize;

	public CustomizedFonts(Font font) {

		super(font);
		try {
			File fontFile = new File("src/fonts/splashScreenFonts/Poppins ExtraBold 800.ttf");
			poppinsBold = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.BOLD, fontSize);

			File fontFile2 = new File("src/fonts/splashScreenFonts/Poppins Regular 400.ttf");
			poppinsThin = Font.createFont(Font.TRUETYPE_FONT, fontFile2).deriveFont(Font.PLAIN, fontSize);

		} catch (IOException | FontFormatException e) {

		}
	}

	public int setFontSize(int fontSize) {
		this.fontSize = fontSize;
		return fontSize;

	}

	public Font setFontBold() {
		
		return poppinsBold;

	}

	public Font setFontThin() {

		return poppinsThin;

	}
}