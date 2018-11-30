package snake_block;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
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
		a.setStyle("-fx-padding: 0 0 0 18; -fx-font-size: 19px; -fx-font-family: Rubik");
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
	public Circle c= new Circle();
	Image m= new Image("https://image.flaticon.com/icons/png/512/223/223558.png");
	
	Token1(){
		this.c.setRadius(15);
		//this.c.setFill(new ImagePattern(this.m));
		this.c.setFill(new ImagePattern(m));
	}
}

class Ball{
	public Circle c= new Circle();
	//Image m= new Image("https://www.freepngimg.com/thumb/dollar/1-2-dollar-transparent-thumb.png");
	
	Ball(){
		this.c.setRadius(15);
		//this.c.setFill(new ImagePattern(this.m));
		this.c.setFill(Color.BLACK);
	}
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
		
		Random rand= new Random();
		Group root=new Group();
		root.getChildren().clear();
		
		//List of Balls
		ArrayList<Ball> balllist=new ArrayList<Ball>();
		//List of Magnets
		ArrayList<Token1> token1list=new ArrayList<Token1>();
		//List of Blocks
		ArrayList<Block> blocklist=new ArrayList<Block>();
		
		int score=0;
		
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
			
			Label scorelabel= new Label("0");
			
			int posx=10;
			int posy=300;
			
			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				
				for(int i=0;i<blocklist.size();i++) {
					//Get blocks to move down
					blocklist.get(i).p.setLayoutY(blocklist.get(i).p.getLayoutY()+1);
					if(blocklist.get(i).p.getLayoutY()>900) {
						//root.getChildren().remove(blocklist.get(i).p);
						blocklist.remove(i);
					}
				}
				
				for(int i=0;i<token1list.size();i++) {
					//get magnets to move down
					token1list.get(i).c.setLayoutY(token1list.get(i).c.getLayoutY()+1);
					if(token1list.get(i).c.getLayoutY()>900) {
						token1list.remove(i);
					}
				}
				
				for(int i=0;i<token1list.size();i++) {
					//get balls to move down
					balllist.get(i).c.setLayoutY(balllist.get(i).c.getLayoutY()+1);
					if(balllist.get(i).c.getLayoutY()>900) {
						balllist.remove(i);
					}
				}
				
				for(int i=0;i<blocklist.size();i++) {
					//block collision
					if (blocklist.get(i).p.getBoundsInParent().intersects(snake.longboi.getBoundsInParent())) {
						blocklist.get(i).p.setOpacity(0);
						
						root.getChildren().remove(blocklist.get(i));
					}
				}
				
				for(int i=0;i<token1list.size();i++) {
					//magnet collision
					if (token1list.get(i).c.getBoundsInParent().intersects(snake.longboi.getBoundsInParent())) {
						token1list.get(i).c.setOpacity(0);
						
						root.getChildren().remove(token1list.get(i));
					}
				}
				
				for(int i=0;i<balllist.size();i++) {
					//ball collision
					if (balllist.get(i).c.getBoundsInParent().intersects(snake.longboi.getBoundsInParent())) {
						balllist.get(i).c.setOpacity(0);
						
						root.getChildren().remove(balllist.get(i));
					}
				}
				
				for(int i=0;i<5;i++) {
					
					if (blocklist.size()<1000 && token1list.size()<500 && balllist.size()<1000) {
					int weight = 12;
					
					int k= rand.nextInt(14);
					
					//adding blocks
					if(k==0||k==1||k==2||k==3||k==7||k==8||k==9||k==10) {
					//if condition for randomization
					Block b= new Block(weight ,posx, posy);
					b.p.setLayoutX(posx);
					b.p.setLayoutY(posy);
					if(k==0||k==1) {
						b.r.setFill(Color.BLACK); 
						b.a.setTextFill(Color.LIME);}
					else {
						b.r.setFill(Color.LIME); 
						b.a.setTextFill(Color.BLACK);
					}
					
					root.getChildren().add(b.p);
					
					blocklist.add(b);
					}
					
					//adding balls
					if(k==1||k==2||k==7||k==8) {
						Ball b=new Ball();
						b.c.setLayoutX(posx+41);
						b.c.setLayoutY(posy+100);
						
						root.getChildren().add(b.c);
					
					balllist.add(b);
					}
					
					//adding magnets
					if(k==5) {
						Token1 t= new Token1();
						t.c.setLayoutX(posx+37);
						t.c.setLayoutY(posy+130);
						
						root.getChildren().add(t.c);
						
					token1list.add(t);
					}
					posx+=70;
					//posx1+=20;
					}
				}
				
				posx=10;
				//posx1=10;
				posy-=210;
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
