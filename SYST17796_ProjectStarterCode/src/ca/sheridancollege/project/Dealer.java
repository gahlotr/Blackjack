package ca.sheridancollege.project;

public class Dealer extends BlackjackPlayer {

    private boolean hiddenCardRevealed; // tracks if  hidden card has been flipped

    //Constructor for Dealer.
    public Dealer() {
        super("Dealer", 0); // Dealer has no balance; uses house rules
        this.hiddenCardRevealed = false;
    }

    // Return true if the dealer's hidden card has been revealed
    public boolean isHiddenCardRevealed() {
        return hiddenCardRevealed;
    }

    // Dealer flips their face-down card
    public void setHiddenCardRevealed(boolean hiddenCardRevealed) {
        this.hiddenCardRevealed = hiddenCardRevealed;
    }

    //showing only the visible portion of the dealer's hand
    public String getVisibleHandString() {
        if (getHand().isEmpty()) {
            return "[No cards]";
        }
        // First card is visible, second is hidden
        return getHand().get(0).toString() + ", [Hidden Card]";
    }

    // Resets the dealer's hand and ready for a new round.
    @Override
    public void resetHand() {
        super.resetHand();
        hiddenCardRevealed = false;
    }

    
    @Override
    public void play() {
    }
}
