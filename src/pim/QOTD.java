package pim;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class QOTD {
	/**
	 * Gets the quote to display today. Based from system clock
	 * @return Today's quote
	 */
	public static String getTodaysQuote() {
		try {
			File quoteFile = new File("Data/Quotes/quotes.txt");
			List<String> quotes = Files.readAllLines(quoteFile.toPath(), Charset.defaultCharset());
			Date date = new Date();
			int todaysDate = Integer.parseInt(new SimpleDateFormat("dd").format(date));
			String todaysQuote = quotes.get(todaysDate);
			return todaysQuote;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

}
