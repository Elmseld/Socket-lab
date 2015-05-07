/**
 * 
 */
package TCPFile;

import java.io.*;
import java.net.*;

/**
 * @author Emily Elmseld 28 apr 2015
 *
 * En TCP-Server-klass som skickar en fillista till en klientklass 
 * över en TCP-socket som klienten kan välja på och sedan skickar 
 * den önskade filen.
 * 
 */
public class TCPServer {

	public static void main(String args[])throws IOException {
		//Skapar variabeln c av typen int
		int c;
		// Skapar variabeln inp av typen String
		String inp;
		
		//Skapar kontakten mellan server och klient
		ServerSocket ss = new ServerSocket(12000);
		// Skriver ut meddelandet Waiting for client i terminalen
		System.out.println("Waiting for client\n\n");
		// Lyssnar efter att en koppling till klienten är gjort och accepterar den och lägger in refernesen i soc
		Socket soc = ss.accept();
		
		// Skriver ut meddelandet från den accepterade kopplingen och and server connected i terminalen
		System.out.println(soc + " and server connected!");
        // Skapar en ny DataInputStream med referens till inputströmmen från socketen och lägger till detta i varaibeln i
		DataInputStream i = new DataInputStream(soc.getInputStream());
        // Skapar en ny DataOutputStream med referens till outputströmmen från socketen och lägger till detta i varaibeln o
		DataOutputStream o = new DataOutputStream(soc.getOutputStream());
		
		//Startat metoden getFilelist med parametrarna DataInputStream och DataOutputStream
		getFilelist(i,o);
		
		//läster in i i Unicode Text Format och lägger in referensen i inp
		inp = i.readUTF(); 
		
		//SKapar en FileInputStream för att hämta den valda filen
		FileInputStream fin = new FileInputStream("/Users/emilyelmseld/Documents/fil-lista/" + inp);
		//While-loop som skickar den valda filen till klienten
		while((c = fin.read())!= -1)
		{
			o.write(c);
			o.flush();
		}
		//Stänger FileInputStream
		fin.close();
	}
	
	/**
	 * Metod som skapar fillistan som skickas till klienten så denne kan välja en fil
	 * @param din DataInputStrömmen
	 * @param dout DataOutputStrömmen
	 * @throws IOException Kastar exception vid problem i listan
	 */
	public static void getFilelist(DataInputStream din, DataOutputStream dout) throws IOException {
		// Skapar variabeln p av typen int med värdet 0
		int p = 0;
		// Skapar variabeln folder av typen File med referens till mappen som innehåller fillistan som ska skickas till klienten
		File folder = new File("/Users/emilyelmseld/Documents/fil-lista/");
		// Skapar en File-array listOfFiles med filerna i fillistan
		File[] listOfFiles = folder.listFiles();
		// Förenklad forloop för att loop fram enbart filer och inga mappar
		for(File listOfFile : listOfFiles)
		//listFiles() returnera både filer och mappar så nästa rad är för att bara få fram filer
		if(listOfFile.isFile()) {
			//Nummrerar filerna
			p++;
		}
		
		//Skickar in datan från p till outputströmmen
		dout.write(p);
		// tvingar bruffrande data ut i outputströmmen.
		dout.flush();
		// Förenklad forloop för att loop fram enbart filer och inga mappar
		for(File listOfFile :listOfFiles)
			//listFiles() returnera både filer och mappar så nästa rad är för att bara få fram filer
			if(listOfFile.isFile()) {
				//Skickar in filnamnen från fillistan till outputströmmen				//
				dout.writeUTF(listOfFile.getName());
				// tvingar bruffrande data ut i outputströmmen.
				dout.flush();
			}
	}
}
