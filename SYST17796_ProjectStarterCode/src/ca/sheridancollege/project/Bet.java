package ca.sheridancollege.project;

public class Bet {

    private double amount; // Bet amount
    private boolean isWon; // Player win or lose

    //Constructor for Bet. Triggers when new BET() created
    public Bet(double amount) {
        this.amount = amount;
        this.isWon = false; // False, because game not over yet
    }

    // Getter: return the amount bet
    public double getAmount() {
        return amount;
    }

    // Setter: set bet
    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Getter: return true if this bet was won
    public boolean getIsWon() {
        return isWon;
    }

    // Setter: set isWon true if the bet was won

    public void setIsWon(boolean isWon) {
        this.isWon = isWon;
    }
}
