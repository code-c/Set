//------------------------------------------------------------------------------
/** Codie Cottrell						                                      	
 Game of Set - BoardSquare Class	                                 						
 CS 110 B: Java
 		                                 				   	
 This is the BoardSquare class, It holds a card and row and column information.
 */							                                             		
//-------------------------------------------------------------------------------

class BoardSquare
{
   public int row;
   public int column;
   public Card card;
   public boolean selected;
   
   /**
      constructs a square that has a card and a row and col
      it sets these and then sets the sqaure as unselected.
   **/
   public BoardSquare(Card card, int row, int col)
   {
      this.card = card;
      this.row = row;
      column = col;
      selected = false;
   }
   
   /**
      selects the square
   **/
   public void select()
   {
      this.selected = true;
   }
   
   /**
      unselects the square
   **/
   public void unSelect()
   {
      this.selected = false;
   }
   
   /**
      tells if the square is selected or not.
   **/
   public boolean isSelected()
   {
      return selected;
   }
   
   /**
      returns the row the square is in
   **/
   public int getRow()
   {
      return row;
   }
   
   /**
      returns the column the square is in
   **/
   public int getColumn()
   {
      return column;
   }
   
      /**
      returns the Card that is at that boardSquare.
   **/
   public Card getCard()
   {
      return card;
   }
   
   /**
      returns the BoardSquare in string form only as the card
   */
   
   public String toString()
   {
      return card.toString();
   }
}