package edu.projectuz.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class FlightOrderTest {

    @Test
    public void getDistance() throws Exception {
        //Arrange
        Airport airport1 = new Airport(0, "test1");
        airport1.setPosition(new Vector2(6,3));
        Airport airport2 = new Airport(1, "test2");
        airport2.setPosition(new Vector2(4,2));
        Map map = new Map(2);
        AirportsContainer airportsContainer = new AirportsContainer();
        airportsContainer.addAirport(airport1);
        airportsContainer.addAirport(airport2);
        map.setAirportsContainer(airportsContainer);

        FlightOrder order = new FlightOrder(1, airport1, map);
        double expected = 2.2360;
        //Act
        double result = order.getDistance(airport2);
        //Assert
        assertEquals(expected, result, 0.001);
    }
}