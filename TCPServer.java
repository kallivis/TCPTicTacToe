
// TCPServer.java
// A server program implementing TCP socket
import java.net.*; 
import java.io.*; 

public class TCPServer { 
  public static void main (String args[]) 
  { 
    try{ 
      int serverPort = 6880; 
      ServerSocket listenSocket = new ServerSocket(serverPort); 

      System.out.println("server start listening... ... ...");

      while(true) { 
        Socket clientSocket = listenSocket.accept(); 
        Connection c = new Connection(clientSocket); 
      } 
    } 
    catch(IOException e) {
      System.out.println("Listen :"+e.getMessage());}

  }
}

class Connection extends Thread { 
  DataInputStream input; 
  DataOutputStream output; 
  Socket clientSocket; 

  public Connection (Socket aClientSocket) { 
    try { 
      clientSocket = aClientSocket; 
      input = new DataInputStream( clientSocket.getInputStream()); 
      output =new DataOutputStream( clientSocket.getOutputStream()); 
      String splash ="P2P Tic Tac Toe \n" 
          +"1 | 2 | 3 \n" 
          +"4 | 5 | 6 \n" 
          +"7 | 8 | 9 \n\n"
          +"Choose a number to make your move: ";
      output.writeInt(splash.length());
      output.writeBytes(splash);

      this.start(); 
    } 
    catch(IOException e) {
      System.out.println("Connection:"+e.getMessage());
    } 
  } 

  public void run() { 
    try { // an echo server 
      //  String data = input.readUTF();

      System.out.println("test82");
      FileWriter out = new FileWriter("test.txt");
      BufferedWriter bufWriter = new BufferedWriter(out);

      //Step 1 read length
      int nb = input.readInt();
      System.out.println("Read Length"+ nb);
      byte[] digit = new byte[nb];
      //Step 2 read byte
      System.out.println("Writing.......");
      for(int i = 0; i < nb; i++)
        digit[i] = input.readByte();

      String st = new String(digit);
      bufWriter.append(st);
      bufWriter.close();
      System.out.println ("receive from : " + 
          clientSocket.getInetAddress() + ":" +
          clientSocket.getPort() + " message - " + st);

      //Step 1 send length
      //   output.writeInt("Cody".length());
      //Step 2 send length
      //  output.writeBytes("Cody"); // UTF is a string encoding
      //  output.writeUTF(data); 
      // output.writeInt("Choose a Number to make a move: ".length()); 
      // output.writeBytes("P2P Tic Tac Toe");
      // output.writeBytes(" 1 | 2 | 3 "); 
      // output.writeBytes(" 4 | 5 | 6 "); 
      // output.writeBytes(" 7 | 8 | 9 "); 


    } 
    catch(EOFException e) {
      System.out.println("EOF:"+e.getMessage()); } 
    catch(IOException e) {
      System.out.println("IO:"+e.getMessage());}  

    finally { 
      try { 
        clientSocket.close();
      }
      catch (IOException e){/*close failed*/}
    }
  }
}

