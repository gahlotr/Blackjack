package ca.sheridancollege.project;

public class Bet {

	private double amount;
	private boolean isWon;

	public double getAmount() {
		return this.amount;
	}

	/**
	 * 
	 * @param amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean getIsWon() {
		return this.isWon;
	}

	/**
	 * 
	 * @param isWon
	 */
	public void setIsWon(boolean isWon) {
		this.isWon = isWon;
	}

}