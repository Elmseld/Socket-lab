/**
 * 
 */
package TCPFile;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author Emily Elmseld 28 apr 2015
 * Klassen connectar till en server och tar emot en 
 * fil från servern och sparar filen på klientens dator
 */
public class TCPClient {

	public static void main(String args[]) throws IOException {
		// Skapar en ny array list[] av typen String med längden 20
		String list[] = new String[20];
		//Skapar en variabel antal av typen int
		int tal;
		// Skapar en ny Socket som ges ip-nummret till server och portnr 12000
		Socket s = new Socket(InetAddress.getByName("192.168.1.15"), 12000);
		// Skriver ut Client connected to Server at + socketen i terminalen
		System.out.println("Client connected to Server at" + s);
		// Skapar en ny DataInputStream men variabelnamnet dis med en referens som hämtar InputStream från socketen 
		DataInputStream dis = new DataInputStream(s.getInputStream());
		// Skapar en ny DataOutputStream men variabelnamnet dos med en referens som hämtar OutputStream från socketen 
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		
		// lägger in informationen från metoden getFile i inten tal
		tal = getFile(dis, dos, list);
		
		// Skapar en FileOutputStream med variabelnamnet fout som skapar en ny fil i /Users/emilyelmseld/Downloads/ + filnamnet från servern
		FileOutputStream fout = new FileOutputStream("/Users/emilyelmseld/Downloads/" + list[tal-1]);
		try {
			//Skriver ut Transferring File: + filnamnet i terminalen
			System.out.println("Transferring File: " + list[tal-1] + "\n\n");
			//En do-while-loop som lägger in datan från servern i den nya filen
			do {
				//läser in datan från DataInputstream
				tal = dis.read();
				//Skriver ut datan till den nya filen
				fout.write(tal);
			}while(tal!=-1);
		// exception indikerar att Filen är nedladdad.
		}catch(SocketException e) {
			System.out.println("File tranfer complete");
			
		}
	}
	
	/**
	 * Metod som hämtar och skriver ut en fillista från servern som man kan välja från för att ladda ned filer från servern
	 * @param din DataInputStream
	 * @param dout DataOutputStream
	 * @param flist String, fillistan med filerna från servern
	 * @return returnerar filnumret som anges i terminalen
	 * @throws IOException kastar exceptions vid fel i parametrarna
	 */
	public static int getFile(DataInputStream din, DataOutputStream dout, String flist[]) throws IOException {
		
		Scanner src = new Scanner(System.in);
		// Skapar två intar i och tal med värdet 0.
		int i = 0, tal = 0;
		// Skriver ut file-list from Server i terminalen
		System.out.println("file-list from Server \n");
		// Lägger in InputStreamens data i byte i variabeln tal
		tal = din.read();
		
		//For-loop som skriver ut fillistan rad för rad i terminalen
		for(i = 0; i < tal; i++) {
			flist[i] = din.readUTF();
			System.out.println((i+1) + "." + flist[i]);
		}
		
		//Skriver ut Enter Filenummer to be Requested i terminalen 
		System.out.println("Enter Filenummer to be Requested");
		// Lägger in talet som angavs i terminalen i variablen tal
		tal = src.nextInt();
		// lägger in sträng-namnet från den valda filen i DataOutputStreamen 
		dout.writeUTF(flist[(tal-1)]);
		// tvingar bruffrande data ut i output strömen.
		dout.flush();
		// Stänger möjligheten att skriva i terminalen
		src.close();
		// returnerar talet som avgavs
		return (tal);
	}
	
}
