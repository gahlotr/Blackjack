/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author
 */
public abstract class PlayingCard extends DeckOfCards {

	public String Suit;
	public String Rank;
	private Suit suit;
	private Rank rank;
	private int value;
        
        public PlayingCard(Rank rank, Suit suit) {
            super(rank, suit);
        }
        
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

}