package pim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QOTD {
	/**
	 * Gets the quote to display today. Based from system clock
	 * @return Today's quote
	 */
	public static String getTodaysQuote() {

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(QOTD.class.getClassLoader().getResourceAsStream("quotes.txt")))) {
			int i = 1;
			int todaysDate = Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));

			String quote = "";
			while (i <= todaysDate) {
				quote = reader.readLine();
				i++;
			}

			return quote;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

}
