package rockpaperscissors;

import java.awt.Color;
import java.awt.Font;
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

public class Main {

	private static boolean isThemeLight = true;

	private static final int ROCK = 1;
	private static final int PAPER = 2;
	private static final int SCISSORS = 3;

	public static void main(String[] args) {

		Game game = new Game();
		Save gameSave = new Save();

		JFrame menuFrame = new JFrame("Rock Paper Scissors");


		// LABELS
		// =========================
		JLabel userStatsJLabel = new JLabel("User: 0");
		userStatsJLabel.setBounds(420, 0, 150, 30);
		userStatsJLabel.setFont(new Font("Verdana", Font.BOLD, 15));

		JLabel botStatsJLabel = new JLabel("Bot: 0");
		botStatsJLabel.setBounds(10, 0, 150, 30);
		botStatsJLabel.setFont(new Font("Verdana", Font.BOLD, 15));

		JLabel showPlayerSelection = new JLabel();
		showPlayerSelection.setBounds(146, 50, 64, 64);

		JLabel showBotSelection = new JLabel();
		showBotSelection.setBounds(290, 50, 64, 64);


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
		JButton rockbtn = new JButton("Taş");
		rockbtn.setBounds(138, 250, 64, 64);

		JButton paperbtn = new JButton("Kağıt");
		paperbtn.setBounds(218, 250, 64, 64);

		JButton scissorsbtn = new JButton("Makas");
		scissorsbtn.setBounds(298, 250, 64, 64);

		JButton startButton = new JButton();
		startButton.setBounds(150, 100, 200, 200);


		// LOAD IMAGES
		// =========================
		URL rockUrl = Main.class.getResource("/rockpaperscissors/assets/rock.png");
		URL paperUrl = Main.class.getResource("/rockpaperscissors/assets/paper.png");
		URL scissorsUrl = Main.class.getResource("/rockpaperscissors/assets/scissors.png");
		URL startUrl = Main.class.getResource("/rockpaperscissors/assets/rock-paper-scissors.png");

		if (rockUrl != null) {
			rockbtn.setIcon(new ImageIcon(rockUrl));
		} else {
			System.out.println("rock.png bulunamadi");
		}

		if (paperUrl != null) {
			paperbtn.setIcon(new ImageIcon(paperUrl));
		} else {
			System.out.println("paper.png bulunamadi");
		}

		if (scissorsUrl != null) {
			scissorsbtn.setIcon(new ImageIcon(scissorsUrl));
		} else {
			System.out.println("scissors.png bulunamadi");
		}

		if (startUrl != null) {
			startButton.setIcon(new ImageIcon(startUrl));
		} else {
			System.out.println("rock-paper-scissors.png bulunamadi");
		}


		// BUTTON APPEARANCE
		// =========================
		rockbtn.setBorderPainted(false);
		rockbtn.setContentAreaFilled(false);
		rockbtn.setFocusPainted(false);

		paperbtn.setBorderPainted(false);
		paperbtn.setContentAreaFilled(false);
		paperbtn.setFocusPainted(false);

		scissorsbtn.setBorderPainted(false);
		scissorsbtn.setContentAreaFilled(false);
		scissorsbtn.setFocusPainted(false);

		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);


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
				gameSave.saveTheGame(game,isThemeLight);
				JOptionPane.showMessageDialog(menuFrame, "Game saved.");
			}
		});

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameSave.saveTheGame(game,isThemeLight);
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
				game.playGame(ROCK);
				userStatsJLabel.setText("User: " + game.getPlayerStats());
				botStatsJLabel.setText("Bot: " + game.getBotStats());
				showPlayerSelection.setIcon(new ImageIcon(rockUrl));

				String botChoice = game.getSelectionOfBot();

				switch (botChoice) {
				case "Rock":
					showBotSelection.setIcon(new ImageIcon(rockUrl));
					break;
				case "Paper":
					showBotSelection.setIcon(new ImageIcon(paperUrl));
					break;
				case "Scissors":
					showBotSelection.setIcon(new ImageIcon(scissorsUrl));
					break;
				}
			}
		});

		paperbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.playGame(PAPER);
				userStatsJLabel.setText("User: " + game.getPlayerStats());
				botStatsJLabel.setText("Bot: " + game.getBotStats());
				showPlayerSelection.setIcon(new ImageIcon(paperUrl));

				String botChoice = game.getSelectionOfBot();

				switch (botChoice) {
				case "Rock":
					showBotSelection.setIcon(new ImageIcon(rockUrl));
					break;
				case "Paper":
					showBotSelection.setIcon(new ImageIcon(paperUrl));
					break;
				case "Scissors":
					showBotSelection.setIcon(new ImageIcon(scissorsUrl));
					break;
				}
			}
		});

		scissorsbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.playGame(SCISSORS);
				userStatsJLabel.setText("User: " + game.getPlayerStats());
				botStatsJLabel.setText("Bot: " + game.getBotStats());
				showPlayerSelection.setIcon(new ImageIcon(scissorsUrl));

				String botChoice = game.getSelectionOfBot();

				switch (botChoice) {
				case "Rock":
					showBotSelection.setIcon(new ImageIcon(rockUrl));
					break;
				case "Paper":
					showBotSelection.setIcon(new ImageIcon(paperUrl));
					break;
				case "Scissors":
					showBotSelection.setIcon(new ImageIcon(scissorsUrl));
					break;
				}
			}
		});

		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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

				// Redraw and refresh the frame so visibility changes appear on screen
				menuFrame.repaint();
				menuFrame.revalidate();
			}
		});


		// ADD COMPONENTS TO FRAME
		// =========================
		menuFrame.setJMenuBar(menuBar);
		menuFrame.add(userStatsJLabel);
		menuFrame.add(botStatsJLabel);
		menuFrame.add(showPlayerSelection);
		menuFrame.add(showBotSelection);
		menuFrame.add(rockbtn);
		menuFrame.add(paperbtn);
		menuFrame.add(scissorsbtn);
		menuFrame.add(startButton);


		// FRAME SETTINGS
		// =========================
		menuFrame.setSize(500, 400);
		menuFrame.setLayout(null);
		menuFrame.setResizable(false);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setVisible(true);
	}
	
}