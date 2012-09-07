import java.net.*;
import java.io.*;

public class Server {
  public static void main(String[] args) throws IOException {

    ServerSocket serverSocket = null;
    Board board = new Board();
    try {
      serverSocket = new ServerSocket(4444);

      System.out.println("server start listening... ... ...");
    } catch (IOException e) {
      System.err.println("Could not listen on port: 4444.");
      System.exit(1);
    }

    Socket clientSocket = null;
    try {
      clientSocket = serverSocket.accept();
    } catch (IOException e) {
      System.err.println("Accept failed.");
      System.exit(1);
    }

    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(
        new InputStreamReader(
          clientSocket.getInputStream()));
    String inputLine, outputLine;

      String splash = "start\n"
        +"TCP Tic Tac Toe \n" 
        +"1 | 2 | 3 \n" 
        +"4 | 5 | 6 \n" 
        +"7 | 8 | 9 \n\n"
        +"" 
        +"Choose a number to make your move: \n"
        +"end";


    out.println(splash);

    while ((inputLine = in.readLine()) != null) {
      board.markAt(Integer.parseInt(inputLine), "X");
      outputLine = "";
      outputLine = outputLine.concat("start\n"); 
      outputLine = outputLine.concat(formatBoard(board.getCurrentBoard())); 
      outputLine = outputLine.concat("Choose a number to make your move: \n"); 
      outputLine = outputLine.concat("end"); 
      out.println(outputLine);
      if (outputLine.equals("quit"))
        break;
    }
    out.close();
    in.close();
    clientSocket.close();
    serverSocket.close();
  }
  private static String formatBoard(String[][] b)
  {
    String formatedBoard = "";
    for (int i = 0; i < b.length; i++)
    {
    for (int k = 0; k < b.length; k++)
    {
      formatedBoard = formatedBoard.concat(" "+(b[i][k]));
      if (k < (b.length -1))
        formatedBoard = formatedBoard.concat(" "+"|");
    }

        formatedBoard = formatedBoard.concat(" "+"\n");
    }
    return formatedBoard;
  }
}
