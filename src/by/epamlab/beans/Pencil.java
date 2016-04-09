package by.epamlab.beans;

import java.util.Random;

public class Pencil implements Runnable {
	private long finishTime;
	private Thread thread;
	private String pencil = "Pencil";
	private Artist artist;
	private final static char[] chars = { '-', '@', '#', '/', '~', '^', '$',
			'=', '%', '&' };

	public Pencil(Artist artist) {
		this.artist = artist;
	}

	public void draw(long time) {
		finishTime = time;
		thread = new Thread(this, pencil);
		thread.start();
	}

	@Override
	public void run() {
		System.out.println(pencil + " started");
		int size = chars.length;
		while (System.currentTimeMillis() < finishTime) {
			int nSymbol = (new Random()).nextInt(size);
			char[] newChars = new char[nSymbol];
			for (int i = 0; i < nSymbol; i++) {
				newChars[i] = chars[(new Random()).nextInt(size)];
			}
			artist.draw(new String(newChars));
		}
	}
}
