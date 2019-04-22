package com.doublewattgames.war.concept;

import java.util.List;

import com.doublewattgames.war.componet.Card;
import com.doublewattgames.war.componet.Deck;
import com.doublewattgames.war.exception.OutOfCardsException;

public class Player {

	private Deck deck = new Deck();
	private String name = null;

	public Player(String name) {
		setName(name);
	}

	public void accept(Card card) {

		if (card == null)
			return;

		getDeck().add(card);
	}

	public void accept(Deck deck) {
		getDeck().addAll(deck.getCards());
	}

	public void accept(List<Card> cards) {
		getDeck().addAll(cards);
	}

	public Card topCard() throws OutOfCardsException {
		return getDeck().topCard();
	}

	public List<Card> topCard(int x) throws OutOfCardsException {
		return getDeck().topCard(x);
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isOutOfCards() {
		return getDeck().isOutOfCards();
	}

}
