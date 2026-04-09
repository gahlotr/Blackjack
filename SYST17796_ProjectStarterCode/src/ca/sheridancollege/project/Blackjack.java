package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

// @author Ria Gahlot, Madison Scarlett

public class Blackjack extends Game {

	private GroupOfCards deck;
	private Dealer dealer;
        
        private static final double STARTING_BALANCE = 500.0;
        private static final double MIN_BET = 5.0;
        private static final double MAX_BET = 100.0;
        
        private Scanner scanner;
        
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
        ArrayList<BlackjackPlayer> bjPlayers = registration.getPlayers();

        // adding players and setting players in Game's player list
        ArrayList<Player> playerList = new ArrayList<>();
        for (BlackjackPlayer p : bjPlayers) {
            playerList.add(p);
        }
        setPlayers(playerList);
        
        //build and shuffle the deck once at the start
        buildAndShuffleDeck();
        
        // Step 2: Game loop — each iteration is one full round
        boolean playAgain = true;
        while (playAgain) {
            playRound(bjPlayers);
            playAgain = askPlayAgain();

            // Remove players who are broke
            removeBrokePlayers(bjPlayers);
            if (bjPlayers.isEmpty()) {
                System.out.println("\nAll players are out of funds. Game over!");
                break;
            }
        }

