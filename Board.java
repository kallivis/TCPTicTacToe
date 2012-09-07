import java.lang.Math;
class Board
{
  private String[][] currentBoard;
  private int boardSize;
  private int rootSize;
  public Board(int size)
  {
    if (size % 2 != 0)
      size++;
    boardSize = size;
    rootSize = (int) Math.sqrt(size); 
    setupBoard();
  }
  public Board()
  {
    boardSize = 9;
    rootSize = (int)Math.sqrt(9); 
    setupBoard();
  }
  private void setupBoard()
  {
    currentBoard = new String[rootSize][rootSize];
    int num = 0;
    for (int i = 0; i < rootSize; i++) 
    {
      for (int k = 0; k < rootSize; k++) 
      {
        currentBoard[i][k] = Integer.toString(++num); 
      }
    }
  }
  public void markAt(int pos, String mark)
  {
    int row = (int)Math.floor((double)(pos / rootSize));
    int col = (pos % rootSize) - 1;
    System.out.println("row: "+row);
    System.out.println("col: "+col);
    currentBoard[row][col] = mark;
  }
  public String hasWinner()
  {
    String winner = null;
    if ((winner = checkColumns()) != null)
      return winner;
    else if ((winner = checkRows()) != null)
      return winner;
    else if ((winner = checkDiagnols()) != null)
      return winner;
    else 
      return null;
  }
  private String checkColumns()
  {
    String mark = null;
    for (int column = 0; column < rootSize; column++)
    {
      for (int row = 0; row < rootSize; row++)
      {
        if (row == 0) 
          mark = currentBoard[row][column];
        else if (!mark.equals(currentBoard[row][column]))
            {
              mark = null;
              break;
            }

      }
      if (mark != null)
      break;
    }
    return mark;
  }
  private String checkRows()
  {
    String mark = null;
    for (int row = 0; row < rootSize; row++)
        {
          for (int column = 0; column < rootSize; column++)
        {
          if (column == 0) 
          mark = currentBoard[row][column];
          else if (!mark.equals(currentBoard[row][column]))
              {
                mark = null;
                break;
              }

        }
        if (mark != null)
        break;
        }
        return mark;
  }
  private String checkDiagnols()
  {
    boolean bothFailed = false;
    String mark1 = null;
    String mark2 = null;
    for (int i = 0; i < rootSize; i++)
          {
            if (i == 0)
          {
            mark1 = currentBoard[i][i];
            mark2 = currentBoard[rootSize - 1 - i][rootSize - 1 - i];
          }
          else if ((mark1 = mark1.equals(currentBoard[i][i]) ? currentBoard[i][i] : null) == null && (mark2 = mark2.equals(currentBoard[i][i]) ? currentBoard[i][i] : null) == null)
              {
                bothFailed = true;
                break;
              }
          }
          if (bothFailed)
          return null;
          else if (mark1 != null)
          return mark1;
          else 
          return mark2;
  }
  public String[][] getCurrentBoard()
  {
    return currentBoard;
  }
}
