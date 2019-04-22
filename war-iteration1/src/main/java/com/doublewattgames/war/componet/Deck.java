package com.doublewattgames.war.componet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.doublewattgames.war.exception.OutOfCardsException;

public class Deck {

	private List<Card> cards = new ArrayList<>();

	public void add(Card card) {
		getCards().add(card);
	}

	public void addAll(List<Card> cards) {
		getCards().addAll(cards);
	}

	public int cardCount() {
		return getCards().size();
	}

	public void shuffle() {
		Collections.shuffle(getCards());
	}

	public Card topCard() throws OutOfCardsException {

		Card card = getCards().remove(0);

		if (getCards().isEmpty())
			throw new OutOfCardsException(card, null);

		return card;
	}

	public List<Card> topCard(int x) throws OutOfCardsException {

		List<Card> cardsToReturn = new ArrayList<>();

		for (; x > 0; x--) {
			Card card = getCards().remove(0);
			if (getCards().isEmpty()) {
				throw new OutOfCardsException(card, cardsToReturn);
			}
			cardsToReturn.add(card);
		}

		return cardsToReturn;

	}

	public int lowestRank() {

		int lowestRank = 20;

		for (Card card : getCards()) {
			if (card.getRank() < lowestRank)
				lowestRank = card.getRank();
		}

		return lowestRank;
	}

	public void purge(int rank) {
		setCards(getCards().stream().filter(card -> card.getRank() > rank).collect(Collectors.toList()));
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	@Override
	public String toString() {

		StringBuffer stringBuffer = new StringBuffer();

		getCards().stream().forEach(card -> stringBuffer.append(card));

		return stringBuffer.toString();
	}

	public boolean isOutOfCards() {

		return getCards().isEmpty();
	}

}
