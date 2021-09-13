//------------------------------------------------------------------------------
/** Codie Cottrell						                                      	
 Game of Set - Deck Class	                                 						
 CS 110 B: Java
 		                                 				   	
 This is the Deck class and allows one to create a deck of set cards
 Which can then be shuffled and a top card can be retrieved.
 */							                                             		
//-------------------------------------------------------------------------------


import java.util.Random;
import java.util.ArrayList;

public class Deck 
{
   /** 
   *  Number of cards in standard set deck
   **/
   public final static int CARDS_IN_DECK = 81;


   /** The collection of Cards */
   private ArrayList<Card> deck;
   
   /**
    * Constructs a regular 81-card deck.  Initially, the cards
    * are in a sorted order.  The shuffle() method can be called to
    * randomize the order.  
    */
   public Deck()
   {
      deck = new ArrayList<Card>(CARDS_IN_DECK);

      for (Card.Number num : Card.Number.values())
      {
         for (Card.Color col : Card.Color.values())
         {
           for (Card.Fill fill : Card.Fill.values())
           {
               for (Card.Shape shap : Card.Shape.values())
               {
                  deck.add(new Card(num, col, fill, shap));
               }
           }
         }
      }
       
   }
   /** 
     * Remove and return the top Card on the Deck
     * @return A reference to a Card that was top on the Deck
     */
   public Card getTopCard()
   {
      Card c = deck.remove(deck.size()-1);  //  remove it
      return c;
   }
   


   /**
     * isEmpty sees if the deck has any cards left
     * @return returns true if it is and false if it isnt.
   */   
   public boolean isEmpty()
   {
      if (deck.size() == 0)
         return true;
      return false;
   }
   
   /**
      returns the number of cards in the deck left
   */
   public int remCards()
   {
      return deck.size();
   }
   
   /** 
     * Randomize the order of Cards in Deck
     */

   public void shuffle()
   {
      int randNum;
      Card temp;
      Random r = new Random();
      for (int i = 0; i < deck.size(); i++)
      {
         randNum = r.nextInt(deck.size());
         temp = deck.get(i);
         deck.set(i,deck.get(randNum));
         deck.set(randNum,temp);
      }      
   }
   
   /**
      makes the deck a readable out put
   */
   public String toString()
   {
      String deckString = " ";
      for(int i = 0; i<CARDS_IN_DECK; ++i)
      {
         deckString += deck.get(i).toString() + "\n"; 
      }
      return deckString;
   }
}