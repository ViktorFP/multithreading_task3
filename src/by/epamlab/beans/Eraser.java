package by.epamlab.beans;

import java.util.Random;

import by.epamlab.Constants;

public class Eraser implements Runnable {
	private long finishTime;
	private Thread thread;
	private String eraser = "Eraser";
	private Artist artist;

	public Eraser(Artist artist) {
		this.artist = artist;
	}

	public void clean(long time) {
		finishTime = time;
		thread = new Thread(this, eraser);
		thread.start();
	}

	@Override
	public void run() {
		System.out.println(eraser + " started");
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			System.out.println(eraser + Constants.INTERRUPTED_EXCEPTION
					+ e.getMessage());
		}
		while (System.currentTimeMillis() < finishTime) {
			artist.erase((new Random()).nextInt(11));
		}
	}
}