        System.out.println("\nThanks for playing Blackjack! Goodbye.");
        
    }

    // prompting user to enter number of players and corresponding names
    // validating input according to ensure:
        // valid numerical value for number of players is entered (1-4),
        // unique name for each player is entered
    private PlayerRegistration registerPlayers() {
        
        PlayerRegistration registration = new PlayerRegistration();

        // prompting user for number of players
        int numPlayers = 0;
        while (numPlayers < 1 || numPlayers > 4) {
            System.out.print("\nEnter number of players (1-4): ");
            // reading input as String to prevent errors if non-numerical input entered
            String input = scanner.nextLine();
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
                name = scanner.nextLine();
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
     //MADISON
    //will run a single round of Blackjack as follows: betting, dealing, player turns, dealer turn, determine outcomes, payout
    private void playRound(ArrayList<BlackjackPlayer> bjPlayers) {
        System.out.println("-- New Round --");
        

        //reset all hands for the new round
        for (BlackjackPlayer p : bjPlayers) {
            p.resetHand();
        }
        dealer.resetHand();

        // collect bets
        collectBets(bjPlayers);

        //shuffle deck if needed, then deal 2 cards to each player and dealer
        dealInitialCards(bjPlayers);

        //display hands
        displayInitialHands(bjPlayers);

        //check for Blackjack on initial deal
        checkInitialBlackjacks(bjPlayers);

        //player turns
        for (BlackjackPlayer player : bjPlayers) {
            if (!player.hasBlackjack() && !player.isBust()) {
                playerTurn(player);
            }
        }

        //dealer reveals hidden card and draws
        dealerTurn(bjPlayers);

        //determine outcomes and display results
        declareWinner();
    }
    
    
    //asks players if they want to play another round
    private boolean askPlayAgain() {
        while (true) {
            System.out.print("\nWould you like to play again? (yes / no): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("no") || input.equals("n")) {
                return false;
            } else {
                System.out.println("Please type 'yes' or 'no'.");
            }
        }
    }
    
    
    //ensuring input entered is a valid positive number with up to 2 decimal places
    private boolean isNumeric(String input) {
        if (input == null || input.length() == 0) {
            return false;
        }
        boolean hasDigit = false;
        boolean hasDecimal = false;
        int decimalPlaces = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                hasDigit = true;
                //count decimal places if after the decimal point
                if (hasDecimal) {
                    decimalPlaces++;
                    if (decimalPlaces > 2) {
                        return false; //more than 2 decimal places
                    }
                }
            } else if (c == '.') {
                if (hasDecimal) {
                    return false; //more than one decimal inputted
                }
                hasDecimal = true;
                //the decimal can't be first or last
                if (i == 0 || i == input.length() - 1) {
                    return false;
                }
            } else {
                return false; // invalid character
            }
        }
        return hasDigit;
    }

    
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
                    System.out.println("Invalid input. Please enter a postive number.");
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

        System.out.println("\nDeck shuffled.");
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

    // draws card from top of deck if deck is not empty
    // deck is rebuilt and reshuffled if empty
    private PlayingCard drawCard() {
        if (deck.isEmpty()) {
            System.out.println("\nDeck is empty");
            System.out.println("Reshuffling...");

            buildAndShuffleDeck();
        }
        PlayingCard card = (PlayingCard) deck.dealCard();

        return card;
    }

    // displaying each player's hand and score after cards are intially dealt,
    // displaying dealer's visible card
    private void displayInitialHands(ArrayList<BlackjackPlayer> players) {
        System.out.println("\n-- Current Hands --");
        for (BlackjackPlayer player : players) {
            System.out.printf("%s: %s  (Score: %d)%n",
                    player.getName(), player.handToString(), player.getScore());
        }
        System.out.println("Dealer: " + dealer.getVisibleHandString());
    }

    
    // checking each player's hand for Blackjack after initial cards are dealt
    // marks player as has blackjack to skip that player's turn
    private void checkInitialBlackjacks(ArrayList<BlackjackPlayer> players) {
        for (BlackjackPlayer player : players) {
            // player has blackjack if score is 21
            if (player.getScore() == 21) {
                player.setHasBlackjack(true);
                System.out.println("\n" + player.getName() + " has BLACKJACK!");
            }
        }
    }

    // prompting player to hit or stand until player stands
    // determining outcome of player's hand (bust or blackjack) based on card drawn
    private void playerTurn(BlackjackPlayer player) {
        System.out.println("\n-- " + player.getName() + "'s Turn --");

        boolean isTurnOver = false;
        while (!isTurnOver) {
            System.out.printf("%s's hand: %s  (Score: %d)%n",
                    player.getName(), player.handToString(), player.getScore());
            System.out.print("Hit or Stand? (h/s): ");
            String input = scanner.nextLine().trim().toLowerCase();

            // dealing another card if player wants to draw
            if (input.equals("h") || input.equals("hit")) {
                PlayingCard newCard = drawCard();
                player.addCard(newCard);
                System.out.println("  Dealt: " + newCard);

                // determining if player busted or has blackjack
                if (player.getScore() > 21) {
                    System.out.println("  Bust! " + player.getName() + " exceeded 21 with " + player.getScore());
                    isTurnOver = true;
                } else if (player.getScore() == 21) {
                    System.out.println("  " + player.getName() + " reached 21!");
                    isTurnOver = true;
                }

            // player's turn is over if player does not want to draw
            } else if (input.equals("s") || input.equals("stand")) {
                System.out.println("  " + player.getName() + " stands at " + player.getScore() + ".");
                isTurnOver = true;
            } else {
                System.out.println("  Invalid input. Please type 'h' to hit or 's' to stand.");
            }
        }
    }
    
    // dealer's hidden card is revealed and score is determined
    // dealer hits until reaching a score of at least 17
    // if all players have busted, dealer's card is still revealed,
    // but dealer does not draw any cards
    private void dealerTurn(ArrayList<BlackjackPlayer> players) {
        System.out.println("\n-- Dealer's Turn --");
        dealer.setHiddenCardRevealed(true);
        System.out.println("Dealer reveals hidden card: " + dealer.getHand().get(1));
        System.out.printf("Dealer's hand: %s  (Score: %d)%n",
                dealer.handToString(), dealer.getScore());

        // checking if dealer's initial cards add to 21 for blackjack
        if (dealer.getScore() == 21 && dealer.getHand().size() == 2) {
            dealer.setHasBlackjack(true);
            System.out.println("Dealer has BLACKJACK!");
            return;
        }

        //checking if all players have busted
        //dealer's card is still revealed, but dealer does not draw cards
        boolean allBusted = true;
        for (BlackjackPlayer p : players) {
            if (!p.isBust()) {
                allBusted = false;
                break;
            }
        }

        if (allBusted) {
            System.out.println("All players busted. Dealer does not draw further.");
            return;
        }

        //dealer draws cards until score is higher than 17
        while (dealer.getScore() < 17) {
            PlayingCard card = drawCard();
            dealer.addCard(card);
            System.out.println("  Dealer hits: " + card + "  (Score: " + dealer.getScore() + ")");
        }

        if (dealer.isBust()) {
            System.out.println("  Dealer busts with " + dealer.getScore() + "!");
        } else {
            System.out.println("  Dealer stands at " + dealer.getScore() + ".");
        }
    }
    
    //compares dealer's score with each player's score
    //bets are paid according to winner
    //if dealer and player have the same score (push), player bet is returned
    @Override
    public void declareWinner() {
        System.out.println("\nRESULTS");

        int dealerScore = dealer.getScore();

        ArrayList<Player> allPlayers = getPlayers();
        for (Player p : allPlayers) {
            BlackjackPlayer player = (BlackjackPlayer) p;
            double betAmount = player.getBet().getAmount();
            String result;

            //player busted
            if (player.isBust()) {
                result = "BUST - You lose $" + betAmount;
                player.getBet().setIsWon(false);

                //dealer busted, player did not bust
            } else if (dealer.isBust()) {
                player.setBalance(player.getBalance() + betAmount * 2);
                player.getBet().setIsWon(true);
                result = "DEALER BUSTS - You win $" + betAmount + "!";

                //player has blackjack, dealer does not have blackjack
            } else if (player.hasBlackjack() && !dealer.hasBlackjack()) {
                double winnings = betAmount + (betAmount * 1.5);
                player.setBalance(player.getBalance() + winnings);
                player.getBet().setIsWon(true);
                result = "BLACKJACK! You win $" + betAmount * 1.5 + " bonus!";

                //both dealer and player have blackjack (push)
            } else if (player.hasBlackjack() && dealer.hasBlackjack()) {
                player.setBalance(player.getBalance() + betAmount);
                result = "PUSH (both Blackjack) - Bet returned.";

                //dealer has blackjack, player does not have blackjack
            } else if (dealer.hasBlackjack() && !player.hasBlackjack()) {
                result = "DEALER BLACKJACK - You lose $" + betAmount + ".";

                //player's score is higher than the dealer's score
            } else if (player.getScore() > dealerScore) {
                player.setBalance(player.getBalance() + betAmount * 2);
                player.getBet().setIsWon(true);
                result = "You WIN $" + betAmount + "!";

                //player and dealer scores are the same (push)
            } else if (player.getScore() == dealerScore) {
                player.setBalance(player.getBalance() + betAmount);
                result = "PUSH - Bet returned.";

                //dealer's score is higher than player's score
            } else {
                result = "Dealer wins - You lose $" + betAmount + ".";
            }

            System.out.println("------------------------------------------");
            System.out.printf("Player  : %s%n", player.getName());
            System.out.printf("Hand    : %s  (Score: %d)%n", player.handToString(), player.getScore());
            System.out.printf("Dealer  : %s  (Score: %d)%n", dealer.handToString(), dealerScore);
            System.out.printf("Result  : %s%n", result);
            System.out.printf("Balance : $%.2f%n", player.getBalance());
        }

        //compare players against each other and show round winner
        int highestScore = -1;
        ArrayList<BlackjackPlayer> roundWinners = new ArrayList<>();

        for (Player p : allPlayers) {
            BlackjackPlayer player = (BlackjackPlayer) p;

            if (!player.isBust()) {
                if (player.getScore() > highestScore) {
                    highestScore = player.getScore();
                    roundWinners.clear();
                    roundWinners.add(player);
                } else if (player.getScore() == highestScore) {
                    roundWinners.add(player);
                }
            }
        }

        System.out.println("------------------------------------------");
        if (roundWinners.isEmpty()) {
            System.out.println("Round Winner: Dealer won the round because all players busted.");
        } else if (roundWinners.size() == 1) {
            System.out.println("Round Winner: " + roundWinners.get(0).getName()
                    + " with a score of " + highestScore + "!");
        } else {
            System.out.print("Round Winners: ");
            for (int i = 0; i < roundWinners.size(); i++) {
                System.out.print(roundWinners.get(i).getName());
                if (i < roundWinners.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(" tied with a score of " + highestScore + "!");
        }
    }
  
    //MADISON
    //removes any players where the balance has dropped to $0
    private void removeBrokePlayers(ArrayList<BlackjackPlayer> bjPlayers) {
        for (int i = 0; i < bjPlayers.size(); i++) {
            BlackjackPlayer p = bjPlayers.get(i);

            if (p.getBalance() <= 0) {
                System.out.println(p.getName() + " has run out of funds and is out of the game.");
                bjPlayers.remove(i);
                i--; //adjusts index after removal to not skip elements
            }
        }

        //sync Game.java player list
        ArrayList<Player> playerList = new ArrayList<>();
        for (int i = 0; i < bjPlayers.size(); i++) {
            playerList.add(bjPlayers.get(i));
        }
        setPlayers(playerList);
    }
    
}