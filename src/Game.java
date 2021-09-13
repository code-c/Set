//------------------------------------------------------------------------------
/** Codie Cottrell						                                      	
 Game of Set - Game Class	                                 						
 CS 110 B: Java
 		                                 				   	
 This is the game class, It is the driver for the text based game
 */							                                             		
//-------------------------------------------------------------------------------
import java.util.ArrayList;
class Game
{
   private Board board;   //the board
   private Deck deck;     //a deck for the board
   private ArrayList<BoardSquare> selected; // the selected boardsquare
   private boolean done = false;    //tells you when the game is done


   /**
      a no arg constructor that initialized the game with 
      and board and a deck
   */   
   public Game()
   {
      selected = new ArrayList<BoardSquare>(3);
      deck = new Deck();
      deck.shuffle();
      board = new Board(deck);
   }


   /**
      outofCards tell you when the deck has been dealt
      return returns true of it is out of cards
   */   
   public boolean outOfCards()
   {
      return deck.isEmpty();
   }
   
   /**
      This add's three cards from the deck to the board
   */   
   public void add3()
   {
      board.add3(deck);
   }
   
   /**
      returns the number of cards remaining in the deck
   */
   public String remCards()
   {
      return Integer.toString(deck.remCards());
   }
 

   /**
      this is a to string method that just calls the board to string
   */   
   public String toString()
   {
      return board.toString();
   }
      

   /**
      addToSelected  selects the card
   */   
   public void addToSelected(int row, int col)
   {     
      selected.add(board.getBoardSquare(row, col));
   }


   /**
      numSelected runs through the whole board and counts
      how many cards are selected and returns that number
      @return returns the number of selected cards
   */   
   public int numSelected()
   {
      return selected.size();
   }


   /**
      testSelected tests to see if the number of cards selected is a set
      It also unselects all the cards and removes then if its a set
      @return saying it was a set with the string of cards or not a set
   */   
   public void testSelected()
   {
      
      // checks to see if they are a set and if they are then it replaces the cards
      if (Card.isSet(selected.get(0).getCard(), selected.get(1).getCard(), selected.get(2).getCard()))
      {     
         //going through the selected array and replacing each card and unselecting them
         for(int i = 0; i < 3; i++)
         {    
                  // regular event
            if(deck.remCards() > 0 && board.getNumAdd3() == 0)
            {
               board.replaceCard(deck.getTopCard(), selected.get(i).getRow(), selected.get(i).getColumn());
            }
                  //for when no cards are left and you are at the end
            else if(deck.remCards() == 0 && board.getNumAdd3() == 0)
            {
               done = true;
            }
                  // if it was a card that existed at added 3 cards it removes that card and doesnt replace it
            else if(selected.get(i).getColumn() == board.numCols()-1)
            {
               board.removeCard(selected.get(i).getRow(), selected.get(i).getColumn());
            }
                  // if it went through all of this it most likely is a card in the array that need replacing but there are
                  // cards in the added 3 slot so it replaces it with those cards.
            else
            {
               // this is so add3 actually works it just catches the out of bounds exception when an added card was selected in a set
               try {
                  board.replaceCard(board.getBoardSquare(i, board.numCols()-1).getCard(), selected.get(i).getRow(), selected.get(i).getColumn());
                  board.removeCard(i, board.numCols()-1);
               }
               catch(IndexOutOfBoundsException e){
                  try {
                     board.replaceCard(board.getBoardSquare(i, board.numCols()-2).getCard(), selected.get(i).getRow(), selected.get(i).getColumn());
                     board.removeCard(i, board.numCols()-2);
                  }
                  catch(IndexOutOfBoundsException exception){
                     board.replaceCard(board.getBoardSquare(i, board.numCols()-3).getCard(), selected.get(i).getRow(), selected.get(i).getColumn());
                     board.removeCard(i, board.numCols()-3);
                     //honestly if you get here and crashed the game congrats man
                  }
               }
            }
         }
         reduceNumAdds();
         clearSelected();
      }
      clearSelected();
   }

   
   /**
      removeSelected unselects the selected card on @param row and @param column
   */  
   public void removeSelected(int row, int col)
   {
      selected.remove(board.getBoardSquare(row, col));
   }
   
   /**
      this clears the selected pane
   */
   public void clearSelected()
   {   
      selected.clear();
   }
   
   /**
      tests to see if a boardsquare is selected
   */
   public boolean isSelected(BoardSquare square)
   {
      for(int i=0; i < selected.size(); i++)
        if(selected.get(i) == square)
            return true;
      return false;
   }

   /**
      getSelected gets the selected cards and returns an arraylist
      @return arraylist of selected boardsquares
   */   
   public ArrayList<BoardSquare> getSelected()
   {
      // returns the array
      return selected;
   }
   
   /**
      This gets a boardsquare in a specific spot on the board
      @param row of boardsquare
      @param col of boardsqaure
      @return Boardsquare at row and col
   */   
   public  BoardSquare getBoardSquare(int row, int col)
   {
      return board.getBoardSquare(row, col);
   }
   
   /**
      This returns whether the game is done or not
   */
   public boolean done()
   {
      return done;
   }
   
   /**
      this returns the number of rows in the game
   */
   
   public int numRows()
   {
      return board.numRows();
   }
   
   /**
      this returns the number of columns in the game
   */
   
   public int numCols()
   {
      return board.numCols();
   }
   
   /**
      resets the number of times you added three
   */
   private void resetNumAdds()
   {
      board.resetNumAdd3();
   }
   
   /**
      subtracts 1 from the number of times you added three
   */
   private void reduceNumAdds()
   {
      board.reduceNumAdd3();
   }
}

