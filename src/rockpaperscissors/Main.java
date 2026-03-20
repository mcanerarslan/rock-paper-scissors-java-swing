package rockpaperscissors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

public class Main {

	private static boolean isThemeLight = true;

	private static final int ROCK = 1;
	private static final int PAPER = 2;
	private static final int SCISSORS = 3;

	private static URL rockUrl = Main.class.getResource("/rockpaperscissors/assets/rock.png");
	private static URL paperUrl = Main.class.getResource("/rockpaperscissors/assets/paper.png");
	private static URL scissorsUrl = Main.class.getResource("/rockpaperscissors/assets/scissors.png");
	private static URL startUrl = Main.class.getResource("/rockpaperscissors/assets/rock-paper-scissors.png");

	public static void main(String[] args) {

		Game game = new Game();
		Save gameSave = new Save();

		JFrame menuFrame = new JFrame("Rock Paper Scissors");
		menuFrame.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		JPanel centerWrap = new JPanel(new BorderLayout());
		JPanel centerPanel = new JPanel(new GridLayout(2, 2, 30, 10));
		JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 15, 15));

		// LABELS
		// =========================
		JLabel userStatsJLabel = new JLabel("User: 0");
		userStatsJLabel.setHorizontalAlignment(JLabel.CENTER);
		userStatsJLabel.setVerticalAlignment(JLabel.CENTER);
		JLabel botStatsJLabel = new JLabel("Bot: 0");
		botStatsJLabel.setHorizontalAlignment(JLabel.CENTER);
		botStatsJLabel.setVerticalAlignment(JLabel.CENTER);
		JLabel showPlayerSelection = new JLabel();
		showPlayerSelection.setHorizontalAlignment(JLabel.CENTER);
		showPlayerSelection.setVerticalAlignment(JLabel.CENTER);
		JLabel showBotSelection = new JLabel();
		showBotSelection.setHorizontalAlignment(JLabel.CENTER);
		showBotSelection.setVerticalAlignment(JLabel.CENTER);

		JLabel userChoiceJLabel = new JLabel();
		userChoiceJLabel.setHorizontalAlignment(JLabel.CENTER);
		userChoiceJLabel.setVerticalAlignment(JLabel.CENTER);
		JLabel botChoicelJLabel = new JLabel();
		botChoicelJLabel.setHorizontalAlignment(JLabel.CENTER);
		botChoicelJLabel.setVerticalAlignment(JLabel.CENTER);

		JLabel roundStatsMsgJLabel = new JLabel();
		roundStatsMsgJLabel.setHorizontalAlignment(JLabel.CENTER);
		roundStatsMsgJLabel.setVerticalAlignment(JLabel.CENTER);
		roundStatsMsgJLabel.setFont(new Font("Arial", Font.BOLD, 18));

		// MENU BAR
		// =========================
		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("Game");
		JMenu options = new JMenu("Options");

		JMenuItem start = new JMenuItem("Start");
		JMenuItem restart = new JMenuItem("Restart");
		JMenuItem save = new JMenuItem("Save");

		JMenuItem about = new JMenuItem("About");
		JMenuItem theme = new JMenuItem("Theme");
		JMenuItem exit = new JMenuItem("Exit");

		menu.add(start);
		menu.add(restart);
		menu.add(save);

		options.add(about);
		options.add(theme);

		menuBar.add(menu);
		menuBar.add(options);
		menuBar.add(exit);

		// BUTTONS
		// =========================
		JButton rockbtn = new JButton("Rock");
		JButton paperbtn = new JButton("Paper");
		JButton scissorsbtn = new JButton("Scissors");

		JButton startButton = new JButton();
		startButton.setVerticalAlignment(JButton.CENTER);
		startButton.setHorizontalAlignment(JButton.CENTER);

		// BUTTON APPEARANCE
		// =========================
		rockbtn.setBorderPainted(false);
		rockbtn.setContentAreaFilled(false);
		rockbtn.setFocusPainted(false);
		if (rockUrl != null) {
			rockbtn.setIcon(new ImageIcon(rockUrl));
		} else {
			System.out.println("rock.png bulunamadi");
		}

		paperbtn.setBorderPainted(false);
		paperbtn.setContentAreaFilled(false);
		paperbtn.setFocusPainted(false);
		if (paperUrl != null) {
			paperbtn.setIcon(new ImageIcon(paperUrl));
		} else {
			System.out.println("paper.png bulunamadi");
		}

		scissorsbtn.setBorderPainted(false);
		scissorsbtn.setContentAreaFilled(false);
		scissorsbtn.setFocusPainted(false);
		if (scissorsUrl != null) {
			scissorsbtn.setIcon(new ImageIcon(scissorsUrl));
		} else {
			System.out.println("scissors.png bulunamadi");
		}

		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		if (startUrl != null) {
			startButton.setIcon(new ImageIcon(startUrl));
		} else {
			System.out.println("rock-paper-scissors.png bulunamadi");
		}

		// INITIAL VISIBILITY
		// =========================
		userStatsJLabel.setVisible(false);
		botStatsJLabel.setVisible(false);
		rockbtn.setVisible(false);
		paperbtn.setVisible(false);
		scissorsbtn.setVisible(false);

		// MENU ACTIONS
		// =========================
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Rock Paper Scissors\nMade by Mahmut Caner Arslan\nGitHub: mcanerarslan");
			}
		});

		theme.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isThemeLight) {
					menuFrame.getContentPane().setBackground(Color.BLACK);
					userStatsJLabel.setForeground(Color.WHITE);
					botStatsJLabel.setForeground(Color.WHITE);
					isThemeLight = false;
				} else {
					menuFrame.getContentPane().setBackground(Color.WHITE);
					userStatsJLabel.setForeground(Color.BLACK);
					botStatsJLabel.setForeground(Color.BLACK);
					isThemeLight = true;
				}
			}
		});

		restart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.restartGame();
				userStatsJLabel.setText("User: 0");
				botStatsJLabel.setText("Bot: 0");
				showPlayerSelection.setIcon(null);
				showBotSelection.setIcon(null);
			}
		});

		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameSave.saveTheGame(game, isThemeLight);
				JOptionPane.showMessageDialog(menuFrame, "Game saved.");
			}
		});

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameSave.saveTheGame(game, isThemeLight);
				System.exit(0);
			}
		});

		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rockbtn.setVisible(true);
				paperbtn.setVisible(true);
				scissorsbtn.setVisible(true);
				startButton.setVisible(false);
				userStatsJLabel.setVisible(true);
				botStatsJLabel.setVisible(true);

				isThemeLight = gameSave.loadTheGame(game);

				userStatsJLabel.setText("User: " + game.getPlayerStats());
				botStatsJLabel.setText("Bot: " + game.getBotStats());
			}
		});

		// GAME BUTTON ACTIONS
		// =========================

		rockbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				buttonActions(ROCK, game, userStatsJLabel, botStatsJLabel, showPlayerSelection, showBotSelection,
						userChoiceJLabel, botChoicelJLabel, roundStatsMsgJLabel);
			}
		});

		paperbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				buttonActions(PAPER, game, userStatsJLabel, botStatsJLabel, showPlayerSelection, showBotSelection,
						userChoiceJLabel, botChoicelJLabel, roundStatsMsgJLabel);
			}
		});

		scissorsbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				buttonActions(SCISSORS, game, userStatsJLabel, botStatsJLabel, showPlayerSelection, showBotSelection,
						userChoiceJLabel, botChoicelJLabel, roundStatsMsgJLabel);
			}
		});

		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				menuFrame.remove(startButton);
				menuFrame.add(centerWrap, BorderLayout.CENTER);

				rockbtn.setVisible(true);
				paperbtn.setVisible(true);
				scissorsbtn.setVisible(true);
				startButton.setVisible(false);
				botStatsJLabel.setVisible(true);
				userStatsJLabel.setVisible(true);

				isThemeLight = gameSave.loadTheGame(game);
				System.out.println(isThemeLight);

				userStatsJLabel.setText("User: " + game.getPlayerStats());
				botStatsJLabel.setText("Bot: " + game.getBotStats());

				menuFrame.revalidate();
				menuFrame.repaint();

			}
		});

