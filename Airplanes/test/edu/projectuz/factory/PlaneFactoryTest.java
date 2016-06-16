package edu.projectuz.factory;

import edu.projectuz.model.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlaneFactoryTest {

    @Test
    public void testCreatePlane() throws Exception {

        //Arrange
        PlaneFactory planeFactory = new PlaneFactory();
        Vector2 position = new Vector2(1,1);
        Airport airport = new Airport(1, "testName");
        Airport airport2 = new Airport(2, "testName");
        airport.setPosition(position);
        AirportsContainer airportsContainer = new AirportsContainer();
        airportsContainer.addAirport(airport);
        airportsContainer.addAirport(airport2);
        Map map = new Map(2);
        map.setAirportsContainer(airportsContainer);
        FlightOrder flightOrder = new FlightOrder(2, airport, map);

        //Act
        Plane plane = planeFactory.createPlane(1, position, flightOrder, map);

        //Assert
        assertEquals(plane.getPlaneId(), 1);
        assertEquals(plane.getPosition(), position);
        assertNotNull(plane.getPlaneName());
        assertNotNull(plane.getFuelTank());
        assertEquals(2, flightOrder.getId());
        assertEquals(airport.getPosition(), flightOrder.getActualAirport().getPosition());
    }
}