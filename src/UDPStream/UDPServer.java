/**
 * 
 */
package UDPStream;
import java.net.*;
import java.io.*;

/**
 * @author Emily Elmseld 28 apr 2015
 * 
 * En UDP-server-klass som skickar ett antal sträng-objekt till klienten via en UDP-socket.
 *
 */
public class UDPServer {
		
		/**
		 * Klassen som kommuniserar med Klient-klassen
		 * @param ip KLientens IP-nummer
		 * @param port Port-nummret som används
		 * @param messages Strängarna som ska skickas till Klienten
		 */
	    public static void send( String ip, int port, String[] messages ) {
	        try {
	        	// Skapar ett nytt InetAddress-objekt med variabelnamnet receiver med en referens till Klientens ip-nummer
	            InetAddress receiver = InetAddress.getByName( ip );
	            // Skapar en ny DatagramSocket med variabelnamnet socket
	            DatagramSocket socket = new DatagramSocket();
	            // Skapar DatagramPacket med variabelnamnet packet;  
	            DatagramPacket packet;
	            // Skapar buffer-variabeln data
	            byte[] data;
	            
	            // for-loop för att skicka sträng för sträng
	            for( int i = 0; i < messages.length; i++ ) { 
	            	// Lägger in Strängarna som ska skickas till variabeln data
	                data = messages[ i ].getBytes("ISO-8859-1");
	                // Skapar en ny datagramPacket med parametrarna data(datan som ska skickas), data.length(storleken på datan), receiver(klientens IP-nummer), port(port-nummret) 
	                packet = new DatagramPacket( data, data.length, receiver, port );
	                // Skickar DatagramPacketet till klienten via socketen 
	                socket.send( packet );
	                // Får tråden att pausa i 10 millisekunder
	                Thread.sleep( 10 );
	            }
	           //Kastar exception och skriver ut meddelandet i terminalen vid problem i kommunikationen mellan klient och server 
	        } catch( IOException e1 ) {
	            System.out.println( e1 );
	        } catch( InterruptedException e2 ) {
	            System.out.println( e2 );
	        }
	    }

	    /**
	     * Metod som skapar en Stringarray med strängarna som ska skickas till klienten, satt på en evighets forloop för testning.
	     * @param args
	     */
	    public static void main( String[] args ) {
	    	for(int i = 0; i < 1; i++) {
	    		String[] meddelanden = { "Veni, vidi, vici", 
                        "Jag kom, jag såg, jag segrade",
                        "Alea iacta est", 
                        "Tärningen är kastad",
                        "Et tu Brute", 
                        "Även du, min käre Brutus",
                        "Det kan kännas nog så motigt att plocka bort tegelsten efter tegelsten i en stor mur. Men en dag rasar hela fängelset och du inser vad du har åstadkommit",
                        "Success is a poor teacher. We learn the most of ourselves when we fail, so don’t be afraid of failing. Failing is part of the process of success. You cannot have success without failure. So unsuccessful people are people who never fail",
                        "Everything that someone does, is either love or a call for love. When people behave unlovingly, they have forgotten who they are. When we attack back, we initiate a war that no one can win",
                        "Redan i slutet av Novembris börjar julbacillen att vakna till sig ur sin årsdvala. Den smyger sig då ut och uppsöker sitt offer, som den genast angriper. Till att börja med märker patienten ingenting, utan fortfar med sina dagliga och nattliga sysslor. Men snart nog göra bacillens verkningar sig förnimbara, och efter ett par dagars inkubation utbryter hos patienten en åkomma, som jag måste kalla julfebern, Februs ]ulicus, F.0Denna helsot yttrar sig alltid i en omisskännlig oro och brådska; patienten kan inte sitta stilla, han springer ut och in i butiker, köper utan att pruta (ett mycket svårt symptom!), förseglar små och stora paketer med lack (ett osvikligt tecken!), känner en sjuklig lusta att äta lutfisk, dricker gärna mörkt öl, skriker god jul! till höger och vänster och är fullständigt komfys i huvudet. Febern når alltid sin höjdpunkt den 24 december på aftonen, då patienten känner sig synnerligen sällskapssjuk; flera patienter pläga då sluta sig samman kring ett stort bord, de skratta åt allting, dricka spirituosa och knäcka sönder nötter, äta förfärligt mycket och slumra slutligen in i en dov sömn, ur vilken de ofta rusa upp mitt i natten, d. v. s. vid 6-tiden på morgonen, då de springa till ett stort, upplyst hus, varest de sjunga unisont åtskilliga sånger, och gå därpå något förslappade hem.Härpå börjar förbättringen, långsamt men säkert. Men för att återhämta krafterna måste patienten äta och dricka förskräckligt mycket en tre veckors tid. Därpå börjar i regel en del råd och anmaningar att ställas till patienten, i form av s. k. räkningar, och dessa recept pläga lugna den sjuke rätt märkbart. Sedan han erhållit ett tjogtal dylika recept, är sjukdomen hävd, och endast en viss märkbar nervositet sitter kvar i kroppen ännu en tid."
                        };
	    		i--;
	    	// Skickar parametrarna Ip-nummer till klienten, portnr här 4444 och Stringarrayen till send-metoden.	
	        UDPServer.send( "192.168.1.7", 4444, meddelanden );
	    }
	  }
	}