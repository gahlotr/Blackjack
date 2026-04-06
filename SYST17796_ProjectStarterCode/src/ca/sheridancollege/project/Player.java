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

    private String name; // Players name

    // Assigning the name
    public Player(String name) {
        this.name = name;
    }

    // Getter: return the player name
    public String getName() {
        return name;
    }

    // Setter: erase and rewrite the name
    public void setName(String name) {
        this.name = name;
    }

    // every player must know how to play
    public abstract void play();
}
