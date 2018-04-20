import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
public class SpeedwayConnectClient
{
    public static void main(String[] args)
    {
        // The IP address or hostname of your reader
        final String READER_HOSTNAME = "192.168.1.3";
        // The TCP port specified in Speedway Connect
        final int READER_PORT = 14150;
         
        try
        {
            // Create a TCP socket connection to the reader
            Socket s = new Socket(READER_HOSTNAME, READER_PORT);
            // Create a BufferedReader object from the socket connection
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            // Receive data in an infinite loop
            int cont = 0;
            while (true)
            {
                // Read one line at a time
                String line = br.readLine();
                // Print it to the screen
                System.out.println(++cont + ") "+ line);
                if (line.equals(null)) break;
                	
            }
        }
        catch (Exception e)
        {
            // An error has occurred
            System.out.println(e.getMessage());
        }
    }
}