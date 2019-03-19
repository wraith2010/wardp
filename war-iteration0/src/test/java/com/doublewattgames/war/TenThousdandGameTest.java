package com.doublewattgames.war;

import org.junit.Test;

import com.doublewattgames.war.concept.Game;
import com.doublewattgames.war.concept.Player;
import com.doublewattgames.war.exception.BoredomException;

public class TenThousdandGameTest {

	private static final int LIMIT = 10000;

	@Test
	public void multiGame() {

		int totalRoundCount = 0;
		int totalWarCount = 0;
		int boredomCount = 0;

		for (int x = 0; x < LIMIT; x++) {

			Game game = new Game();

			game.addPlayer(new Player("John"));
			game.addPlayer(new Player("Jill"));

			game.deal();

			while (!game.isGameOver()) {
				try {
					game.playOneRound();
				} catch (BoredomException boredomException) {
					boredomCount++;
					break;
				}
			}

			totalRoundCount += game.getRoundCount();
			totalWarCount += game.getWarCount();
		}

		System.out.println("Average Rounds:\t" + (totalRoundCount / LIMIT));
		System.out.println("Averge War:\t" + (totalWarCount / LIMIT));
		System.out.println(
				String.format("Games ending in Boredom:\t%s percent", (((boredomCount / ((double) LIMIT)) * 100.00))));

	}

}
