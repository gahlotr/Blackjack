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
 * @author Group 2 - Bruins (Ria Gahlot, Madison Scarlett, Rayyan Javed, John Michael Lagumbay)
 */
public class GroupOfCards {
    private ArrayList<Card> cards;
    private int size;

    public GroupOfCards(int size) {
        this.size = size;
        this.cards = new ArrayList<>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

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

    /**
     * Builds a standard 52-card deck using PlayingCard objects
     */
    public void buildDeck() {
        cards.clear();
        for (DeckOfCards.Suit suit : DeckOfCards.Suit.values()) {
            for (DeckOfCards.Rank rank : DeckOfCards.Rank.values()) {
                // use PlayingCard
                cards.add(new PlayingCard(rank, suit));
            }
        }
        this.size = cards.size();
    }

    public int currentSize() {
        return cards.size();
    }
    
}


