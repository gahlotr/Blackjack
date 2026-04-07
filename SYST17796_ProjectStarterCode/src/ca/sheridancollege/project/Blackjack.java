package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack extends Game {

	private GroupOfCards deck;
	private Dealer dealer;
        
        private static final double STARTING_BALANCE = 500.0;
        private static final double MIN_BET = 5.0;
        private static final double MAX_BET = 100.0;
        
        private Scanner scanner;
        private ArrayList<BlackjackPlayer> activePlayers;
        
        public Blackjack() {
            super("Blackjack");
            this.deck = new GroupOfCards(52);
            this.dealer = new Dealer();
            this.scanner = new Scanner(System.in);
            
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
    
    //MADISON
    
    private void collectBets(ArrayList<BlackjackPlayer> bjPlayers) {
        System.out.println("\n-- Placing Bets --");
        System.out.println("Min bet: $" + (int) MIN_BET + " | Max bet: $" + (int) MAX_BET);

        for (BlackjackPlayer player : bjPlayers) {
            double betAmount = 0;
            boolean validBet = false;
            while (!validBet) {
                System.out.printf("%s (Balance: $%.2f) - Enter your bet: $", player.getName(), player.getBalance());
                String input = scanner.nextLine().trim();
                if (isNumeric(input)) {
                    betAmount = Double.parseDouble(input);
                    if (betAmount < MIN_BET) {
                        System.out.println("Minimum bet is $" + (int) MIN_BET + ". Please try again.");
                    } else if (betAmount > MAX_BET) {
                        System.out.println("Maximum bet is $" + (int) MAX_BET + ". Please try again.");
                    } else if (betAmount > player.getBalance()) {
                        System.out.println("You don't have enough funds. Your balance is $" + player.getBalance());
                    } else {
                        validBet = true;
                    }
                } else {
                    System.out.println("Invalid input. Please enter a numeric amount.");
                }
            }

            //deducts bet from players balance immediately to avoid inflation
            player.setBalance(player.getBalance() - betAmount);
            player.setBet(new Bet(betAmount));
            System.out.printf("%s bets $%.2f. Remaining balance: $%.2f%n", player.getName(), betAmount, player.getBalance());
        }
    }
    
        private void buildAndShuffleDeck() {
        deck.buildDeck();
        deck.shuffle();

        //removes any cards currently held in players or dealer hands
        //that way the reshuffled deck does not contain duplicates of in-use cards
        if (activePlayers != null) {
            for (BlackjackPlayer player : activePlayers) {
                for (PlayingCard inHand : player.getHand()) {
                    removeCardFromDeck(inHand);
                }
            }
        }
        if (dealer != null) {
            for (PlayingCard inHand : dealer.getHand()) {
                removeCardFromDeck(inHand);
            }
        }

        System.out.println("\nDeck shuffled.");
    }

    //looks through the deck and removes the first card that matches the one passed in
    //used to make sure cards already in someone’s hand aren’t still in the deck
    private void removeCardFromDeck(PlayingCard target) {
        ArrayList<Card> cards = deck.getCards();
        for (int i = 0; i < cards.size(); i++) {
            PlayingCard c = (PlayingCard) cards.get(i);
            if (c.getSuit() == target.getSuit() && c.getRank() == target.getRank()) {
                cards.remove(i);
                return;
            }
        }
    }
    
    private void dealInitialCards(ArrayList<BlackjackPlayer> players) {
        System.out.println("\n-- Dealing Cards --");

        //do two rounds so everyone ends up with 2 cards
        //each round: every player gets one card, then the dealer gets one
        for (int round = 0; round < 2; round++) {
            for (BlackjackPlayer player : players) {
                PlayingCard card = drawCard();
                player.addCard(card);
            }

            // Dealer also gets one card per round
            PlayingCard dealerCard = drawCard();
            dealer.addCard(dealerCard);
        }
    }
   
    //RIA
    
    @Override
    public void declareWinner() {
        
    }
    
}