/**
 * 
 */
package TCPStream;
import java.net.*;
import java.io.*;

/**
 * @author Emily Elmseld 28 apr 2015
 * 
 * Klassens tar emot strängar via TCP-socket och skriver ut detta i terminalfönster ihop 
 * med information om serverns Ip-adress och antalet bytes som 
 * strängen innehåller.
 *
 */

public class TCPClient {
	/**
	 * Metoden som kommunicerar med servern, tar emot strängarna och skriver ut dessa i terminalen  
	 * @param serverIP Serverns Ip-nummer
	 * @param serverPort Porten som används
	 */
    public static void receive( String serverIP, int serverPort ) {
    	// Skapar ett variabeln socket av typen Socket med värdet null
        Socket socket = null;
        try {
        	// Skapar variabeln adress av typen InetAddress med referens till servern Ip-nr
            InetAddress adress = InetAddress.getByName( serverIP );
            // koppla upp till servern
            socket = new Socket( adress , serverPort );
    		// Skapar en ny ObjectInputStream med variabelnamnet input med en referens som hämtar InputStream från socketen 
            ObjectInputStream input = new ObjectInputStream( 
                                              socket.getInputStream() );
            // Skapar variabel message av typen String
            String message;
            // While-loop för att ta emot informationen från server och skriva ut den i terminalen
            while( true ) {
            	// här väntar tråden
                message = input.readUTF(); 
	            // Skriver ut meddelandet i terminalen i formatet, ip-adress: medelandet : (medelandets längd i byte) bytes  
	            System.out.println( serverIP.toString() + ": " + message + " : " + message.length() + " bytes"); 

                
            }
        // Kastar ett excetion ifall det blir några problem med kommunikationen mellan server och klient
        } catch(IOException e) {
            System.out.println( e );
        }       
        try {
        	// avsluta Socket-objektet
            socket.close(); 
        // Kastar ett exception ifall det inte går att stänga socketen
        } catch( Exception e ) {
            System.out.println( e );
        }
    }
    
    /**
     * Metod som startar klienten och anger serverns ip-nummer och port adressen
     * @param args
     */
    public static void main( String[] args ) {
        TCPClient.receive( "192.168.1.9", 5555 );
    }
}
