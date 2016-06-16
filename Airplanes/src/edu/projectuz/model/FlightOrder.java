package edu.projectuz.model;

import edu.projectuz.helpers.Helper;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FlightOrder {

	private Map map;
	private int id;
	private Airport actualAirport;
	private Airport destinationAirport;
	private Queue<Airport> airportsFlight = new ConcurrentLinkedQueue<>();

	public FlightOrder(int id, Airport startAirport, Map map){
		if(startAirport == null){
			throw new IllegalArgumentException("Start airport cannot be null");
		}

		setId(id);
		setMap(map);
		setActualAirport(startAirport);
		setDestinationAirport(generateDestinationAirport(startAirport));
	}

	private Airport generateDestinationAirport(Airport actualAirport)
	{
		Random random = new Random();
		Airport airport;

		do {
			airport = map.getAirportsContainer().getAirport(random.nextInt(map.getAirportsContainer().count()));
		} while(airport.getId() == actualAirport.getId());

		return airport;
	}

	public double getDistance(Airport nextAirport) {
		return Helper.getSectionLength(actualAirport.getPosition(), nextAirport.getPosition());
	}

	public double getDistance(Airport startAirport, Airport endAirport) {
		return Helper.getSectionLength(startAirport.getPosition(), endAirport.getPosition());
	}

	public void planFlight(Plane plane) {
		double fuelWithTolerantion = plane.getFuelTank().getActualFuelLevel() * 0.90;

		if (fuelWithTolerantion < getDistance(destinationAirport))
		{
			for(int i=0; i<map.getAirportsContainer().count(); i++)
			{
				if((map.getAirportsContainer().getAirport(i).getId() != destinationAirport.getId()) && (fuelWithTolerantion > getDistance(map.getAirportsContainer().getAirport(i))) && (getDistance(map.getAirportsContainer().getAirport(i), destinationAirport) < fuelWithTolerantion))
				{
					airportsFlight.add(map.getAirportsContainer().getAirport(i));
					airportsFlight.add(destinationAirport);
					//System.out.println("Samolot: " + plane.getPlaneId() + " Miedzyladowanie: [" + map.getAirportsContainer().getAirport(i).getId()+ "}" + map.getAirportsContainer().getAirport(i).getName() + "  Lotnisko docelowe: [" + destinationAirport.getId() +"}" + destinationAirport.getName());
					break;
				}
				else
				{
					if((map.getAirportsContainer().getAirport(i).getId() != destinationAirport.getId()) && (fuelWithTolerantion > getDistance(map.getAirportsContainer().getAirport(i))) && isCloser(actualAirport, map.getAirportsContainer().getAirport(i)))
					{
						setToActualAirportAndAddToQueue(map.getAirportsContainer().getAirport(i));
						planFlight(plane);
						break;
					}

				}

			}
		}
		else
		{
			airportsFlight.add(destinationAirport);
			//System.out.println("Samolot: " + plane.getPlaneId() + " leci prosto do lotniska docelowego: ["  + destinationAirport.getId()+ "}" + destinationAirport.getName());
		}

	}

	private void setToActualAirportAndAddToQueue(Airport airport)
	{
		this.actualAirport = airport;
		airportsFlight.add(airport);
	}


	public void flightToNextAirport() {
		if(!airportsFlight.isEmpty()){
			setActualAirport(airportsFlight.poll());
		}
	}

	public boolean isCloser(Airport actualAirport, Airport differentAirport)
	{
		if(getDistance(actualAirport, destinationAirport) > getDistance(differentAirport, destinationAirport))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public Airport getActualAirport() {
		return actualAirport;
	}

	public void setActualAirport(Airport actualAirport) {
		this.actualAirport = actualAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void setAirportsFlight(Queue<Airport> airportsFlight) {
		this.airportsFlight = airportsFlight;
	}

	public Airport getAirportFromQueue() {
		return airportsFlight.peek();
	}

	public Airport getAndRemoveAirportFromQueue() {
		return airportsFlight.poll();
	}

	public int sizeOfQueue()
	{
		return airportsFlight.size();
	}

	public Queue getQueue()
	{
		return airportsFlight;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\tPlan lotu:\n");

		for (Airport airport: airportsFlight) {
			builder.append(String.format("\t --> %s \n", airport.toString()));

		}

		return builder.toString();

	}
}