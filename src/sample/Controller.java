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

    public double getLaunchCost(double input1, double input2, double input3, double input4, int input5) {
        Model model = new Model(input1,input2,input3,input4,input5);

        return (model.getSolarPanelCost()+ model.getFuelCost()+ model.getProductionCost());

    }

}
