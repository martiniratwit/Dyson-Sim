package sample;
/*
        A = Solar Panel Cost (10$per sq ft)
        B = Square Footage ~500
        C = Production cost (constant 1mil per)
        D = Fuel Cost(1.65$ per gallon 3ton 770g/t)
        E = Launch Cost (Constant 20mil)
*/

import javafx.stage.Stage;

public class Controller {

    public Controller() {

    }

    public double getTotalCost(Satellite satellite) {
        
    	return (satellite.getCostPerSqFootage() * satellite.getSqFootage()) + 
    			(satellite.getFuelPrice() * satellite.getFuelTons()) + satellite.getLaunchCost() +
    			satellite.getProductionCost();

    }

}
