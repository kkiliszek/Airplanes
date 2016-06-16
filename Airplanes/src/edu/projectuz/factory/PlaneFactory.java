package edu.projectuz.factory;

import edu.projectuz.enums.SegmentType;
import edu.projectuz.model.*;

import java.util.Random;

public class PlaneFactory {

	private static final double FUEL_TANK_CAPACITY = 350;
	private String[] planeModelList = {"Airbus A310 MRT", "Airbus A330 MRTT", "Airbus A400M", "Airbus Beluga", "Boeing Dreamlifter", "CASA CN-235", "CASA C-207", "Let L-410 Turbolet", "Embraer KC-390", "An-178"};

	public Plane createPlane(int id, Vector2 vector2, FlightOrder flightOrder, Map map)
	{
		return new Plane(id, getRandomPlaneName(), randomFuelTankCapacity(), vector2, flightOrder, map);
	}

	private String getRandomPlaneName()
	{
		Random random = new Random();
		return planeModelList[random.nextInt(planeModelList.length)];
	}

	private double randomFuelTankCapacity()
	{
		Random random = new Random();
		double min = FUEL_TANK_CAPACITY-FUEL_TANK_CAPACITY*0.25;
		double max = FUEL_TANK_CAPACITY+FUEL_TANK_CAPACITY*0.25;
		return min + (max - min) * random.nextDouble();
	}

}