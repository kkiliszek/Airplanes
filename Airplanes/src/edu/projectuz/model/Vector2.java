package edu.projectuz.model;

public class Vector2 {

	private float x;
	private float y;

	public Vector2(){}

	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return this.x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return this.y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public static Vector2 zero = new Vector2(0,0);

	public void addToX(float x)
	{
		this.x += x;
	}

	public void addToY(float y)
	{
		this.y += y;
	}

	@Override
	public String toString() {
		return "X:" + getX() +
				"  Y:" + getY();
	}
}