//		JLabel bgJLabel = new JLabel();
//		bgJLabel.setHorizontalAlignment(JLabel.CENTER);
//		bgJLabel.setVerticalAlignment(JLabel.CENTER);
//		URL bgURL = Main.class.getResource("/rockpaperscissors/assets/as.png");
//		
//		if (bgURL != null) {
//			bgJLabel.setIcon(new ImageIcon(bgURL));
//		} else {
//			System.out.println("scissors.png bulunamadi");
//		}

		// ADD COMPONENTS TO FRAME
		// =========================

		topPanel.add(userStatsJLabel);
		topPanel.add(botStatsJLabel);
		menuFrame.add(topPanel, BorderLayout.NORTH);
		bottomPanel.add(rockbtn);
		bottomPanel.add(paperbtn);
		bottomPanel.add(scissorsbtn);
		menuFrame.add(bottomPanel, BorderLayout.SOUTH);
		centerPanel.add(showPlayerSelection);
		centerPanel.add(showBotSelection);
		centerPanel.add(userChoiceJLabel);
		centerPanel.add(botChoicelJLabel);
		centerWrap.add(roundStatsMsgJLabel, BorderLayout.NORTH);
		centerWrap.add(centerPanel, BorderLayout.CENTER);
		menuFrame.setJMenuBar(menuBar);
//		menuFrame.add(bgJLabel,BorderLayout.CENTER);
		menuFrame.add(startButton, BorderLayout.CENTER);

		// FRAME SETTINGS
		// =========================
		menuFrame.setSize(425, 270);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setVisible(true);
	}

	public static void buttonActions(int playerChoice, Game game, JLabel userStatsJLabel, JLabel botStatsJLabel,
			JLabel showPlayerSelection, JLabel showBotSelection, JLabel userChoiceJLabel, JLabel botChoiceJLabel,
			JLabel roundStatsMsgJLabel) {

		String msg = game.playGame(playerChoice);

		userStatsJLabel.setText("User: " + game.getPlayerStats());
		botStatsJLabel.setText("Bot: " + game.getBotStats());
		roundStatsMsgJLabel.setText(msg);

		switch (playerChoice) {
		case ROCK:
			showPlayerSelection.setIcon(new ImageIcon(rockUrl));
			userChoiceJLabel.setText("Rock");
			break;
		case PAPER:
			showPlayerSelection.setIcon(new ImageIcon(paperUrl));
			userChoiceJLabel.setText("Paper");
			break;
		case SCISSORS:
			showPlayerSelection.setIcon(new ImageIcon(scissorsUrl));
			userChoiceJLabel.setText("Scissors");
			break;
		}

		String botChoice = game.getSelectionOfBot();

		if (botChoice != null) {
			switch (botChoice) {
			case "Rock":
				showBotSelection.setIcon(new ImageIcon(rockUrl));
				botChoiceJLabel.setText("Rock");
				break;
			case "Paper":
				showBotSelection.setIcon(new ImageIcon(paperUrl));
				botChoiceJLabel.setText("Paper");
				break;
			case "Scissors":
				showBotSelection.setIcon(new ImageIcon(scissorsUrl));
				botChoiceJLabel.setText("Scissors");
				break;
			}
		}
	}

	public static void changeTheme() {

	}

}