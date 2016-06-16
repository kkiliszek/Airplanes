package edu.projectuz.model;

import java.util.ArrayList;

public class AirportsContainer {

	private ArrayList<Airport> airports = new ArrayList<>();

	public Airport getAirport(int id) {
		return airports.get(id);
	}

	public void addAirport(Airport airport) {
		this.airports.add(airport);
	}

	public ArrayList<Airport> getAirports(){ return this.airports;}

	public int count()
	{
		return this.airports.size();
	}

	public Vector2[] getVector2(){
		Vector2[] coordinates = new Vector2[airports.size()];
		for (int i = 0; i<coordinates.length; i++){
			coordinates[i] = airports.get(i).getPosition();
		}
		return coordinates;
	}
}