package edu.projectuz;

import edu.projectuz.controller.MapController;
import edu.projectuz.model.Company;
import edu.projectuz.model.Map;
import edu.projectuz.model.MyFrame;

import java.awt.*;


public class Main {

    public static void main(String[] args) {

        final int MAP_SIZE = 700;
        final int AIRPORT_COUNT = 50;
        Thread[] AirplaneThreads = new Thread[AIRPORT_COUNT];

        MapController mapController = new MapController();
        mapController.generateMap(MAP_SIZE, AIRPORT_COUNT);
        Map map = mapController.getMap();

        Company company = new Company(map);
        company.createPlanes(map);

        for(int i =0; i<AIRPORT_COUNT; i++)
        {
            company.getPlane(i).getFlightOrder().planFlight(company.getPlane(i));
        }

        /*for(int i =0; i<1; i++)
        {
            if(company.getPlane(i).getFlightOrder().getAirportFromQueue() != null)
            {
                System.out.println("Samolot: " + company.getPlane(i).getPlaneId() + "  Nastepne ladowanie w: [" + company.getPlane(i).getFlightOrder().getAirportFromQueue().getId() + "]" + company.getPlane(i).getFlightOrder().getAirportFromQueue().getName() + " |   Rozmiar kolejki: " + company.getPlane(i).getFlightOrder().sizeOfQueue());
            }
        }

        for(int i =0; i<1; i++)
        {
            if(company.getPlane(i).getFlightOrder().getAirportFromQueue() != null)
            {
                System.out.println("Samolot o ID: " + company.getPlane(i).getPlaneId() + " wylatujacy z " + map.getAirportsContainer().getAirport(i) + " do " + company.getPlane(i).getFlightOrder().getDestinationAirport() + company.getPlane(i).getFlightOrder().toString());
            }
        }*/


        for (int i=0; i<AIRPORT_COUNT; i++) {
            AirplaneThreads[i] = company.getPlane(i);
            AirplaneThreads[i].start();
        }

        //Thread thread = company.getPlane(0);
        //thread.start();

        EventQueue.invokeLater(() -> new MyFrame(map, company));

    }
}
