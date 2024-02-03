/*import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class webserver {

	public static void main(String[] args) {
			ServerSocket myS = null;
			String contentType; 
			String data = null;
			try{
				myS = new ServerSocket(80);
				while(true){
					//waiting....
					Socket clientSocket = myS.accept();// blocking
					//get a client , input stream, output stream
					Scanner inFClient = new Scanner(clientSocket.getInputStream());
					DataOutputStream outTClient = new 
					DataOutputStream(clientSocket.getOutputStream());
					//dummy message
					//Find the file name
					String requestline = inFClient.nextLine(); // blocking
					System.out.println(requestline);
					String[] temp = requestline.split(" ");
					String path = temp[1];
					System.out.println(path); // file path // get what files they want
					byte[] buffer = null;
					//get the file // catch exceptions try and catch if they did not get the file right show 408 error.
					Path filepath = Paths.get("." + path);// ./US.png // set path in stone //change the content of "" to html // change whats in get
					//Path filePath = Paths.get();
					if(Files.exists(filepath) == false){
						System.out.println("Error: 404 No such request Exists.");
						filepath = Paths.get("./download.jfif");
						contentType = "text/html";
						buffer = Files.readAllBytes(filepath);
						data = Files.readString(filepath);
						String message = createMessage(contentType, filepath, buffer);
						outTClient.writeBytes(message);
						outTClient.write(buffer, 0, buffer.length);
						outTClient.flush();
					}else{
						buffer = Files.readAllBytes(filepath);
						String extension = fileExtention(filepath);
						String type = fileType(extension);
						contentType = type + "/" + extension;
						if(type == "image" ){
							data = "<div><image src = "+ filepath +"</div>";
						}else if(type == "text"){
							data = Files.readString(filepath);
						}
						String message = createMessage(contentType, filepath, buffer);
						System.out.println(message);
						outTClient.writeBytes(message);
						outTClient.write(buffer, 0, buffer.length);
						outTClient.flush();
					}
					//close
					inFClient.close();
					outTClient.close();
					clientSocket.close();
				}
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
 
	}

	public static String createMessage(String type, Path filepath, byte[] buffer) {
		// swap images
		String statusline = "HTTP/1.1 200 \r\n";
		String typeline = "Content-Type: " + type + "\r\n"; // change the type to html style
		String separator = "\r\n";
		String lengthlines = "Content-Length:" + buffer.length + "\r\n";
		//String message = statusline + contentline + "\r\n" + data;
		String message = statusline + typeline + lengthlines + separator;
		return message;
	}

	public static String fileExtention(Path filePath){
		String extension = null;
		String fileName = filePath.toString();
		int index = fileName.lastIndexOf('.');
		if(index > 0) {
			extension = fileName.substring(index + 1);
		}
		return extension;
	}

	public static String fileType(String extention) {
		switch (extention) {
		case "png":
			return "image/png";
		case "jpg":
			return "image/jpg";
		case "html":
			return "text";
		case "txt":
			return "text";
		}
		return "No such Type";
	}
}*/
