/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author Group 2 - Bruins (Ria Gahlot, Madison Scarlett, Rayyan Javed, John Michael Lagumbay)
 */
public abstract class Player {

    private String name; // the unique name for this player

    // constructor that allows you to set the player's unique name.
    public Player(String name) {
        this.name = name;
    }

    // getter that returns the player name
    public String getName() {
        return name;
    }

    // setter to set the players name
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Anyone can call play(), but this class doesn't define what it does
     * subclasses must implement it themselves, and it returns nothing.
     */
    public abstract void play();
}
