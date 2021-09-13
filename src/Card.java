//------------------------------------------------------------------------------
/** Codie Cottrell						                                      	
 Game of Set - Card Class	                                 						
 CS 110 B: Java
 		                                 				   	
 This is the Card class and allows one to create a single card with custom
 parameters number color fill and shape, along with comparing three cards to see of they are a set.
 */							                                             		
//-------------------------------------------------------------------------------

public class Card
{
   public static enum Shape{OVAL,SQUIGGLE, DIAMOND}
   public static enum Color{RED, GREEN, PURPLE}
   public static enum Fill{SOLID, STRIPED, OUTLINED}
   public static enum Number{ONE, TWO, THREE}
   
   private Shape shape;
   private Color color;
   private Fill fill;
   private Number number;   
   
   // constructor      
   public Card(Number number, Color color, Fill fill, Shape shape)
   {
      this.shape = shape;
      this.color = color;
      this.fill = fill;
      this.number = number;
      
   }
   
   /**
   the isSet method takes three cards and compares them to see if they are a set
   @param Card    first card to be compared.
   @param Card    second card to be compared.
   @param Card    third card to be compared.
   @return truth  true of its a set false if not.
   **/
   public static boolean isSet(Card c1, Card c2, Card c3)
   {
      boolean numberTruth = false; 
      boolean fillTruth = false;
      boolean colorTruth = false;
      boolean shapeTruth = false;
      
      // test each of the equivalences
      if (((c1.number == c2.number) && (c2.number == c3.number)) || (((c1.number != c2.number) && (c2.number != c3.number)) && (c1.number != c3.number)))
         numberTruth = true;
               
      if (((c1.fill == c2.fill) && (c2.fill == c3.fill)) || (((c1.fill != c2.fill) && (c2.fill != c3.fill)) && (c1.fill != c3.fill)))
         fillTruth = true;
                
      if (((c1.color == c2.color) && (c2.color == c3.color)) || (((c1.color != c2.color) && (c2.color != c3.color)) && (c1.color != c3.color)))
         colorTruth = true;
         
      if (((c1.shape == c2.shape) && (c2.shape == c3.shape)) || (((c1.shape != c2.shape) && (c2.shape != c3.shape)) && (c1.shape != c3.shape)))
         shapeTruth = true;
               
         
      //now compare the weird sets
         // these weird sets are ones where two and three are eqaul but one and three arnt
         // above these werent taken care of and would make the program read true for these sets of not specified
      if (((c1.number == c2.number) && (c2.number != c3.number)) || ((c1.number == c2.number) && (c1.number != c3.number)))
         return false;
      
      if (((c2.number == c3.number) && (c2.number != c1.number)) || ((c2.number == c3.number) && (c3.number != c1.number)))
         return false;
         
      if (((c1.fill == c2.fill) && (c2.fill != c3.fill)) || ((c1.fill == c2.fill) && (c1.fill != c3.fill)))
         return false;
         
      if (((c3.fill == c2.fill) && (c2.fill != c1.fill)) || ((c3.fill == c2.fill) && (c3.fill != c1.fill)))
         return false;
         
      if (((c3.color == c2.color) && (c2.color != c1.color)) || ((c3.color == c2.color) && (c1.color != c3.color)))
         return false;
         
      if (((c1.color == c2.color) && (c2.color != c3.color)) || ((c1.color == c2.color) && (c1.color != c3.color)))
         return false;
         
      if (((c3.shape == c2.shape) && (c2.shape != c1.shape)) || ((c3.shape == c2.shape) && (c1.shape != c3.shape)))
         return false;
         
      if (((c1.shape == c2.shape) && (c2.shape != c3.shape)) || ((c1.shape == c2.shape) && (c1.shape != c3.shape)))
         return false;
         
         
      //return the final statement
      if ((numberTruth || fillTruth) || (colorTruth || shapeTruth))
         return true;
         
      return false;   
         
   }
   
   
   /**
      The toString method convers a card to a string in a readable fashing.
      this consisted of the number, color, shape and fill
      @return cardName the name of the card.
   **/
   public String toString()
   {
      return number.name() +"_"+ color.name() +"_"+ shape.name() +"_"+ fill.name();
   }
   
   /**
      getter for color
   */
   public Color getColor()
   {
      return this.color;
   }
   
   /**
      getter for shape
   */
   public Shape getShape()
   {
      return this.shape;
   }

   /**
      getter for fill
   */
   public Fill getFill()
   {
      return this.fill;
   }
   
   /**
      getter for number
   */
   public Number getNumber()
   {
      return this.number;

   }


}