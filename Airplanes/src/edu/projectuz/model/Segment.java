package edu.projectuz.model;

import edu.projectuz.enums.SegmentType;

public class Segment {

	private Vector2 position;
	private int size;
	private SegmentType segmentType;

	public Segment() {
		position = new Vector2();
	}

	public void setPosition(float x, float y)
	{
		position.setX(x);
		position.setY(y);
	}

	public SegmentType getSegmentType() {
		return segmentType;
	}

	public void setSegmentType(SegmentType segmentType) {
		this.segmentType = segmentType;
	}

	public Vector2 getPosition() {
		return position;
	}
}
