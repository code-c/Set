import javafx.application.Application; 
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.geometry.Orientation;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.io.FileNotFoundException;


public class CardPane extends TilePane
{
   private final int V_GAP = 20;
   private final int WIDTH = 90;
   private final int HEIGHT = 20;
   private final int COLUMNS = 1;
   
   public int col;
   public int row;
   
   private boolean selected;
   private int number;                       // number of shapes to draw
   private Paint color = Color.rgb(0,0,0);   // what to fill the shapes with
   public BoardSquare square;   // the boardsquare in the pane
   private String style = "-fx-background-color: #34495e;";
   
   public CardPane(BoardSquare square)
   {
      this.setVgap(V_GAP);
      this.setPrefColumns(COLUMNS);
      this.setOrientation(Orientation.VERTICAL);
      this.setTileAlignment(Pos.CENTER);
      this.setPrefTileWidth(WIDTH);
      this.setPrefTileHeight(HEIGHT);
      this.setAlignment(Pos.CENTER);
      this.setStyle("-fx-background-insets: 2, 2, 2, 2;"); 
      this.setStyle("-fx-background-radius: 10px, 10px, 10px, 10px;");
      this.square = square;
      if (square.isSelected())
         hoveredOverStyle();
      
      setNumber();
      setColor();      
      drawShape();

   }
   
   public CardPane(BoardSquare square, int row, int col)
   {
      this.setVgap(V_GAP);
      this.setPrefColumns(COLUMNS);
      this.setOrientation(Orientation.VERTICAL);
      this.setTileAlignment(Pos.CENTER);
      this.setPrefTileWidth(WIDTH);
      this.setPrefTileHeight(HEIGHT);
      this.setAlignment(Pos.CENTER);
      this.setStyle("-fx-background-insets: 2, 2, 2, 2;"); 
      this.setStyle("-fx-background-radius: 10px, 10px, 10px, 10px;");
      this.square = square;
      if (square.isSelected())
         hoveredOverStyle();
      
      this.row = row;
      this.col = col;
      
      setNumber();
      setColor();      
      drawShape();

   }

   
   /**
      setShape sets shape shape
   */
   private void drawShape()
   {
      Card.Shape shape = square.getCard().getShape();
      switch(shape) {
         case OVAL:
            for (int i = 0; i < this.number; i++)
               drawEllipse(color);  
            break; 
              
         case SQUIGGLE:
            for (int i = 0; i < this.number; i++)
               drawSquiggle(color);
            break;  
             
         case DIAMOND:
            for (int i = 0; i < this.number; i++)
               drawDiamond(color);
            break;
            
         default:
            break;
      }
   }

   
   /**
      setColor sets the color of the soon to be shape
   */
   private void setColor()
   {
      Card.Color col = square.getCard().getColor();
      switch(col) {
         case RED:
            this.color = Color.web("#e67e22");  
            break; 
              
         case GREEN:
            this.color = Color.web("#16a085");
            break;  
             
         case PURPLE:
            this.color =  Color.web("#8e44ad");
            break;
            
         default:
            this.color = Color.rgb(256,256,256);
            break;
      }

   }
   
   /**
      setNumber sets the number of cards to be
   */
   private void setNumber()
   {
      Card.Number num = square.getCard().getNumber();
      switch(num) {
         case ONE:
            this.number = 1;  
            break; 
              
         case TWO:
            this.number = 2;
            break;  
             
         case THREE:
            this.number = 3;
            break;
            
         default:
            this.number = 0;
            break;
      }
   }
   
   
   /**
      drawEllipse draws the elipse
   */
   private void drawEllipse(Paint color)
   {
      Card.Fill fill = square.getCard().getFill();
      Ellipse e = new Ellipse(28,15);
      e.setStrokeLineJoin(StrokeLineJoin.ROUND);
      switch(fill) {
         case SOLID:
            e.setFill(color);
            e.setStroke(color);
            e.setStrokeWidth(5.0);  
            break; 
              
         case STRIPED:
            try{
               Image img = new Image("resources/hatch.png");
               e.setFill(new ImagePattern(img));
            } catch (Exception exception) {
               e.setFill(Color.web("#bdc3c7"));
            }
            e.setStroke(color);
            e.setStrokeWidth(5.0);
            break;  
             
         case OUTLINED:
            e.setStroke(color);
            e.setStrokeWidth(5.0);
            e.setFill(Color.TRANSPARENT);
            break;
            
         default:
            e.setFill(Color.rgb(0,0,0));
            break;  
      }
      this.getChildren().add(e);
   }
   
   /**
      drawSquiggle draws the squiggle (as a rectangle for now)
   */
   private void drawSquiggle(Paint color)
   {
      Rectangle r = new Rectangle(50,30);
      r.setStrokeLineJoin(StrokeLineJoin.ROUND);
      switch(square.getCard().getFill()) {
         case SOLID:
            r.setFill(color);
            r.setStroke(color);
            r.setStrokeWidth(5.0);  
            break; 
              
         case STRIPED:
            try{
               Image img = new Image("resources/hatch.png");
               r.setFill(new ImagePattern(img));
            } catch (Exception exception) {
               r.setFill(Color.web("#bdc3c7"));
            }
            r.setStroke(color);
            r.setStrokeWidth(5.0);
            break;  
             
         case OUTLINED:
            r.setStroke(color);
            r.setStrokeWidth(5.0);
            r.setFill(Color.TRANSPARENT);
            break;
            
         default:
            r.setFill(Color.rgb(0,0,0));
            break;  
      }    
      this.getChildren().add(r);
   }

   /**
      drawDiamond draws the diamond
   */
   private void drawDiamond(Paint color)
   {
      Polygon d = new Polygon(0.0, 15.0,  30.0, 0.0,   60.0, 15.0,   30.0, 30.0);
      d.setStrokeLineJoin(StrokeLineJoin.ROUND);
      switch(square.getCard().getFill()) {
         case SOLID:
            d.setFill(color); 
            d.setStroke(color); 
            d.setStrokeWidth(5.0);
            break; 
              
         case STRIPED:
            try{
               Image img = new Image("resources/hatch.png");
               d.setFill(new ImagePattern(img));
            } catch (Exception exception) {
               d.setFill(Color.web("#bdc3c7"));
            }
            d.setStroke(color);
            d.setStrokeWidth(5.0);
            break;  
             
         case OUTLINED:
            d.setStroke(color);
            d.setStrokeWidth(5.0);
            d.setFill(Color.TRANSPARENT);
            break;
            
         default:
            d.setFill(Color.rgb(0,0,0));
            break;  
      }    
      this.getChildren().add(d); 
   }
 
   // event handler for a user hovering over a Card
   //@Override
   public void hoveredOverStyle()
   {
      this.setStyle("-fx-background-color: #2c3e50;");
      this.setScaleX(1.1);
      this.setScaleY(1.1);
   }
   
   // event handler for a user stopping hovering over a card
   public void normalStyle()
   {
      this.setStyle("-fx-background-color: #34495e;");
      this.setScaleX(-1);
      this.setScaleY(-1);
   }
   
   //this gets the boardsquare in this pane
   public BoardSquare getBoardSquare()
   {
      return square;
   }
   
   // gets the row of the pane
   public int getRow()
   {
      return row;
   }
   
   // gets the column of the pane
   public int getCol()
   {
      return col;
   }
}
