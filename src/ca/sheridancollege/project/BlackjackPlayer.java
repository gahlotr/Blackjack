/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author
 */
public abstract class BlackjackPlayer extends Player {

	private PlayingCard hand;
	private int score;
	private double balance;
	private Bet bet;
        
        public BlackjackPlayer(String name) {
            super(name);
        }

	public PlayingCard getHand() {
		return this.hand;
	}

	/**
	 * 
	 * @param hand
	 */
	public void setHand(PlayingCard hand) {
		this.hand = hand;
	}

	public int getScore() {
		return this.score;
	}

	/**
	 * 
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	public double getBalance() {
		return this.balance;
	}

	/**
	 * 
	 * @param balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Bet getBet() {
		return this.bet;
	}

	/**
	 * 
	 * @param bet
	 */
	public void setBet(Bet bet) {
		this.bet = bet;
	}

}