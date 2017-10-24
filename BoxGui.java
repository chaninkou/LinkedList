package application;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoxGui extends BorderPane {
	private Label cateory1 = null;
	private Label cateory2 = null;
	private Label cateory3 = null;

	public void display(LinkedList list) {
		Node current = list.head;
		Rectangle rectangle1 = null;

		// rectangle1
		rectangle1 = new Rectangle();
		rectangle1.setStroke(Color.BLACK);
		rectangle1.setStrokeWidth(3);
		rectangle1.setFill(Color.WHITE);
		rectangle1.setX(50);
		rectangle1.setY(50);
		rectangle1.setWidth(200);
		rectangle1.setHeight(100);

		// couldn't make the loop work
		while (current != null) {
			cateory1 = new Label(" " + list.category1Label + ": " + current.getCategory1());
			cateory2 = new Label(" " + list.category2Label + ": " + current.getCategory2());
			cateory3 = new Label(" " + list.category3Label + ": " + current.getCategory3());
			current = current.right;
		}

		// creating a vbox
		VBox boom = new VBox(10);
		boom.getChildren().addAll(cateory1, cateory2, cateory3);
		boom.setAlignment(Pos.CENTER);
		
		// using a stackpane so the text could be inside the rectangle
		StackPane boxForKey2 = new StackPane();
		boxForKey2.getChildren().addAll(rectangle1, boom);
		boxForKey2.setAlignment(Pos.CENTER);
		setCenter(boxForKey2);
		
	}

}