

// TCPClient.java
// A client program implementing TCP socket
import java.net.*; 
import java.io.*; 
import java.util.Scanner;

public class TCPClient { 
  public static void main (String args[]) 
  {// arguments supply message and hostname of destination  
      Game game = new Game();
  }
}
    class Game extends Thread {
      DataInputStream input;
      DataOutputStream output;
            Socket s = null; 
          public Game()
          {
        try{ 
            int serverPort = 6880;
            String ip = "localhost";
            String data = "Hello, How are you?"; 


            s = new Socket(ip, serverPort); 
            input = new DataInputStream( s.getInputStream()); 
            output = new DataOutputStream( s.getOutputStream()); 
            this.start();
        }
catch (UnknownHostException e){ 
      System.out.println("Sock:"+e.getMessage());}
    catch (EOFException e){
      System.out.println("EOF:"+e.getMessage()); }
    catch (IOException e){
      System.out.println("IO:"+e.getMessage());} 
          } 



          //Step 1 send length
          //System.out.println("Length"+ data.length());
          // output.writeInt(data.length());
          //Step 2 send length
          //System.out.println("Writing.......");
          //output.writeBytes(data); // UTF is a string encoding

          //Step 1 read length
          public void run()
          {
            try {
              System.out.println("TEST");
            String moveChoice;
            int nb = input.readInt();
            byte[] digit = new byte[nb];
            //Step 2 read byte
            for(int i = 0; i < nb; i++)
              digit[i] = input.readByte();

            String st = new String(digit);
            System.out.println(st); 
            Scanner scanIn = new Scanner(System.in);
            moveChoice = scanIn.nextLine();
            scanIn.close();         

            output.writeInt(moveChoice.length());
            output.writeBytes(moveChoice); // UTF is a string encoding
            }
catch (UnknownHostException e){ 
      System.out.println("Sock:"+e.getMessage());}
    catch (EOFException e){
      System.out.println("EOF:"+e.getMessage()); }
    catch (IOException e){
      System.out.println("IO2:"+e.getMessage());} 
          } 
          }
    

