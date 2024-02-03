import java.io.*;
import java.net.*;
import java.util.*;

public class EmailSender2
{
   public static void main(String[] args) throws Exception
   {
      // Create a socket which connect to Server (HOSTNAME, PORTNUM)
        String Hostname = "";
        int Port = 25;
        Socket emailClient;
		
        String[] emails = {"Testing@gmail.com", "Testing2@gmail.com"}; // array of strings
		Random r = new Random();
		int randomNumber = r.nextInt(emails.length);
        
        int a = 0;
        
        while(a < 5) {
        try {
            emailClient = new Socket(Hostname, Port);
            
            
            //InputStream & outputStream
            Scanner inFromServer = new Scanner(emailClient.getInputStream());
            DataOutputStream outToServer = new DataOutputStream(emailClient.getOutputStream());

            // Read greeting from the server.
            String response = inFromServer.nextLine();
            System.out.println(response);
            if (!response.startsWith("220")) {
                throw new Exception("220 reply not received from server.");
            }

            // Send HELO command and get server response.
            String command = "HELO usca.edu\r\n";
            System.out.print(command);
            outToServer.writeBytes(command);
            response = inFromServer.nextLine();
            System.out.println(response);
            if (!response.startsWith("250")) {
                throw new Exception("250 reply not received from server.");
            }


      // Send MAIL FROM command.
            String commandMail = "MAIL FROM: FakeSpamMail@usca.edu \r\n";
            System.out.print(commandMail);
            outToServer.writeBytes(commandMail);
            response = inFromServer.nextLine();
            System.out.println(response);
            if (!response.startsWith("250")) {
            emailClient.close();
                throw new Exception("250 reply not received from server.");
            }
            
            
      // Send RCPT TO command.
            String commandRCPT = "RCPT TO: " + emails[randomNumber] + " \r\n";
            System.out.print(commandRCPT);
            outToServer.writeBytes(commandRCPT);
            response = inFromServer.nextLine();
            System.out.println(response);
            if (!response.startsWith("250")) {
                emailClient.close();
                throw new Exception("250 reply not received from server.");
            }

      // Send DATA command.
            String commandDATA = "DATA \r\n";
            System.out.print(commandDATA);
            outToServer.writeBytes(commandDATA);
            response = inFromServer.nextLine();
            System.out.println(response);
            if (!response.startsWith("354")) {
                emailClient.close();
                throw new Exception("354 reply not received from server.");
            }
      

      // Send message data.
            String messageLine1 = "From: FakeSpamMail@usca.edu\r\nTo: " + emails[randomNumber] + " \r\nSubject:Nice to meet you\r\n Hello, hello";
            System.out.print(messageLine1);
            outToServer.writeBytes(messageLine1);
      
      // End with line with a single period.
            String messageLine2 = "\r\n.\r\n";
            System.out.print(messageLine2);
            outToServer.writeBytes(messageLine2);
            response = inFromServer.nextLine();
            System.out.println(response);
            if (!response.startsWith("250")) {
                emailClient.close();
                throw new Exception("250 reply not received from server.");
            }

      // Send QUIT command.
            String commandQUIT = "QUIT";
            System.out.print(commandQUIT);
            outToServer.writeBytes(commandQUIT);
            response = inFromServer.nextLine();
            System.out.println(response);
            if (!response.startsWith("221")) {
                emailClient.close();
                throw new Exception("221 reply not received from server.");
            }

     // Close the connection
            emailClient.close();

        } catch (Exception e) {
            System.err.print("Caught Exception" + e.getMessage());
        }
        a++;
        }
    }
}
