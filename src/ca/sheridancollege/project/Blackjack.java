/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author
 */
public abstract class Blackjack extends Game {

	private GroupOfCards deck;
	private Dealer dealer;
        
        public Blackjack(String name) {
            super(name);
        }

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