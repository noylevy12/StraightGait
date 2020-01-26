package StraightGaitS;
import java.io.BufferedReader;
import java.util.Random;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.TimerTask;

public class uptadeStatus extends TimerTask
{
	@Override
	public void run() {
		URL url = null;
		String strUrl = "https://straightgait.000webhostapp.com/?requestName=legMoved&degree=";
		Random rand= new Random();  // this random number simulate the degree of the leg

		// Obtain a degree number between [0 - 90].
		int randnum = rand.nextInt(90);
		strUrl+=String.valueOf(randnum);

		// open a connection to the our site
		try {
			url = new URL("https://straightgait.000webhostapp.com"); 
			URLConnection connection = null;
			connection = url.openConnection();
			// activate the output
			connection.setDoOutput(true);

			PrintStream ps = new PrintStream(connection.getOutputStream());
			// send the parameters to the site
			String str = "&degree="+ String.valueOf(randnum);
			ps.print("requestName=legMoved");   // we have to get the input stream in order to actually send the			
			ps.print(str);   // we have to get the input stream in order to actually send the
			// request
			connection.getInputStream();
			// close the print stream
			ps.close();
			
			try (BufferedReader in = new BufferedReader(
					new InputStreamReader(connection.getInputStream())))
			{
				String line;
				try {
					while ((line = in.readLine()) != null)
						System.out.println(line);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// connection.setRequestMethod("GET");


	}
}

