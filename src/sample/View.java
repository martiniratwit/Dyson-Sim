package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Shear;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class View extends Application implements EventHandler<ActionEvent>{
    
	private Controller controller;
	
    private double costPerSqFootage = 10;
    private double sqFootage = 500;
    private double fuelTons = 3;
    private double fuelCost = 1270.5;
    private double productionCost = 1000000;
    private double launchCost = 20000000;
    
    private ArrayList<Box> visualSats = new ArrayList<>();

    private Camera camera = new PerspectiveCamera(true);

    private final Sphere sun = sun();
    private final Sphere mercury = mercury();
    private final Sphere milkyWay = milkyWay();

    private double timeSpeed = 1;

    private Group animationGroup = new Group(milkyWayIV(), new Group(sun, mercury));

    private HBox pane = new HBox();
    private VBox pane2 = new VBox();
    private HBox currentWindow = new HBox();
    private VBox labels = new VBox();
    private VBox values = new VBox();
    
    private int row = 50;
    private int tilt = 10;
    
    public View()
    {
    	this.controller = new Controller(this);
    }
    
    @Override
    public void handle(ActionEvent t) {
        Box satellite = satellite(); //Creates the visual satellite box
        visualSats.add(satellite); //Adds the box to the visual satellite list
        this.controller.addSatellite(costPerSqFootage, sqFootage, fuelCost, fuelTons, productionCost, launchCost, satellite); //Adds satellite object to satellite list                
        animationGroup.getChildren().add(satellite);
        satellite.getTransforms().add(new Translate(12.5,0,125));

        AnimationTimer rotation = new AnimationTimer() {
            @Override
            public void handle(long l) {
                satellite.rotateProperty().set(satellite.getRotate() - 1);


            }
        };
        rotation.start();

        row+=10;
        tilt+=10;
    }
    
    private Sphere sun() {
        Sphere sun = new Sphere(100);

        PhongMaterial photosphereMaterial = new PhongMaterial();
        Image sunTexture = new Image(getClass().getResourceAsStream("/resources/Photosphere Texture.jpg"));
        photosphereMaterial.setDiffuseMap(sunTexture); //Initialize Texture to Material
        photosphereMaterial.setSelfIlluminationMap(sunTexture); //Initialize Illumination to Material
        sun.setMaterial(photosphereMaterial); //Add material to Sun

        sun.setRotationAxis(Rotate.Y_AXIS);

        return sun;

    }

    private Sphere mercury() {
        Sphere mercury = new Sphere(10);

        PhongMaterial mercuryMaterial = new PhongMaterial();
        Image mercuryTexture = new Image(getClass().getResourceAsStream("/resources/Mercury Texture.jpg"));
        mercuryMaterial.setDiffuseMap(mercuryTexture); //Initialize Texture to Material
        mercuryMaterial.setSelfIlluminationMap(mercuryTexture); //Initialize Illumination to Material
        mercury.setMaterial(mercuryMaterial); //Add material to Mercury

        mercury.getTransforms().add(new Translate(-150,0,-200));

        mercury.setRotationAxis(Rotate.Y_AXIS);

        return mercury;

    }

    private Sphere milkyWay() {
        Sphere milkyWay = new Sphere(1000);
        milkyWay.setCullFace(CullFace.NONE); //Remove culling to texture faces inside sphere

        PhongMaterial milkyWayMaterial = new PhongMaterial();
        Image milkyWayTexture = new Image(getClass().getResourceAsStream("/resources/Milky Way Texture.jpg"));
        milkyWayMaterial.setDiffuseMap(milkyWayTexture); //Initialize Texture to Material
        milkyWayMaterial.setSelfIlluminationMap(milkyWayTexture); //Initialize Illumination to Material
        milkyWay.setMaterial(milkyWayMaterial); //Add material to Mercury

        return milkyWay;
    }

    private ImageView milkyWayIV(){
        Image milkyWayTexture = new Image(getClass().getResourceAsStream("/resources/Milky Way Texture.jpg"));
        ImageView milkyWayImageView = new ImageView(milkyWayTexture);
        milkyWayImageView.setPreserveRatio(true);
        milkyWayImageView.getTransforms().add(new Translate(-milkyWayTexture.getWidth()/2,-milkyWayTexture.getHeight()/2, 2000));
        return milkyWayImageView;
    }

    private Box satellite() {
        Box satellite = new Box(100,100,1);
        PhongMaterial satelliteMaterial = new PhongMaterial();
        Image satelliteTexture = new Image(getClass().getResourceAsStream("/resources/Satellite Texture.jpg"));
        satelliteMaterial.setDiffuseMap(satelliteTexture); //Initialize Texture to Material
        satelliteMaterial.setSelfIlluminationMap(satelliteTexture); //Initialize Illumination to Material
        satellite.setMaterial(satelliteMaterial); //Add material to Box

        satellite.setRotationAxis(Rotate.Y_AXIS);
        satellite.setOnMouseClicked(event ->
        {
        	controller.satelliteWindow(satellite);
        });
        return satellite;
    }

    private void mercuryRotation() {

        AnimationTimer Rotation = new AnimationTimer() {
            @Override
            public void handle(long l) {

                mercury.rotateProperty().set(mercury.getRotate()-.05 * timeSpeed);
                mercury.setRotate(mercury.getRotate()-.25 * timeSpeed);

                sun.rotateProperty().set(sun.getRotate() - 0.025 * timeSpeed);

            }
        };
        Rotation.start();
    }

    private Pane animationPane() {
        Pane animationPane = new Pane();
        SubScene subScene = new SubScene(animationGroup, 1000,700);
        animationPane.getChildren().add(subScene);

        return animationPane;

    }
    
    //This function checks to see if the current value set in the text box is valid, and updates the store variable accordingly
    private double textTest(TextField text, String value, String old, double base) {
    	try {
    		int first = value.indexOf(".");
    		if(first != -1) {
    			//If there are two '.', then the old text and value are used
    			if(value.indexOf(".", first + 1) != -1) {
    				setText(text, old);
    				return -1.0000;
    			}
    		}
    		//Happens only with empty text box, using base value as a placeholder in variable storage
    		if(value.equals("") || value.equals(".")) {
    			setText(text, value);
    			return base;
    		} 
    	}
    	//When something goes wrong, the base value is the safety value that overrides the new value
    	catch(Exception ex) {
    		setText(text, Double.toString(base));
    		return base;
    	}
		//Happens when nothing is wrong, and value is valid
		setText(text, value);
		return Double.parseDouble(value);
    }
    	
    private void setText(TextField text, String value) {
    	text.textProperty().setValue(value);
    }
    
    
    private void setInputPane() {
    	
    	//This filters out any non-numeric values from being handled in the listener
        EventHandler<KeyEvent> keyHandler = new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
            	if(!event.getCharacter().matches("[0123456789.]")) {		
            		if(!event.getCode().isArrowKey()) {
            			if(event.getCode() != KeyCode.BACK_SPACE && event.getCode() != KeyCode.DELETE) {
            				event.consume();
            			}
            		}
            	}
            }
            
        };
    	
    	//Panel Cost Text Field
        VBox sPCBox = new VBox();
        Label sPCLabel = new Label("Panel Cost (Per Square Footage)");
        TextField sPCText = new TextField("10.00");
        sPCText.addEventFilter(KeyEvent.ANY, keyHandler);
        sPCBox.getChildren().addAll(sPCLabel,sPCText);
        sPCText.textProperty().addListener((observable, oldValue, newValue) -> {
        	double result = textTest(sPCText, newValue, oldValue, 10);
        	//Only occurs when text doesn't contain two '.', meaning the value doesn't change
        	if(result != -1) {
        		costPerSqFootage = result;
        	}
        });

        pane2.getChildren().add(sPCBox);

        //Square Footage Text Field
        VBox sFBox = new VBox();
        Label sFLabel = new Label("Square Footage");
        TextField sFText = new TextField("500.00");
        sFText.addEventFilter(KeyEvent.ANY, keyHandler);
        sFBox.getChildren().addAll(sFLabel,sFText);
        sFText.textProperty().addListener((observable, oldValue, newValue) -> {
        	double result = textTest(sFText, newValue, oldValue, 500.00);
        	//Only occurs when text doesn't contain two '.', meaning the value doesn't change
        	if(result != -1) {
        		sqFootage = result;
        	}
        });
        pane2.getChildren().add(sFBox);

        //Production Cost Text Field
        VBox pCBox = new VBox();
        Label pCLabel = new Label("Production Cost");
        TextField pCText = new TextField("1000000.00");
        pCText.addEventFilter(KeyEvent.ANY, keyHandler);
        pCBox.getChildren().addAll(pCLabel,pCText);
        pCText.textProperty().addListener((observable, oldValue, newValue) -> {
        	double result = textTest(pCText, newValue, oldValue, 1000000.00);
        	//Only occurs when text doesn't contain two '.', meaning the value doesn't change
        	if(result != -1) {
        		productionCost = result;
        	}
        });
        pane2.getChildren().add(pCBox);
             
        //Fuel Cost Text Field
        VBox fCBox = new VBox();
        Label fCLabel = new Label("Fuel Cost (Per Ton) ");
        TextField fCText = new TextField("1270.50");
        fCText.addEventFilter(KeyEvent.ANY, keyHandler);
        fCBox.getChildren().addAll(fCLabel,fCText);
        fCText.textProperty().addListener((observable,oldValue,newValue) -> {
        	double result = textTest(fCText, newValue, oldValue, 1270.50);
        	//Only occurs when text doesn't contain two '.', meaning the value doesn't change
        	if(result != -1) {
        		fuelCost = result;
        	}
        });
        pane2.getChildren().add(fCBox);
        
        //Fuel Tons
        VBox fTBox = new VBox();
        Label fTLabel = new Label("Amount of Fuel (Tons)");
        TextField fTText = new TextField("3.00");
        fTText.addEventFilter(KeyEvent.ANY, keyHandler);
        fTBox.getChildren().addAll(fTLabel,fTText);
        fTText.textProperty().addListener((observable,oldValue,newValue) -> {
        	double result = textTest(fTText, newValue, oldValue, 3.00);
        	//Only occurs when text doesn't contain two '.', meaning the value doesn't change
        	if(result != -1) {
        		fuelTons = result;
        	}
        });
        pane2.getChildren().add(fTBox);

        //Launch Cost
        VBox lCBox = new VBox();
        Label lCLabel = new Label("Launch Cost");
        TextField lCText = new TextField("20000000.00");
        lCText.addEventFilter(KeyEvent.ANY, keyHandler);
        lCBox.getChildren().addAll(lCLabel,lCText);
        lCText.textProperty().addListener((observable,oldValue,newValue) -> {
        	double result = textTest(lCText, newValue, oldValue, 20000000.00);
        	//Only occurs when text doesn't contain two '.', meaning the value doesn't change
        	if(result != -1) {
        		launchCost = result;
        	}
        });
        pane2.getChildren().add(lCBox);
        
        VBox aSBox = new VBox();
        
        Button satelliteButton = new Button("Add Satellite");
        satelliteButton.setLayoutX(-150);
        satelliteButton.setLayoutX(200);
        satelliteButton.setOnAction(this);
        
        
        aSBox.getChildren().add(satelliteButton);
        aSBox.alignmentProperty().set(Pos.CENTER);
        pane2.getChildren().add(aSBox);
        
        Label number = new Label("Number of Satellites: 0");
        pane2.getChildren().add(number);
        
        
        //Creates labels for individual satellites
        Label cPSF = new Label("Cost Per Sq Foot: ");
        Label sF = new Label("Sq Feet: ");
        Label fP = new Label("Fuel Price: ");
        Label fT = new Label("Amount of Fuel (Tons): ");
        Label pC = new Label("Production Cost: ");
        Label lC = new Label("Launch Cost: ");
        Label total = new Label("Total Cost: ");
        labels.getChildren().addAll(cPSF,sF,fP,fT,pC,lC, total);
        currentWindow.getChildren().addAll(labels, values);
        pane2.getChildren().add(currentWindow);
        
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> {
        	controller.reset();
        });
        pane2.getChildren().add(resetButton);
    }



    @Override
    public void start(Stage primaryStage) throws Exception{

        Pane animationPane = new Pane();
        SubScene subScene = new SubScene(animationGroup, 1000,700, true,SceneAntialiasing.BALANCED); //Add Z-buffer
        animationPane.getChildren().add(subScene);

        setInputPane();
        StackPane inputPane = new StackPane();
        inputPane.getChildren().add(pane2);
        inputPane.setPrefWidth(200);

        pane.getChildren().addAll(animationPane, inputPane);
        HBox.setHgrow(animationPane,Priority.ALWAYS);

        Scene scene = new Scene(pane);

        camera.setNearClip(1);
        camera.setFarClip(10000);
        subScene.setCamera(camera);
        camera.translateZProperty().set(-1000);

        primaryStage.setTitle("Dyson Swarm Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();

        subScene.heightProperty().bind(animationPane.heightProperty());
        subScene.widthProperty().bind(animationPane.widthProperty());

        mercuryRotation();
    }
    
    //Empties the current satellite and adds the new values
    public void setSatelliteWindow(double[] values, double total)
    {
    	this.values.getChildren().clear();
    	this.values.getChildren().addAll(new Label(String.valueOf(values[0])), new Label(String.valueOf(values[1])),
    									 new Label(String.valueOf(values[2])), new Label(String.valueOf(values[3])),
    									 new Label(String.valueOf(values[4])), new Label(String.valueOf(values[5])),
    									 new Label(String.valueOf(total)));
    }
    
    public void setNumSatellites(int num)
    {
    	((Label)this.pane2.getChildren().get(7)).setText("Number of Satellites: " + num);
    }
    
    public void reset(double[] values)
    {
    	while(visualSats.size() != 0)
    	{
    		animationGroup.getChildren().remove(visualSats.get(0));
    		visualSats.remove(0);   		
    	}
    	this.values.getChildren().clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
