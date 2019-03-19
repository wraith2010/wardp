package com.doublewattgames.war.componet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		if (getCards().isEmpty())
			throw new OutOfCardsException();

		return getCards().remove(0);
	}

	public List<Card> topCard(int x) throws OutOfCardsException {

		List<Card> cardsToReturn = new ArrayList<>();

		for (; x > 0; x--) {
			if (getCards().isEmpty()) {
				throw new OutOfCardsException();
			}
			cardsToReturn.add(getCards().remove(0));
		}

		return cardsToReturn;

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

}
