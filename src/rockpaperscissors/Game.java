package rockpaperscissors;

import java.util.Random;

public class Game {
	

	
	Random random = new Random();

	private int playerStats = 0, botStats = 0;
	private String selectionOfBot;

	private int generateBotChoice() {

		int number = random.nextInt(3) + 1;
		return number;
	}

	public void playGame(int playerSelection) {

		int botSelection = generateBotChoice();
		saveSelectionOfBot(botSelection);

		// 1 : Rock
		// 2 : Paper
		// 3 : Scissors
		
		//asd

//		System.out.println("User: " + playerSelection);
//		System.out.println("Bot: " + getSelectionOfBot());

		if (playerSelection == botSelection) {
//			System.out.println("Berabere");
		} else if ((playerSelection == 1 && botSelection == 3) || (playerSelection == 2 && botSelection == 1)
				|| (playerSelection == 3 && botSelection == 2)) {
//			System.out.println("Oyuncu kazandi");
			playerStats++;
		} else {
//			System.out.println("Bot kazandi");
			botStats++;
		}

	}
	
	public void restartGame() {
		playerStats = 0;
		botStats = 0;
		selectionOfBot = null;
	}
	
	public void saveGame() {
		
	}

	public String saveSelectionOfBot(int value) {
		switch (value) {
		case 1:
			this.selectionOfBot = "Rock";
			break;
		case 2:
			this.selectionOfBot = "Paper";
			break;
		case 3:
			this.selectionOfBot = "Scissors";
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + value);
		}
		return "save";
	}
	
	

	public String getSelectionOfBot() {
		return selectionOfBot;
	}

	public int getPlayerStats() {
		return playerStats;
	}

	public void setPlayerStats(int playerStats) {
		this.playerStats = playerStats;
	}

	public int getBotStats() {
		return botStats;
	}

	public void setBotStats(int botStats) {
		this.botStats = botStats;
	}

}
