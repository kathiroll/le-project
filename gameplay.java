package snake_block;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.EventHandler;

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
	Rectangle r= new Rectangle();
	int score;
	
}

class Token1{
	
}

public class gameplay extends Application{
	
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

		
		AnimationTimer blocksnake=new AnimationTimer() {
			
			int posx=10;
			int posy=200;
			
			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				
				for(int i=0;i<blocklist.size();i++) {
					blocklist.get(i).p.setLayoutY(blocklist.get(i).p.getLayoutY()+1);
					
				}
				
				for(int i=0;i<5;i++) {

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
				posx=10;
				//posx1=10;
				posy-=250;
				//posy2-=250;
					
			}
		};
		
		blocksnake.start();
		
		arg0.show();
	}
	
}
