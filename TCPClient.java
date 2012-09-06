

// TCPClient.java
// A client program implementing TCP socket
import java.net.*; 
import java.io.*; 
import java.util.Scanner;

public class TCPClient { 
  public static void main (String args[]) 
  {// arguments supply message and hostname of destination  
    int gState = 0;
    while(true)
    {
      System.out.println("CLIENT gState: "+ gState);
      Game game = new Game(gState);
      gState = game.getGamegState();
    }
  }
}
class Game extends Thread {
  DataInputStream input;
  DataOutputStream output;
  Socket s = null; 
  String moveChoice;
  int gState ;
  public Game(int setgState)
  {
    try{ 
      gState = setgState;
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
  public int getGamegState()
  {
    return gState;
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
      if (gState == 0)
      {
        gState = -1;
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
      else if (false) 
      {
        int nb = input.readInt();
        byte[] digit = new byte[nb];
        //Step 2 read byte
        for(int i = 0; i < nb; i++)
          digit[i] = input.readByte();
        if (nb == 1)
          gState = toInt(digit, 0);
      }
      else
      {
        System.out.println("CLIENT ELSE");
      }
    }
    catch (UnknownHostException e){ 
      System.out.println("Sock:"+e.getMessage());}
    catch (EOFException e){
      System.out.println("EOF:"+e.getMessage()); }
    catch (IOException e){
      System.out.println("IO2:"+e.getMessage());} 
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


