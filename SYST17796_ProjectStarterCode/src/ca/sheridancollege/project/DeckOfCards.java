/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author rgahlot
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
     * @param rank the rank to set
     */
    public void setRank(Rank rank) {
        this.rank = rank;
    }

    /**
     * @return the suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * @param suit the suit to set
     */
    public void setSuit(Suit suit) {
        this.suit = suit;
    }
    
}
