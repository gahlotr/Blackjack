/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public class GroupOfCards {

    //The group of cards, stored in an ArrayList
    private ArrayList<Card> cards;
    private int size;//the size of the grouping

    public GroupOfCards(int size) {
        this.size = size;
        this.cards = new ArrayList<>();
    }

    /**
     * A method that will get the group of cards as an ArrayList
     *
     * @return the group of cards.
     */
    public ArrayList<Card> getCards() {
        return cards;
    }
    
     
    //@param cards the card list to set
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * @return the size of the group of cards
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the max size for the group of cards
     */
    public void setSize(int size) {
        this.size = size;
    }
    
     //adds a card to this group
     //@param card the card to add
    public void addCard(Card card) {
        cards.add(card);
    }
    
     
     //removes and returns the top card (index 0) from this group
     //returns null if the group is empty
     //@return the dealt card, or null if none of them remain
    public Card dealCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }
    
   
    //return true if there are no cards remaining in this group
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    
    //@return the number of cards currently in this group
    public int currentSize() {
        return cards.size();
    }
    
    //builds a full standard 52-card deck using all Suit and Rank enum values listed
    //clears any existing cards before building the deck
    public void buildDeck() {
            cards.clear();
            for (DeckOfCards.Suit suit : DeckOfCards.Suit.values()) {
                for (DeckOfCards.Rank rank : DeckOfCards.Rank.values()) {
                    cards.add(new DeckOfCards(rank, suit));
                }
            }
            // should be 52 cards (4 suits × 13 ranks)
            this.size = cards.size();
    }
}


