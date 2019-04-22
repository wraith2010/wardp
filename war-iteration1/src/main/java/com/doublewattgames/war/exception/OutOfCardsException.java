package com.doublewattgames.war.exception;

import java.util.List;

import com.doublewattgames.war.componet.Card;

@SuppressWarnings("serial")
public class OutOfCardsException extends Exception {

	private Card lastCard = null;
	private List<Card> additionalCards = null;

	public OutOfCardsException(Card lastCard, List<Card> additionalCard) {
		setLastCard(lastCard);
		setAdditionalCards(additionalCard);
	}

	public void setLastCard(Card lastCard) {
		this.lastCard = lastCard;
	}

	public Card getLastCard() {
		return lastCard;
	}

	public List<Card> getAdditionalCards() {
		return additionalCards;
	}

	public void setAdditionalCards(List<Card> additionalCards) {
		this.additionalCards = additionalCards;
	}

}
