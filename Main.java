package application;
import java.io.File;

import javax.swing.JFileChooser;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	private TextField input1;
	private TextField input2;
	private TextField input3;
	protected LinkedList list;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane mainPane = new BorderPane();
			TextField tfKey = new TextField();
			tfKey.setPrefColumnCount(5); 
			tfKey.setAlignment(Pos.BASELINE_LEFT); 
			list = new LinkedList(importFromFile(), 1);
			
			// adding the button
			Button importButton = new Button("Import"); 
			Button clearButton = new Button("clear"); 
			
			importButton.setOnAction(e -> {
				LinkedList list2 = new LinkedList(importFromFile(), 1);
		        });
			
			clearButton.setOnAction(e ->{
				list.clear();
			});
			
//			Label question = new Label(" " + list.category1Label + ": " + current.getCategory1());
//			HBox boxForKey = new HBox(10); 
//			boxForKey.getChildren().addAll(question, tfKey, importButton, clearButton);
//			boxForKey.setAlignment(Pos.CENTER);
//			mainPane.setBottom(boxForKey);
			
			 //Name input
			input1 = new TextField();
			input1.setPromptText(list.category1Label);
			input1.setMinWidth(100);

	        //Price input
			input2 = new TextField();
			input2.setPromptText(list.category2Label);

	        //Quantity input
			input3 = new TextField();
			input3.setPromptText(list.category3Label);
	        
	        
	        //Button
	        Button addButton = new Button("Add");
	        addButton.setOnAction(e ->{
	        	addButtonClicked();
	        });
	        
	        // making a hbox
	        HBox hBox = new HBox();
	        hBox.setPadding(new Insets(10,10,10,10));
	        hBox.setSpacing(10);
	        hBox.setAlignment(Pos.CENTER);
	        hBox.getChildren().addAll(input1, input2, input3, addButton);
	        mainPane.setBottom(hBox);
			
	        // calling the boxgui
			BoxGui gui = new BoxGui();
			gui.display(list);
			mainPane.setCenter(gui);

			// Create a scene and place the pane in the stage
			Scene scene = new Scene(mainPane, 800, 800);
			// setting the title
			primaryStage.setTitle("HW:3 List Type Data Structures");
			// setting the stage
			primaryStage.setScene(scene);
			// Display
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    // Add button clicked
    public void addButtonClicked(){
    	Node something = new Node(null, null, null);
    	something.setCategory1(input1.getText());
    	something.setCategory2(input2.getText());
    	something.setCategory3(input3.getText());
    	input1.clear();
    	input2.clear();
    	input3.clear();
    }
	
    // using import
	public static File importFromFile(){
		JFileChooser chooseFile = new JFileChooser();
		File fileSelected = null;
		int retVal = chooseFile.showOpenDialog(null);
		if (retVal == JFileChooser.APPROVE_OPTION) {
			fileSelected = chooseFile.getSelectedFile();
			return fileSelected;
		}
		return fileSelected;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
