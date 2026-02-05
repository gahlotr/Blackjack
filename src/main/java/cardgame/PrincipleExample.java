/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cardgame;

public class PrincipleExample {
    public static void main(String[] args) {
        
        // user interaction code print deck
        // request the generated class for the hand
        // generation of card is delegated to another class
        // print cards generated
        
        Card[] hand = GenerateClass.generateHand(10);
        for(Card c: hand)
        {
            System.out.println(c.getSuit() + " " + c.getValue());
        }
    }
}