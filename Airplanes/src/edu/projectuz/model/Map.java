package edu.projectuz.model;

import edu.projectuz.enums.SegmentType;

public class Map {

	private Segment[][] segments;
	private int segmentSize;
	private int size;
	private AirportsContainer airportsContainer =  new AirportsContainer();

	public Map(int size) {
		this.size = size;
		segments = new Segment[size][size];
		setSegmentsPositions();
	}

	public Segment[][] getSegments() {
		return segments;
	}

	public int getSize() {
		return size;
	}

	public AirportsContainer getAirportsContainer() {
		return airportsContainer;
	}

	public void addAirport(Airport airport) {
		airportsContainer.addAirport(airport);
	}

	public void setAirportsContainer(AirportsContainer airportsContainer) {
		this.airportsContainer = airportsContainer;
	}

	public void setSegment(Vector2 position, SegmentType segmentType){
		Segment segment = getSegments()[(int)position.getX()][(int)position.getY()];
		if(segment != null){
			segment.setSegmentType(segmentType);
		}
	}

	public Segment[] getSegment(SegmentType segmentType) {

		Segment[] segments = new Segment[airportsContainer.count()];

		int k=0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(this.segments[i][j].getSegmentType() == segmentType){
					segments[k] = this.segments[i][j];
					k++;
				}
			}
		}
		return segments;
	}

	private void setSegmentsPositions()
	{
        for(int i=0; i<size; i++)
		{
			for(int j=0; j<size; j++)
			{
				segments[i][j] = new Segment();
				segments[i][j].setPosition((float)i,(float)j);
				segments[i][j].setSegmentType(SegmentType.EMPTY);
			}
		}
	}

	public void refresh() {
		// TODO - implement Map.refresh
		throw new UnsupportedOperationException();
	}
}