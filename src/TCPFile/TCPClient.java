/**
 * 
 */
package TCPFile;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author Emily Elmseld 28 apr 2015
 *
 */
public class TCPClient {

	public static void main(String args[]) throws IOException {
		String list[] = new String[20];
		int p = 0, ch;
		Socket s = new Socket(InetAddress.getByName("192.168.1.15"), 12000);
		System.out.println("Client connected to Server at" + s);
		DataInputStream i = new DataInputStream(s.getInputStream());
		DataOutputStream o = new DataOutputStream(s.getOutputStream());
		
		ch = getFile(i, o, list);
		
		FileOutputStream fout = new FileOutputStream("/Users/emilyelmseld/Downloads/" + list[ch-1]);
		try {
			System.out.println("Transferring File: " + list[ch-1] + "\n\n");
			do {
				ch = i.read();
				fout.write(ch);
			}while(ch!=-1);
		}catch(SocketException e) { //Exception indicates File transfer complete.
			System.out.println("File tranfer complete");
			
		}
	}
	
	public static int getFile(DataInputStream din, DataOutputStream dout, String flist[]) throws IOException {
		
		Scanner src = new Scanner(System.in);
		int i = 0, ch = 0;
		System.out.println("file-list from Server \n");
		ch = din.read();
		
		for(i = 0; i < ch; i++) {
			flist[i] = din.readUTF();
			System.out.println((i+1) + "." + flist[i]);
		}
		
		System.out.println("Enter Filenummer to be Requested");
		ch = src.nextInt();
		dout.writeUTF(flist[(ch-1)]);
		dout.flush();
		return (ch);
	}
	
}
