package edu.projectuz.model;

import edu.projectuz.enums.SegmentType;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest {

    @org.junit.Test
    public void setSegmentsForNewMap() throws Exception {
        Map map = new Map(10);
        for (int i = 0; i < map.getSize(); i++) {
            for(int j = 0; j < map.getSize(); j++){
                if (map.getSegments()[i][j] == null || !map.getSegments()[i][j].getSegmentType().equals(SegmentType.EMPTY)){
                    fail();
                }
            }
        }
    }

    @Test
    public void setSegment() throws Exception {
        //Arrange
        Map map = new Map(5);
        Vector2 segmentPos = new Vector2(2,2);

        //Act
        map.setSegment(segmentPos, SegmentType.AIRPORT);
        //Assert
        assertEquals(SegmentType.AIRPORT, map.getSegments()[(int)segmentPos.getX()][(int)segmentPos.getY()].getSegmentType());
    }
}