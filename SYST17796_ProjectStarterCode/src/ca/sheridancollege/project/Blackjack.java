package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack extends Game {

	private GroupOfCards deck;
	private Dealer dealer;
        
        private static final double STARTING_BALANCE = 500.0;
        
        
        public Blackjack() {
            super("Blackjack");
        }

        // returns deck used
	public GroupOfCards getDeck() {
		return this.deck;
	}

        // sets deck
	public void setDeck(GroupOfCards deck) {
		this.deck = deck;
	}

        // return the dealer of the game
	public Dealer getDealer() {
		return this.dealer;
	}

        // sets dealer
	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

        
    // playing the game
    // handling player registration, adding players to game
    @Override
    public void play() {
        
        System.out.println("Welcome to Blackjack!");

        // registering players
        PlayerRegistration registration = registerPlayers();
        ArrayList<BlackjackPlayer> blackjackPlayers = registration.getPlayers();

        // adding players and setting players in Game's player list
        ArrayList<Player> playerList = new ArrayList<>();
        for (BlackjackPlayer p : blackjackPlayers) {
            playerList.add(p);
        }
        setPlayers(playerList);
        
    }

    // prompting user to enter number of players and corresponding names
    // validating input according to ensure:
        // valid numerical value for number of players is entered (1-4),
        // unique name for each player is entered
    private PlayerRegistration registerPlayers() {
        
        PlayerRegistration registration = new PlayerRegistration();

        // prompting user for number of players
        Scanner in = new Scanner(System.in);
        int numPlayers = 0;
        while (numPlayers < 1 || numPlayers > 4) {
            System.out.print("\nEnter number of players (1-4): ");
            // reading input as String to prevent errors if non-numerical input entered
            String input = in.nextLine();
            // validating input
            if (isNumeric(input)) {
                numPlayers = Integer.parseInt(input);
                if (numPlayers < 1 || numPlayers > 4) {
                    System.out.println("Invalid number. Please enter a value between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // prompting user for each player's unique name
        for (int i = 1; i <= numPlayers; i++) {
            String name = "";
            boolean isValidName = false;
            while (!isValidName) {
                System.out.print("Enter name for Player " + i + ": ");
                name = in.nextLine();
                // validating name entered
                if (name.isEmpty()) {
                    System.out.println("Name cannot be blank. Please enter a name.");
                } else if (registration.nameExists(name)) {
                    System.out.println("Name '" + name + "' is already taken. Please choose a different name.");
                } else {
                    isValidName = true;
                }
            }
            // creating new player with unique name and fixed starting balance
            BlackjackPlayer player = new BlackjackPlayer(name, STARTING_BALANCE);
            registration.addPlayer(player);
            System.out.println(name + " joined with $" + STARTING_BALANCE);
        }

        return registration;
    }
    
    // ensuring input entered is a valid numerical value
    private boolean isNumeric(String input) {
        
        boolean hasDigit = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                // character is not a digit
                return false;
            }
        }
        // input is valid
        return hasDigit;
    }
    
    @Override
    public void declareWinner() {
        
    }
    
}