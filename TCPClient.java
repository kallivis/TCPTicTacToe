

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
  //  DataInputStream input;
  // DataOutputStream output;
  BufferedReader in; 
  PrintWriter out;
  Socket s = null; 
  String moveChoice;
  public Game()
  {
    try{ 
      int serverPort = 6880;
      String ip = "localhost";
      String data = "Hello, How are you?"; 


      s = new Socket(ip, serverPort); 
      // input = new DataInputStream( s.getInputStream()); 
      //  output = new DataOutputStream( s.getOutputStream()); 
      out = new PrintWriter(s.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(s.getInputStream()));
      String inputSplash;
      while ((inputSplash = in.readLine()) != null) {   
        if (inputSplash.equals("done"))
          break;
        else
          System.out.println(inputSplash);
      }
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
      System.out.println("CLIENT RUN");
      //  int nb = input.readInt();
      //  byte[] digit = new byte[nb];
      //Step 2 read byte
      // for(int i = 0; i < nb; i++)
      //   digit[i] = input.readByte();

      //    String st = WriterReader.readItem(input);
      //  st = new String(digit);

      String inputLine, outputLine;
      Scanner scanIn = new Scanner(System.in);
      outputLine = scanIn.next();
      out.println(outputLine);
      if (outputLine.equals("quit")) 
        return;


      out.flush();
      System.out.flush();
      while ((inputLine = in.readLine()) != null) {   
        out.flush();
        System.out.println("CLIENT LOOP");
        System.out.println(inputLine);
        System.out.flush();
        out.flush();
        outputLine = scanIn.next();
        out.println(outputLine);

        out.flush();
        //output.writeInt(moveChoice.length());
        //output.writeBytes(moveChoice); // UTF is a string encoding
        //WriterReader.writeItem(output,outputLine);

        if (outputLine.equals("quit")) 
          break;

        System.out.println("CLIENT LOOP END");
      }

      scanIn.close();         
    }
    catch (UnknownHostException e){ 
      System.out.println("Sock:"+e.getMessage());}
    catch (EOFException e){
      System.out.println("EOF:"+e.getMessage()); }
    catch (IOException e){
      System.out.println("IO2:"+e.getMessage());} 
    finally {
      try {
        s.close();
      }
      catch (IOException e) {/*close failed*/}
    }
  } 
  public static int toInt(byte[] bytes, int offset) {
    int ret = 0;
    for (int i=0; i<4 && i+offset<bytes.length; i++) {
      ret <<= 8;
      ret |= (int)bytes[i] & 0xFF;
    }
    return ret;
  }
}


