package com.doublewattgames.war;

import org.junit.Test;

import com.doublewattgames.war.concept.Game;
import com.doublewattgames.war.concept.Player;
import com.doublewattgames.war.exception.BoredomException;

public class SingleGameTest {

	@Test
	public void singleGame() throws BoredomException {
		Game game = new Game();

		game.addPlayer(new Player("John"));
		game.addPlayer(new Player("Jill"));

		game.deal();

		while (!game.isGameOver()) {
			game.playOneRound();
			if (game.getRoundCount() % 50 == 0) {
				System.out.println(game);
			}
		}

		System.out.println("Rounds:\t" + game.getRoundCount());
		System.out.println("War:\t" + game.getWarCount());
	}

}
