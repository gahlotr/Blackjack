package ca.sheridancollege.project;

/**
 * A concrete class representing a standard playing card.
 */
public class PlayingCard extends Card {
    // Reuse enums from DeckOfCards
    private final DeckOfCards.Rank rank;
    private final DeckOfCards.Suit suit;

    public PlayingCard(DeckOfCards.Rank rank, DeckOfCards.Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public DeckOfCards.Rank getRank() {
        return rank;
    }

    public DeckOfCards.Suit getSuit() {
        return suit;
    }

    /**
     * Returns the Blackjack value of the card.
     * Face cards are 10, Aces are 1
     */
    public int getValue() {
        switch (rank) {
            case ACE: 
                return 1;
            case TWO: 
                return 2;
            case THREE: 
                return 3;
            case FOUR: 
                return 4;
            case FIVE: 
                return 5;
            case SIX: 
                return 6;
            case SEVEN: 
                return 7;
            case EIGHT: 
                return 8;
            case NINE: 
                return 9;
            case TEN:
            case JACK:
            case QUEEN:
            case KING: return 10;
            default: return 0;
        }
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}