/**
 * 
 */
package TCPStream;
import java.net.*;
import java.io.*;

/**
 * @author Emily Elmseld 28 apr 2015
 *
 */

public class TCPClient {
    public static void receive( String serverIP, int serverPort ) {
        Socket socket = null;
        try {
            InetAddress adress = InetAddress.getByName( serverIP );
            socket = new Socket( adress , serverPort ); // koppla upp
            ObjectInputStream input = new ObjectInputStream( 
                                              socket.getInputStream() );
            String message;
            while( true ) {
                message = input.readUTF(); // här väntar tråden
	            System.out.println( serverIP.toString() + ": " + message + " : " + message.length() + " bytes"); // Skriver ut meddelandet

                
            }
        } catch(IOException e) {
            System.out.println( e );
        }       
        try {
            socket.close(); // avsluta Socket-objektet
        } catch( Exception e ) {
            System.out.println( e );
        }
    }
    
    public static void main( String[] args ) {
        TCPClient.receive( "192.168.1.9", 5555 );
    }
}
