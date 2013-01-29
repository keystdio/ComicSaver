import java.io.BufferedReader;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.*;
public class HTML_Saver {
	public static void main(String[] args) throws IOException{
		URL url = new URL("http://www.baozoumanhua.com/");
		URLConnection yc = url.openConnection();
		
		System.out.println("Connection established.");
		
		File f = new File("source.html");
		Scanner input = new Scanner(yc.getInputStream());
		PrintStream output = new PrintStream(f);
		
		while(input.hasNext()){
			String line = input.nextLine();
			output.println(line);
		}
		
		System.out.println("HTML Saved");
		
		Stack<String> withLinks = new Stack<String>();
		Scanner HTMLScan = new Scanner(new File("source.html"));
		
		while(HTMLScan.hasNextLine()){
			String line = HTMLScan.nextLine();
			if(line.contains("width:460px")){
				withLinks.push(line);
			}
		}
		withLinks.pop(); // erase the last one
			
		// strips remains the link of the jpg
		Queue<String> links = strip(withLinks);
			
		//download(links);
		for(String s:links)
			System.out.println(s);
	}
	
	
	private static Queue<String> strip(Stack<String> withLinks) {
		Queue<String> links = new LinkedList<String>();
		for(String s:withLinks){
			Scanner words = new Scanner(s);
			while(words.hasNext()){
				String next = words.next();
				if(next.contains("src="))
					links.add(next.substring(4));
			}
		}
		return links;
	}
	
	public static void downloads(Queue<String> links) throws IOException{
		for(String s:links){
			URL url = new URL(s);
			URLConnection uc = url.openConnection();
			InputStream input = uc.getInputStream();
			//input.
		}
	}
}
