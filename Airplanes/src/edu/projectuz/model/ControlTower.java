package edu.projectuz.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ControlTower implements Runnable{

	private Queue<Plane> waitingPlanes = new ConcurrentLinkedQueue<>();
	private boolean isFree = true;

	public synchronized Queue<Plane> getWaitingPlanes() {
		return waitingPlanes;
	}

	public void setWaitingPlanes(Queue<Plane> waitingPlanes) {
		this.waitingPlanes = waitingPlanes;
	}

	public synchronized boolean checkRunway() {
		return isFree;
	}

	public synchronized void lockRunway() {
		isFree = false;
	}

	public synchronized void unlockRunway() {
		isFree = true;
	}

	public synchronized void synchronizeLandings()
	{
		int k=700;
		for(int i=0; i<waitingPlanes.size(); i++)
		{
			try {
				waitingPlanes.poll().sleep(k);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			unlockRunway();
			k=k+500;
		}
	}


	@Override
	public void run() {

	}
}