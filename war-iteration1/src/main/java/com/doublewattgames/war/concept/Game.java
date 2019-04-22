package com.doublewattgames.war.concept;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.doublewattgames.war.componet.Card;
import com.doublewattgames.war.componet.Card.Suit;
import com.doublewattgames.war.componet.Deck;
import com.doublewattgames.war.exception.BoredomException;
import com.doublewattgames.war.exception.OutOfCardsException;
import com.doublewattgames.war.exception.WarException;

public class Game {

	private List<Player> players = new ArrayList<>();
	private int warCount = 0;
	private int roundCount = 0;

	private boolean purge = false;

	public void addPlayer(Player player) {
		getPlayers().add(player);
	}

	public void deal() {

		Deck deck = new Deck();

		for (Suit suit : Suit.values()) {
			for (int x = 0; x < 13; x++) {
				Card card = new Card(x + 2, suit);
				deck.add(card);
			}
		}

		deck.shuffle();

		while (deck.cardCount() > 0) {
			for (Player player : getPlayers()) {
				try {
					player.accept(deck.topCard());
				} catch (OutOfCardsException outOfCardsException) {
					// this is expected do nothing
				}
			}
		}

	}

	public boolean isGameOver() {

		int playersWithCards = 0;

		for (Player player : getPlayers()) {
			if (player.getDeck().cardCount() > 0)
				playersWithCards++;
			if (playersWithCards > 1)
				return false;
		}

		return true;
	}

	public void playOneRound() throws BoredomException {

		setRoundCount(getRoundCount() + 1);

		playOneRound(null);

	}

	private void playOneRound(List<Card> wager) {

		Map<Player, Card> map = collectOneCardFromEach();

		Player topPlayer;
		try {
			topPlayer = findHighCardPlayer(map);
			topPlayer.accept(map.values().stream().collect(Collectors.toList()));
			if (wager != null) {
				topPlayer.accept(wager);
			}
		} catch (WarException warException) {

			setWarCount(getWarCount() + 1);
			if (wager == null) {
				wager = new ArrayList<>();
			}

			for (Player player : getPlayers()) {
				// if this the players last card they use it for aditional wars

				if (player.isOutOfCards())
					return;

				try {
					wager.addAll(player.topCard(3));
				} catch (OutOfCardsException outOfCardsException) {
					wager.addAll(outOfCardsException.getAdditionalCards());
					player.getDeck().add(outOfCardsException.getLastCard());
				}
			}

			wager.addAll(map.values().stream().collect(Collectors.toList()));

			playOneRound(wager);

			if (isPurge()) {
				purge();
			}
		}

	}

	private Map<Player, Card> collectOneCardFromEach() {

		Map<Player, Card> map = new HashMap<>();

		for (Player player : getPlayers()) {
			try {
				map.put(player, player.topCard());
			} catch (OutOfCardsException outOfCardsException) {
				map.put(player, outOfCardsException.getLastCard());
			}
		}

		return map;
	}

	private void purge() {

		int lowestRank = 100;

		for (Player player : getPlayers()) {
			Deck deck = player.getDeck();
			int deckLowestRank = deck.lowestRank();
			if (lowestRank > deckLowestRank)
				lowestRank = deckLowestRank;
		}

		for (Player player : getPlayers()) {
			player.getDeck().purge(lowestRank);
		}

	}

	private Player findHighCardPlayer(Map<Player, Card> map) throws WarException {

		Player topPlayer = null;
		int topRank = 0;

		for (Entry<Player, Card> entry : map.entrySet()) {

			if (topRank == entry.getValue().getRank()) {
				throw new WarException();
			} else if (topRank < entry.getValue().getRank()) {
				topRank = entry.getValue().getRank();
				topPlayer = entry.getKey();
			}

		}

		return topPlayer;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();

		for (Player player : getPlayers()) {

			stringBuffer.append(
					String.format("%s(%s):\t%s%n", player.getName(), player.getDeck().cardCount(), player.getDeck()));
		}

		stringBuffer.append(String.format("Round: %s\tWar: %s%n", getRoundCount(), getWarCount()));

		return stringBuffer.toString();
	}

	public int getWarCount() {
		return warCount;
	}

	public void setWarCount(int warCount) {
		this.warCount = warCount;
	}

	public int getRoundCount() {
		return roundCount;
	}

	public void setRoundCount(int roundCount) throws BoredomException {

		if (roundCount > 400)
			throw new BoredomException();

		this.roundCount = roundCount;
	}

	public boolean isPurge() {
		return purge;
	}

	public void setPurge(boolean purge) {
		this.purge = purge;
	}
}
