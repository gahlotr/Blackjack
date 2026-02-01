/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.principleexample;

/**
 *
 * @author mikel
 */
public class Card {

   //user input and output
        //enum value, data type safe to avoid tight coupling
        // enum - constants, special class, user defined data type
        
        public enum Suit {DIAMONDS, SPADE,CLUBS, HEARTS} //Allowed values
        //{} // directions EAST, WEST, NORTH,SOUTH
        
        public enum Value {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}
        
        private Value value; //store enum constance, user defined user  type
        private Suit suit; //store enum constance
        
        public Card(Value v, Suit s)
        {
         this.value = v;
         this.suit = s;
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