package by.epamlab.beans;

import java.util.Random;

import by.epamlab.Constants;

public class Artist {
	private Eraser eraser;
	private Pencil pencil;
	private volatile String picture = "";
	private String artist = "Artist";
	private int sleepTime = 250;
	private int spaceCoun = 0;

	public Artist() {
		eraser = new Eraser(this);
		pencil = new Pencil(this);
	}

	public void doWork(long time) {
		long finishTime = System.currentTimeMillis() + time;
		eraser.clean(finishTime);
		pencil.draw(finishTime);
	}

	synchronized void draw(String picture) {
		System.out.println("Start draw");
		char[] chars = picture.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			this.picture += chars[i];
			System.out.println(this.picture);
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				System.out.println(artist + " /draw/ "
						+ Constants.INTERRUPTED_EXCEPTION + e.getMessage());
			}
		}
		System.out.println("End draw");
		notify();
	}

	synchronized void erase(int nSymbols) {
		System.out.println("Start erase");
		while (picture.isEmpty() || spaceCoun == picture.length()) {
			try {
				System.out.println("Wait erase");
				wait();
				System.out.println("Waik up erase");
			} catch (InterruptedException e) {
				System.out.println(artist + " /erase/ "
						+ Constants.INTERRUPTED_EXCEPTION + e.getMessage());
			}
		}
		char[] chars = picture.toCharArray();
		for (int i = 0; i < nSymbols;) {
			if (spaceCoun == picture.length()) {
				break;
			}
			int idx = (new Random()).nextInt(chars.length);
			if (chars[idx] != ' ') {
				chars[idx] = ' ';
				i++;
				spaceCoun++;
				System.out.println(chars);
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					System.out.println(artist + " /erase/ "
							+ Constants.INTERRUPTED_EXCEPTION + e.getMessage());
				}
			}
		}
		picture = new String(chars);
		System.out.println("End erase");
	}
}
