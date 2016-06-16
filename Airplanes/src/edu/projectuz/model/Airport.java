package edu.projectuz.model;

public class Airport {

	private int id;
	private String name;
	private ControlTower controlTower;
	private Vector2 position;

	public Airport(int id, String name) {
		setId(id);
		setName(name);
		controlTower = new ControlTower();
		setPosition(Vector2.zero);
		System.out.println(String.format("Create airport with id: %d", getId()));
	}

	public int getId() {
		return id;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public ControlTower getControlTower() {
		return controlTower;
	}

	public void setControlTower(ControlTower controlTower) {
		this.controlTower = controlTower;
	}

	@Override
	public String toString() {
		return  "[" + getId() + "]" + getName();
	}
}