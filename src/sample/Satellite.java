package sample;


public class Satellite {

    private double costPerSqFootage, sqFootage, fuelPrice, fuelTons, productionCost, launchCost;
    
    public Satellite(double costPerSqFootage, double sqFootage, double fuelPrice, double fuelTons, double productionCost, double launchCost)
    {
        this.costPerSqFootage = costPerSqFootage;
        this.sqFootage = sqFootage;
        this.fuelPrice = fuelPrice;
        this.fuelTons = fuelTons;
        this.productionCost = productionCost;
        this.launchCost = launchCost;
    }
    
    public double getCostPerSqFootage() 
    {
    	return this.costPerSqFootage;
    }

    public double getSqFootage() 
    {
    	return this.sqFootage;
    }
    
    public double getFuelPrice() 
    {
    	return this.fuelPrice;
    }
    
    public double getFuelTons() 
    {
    	return this.fuelTons;
    }
    
    public double getProductionCost()
    {
    	return this.productionCost;
    }
    
    public double getLaunchCost() 
    {
    	return this.launchCost;
    }
}