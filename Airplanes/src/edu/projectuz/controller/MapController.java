package edu.projectuz.controller;

import edu.projectuz.factory.MapFactory;
import edu.projectuz.model.Map;

public class MapController {

    private Map map;
    private MapFactory mapFactory;

    public void generateMap(int mapSize, int airportCount)
    {
        mapFactory = new MapFactory(mapSize, airportCount);
        map = mapFactory.generateMap();
    }

    public Map getMap() {
        return map;
    }
}
