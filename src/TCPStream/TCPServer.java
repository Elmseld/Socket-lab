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



public class TCPServer {
    
    public static void send( int port, String[] messages ) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        ObjectOutputStream output;
        try {
            serverSocket = new ServerSocket( port );
            socket = serverSocket.accept();
            output = new ObjectOutputStream( socket.getOutputStream() );
            for( int i = 0; i < messages.length; i++ ) {
                output.writeUTF( messages[ i ] );
                output.flush();
                Thread.sleep( 1000 ); // paus 3 sekunder
            }
        } catch( Exception e1 ) { // IOException eller InterruptedException
            System.out.println( e1 );
        } 
        try {
            socket.close();
            serverSocket.close();
        } catch( Exception e ) {}
    }
    
    public static void main( String[] args ) {
        String[] meddelanden = { "Veni, vidi, vici", 
                                 "Jag kom, jag såg, jag segrade",
        "Alea iacta est", "Tärningen är kastad",
        "Et tu Brute", "Även du, min käre Brutus" };
        TCPServer.send( 5555, meddelanden );
    }
}
