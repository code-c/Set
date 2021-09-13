import javafx.application.Application; 
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Orientation;

 
 public class Set extends Application {
 
   //Declare things we are going to need
   private Game game;
   private GridPane grid;
   private BorderPane mainPane;
   private VBox statusPane;
   private HBox buttonPane;
   private Text title, remCards, finish;
   private Button exit, add3, newGame;
   
   
   @Override
   public void start(Stage primaryStage) {
         primaryStage.setTitle("Game of Set");
         
         //initialize game
         game = new Game();
   
         // initialize the Panes and set their styling and items
         //main pane
         mainPane = new BorderPane();
         mainPane.setStyle("-fx-background-color: #34495e;");
         
         //where the cards reside and we can draw the board now
         grid = new GridPane();
         grid.setAlignment(Pos.CENTER);
         grid.setHgap(15);
         grid.setVgap(25);
         drawBoard();
         
         //where the title and other text resides
         statusPane = new VBox();
         title = new Text("Set");
         title.setFill(Color.web("#ecf0f1"));
         title.setFont(Font.loadFont("file:resources/LoveloLineBold.otf", 50));
         statusPane.setAlignment(Pos.CENTER);
         
         remCards = new Text("Cards: " + game.remCards());
         remCards.setFill(Color.web("#95a5a6"));
         remCards.setFont(Font.loadFont("file:resources/LoveloBlack.otf", 15));
         
         finish = new Text();
         finish.setFill(Color.web("#95a5a6"));
         finish.setFont(Font.loadFont("file:resources/LoveloBlack.otf", 20));
         statusPane.getChildren().add(title);
         statusPane.getChildren().add(remCards);
         statusPane.getChildren().add(finish);
 
         
         //where the buttons reside
         buttonPane = new HBox();
         buttonPane.setAlignment(Pos.CENTER);
         
         //setting up the buttons
         exit = new Button("Exit");
         exit.setFont(Font.loadFont("file:resources/LoveloLineLight.otf",20));
         exit.setStyle("-fx-background-color: transparent; -fx-text-fill: #ecf0f1;");
         exit.setOnAction( new EventHandler<ActionEvent>() 
         {
            public void handle(ActionEvent e)
            {
               Platform.exit();
            }
         });
         exit.setOnMouseEntered(new EventHandler<MouseEvent>() 
            {
               public void handle(MouseEvent me) 
               {
                  exit.setStyle("-fx-background-color: transparent; -fx-text-fill: #95a5a6;");
               }
            });
         exit.setOnMouseExited(new EventHandler<MouseEvent>() 
            {
               public void handle(MouseEvent me) 
               {
                  exit.setStyle("-fx-background-color: transparent; -fx-text-fill: #ecf0f1;");
               }
            });

         add3 = new Button("add3");
         add3.setFont(Font.loadFont("file:resources/LoveloLineLight.otf",20));
         add3.setStyle("-fx-background-color: transparent; -fx-text-fill: #ecf0f1;");
         add3.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                  game.add3();
                  drawBoard();
                  remCards.setText("Cards: " + game.remCards());
            }
         });
         add3.setOnMouseEntered(new EventHandler<MouseEvent>() 
            {
               public void handle(MouseEvent me) 
               {
                  add3.setStyle("-fx-background-color: transparent; -fx-text-fill: #95a5a6;");
               }
            });
         add3.setOnMouseExited(new EventHandler<MouseEvent>() 
            {
               public void handle(MouseEvent me) 
               {
                  add3.setStyle("-fx-background-color: transparent; -fx-text-fill: #ecf0f1;");
               }
            });
      
         newGame = new Button("New Game");
         newGame.setFont(Font.loadFont("file:resources/LoveloLineLight.otf",20));
         newGame.setStyle("-fx-background-color: transparent; -fx-text-fill: #ecf0f1;");
         newGame.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
               game = new Game();
               drawBoard();
               remCards.setText("Cards: " + game.remCards());
            }
         });
         newGame.setOnMouseEntered(new EventHandler<MouseEvent>() 
            {
               public void handle(MouseEvent me) 
               {
                  newGame.setStyle("-fx-background-color: transparent; -fx-text-fill: #95a5a6;");
               }
            });
         newGame.setOnMouseExited(new EventHandler<MouseEvent>() 
            {
               public void handle(MouseEvent me) 
               {
                  newGame.setStyle("-fx-background-color: transparent; -fx-text-fill: #ecf0f1;");
               }
            });
         
         //adding buttons to button pane
         buttonPane.getChildren().add(exit);
         buttonPane.getChildren().add(newGame);
         buttonPane.getChildren().add(add3);
         
         
         //adding it to main
         mainPane.setTop(statusPane);
         mainPane.setBottom(buttonPane);
         mainPane.setCenter(grid);
         
         
         //finish setup
         Scene scene = new Scene(mainPane,450,720);
         primaryStage.setScene(scene);
         primaryStage.show();
      }
   
   public void drawBoard()
   {
      grid.getChildren().clear(); //clear the board
      
      //add in all the cards
      for(int r = 0; r < game.numRows(); r++)
         for(int c = 0; c < game.numCols(); c++) 
         {
            CardPane p = new CardPane(game.getBoardSquare(r,c), r, c);
            if (game.isSelected(game.getBoardSquare(r,c)))
            {
               p.setOnMouseExited(null);
               p.hoveredOverStyle();
            } else 
            {
            p.setOnMouseExited(new EventHandler<MouseEvent>() 
            {
               public void handle(MouseEvent me) 
               {
                  p.normalStyle();
               }
            });
            }
            p.setOnMouseEntered(new EventHandler<MouseEvent>() 
            {
               public void handle(MouseEvent me) 
               {
                  p.hoveredOverStyle();
               }
            });  
            p.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
               public void handle(MouseEvent me) 
               {
                  handleClick(me);
                  
               }
            });
            
            grid.add(p,c,r);  
         }
   }
   
   //this should handle the clicks
   public void handleClick(MouseEvent e)
   {  
      CardPane p = (CardPane)(e.getSource());
      if (!game.isSelected(p.getBoardSquare()))
      {
         p.setOnMouseExited(null);
         game.addToSelected(p.getRow(), p.getCol());
         if (game.numSelected() == 3) 
            {
               game.testSelected();
               if (game.done())
                  finish.setText("GAME OVER");
               drawBoard();
               remCards.setText("Cards: " + game.remCards());
            }
      }
      else
      {
         p.setOnMouseExited(new EventHandler<MouseEvent>() 
            {
               public void handle(MouseEvent me) 
               {
                  p.normalStyle();
                  game.removeSelected(p.getRow(), p.getCol());
               }
         });
         
      }
   }

   
   public static void main(String [] args) {
         launch(args);
   }
}