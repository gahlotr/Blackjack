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
    public enum Value {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}
    public enum Suit {DIAMONDS, SPADES, CLUBS, HEARTS}
    
    private Value value;
    private Suit suit;
    
    public DeckOfCards(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }
    
    @Override
    public String toString() {
        return String.format(value + " of " + suit);
    }

    /**
     * @return the value
     */
    public Value getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Value value) {
        this.value = value;
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
