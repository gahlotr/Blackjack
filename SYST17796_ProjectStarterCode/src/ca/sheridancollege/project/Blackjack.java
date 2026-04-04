package ca.sheridancollege.project;

public class Blackjack extends Game {

	private GroupOfCards deck;
	private Dealer dealer;

	public GroupOfCards getDeck() {
		return this.deck;
	}

	/**
	 * 
	 * @param deck
	 */
	public void setDeck(GroupOfCards deck) {
		this.deck = deck;
	}

	public Dealer getDealer() {
		return this.dealer;
	}

	/**
	 * 
	 * @param dealer
	 */
	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

}