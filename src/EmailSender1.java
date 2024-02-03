import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

public class EmailSender1 {

       public static void main(String[] args) throws Exception {

              // Create a socket which connect to Server (HOSTNAME, PORTNUM)

              String Hostname = "";//add host name
              int Port = 25;
              Socket emailClient;
                     System.out.println("outside loop");
              //String[] emails = {Testing@gmail.com, Testing2@usca.edu}; // array of strings
              //Random r = new Random();
              //int randomNumber = r.nextInt(emails.length);
              int a = 0;
              
              //while(a<5) {
              try {
                     emailClient = new Socket(Hostname, Port);
                     DataOutputStream outToServer = new DataOutputStream(emailClient.getOutputStream());
                     
                     Scanner inFromServer = new Scanner(emailClient.getInputStream());
                     String response = inFromServer.nextLine();
                           System.out.println(response);
                     if (!response.startsWith("220")) {
                           throw new Exception("220 reply not received from server.");
                     }
                     while(a < 1) { // should while be in the try or out the try.
                           
                           
                     System.out.println("inside loop");
                     String[] emails = {"Testing2@gmail.com"}; // array of strings //to //who receives
                     String[] emails2 = {"Testing1@usca.edu", "Testing@gmail.com", "Testing2@gmail.com"};//from // who sends
                     Random r = new Random();
                     int randomNumber = r.nextInt(emails.length);
                     int randomNumber2 = r.nextInt(emails2.length);
                     System.out.println("insideside loop second time");
                     //emailClient = new Socket(Hostname, Port);
                     // InputStream & outputStream
                     //Scanner inFromServer = new Scanner(emailClient.getInputStream());
                     //DataOutputStream outToServer = new DataOutputStream(emailClient.getOutputStream());
                     // Read greeting from the server.
                     //String response = inFromServer.nextLine();
              
                     System.out.println("this is after if");
                     // Send HELO command and get server response.
                     String command = "HELO usca.edu\r\n";
                     System.out.print(command);
                     outToServer.writeBytes(command);
                    response = inFromServer.nextLine();
                     System.out.println(response);
                     if (!response.startsWith("250")) {
                           throw new Exception("250 reply not received from server.");
                     }
                     System.out.println("this is after second if");
                     // Send MAIL FROM command.
                     String commandMail = "MAIL FROM: " + emails2[randomNumber2] + "\r\n"; // Email needs to be added
                     System.out.println(commandMail);
                     outToServer.writeBytes(commandMail);
                     response = inFromServer.nextLine();
                     System.out.println(response);
                     if (!response.startsWith("250")) {
                           emailClient.close();
                           throw new Exception("250 reply not received from server. ");
                     }
                     System.out.println("this is after third if");
                     // Send RCPT TO command.
                     String commandRCPT = "RCPT TO: <" + emails[randomNumber] + ">\r\n";
                     System.out.print(commandRCPT);
                     outToServer.writeBytes(commandRCPT);
                     response = inFromServer.nextLine();
                     System.out.println(response);
                     if (!response.startsWith("250")) {
                           emailClient.close();
                           throw new Exception("250 reply not received from server.");
                     }
                     System.out.println("this is after fourth if");
                     // Send DATA command.
                     String commandDATA = "DATA\r\n";
                     System.out.print(commandDATA);
                     outToServer.writeBytes(commandDATA);
                     response = inFromServer.nextLine();
                     System.out.println(response);
                     if (!response.startsWith("354")) {
                           emailClient.close();
                           throw new Exception("354 reply not received from server.");
                     }
                     System.out.println("this is after fifth if");
                     // Send message data.
                     String subject="Hello"; // the title of the email or rather subject
                     String bodymessage ="Hi, I am spam"; // the text inside the email--- change it 
                     String attachment1 ="Content-Type: multipart/alternative; boundary =\"++++++++\"\r\n"+"--++++++++\r\n" + "Content-Type: text/plain; charset=\"UTF-8\"\r\n\r\n"+bodymessage +"\r\n"; //image/png
                     String attachment2 ="--++++++++\r\n"+"Content-Transfer-Encoding: base64 \r\n" + "Content-Type: text/plain; name=\"uscaicon.jpg\"\r\n\r\n" + "AAAAAAAABRRRESXCFSSFF";// turn content type to txt but sends image.
                     String messageLine1 = "From: " + emails2[randomNumber2] + "\r\nTo: " + emails[randomNumber] +"\r\nSubject:"+subject+"\r\n" +bodymessage;// "MIME-Version: 1.0 \r\n" + attachment1 + attachment2;
                     System.out.println(messageLine1);
                     outToServer.writeBytes(messageLine1);
                     // End with line with a single period.
                     String messageLine2 = "\r\n.\r\n";
                     System.out.println(messageLine2);
                     outToServer.writeBytes(messageLine2);
                     response = inFromServer.nextLine();
                     System.out.println(response);
                     if (!response.startsWith("250")) {
                           emailClient.close();
                           System.out.println("this is inside 250");
                           throw new Exception("250 reply not received from server.");
                     }
                     a++;
              }
                     System.out.println("this is after sixth if");
                     // Send QUIT command.
                     String commandQUIT = "QUIT";
                     System.out.println(commandQUIT);
                     outToServer.writeBytes(commandQUIT);
                     response = inFromServer.nextLine();
                     System.out.println(response);
                     if (!response.startsWith("221")) {
                           emailClient.close();
                           throw new Exception("221 reply not received from server.");
                     }
                     System.out.println("this is after seventh if");
                     // Close the connection
                     emailClient.close();

              } catch (Exception e) {
                     System.err.print("Caught Exception" + e.getMessage());
                     //System.out.println("inside catch second time");
              }
              //emailClient.close();
              //a++;
              }
       //emailClient.close();

       //}

}

