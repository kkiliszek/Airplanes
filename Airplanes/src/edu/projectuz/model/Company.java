package edu.projectuz.model;

import edu.projectuz.enums.SegmentType;
import edu.projectuz.factory.PlaneFactory;

import java.util.ArrayList;
import java.util.List;

public class Company {

	private List<Plane> planes = new ArrayList<>();
	private PlaneFactory planeFactory;
	private Map map;

	public Company(Map map) {
		this.map = map;
	}

	public void createPlanes(Map map) {
		planeFactory = new PlaneFactory();

			for(int i=0; i<map.getAirportsContainer().count(); i++) {
				Vector2 airplanePosition = new Vector2();
				airplanePosition.setX(map.getAirportsContainer().getAirport(i).getPosition().getX());
				airplanePosition.setY(map.getAirportsContainer().getAirport(i).getPosition().getY());
				map.getSegments()[(int)airplanePosition.getX()][(int)airplanePosition.getY()].setSegmentType(SegmentType.AIRPORT_PLANE);
				addPlane(planeFactory.createPlane(i, airplanePosition, new FlightOrder(i, map.getAirportsContainer().getAirport(i), map), map));
			}
	}

	public void addPlane(Plane plane)
	{
		if(plane != null)
		{
			planes.add(plane);
		}
	}

	public Plane getPlane(int index)
	{
		return planes.get(index);
	}

	public void createOrder(int planeId) {
		// TODO - implement Company.createOrder
		throw new UnsupportedOperationException();
	}

	public void getAirports() {
		// TODO - implement Company.getAirports
		throw new UnsupportedOperationException();
	}
}