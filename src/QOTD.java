import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class QOTD {

	/**
	 * quotes should be read from Data/Quotes/quotes.txt and written to Data/Quotes/recent.txt
	 */

	public QOTD() {

	}

	private String loadQuoteFromFile() {
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
