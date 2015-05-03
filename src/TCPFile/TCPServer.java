/**
 * 
 */
package TCPFile;

import java.io.*;
import java.net.*;

/**
 * @author Emily Elmseld 28 apr 2015
 *
 */
public class TCPServer {

	public static void main(String args[])throws IOException {
		int c;
		String inp;
		
		//Connection establishment phase
		ServerSocket ss = new ServerSocket(12000);
		System.out.println("Waiting for client\n\n");
		Socket soc = ss.accept();
		
		//Data transfer phase
		System.out.println(soc + " and server connected!");
		DataInputStream i = new DataInputStream(soc.getInputStream());
		DataOutputStream o = new DataOutputStream(soc.getOutputStream());
		
		getFilelist(i,o);
		
		inp = i.readUTF(); //readUTF läster in i Unicode Text Format
		
		FileInputStream fin = new FileInputStream("/Users/emilyelmseld/Documents/fil-lista/" + inp);
		while((c = fin.read())!= -1)
		{
			o.write(c);
			o.flush();
		}
		fin.close();
	}
	
	public static void getFilelist(DataInputStream din, DataOutputStream dout) throws IOException {
		int p = 0;
		File folder = new File("/Users/emilyelmseld/Documents/fil-lista/");
		File[] listOfFiles = folder.listFiles();
		for(File listOfFile : listOfFiles)
			
		//listFiles() returnera både filer och mappar så nästa rad är för att bara få fram filer
		if(listOfFile.isFile()) {
			p++;
		}
		dout.write(p);
		dout.flush();
		for(File listOfFile :listOfFiles)
			if(listOfFile.isFile()) {
				dout.writeUTF(listOfFile.getName());
				dout.flush();
			}
	}
}
