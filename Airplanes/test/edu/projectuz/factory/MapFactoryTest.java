package edu.projectuz.factory;

import edu.projectuz.enums.SegmentType;
import edu.projectuz.model.Airport;
import edu.projectuz.model.AirportsContainer;
import edu.projectuz.model.Map;
import edu.projectuz.model.Vector2;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class MapFactoryTest {

    @Test
    public void generateMap() throws Exception {
        //Arrange
        int mapSize = 5;
        Map map;
        MapFactory mapFactory = new MapFactory(mapSize, 2);

        //Act
        map = mapFactory.generateMap();
        int airportCounter = 0;
        for (int i = 0; i < map.getSize(); i++) {
            for (int j = 0; j < map.getSize(); j++) {
                if (map.getSegments()[i][j].getSegmentType().equals(SegmentType.AIRPORT)) {
                    airportCounter++;
                }
            }
        }
        //Assert
        assertEquals(airportCounter, 2);
    }

    @Test
    public void setAirportPositions() throws Exception {
        //Arrange
        AirportsContainer airportsContainer = new AirportsContainer();
        Airport airport1 = new Airport(0, "Test 0");
        airport1.setPosition(new Vector2(2,2));
        airportsContainer.addAirport(airport1);

        int mapSize = 10;
        Map map = new Map(10);
        MapFactory mapFactory = new MapFactory(mapSize, 1);

        //Act
        mapFactory.setAirportsPositions(map, airportsContainer);
        //Assert
        assertEquals(SegmentType.AIRPORT, map.getSegments()[2][2].getSegmentType());
    }
}