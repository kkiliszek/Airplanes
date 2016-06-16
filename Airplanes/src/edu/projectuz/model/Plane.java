package edu.projectuz.model;

import java.util.Random;

public class Plane extends Thread{

	private int id;
	private String name;
	private Vector2 position = new Vector2();
	private FuelTank fuelTank;
	private double fuelConsumption = 5;
	private double angle;
	private FlightOrder flightOrder;
	private boolean inAir = false;
	private Map map;
	private boolean isCrashed = false;

	public Plane(int id, String name, double fuelTankCapacity, Vector2 vector2, FlightOrder flightOrder, Map map) {
		setId(id);
		setPlaneName(name);
		setFuelTank(new FuelTank(fuelTankCapacity));
		setPosition(vector2);
		setFlightOrder(flightOrder);
		this.map = map;
		System.out.println("Create plane: " + toString());
	}

	public int getPlaneId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaneName() {
		return this.name;
	}

	public void setPlaneName(String model) {
		this.name = model;
	}

	public FuelTank getFuelTank() {
		return this.fuelTank;
	}

	public void setFuelTank(FuelTank fuelTank) {
		this.fuelTank = fuelTank;
	}

	public FlightOrder getFlightOrder() {
		return flightOrder;
	}

	public void setFlightOrder(FlightOrder flightOrder) {
		this.flightOrder = flightOrder;
	}

	public void consumeFuel() {
		getFuelTank().reduceFuel(fuelConsumption);
	}

	public void move(Airport airport) {
		if(airport == null) return;

		//airport.getControlTower().unlockRunway();
		inAir = true;
		Vector2 tmpPosition = new Vector2(position.getX(), position.getY());
		angle = Math.atan2(airport.getPosition().getX() - position.getX(), airport.getPosition().getY() - position.getY());
		do{
			if(getFuelTank().getActualFuelLevel() <= 0){
				isCrashed = true;
				System.out.println("Samolot: " + id + " rozbil sie");
				return;
			}

			position.addToX(((float)Math.sin(angle)*5));
			position.addToY(((float)Math.cos(angle)*5));
			consumeFuel();
			try{
				sleep(100);
			}catch(Exception e){}

			if(isFarAway(tmpPosition, position))
			{
				airport.getControlTower().unlockRunway();
			}

			if(isNearWhileAirportIsBusy(this.position, airport.getPosition()) && airport.getControlTower().checkRunway() == false)
			{
				airport.getControlTower().getWaitingPlanes().add(this);
				while(airport.getControlTower().checkRunway() == false) {
					airport.getControlTower().synchronizeLandings();
				}
			}

		}while(isNear(this.position, airport.getPosition()) == false);
		getFuelTank().setActualFuelLevel(getFuelTank().getMaxFuelLevel());
		airport.getControlTower().unlockRunway();  //off
		inAir = false;
	}

	public Vector2 getPosition() {
		return position;
	}

	public boolean isNearWhileAirportIsBusy(Vector2 point1, Vector2 point2){
		float distance = (float) Math.sqrt(Math.pow(point1.getX() - point2.getX(),2) + Math.pow(point1.getY() - point2.getY(),2));
		if(distance < 30){
			return true;
		}
		return false;
	}

	public boolean isFarAway(Vector2 point1, Vector2 point2){
		float distance = (float) Math.sqrt(Math.pow(point1.getX() - point2.getX(),2) + Math.pow(point1.getY() - point2.getY(),2));
		if(distance < 31){
			return true;
		}
		return false;
	}

	public boolean isNear(Vector2 point1, Vector2 point2){
		float distance = (float) Math.sqrt(Math.pow(point1.getX() - point2.getX(),2) + Math.pow(point1.getY() - point2.getY(),2));
		if(distance < 10){
			return true;
		}
		return false;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public double getAngle() {
		return angle;
	}

	@Override
	public String toString() {
		return "Plane{" +
				"id=" + id +
				", name='" + name + '\'' +
				", position=" + position.toString() +
				", fuelTank=" + fuelTank.getMaxFuelLevel() +
				", fuelConsumption=" + fuelConsumption +
				", flightTo=" + flightOrder.getDestinationAirport().getName() + "X: " + flightOrder.getDestinationAirport().getPosition().getX() + " Y: " + flightOrder.getDestinationAirport().getPosition().getY() +
				", distanceToFlight=" + flightOrder.getDistance(flightOrder.getDestinationAirport()) +
				'}';
	}

	@Override
	public void run(){
		while (!isCrashed){
			if(getFlightOrder().sizeOfQueue() == 0){
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				FlightOrder newOrder = new FlightOrder(getPlaneId(), getFlightOrder().getDestinationAirport(), map);
				newOrder.planFlight(this);
				setFlightOrder(newOrder);
				System.out.println("Samolot o ID: " + id + " przyjal nowe zlecenie do: " + flightOrder.getDestinationAirport() + "   | " + flightOrder.toString());
				//getFuelTank().setActualFuelLevel(getFuelTank().getMaxFuelLevel());
			}

			if (!inAir && getFlightOrder().sizeOfQueue() != 0) {
				try {
					move(getFlightOrder().getAndRemoveAirportFromQueue());
					sleep(500);
					//move(getFlightOrder().getAndRemoveAirportFromQueue());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}

	}
}