package edu.projectuz.model;

public class FuelTank {

	private double actualFuelLevel;
	private double maxFuelLevel;

	public FuelTank(double maxFuelLevel) {
		setActualFuelLevel(maxFuelLevel);
		setMaxFuelLevel(maxFuelLevel);
	}

	public void reduceFuel(double fuelConsumption){
		setActualFuelLevel(getActualFuelLevel() - fuelConsumption);
	}

	public double getActualFuelLevel() {
		return this.actualFuelLevel;
	}

	public void setActualFuelLevel(double actualFuelLevel) {
		this.actualFuelLevel = actualFuelLevel;
	}

	public double getMaxFuelLevel() {
		return this.maxFuelLevel;
	}

	public void setMaxFuelLevel(double maxFuelLevel) {
		this.maxFuelLevel = maxFuelLevel;
	}

}