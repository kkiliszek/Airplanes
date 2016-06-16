package edu.projectuz.factory;

import edu.projectuz.enums.SegmentType;
import edu.projectuz.model.Airport;
import edu.projectuz.model.AirportsContainer;
import edu.projectuz.model.Map;
import edu.projectuz.model.Vector2;

import java.util.Random;

public class MapFactory {

	private int mapSize;
	private int airportsCount;

	public MapFactory(int mapSize, int airportsCount) {
		this.mapSize = mapSize;
		this.airportsCount = airportsCount;
	}

	public Map generateMap() {
		Map map = new Map(mapSize);
		generateAirports(map);
		return map;
	}

	private void generateAirports(Map map) {
		for (int i=0; i<airportsCount; i++)
		{
			Airport airport = new Airport(i, getRandomAirportName());
			airport.setPosition(randomPosition());
			map.addAirport(airport);
			setAirportsPositions(map, map.getAirportsContainer());
		}
	}

	public void setAirportsPositions(Map map, AirportsContainer airportsContainer)
	{
		for(int i = 0; i<airportsContainer.count(); i++) {
			int x = (int)airportsContainer.getAirport(i).getPosition().getX();
			int y = (int)airportsContainer.getAirport(i).getPosition().getY();
			map.getSegments()[x][y].setSegmentType(SegmentType.AIRPORT);
		}
	}

	private Vector2 randomPosition()
	{
		Random random = new Random();
		int x = random.nextInt(mapSize);
		int y = random.nextInt(mapSize);
		return new Vector2(x,y);
	}

	private String getRandomAirportName() {
		String[] airportsNameList = {"Heathrow Airport", "London Gatwick Airport", "Minneapolis International Airport", "Miami International Airport", "Seattle-Tacoma International Airport", "Chhatrapati Shivaji International Airport", "Leonardo da Vinci–Fiumicino Airport", "Sydney Kingsford-Smith Airport", "Barcelona–El Prat Airport", "Orlando International Airport", "Taiwan Taoyuan International Airport", "Benito Juárez International Airport", "Ninoy Aquino International Airport", "Narita International Airport", "Newark Liberty International Airport", "Kunming Changshui International Airport", "Orlando International Airport", "Madrid Barajas Airport", "Seoul Incheon International Airport", "Kuala Lumpur International Airport", "Suvarnabhumi Airport", "Denver International Airport", "Soekarno-Hatta International Airport", "Singapore Changi Airport", "John F. Kennedy International Airport", "Shanghai Pudong International Airport", "Istanbul Atatürk Airport", "Hong Kong International Airport", "Los Angeles International Airport", "Tokyo Haneda Airport", "Charles de Gaulle Airport", "Frankfurt Airport", "Munich Airport", "Frederic Chopin Airport", "Edinburgh Airport", "Hamburg Airport", "Dusseldorf Airport", "Dublin Airport", "Brussels Airport"};
		Random random = new Random();
		int index = random.nextInt(airportsNameList.length);
		return airportsNameList[index];
	}
}