/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author
 */
public class DeckOfCards extends Card{
    public enum Rank {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}
    public enum Suit {DIAMONDS, SPADES, CLUBS, HEARTS}
    
    private Rank rank;
    private Suit suit;
    
    public DeckOfCards(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }
    
    @Override
    public String toString() {
        return String.format(rank + " of " + suit);
    }

    /**
     * @return the value
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * @param value
     */
    public void setRank(Rank value) {
        this.rank = value;
    }

    /**
     * @return the suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * @param suit
     */
    public void setSuit(Suit suit) {
        this.suit = suit;
    }
    
}