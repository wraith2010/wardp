package com.doublewattgames.war.componet;

public class Card {

	public enum Suit {
		SPADE, CLUB, HEART, DIAMOND
	}

	private int rank;
	private Suit suit;

	public Card(int rank, Suit suit) {
		setRank(rank);
		setSuit(suit);
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	@Override
	public String toString() {

		StringBuffer stringBuffer = new StringBuffer();

		switch (getSuit()) {
		case SPADE:
			stringBuffer.append((char) '\u2660');
			break;
		case DIAMOND:
			stringBuffer.append((char) '\u2666');
			break;
		case CLUB:
			stringBuffer.append((char) '\u2663');
			break;
		case HEART:
			stringBuffer.append((char) '\u2764');
			break;
		}

		switch (getRank()) {
		case 14:
			stringBuffer.append("A");
			break;
		case 13:
			stringBuffer.append("K");
			break;
		case 12:
			stringBuffer.append("Q");
			break;
		case 11:
			stringBuffer.append("J");
			break;
		default:
			stringBuffer.append(getRank());
		}

		return stringBuffer.toString();
	}

}
