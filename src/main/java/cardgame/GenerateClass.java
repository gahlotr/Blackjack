/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardgame;

import java.util.Random;

public class GenerateClass {
    
    // Generation of cards logic - one job - need to be high cohesion
    public static Card[] generateHand(int numCards)
    {
        Card[] hand = new Card[numCards];
        
        // generate random suits, value from enum
        Random random = new Random();
        
        for (int i = 0; i < hand.length; i++)
        {
            // random.nextInt(13) - don't want to hard code 13
            int numberValues = Card.Value.values().length; // how many constants in the enum value
            Card.Value value = Card.Value.values()[random.nextInt(numberValues)];
            
            int numberSuits = Card.Suit.values().length; // 4 constants in the enum suits 
            Card.Suit suit = Card.Suit.values()[random.nextInt(numberSuits)];  
            
            Card card = new Card(value, suit);
            hand[i] = card;
        }
        
        return hand;
    }
}