package ca.sheridancollege.project;
import java.util.ArrayList;

public class PlayerRegistration {

    private ArrayList<BlackjackPlayer> players;

    // Default constructor. Initializes an empty player list.
    public PlayerRegistration() {
        this.players = new ArrayList<>();
    }

    // Getter: return the list of registered players
    public ArrayList<BlackjackPlayer> getPlayers() {
        return players;
    }

    // Setter: set player list

    public void setPlayers(ArrayList<BlackjackPlayer> players) {
        this.players = players;
    }

    // Adds a player to the registration.
    public void addPlayer(BlackjackPlayer player) {
        players.add(player);
    }

    //Checks whether a name is already registered.
    public boolean nameExists(String name) {
        for (BlackjackPlayer p : players) {
            if (p.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // Getter: return the number of registered players
    public int getPlayerCount() {
        return players.size();
    }
}