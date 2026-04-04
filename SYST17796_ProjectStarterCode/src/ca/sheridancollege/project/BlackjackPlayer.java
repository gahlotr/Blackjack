package ca.sheridancollege.project;

public class BlackjackPlayer extends Player {

	private PlayingCard hand;
	private int score;
	private double balance;
	private Bet bet;

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