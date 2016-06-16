package edu.projectuz.model;

import edu.projectuz.enums.SegmentType;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompanyTest {

    @Test
    public void testCreatePlanes() throws Exception {

        //Arrange
        Vector2 position = new Vector2(1,1);
        Airport airport = new Airport(1,"testName");
        Airport airport2 = new Airport(2,"testName");
        airport.setPosition(position);
        AirportsContainer airportsContainer = new AirportsContainer();
        airportsContainer.addAirport(airport);
        airportsContainer.addAirport(airport2);
        Map map = new Map(5);
        map.setAirportsContainer(airportsContainer);
        Company company = new Company(map);

        //Act
        company.createPlanes(map);

        //Assert
        assertEquals(company.getPlane(0).getPosition(), position);
        assertEquals(map.getSegments()[1][1].getSegmentType(),SegmentType.AIRPORT_PLANE);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getPlaneTest() throws Exception {

        //Arrange
        Vector2 position = new Vector2(1,1);
        Airport airport = new Airport(1,"testName");
        Airport airport2 = new Airport(2,"testName");
        airport.setPosition(position);
        AirportsContainer airportsContainer = new AirportsContainer();
        airportsContainer.addAirport(airport);
        airportsContainer.addAirport(airport2);
        Map map = new Map(5);
        map.setAirportsContainer(airportsContainer);
        Company company = new Company(map);

        //Act
        company.createPlanes(map);
        Plane plane = company.getPlane(3);
    }

    @Test
    public void addPlaneTest() throws Exception {

        //Arrange
        Vector2 position = new Vector2(1,1);
        Airport airport = new Airport(1,"testName");
        Airport airport2 = new Airport(2,"testName");
        airport.setPosition(position);
        AirportsContainer airportsContainer = new AirportsContainer();
        airportsContainer.addAirport(airport);
        airportsContainer.addAirport(airport2);
        Map map = new Map(5);
        map.setAirportsContainer(airportsContainer);
        Company company = new Company(map);

        //Act
        company.createPlanes(map);
        company.addPlane(company.getPlane(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addPlaneTestBAD() throws Exception {

        //Arrange
        Vector2 position = new Vector2(1,1);
        Airport airport = new Airport(1,"testName");
        Airport airport2 = new Airport(2,"testName");
        airport.setPosition(position);
        AirportsContainer airportsContainer = new AirportsContainer();
        airportsContainer.addAirport(airport);
        airportsContainer.addAirport(airport2);
        Map map = new Map(5);
        map.setAirportsContainer(airportsContainer);
        Company company = new Company(map);

        //Act
        company.createPlanes(map);
        company.addPlane(company.getPlane(2));

    }


}