package snake_block;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

class Block{

	int weight;
	String k;
	public Rectangle r= new Rectangle();
	public Label a;
	public int posx;
	public int posy;
	StackPane p= new StackPane();
	
	Block(int w, int xa, int ya){
		this.weight=w;
		this.posx=xa;
		this.posy=ya;
		this.k= Integer.toString(weight);
		this.a= new Label(k);
		this.r.setFill(Color.BLACK);
		r.setHeight(60);
		r.setWidth(60);
		a.setPrefSize(60, 60);
		a.setStyle("-fx-padding: 0 0 0 20;");
		this.p.getChildren().addAll(r,a);
	}
	
	Block(int xa, int ya){
		this.r.setFill(Color.BLACK);
		this.posx=xa;
		this.posy=ya;
		r.setHeight(60);
		r.setWidth(60);
	}
	
}

class Snake{
	public int length;
	public Rectangle longboi = new Rectangle();
	
}

class Token1{
	
}

public class gameplay extends Application{
	
	 private static final int      KEYBOARD_MOVEMENT_DELTA = 5;
	 private static final Duration TRANSLATE_DURATION      = Duration.seconds(0.25);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		Group root=new Group();
		root.getChildren().clear();
		
		//ArrayList<Rectangle> boob=new ArrayList<Rectangle>();
		ArrayList<Block> blocklist=new ArrayList<Block>();
		
		Scene s= new Scene(root,350,700);
		Canvas c=new Canvas(350,700);
		root.getChildren().add(c);
		arg0.setScene(s);
		
		arg0.setResizable(false);		

		//adding snake
	    Snake snake = new Snake();
	    snake.longboi.setOpacity(0.7);
	    snake.longboi.setWidth(18);
	    snake.longboi.setHeight(80);
	    snake.longboi.setFill(Color.LIME);
	    root.getChildren().add(snake.longboi);
	    final TranslateTransition transition = createTranslateTransition(snake.longboi);
	    moveRectangleOnKeyPress(s, snake.longboi);
		//
		
		AnimationTimer blocksnake=new AnimationTimer() {
			
			int posx=10;
			int posy=200;
			
			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				
				for(int i=0;i<blocklist.size();i++) {
					blocklist.get(i).p.setLayoutY(blocklist.get(i).p.getLayoutY()+1);
					
				}
				
				for(int i=0;i<blocklist.size();i++) {
					if (blocklist.get(i).r.getBoundsInParent().intersects(snake.longboi.getBoundsInParent())) {
						blocklist.get(i).r.setFill(Color.WHITE);
						blocklist.get(i).r.setWidth(0);
						blocklist.get(i).r.setHeight(0);
						
						root.getChildren().remove(blocklist.get(i));
					}
				}
				for(int i=0;i<5;i++) {
					if (blocklist.size()<500) {
					int weight = 12;
					//if condition for randomization
					Block b= new Block(weight ,posx, posy);
					b.p.setLayoutX(posx);
					b.p.setLayoutY(posy);
					b.r.setFill(Color.BLACK); 
					b.a.setTextFill(Color.LIME);
					
					//Block b= new Block(weight, posx, posy);
					//b.r.setLayoutX(posx);
					//b.r.setLayoutY(posy);
					//r.setFill(Color.BLACK);
					
					root.getChildren().add(b.p);
					//root.getChildren().add(b.a);
					
					
					//boob.add(b.r);
					blocklist.add(b);
					posx+=70;
					//posx1+=20;
					}
				}
				posx=10;
				//posx1=10;
				posy-=250;
				//posy2-=250;
					
			}
		};
		
		blocksnake.start();
		
		arg0.show();
	}
	
	
	
	

	
	  private TranslateTransition createTranslateTransition(final Rectangle rectangle) {
		    final TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION, rectangle);
		    transition.setOnFinished(new EventHandler<ActionEvent>() {
		      @Override public void handle(ActionEvent t) {
		        rectangle.setLayoutX(rectangle.getTranslateX() + rectangle.getX());
		        rectangle.setLayoutY(rectangle.getTranslateY() + rectangle.getY());
		        rectangle.setTranslateX(0);
		        rectangle.setTranslateY(0);
		      }
		    });
		    return transition;
		  }
	  
	  
	  private void moveRectangleOnKeyPress(Scene scene, final Rectangle rectangle) {
		  rectangle.setLayoutY(620);
		  rectangle.setLayoutX(191);
	    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	      @Override public void handle(KeyEvent event) {
	        switch (event.getCode()) {
	         // case UP:    rectangle.setLayoutY(rectangle.getLayoutY() - KEYBOARD_MOVEMENT_DELTA); break;
	          case RIGHT: 
	        	  if(rectangle.getLayoutX()<382)
	        	  rectangle.setLayoutX(rectangle.getLayoutX() + KEYBOARD_MOVEMENT_DELTA); break;
	         // case DOWN:  rectangle.setLayoutY(rectangle.getLayoutY() + KEYBOARD_MOVEMENT_DELTA); break;
	          case LEFT:  
	        	  if(rectangle.getLayoutX()>0)
	        	  rectangle.setLayoutX(rectangle.getLayoutX() - KEYBOARD_MOVEMENT_DELTA); break;
	        }
	      }
	    });
	  }

}

