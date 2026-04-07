package ca.sheridancollege.project;

import ca.sheridancollege.project.DeckOfCards.*;

public class PlayingCard extends Card {

	public String Suit;
	public String Rank;
	private Suit suit;
	private Rank rank;
	private int value;

	public Suit getSuit() {
		return this.suit;
	}

	/**
	 * 
	 * @param suit
	 */
	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public Rank getRank() {
		return this.rank;
	}

	/**
	 * 
	 * @param rank
	 */
	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public int getValue() {
		return this.value;
	}

	/**
	 * 
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
        
        @Override
        public String toString() {
                return "";
        }    
}