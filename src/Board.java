//------------------------------------------------------------------------------
/** Codie Cottrell						                                      	
 Game of Set - Board Class	                                 						
 CS 110 B: Java v1.
 		                                 				   	
 This is the Board class, It is a 2D array that mimics a 3x4 board set up with a 
 row and column set up. 
 */							                                             		
//-------------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class Board
{
   private Deck deck;      // a deck thats needed
   private int row = 3;    // starting row val
   private int column = 4; // starting column val
   private int numAdd3 = 0;// number of times add3 has been implemented
   
   private ArrayList<ArrayList<BoardSquare>> board; // the board itself 
   
   /**
      the Board constructor creates a board that has 3 rows and each is an arrayList 
      in the arrays each item is a BoardSquare.
   **/
   public Board(Deck deck)
   {
      board = new ArrayList<ArrayList<BoardSquare>>();
      this.deck = deck;
      for(int i = 0; i < row; i++)
      {
         board.add(new ArrayList<BoardSquare>());
         for (int j = 0; j < column; j++)
         {
            board.get(i).add(new BoardSquare(deck.getTopCard(),i,j));
         }
      }
   }
    
   /**
      Thr replaceCard method, looks at a boardsquare in a column and row
      takes that boardsquare, removes it then adds in a new one.
      @param a card to be put in.
      @param a row.
      @param a column.
   **/
   public void replaceCard(Card card, int row, int col)
   {
      board.get(row).remove(col);
      board.get(row).add(col, new BoardSquare(card,row,col));
   }
   
   /**
      this removes a card from the board but only when it wont be replaced by another
   */
   public void removeCard(int row, int col)
   {
      board.get(row).remove(col);
   }
   
   /**
      this re-orders the array so that there are no null indices
   */
   public void reOrder()
   {
      board.removeAll(Arrays.asList(null,""));
   }
   
   
   
   /**
      the add3 method makes another column and with new cards.
      @param the deck that we are using.
   **/
   public void add3(Deck deck)
   {
      this.deck = deck;
      if (!(deck.remCards() < 3) && numAdd3 < 2)
      {
         ++column;
         for(int i = 0; i < row; i++)
         {
            board.add(new ArrayList<BoardSquare>());
            for (int j = column-1; j <= column-1; j++)
            {
               board.get(i).add(new BoardSquare(deck.getTopCard(),i,j));
            }
         }
         ++numAdd3;
      }
   }
   
   /**
      gets the boardsquare at certain row and column
      @param row of the square.
      @param column of the square.
      @return the boardsquare.
   **/
   public BoardSquare getBoardSquare(int row, int col)
   {
      return board.get(row).get(col);
   }
   
   /**
      gets the card at certain row and column
      @param row of the card.
      @param column of the column.
      @return the card.
   **/
   public Card getCard(int row, int col)
   {
      return board.get(row).get(col).getCard();
   }
   
   
   /**
      gives the number of rows in the board
   **/
   public int numRows()
   {
      return row;
   }
   
   /**
   returns the number of columns in the board
   **/
   public int numCols()
   {
      return column;
   }
   
   /**
      returns the number of times one added three cards to the board
   */
   public int getNumAdd3()
   {
      return numAdd3;
   }
   
   /**
      sets the number of times one added three cards to the board to one less
      meant to be used when a new set is found.
   */
   public void reduceNumAdd3()
   {
      if (numAdd3 != 0)
      {
         numAdd3 -= 1;
         column -=1;
      }
   }
   
   
   /**
      sets the number of times one added three cards to the board to 0
      meant to be used as a reset.
   */
   public void resetNumAdd3()
   {
      numAdd3 = 0;
   }
   
   
   /**
      used for at the end of the game when there are no cards left
   */
   public void reduceCol()
   {
      column -=1;
   }
   
   /**
      getSize gets the size of the board, Which should be normal size.
      but is handy when the game is ending.
   */
   public int getSize()
   {
      return board.size();
   }
   
   /**
      @override 
      The toString method makes the board readable.
      @return the table as a 3x4 matrix look.
   **/
   public String toString()
   {
      String cards = "";
      for(int i = 0; i < row; i++)
      {
         cards+="\n";
         for (int j = 0; j < column; j++)
         {
            String temp = board.get(i).get(j).getCard().toString();
            cards += temp + "          "; // there is no method to my madness here
                                          // could have done a manipulated string
         }
      }
      return cards;
   }
}
   