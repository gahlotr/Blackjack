package ca.sheridancollege.project;
import java.util.ArrayList;

// @author Group 2 - Bruins (Ria Gahlot, Madison Scarlett, Rayyan Javed, John Michael Lagumbay)

// A Constructor, gets information from player and profile information
public class BlackjackPlayer extends Player {

    private ArrayList<PlayingCard> hand;  // the player's current hand
    private int score;                    // hand total
    private double balance;               // player's balance
    private Bet bet;                      // current round's bet
    private boolean isBust;               // true if hand total > 21
    private boolean hasBlackjack;         // true if initial hand totals exactly 21

    //Constructor for setting up a new BlackjackPlayer.
    public BlackjackPlayer(String name, double balance) {
        super(name);  // tells Parent Player to store their name
        this.hand = new ArrayList<>(); //gives them an empty hand
        this.score = 0;
        this.balance = balance;
        this.bet = null;
        this.isBust = false;
        this.hasBlackjack = false;
    }

    // Getter: return the player's current hand
    public ArrayList<PlayingCard> getHand() {
        return hand;
    }

    // Setter: set hand

    public void setHand(ArrayList<PlayingCard> hand) {
        this.hand = hand;
    }

    // Getter: return the player's current score
    public int getScore() {
        return score;
    }

    // Setter: set score
    public void setScore(int score) {
        this.score = score;
    }

    // Getter: return the player's balance
    public double getBalance() {
        return balance;
    }

    // Setter: set balance
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Getter: return the player's bet
    public Bet getBet() {
        return bet;
    }

    // Setter: set player's bet
    public void setBet(Bet bet) {
        this.bet = bet;
    }

    // Return true if the player has busted (score > 21)
    public boolean isBust() {
        return isBust;
    }

    // Setter: set isBust true if the player has busted
    public void setBust(boolean isBust) {
        this.isBust = isBust;
    }

    // Return true if the player has Blackjack (initial 21)
    public boolean hasBlackjack() {
        return hasBlackjack;
    }

    // Setter: set hasBlackjack true if the player has Blackjack
    public void setHasBlackjack(boolean hasBlackjack) {
        this.hasBlackjack = hasBlackjack;
    }

    //Adds a card to player's hand and recalculates the score.
    public void addCard(PlayingCard card) {
        hand.add(card);
        calculateScore();
    }

    // Calculates the hand score: Each Ace initially counted as 11; if the total 
    // exceeds 21, one Ace at a time is reduced to 1.

    public void calculateScore() {
        int total = 0;
        int aceCount = 0;

//        for (PlayingCard card : hand) {
//            total += card.getRank().get();
//            if (card.getRank() == Rank.ACE) {
//                aceCount++;
//            }
//        }

        // Reduce Aces from 11 to 1 as needed to avoid bust
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        this.score = total;
        this.isBust = (total > 21);
    }

    //Clears the player's hand, resets score, bust, and blackjack flags for a new round.
    public void resetHand() {
        hand.clear();
        score = 0;
        isBust = false;
        hasBlackjack = false;
        bet = null;
    }

    // Returns a formatted string showing all cards in the player's hand.
    public String handToString() {
        String result = "";
        for (int i = 0; i < hand.size(); i++) {
            result = result + hand.get(i).toString();
            if (i < hand.size() - 1) {
                result = result + ", ";
            }
        }
        return result;
    }

    @Override
    public void play() {
    }
}
