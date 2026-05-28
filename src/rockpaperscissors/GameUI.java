package rockpaperscissors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameUI {

	private static final int ROCK = 1;
	private static final int PAPER = 2;
	private static final int SCISSORS = 3;

	private static final Color LIGHT_BG = new Color(245, 247, 250);
	private static final Color LIGHT_CARD = Color.WHITE;
	private static final Color LIGHT_TEXT = new Color(30, 41, 59);
	private static final Color LIGHT_ACCENT = new Color(37, 99, 235);

	private static final Color DARK_BG = new Color(15, 23, 42);
	private static final Color DARK_CARD = new Color(30, 41, 59);
	private static final Color DARK_TEXT = new Color(226, 232, 240);
	private static final Color DARK_ACCENT = new Color(96, 165, 250);

	private final Game game = new Game();
	private final Save save = new Save();
	private final Map<String, ImageIcon> choiceIcons = new HashMap<>();

	private JFrame frame;
	private JPanel rootPanel;
	private JPanel scorePanel;
	private JPanel centerPanel;
	private JPanel choicesPanel;

	private JLabel playerScoreLabel;
	private JLabel botScoreLabel;
	private JLabel roundResultLabel;
	private JLabel playerChoiceLabel;
	private JLabel botChoiceLabel;
	private JLabel playerChoiceTextLabel;
	private JLabel botChoiceTextLabel;

	private JButton rockButton;
	private JButton paperButton;
	private JButton scissorsButton;
	private JButton startButton;

	private boolean isThemeLight = true;
	private boolean started = false;

	public GameUI() {
		initializeIcons();
		buildUi();
		applyTheme();
	}

	public void show() {
		frame.setVisible(true);
	}

	private void initializeIcons() {
		choiceIcons.put("Rock", loadIcon("/rockpaperscissors/assets/rock.png", 86, 86));
		choiceIcons.put("Paper", loadIcon("/rockpaperscissors/assets/paper.png", 86, 86));
		choiceIcons.put("Scissors", loadIcon("/rockpaperscissors/assets/scissors.png", 86, 86));
	}

	private void buildUi() {
		frame = new JFrame("Rock Paper Scissors");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(760, 520));
		frame.setSize(820, 560);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				save.saveTheGame(game, isThemeLight);
			}
		});

		rootPanel = new JPanel(new BorderLayout(14, 14));
		rootPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		frame.setContentPane(rootPanel);

		createMenu();
		createScorePanel();
		createCenterPanel();
		createChoiceButtons();
	}

	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		JMenu optionsMenu = new JMenu("Options");

		JMenuItem startItem = new JMenuItem("Start");
		JMenuItem restartItem = new JMenuItem("Restart");
		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem aboutItem = new JMenuItem("About");
		JMenuItem themeItem = new JMenuItem("Theme");
		JMenuItem exitItem = new JMenuItem("Exit");

		startItem.addActionListener(e -> startGame());
		restartItem.addActionListener(e -> resetRoundUi(true));
		saveItem.addActionListener(e -> {
			save.saveTheGame(game, isThemeLight);
			JOptionPane.showMessageDialog(frame, "Oyun kaydedildi.");
		});
		aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(frame,
				"Rock Paper Scissors\nSwing UI Edition\nMade by Mahmut Caner Arslan"));
		themeItem.addActionListener(e -> {
			isThemeLight = !isThemeLight;
			applyTheme();
		});
		exitItem.addActionListener(e -> {
			save.saveTheGame(game, isThemeLight);
			System.exit(0);
		});

		gameMenu.add(startItem);
		gameMenu.add(restartItem);
		gameMenu.add(saveItem);
		optionsMenu.add(aboutItem);
		optionsMenu.add(themeItem);

		menuBar.add(gameMenu);
		menuBar.add(optionsMenu);
		menuBar.add(exitItem);
		frame.setJMenuBar(menuBar);
	}

	private void createScorePanel() {
		scorePanel = new JPanel(new GridLayout(1, 2, 12, 12));
		scorePanel.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));

		playerScoreLabel = createCardLabel("Oyuncu Skoru: 0");
		botScoreLabel = createCardLabel("Bot Skoru: 0");

		scorePanel.add(playerScoreLabel);
		scorePanel.add(botScoreLabel);
		rootPanel.add(scorePanel, BorderLayout.NORTH);
	}

	private void createCenterPanel() {
		centerPanel = new JPanel(new BorderLayout(12, 12));

		roundResultLabel = new JLabel("Oyunu başlatmak için Start'a tıkla", SwingConstants.CENTER);
		roundResultLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		roundResultLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		centerPanel.add(roundResultLabel, BorderLayout.NORTH);

		JPanel battlePanel = new JPanel(new GridLayout(1, 2, 14, 14));
		battlePanel.add(createChoiceCard(true));
		battlePanel.add(createChoiceCard(false));
		centerPanel.add(battlePanel, BorderLayout.CENTER);

		startButton = new JButton("Start");
		startButton.setFont(new Font("SansSerif", Font.BOLD, 18));
		startButton.setMargin(new Insets(12, 22, 12, 22));
		startButton.addActionListener(e -> startGame());

		JPanel startWrap = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		startWrap.add(startButton);
		centerPanel.add(startWrap, BorderLayout.SOUTH);

		rootPanel.add(centerPanel, BorderLayout.CENTER);
	}

	private JPanel createChoiceCard(boolean playerCard) {
		JPanel card = new JPanel(new BorderLayout(8, 8));
		card.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(148, 163, 184), 1),
				BorderFactory.createEmptyBorder(12, 12, 12, 12)));

		JLabel title = new JLabel(playerCard ? "Oyuncu Seçimi" : "Bot Seçimi", SwingConstants.CENTER);
		title.setFont(new Font("SansSerif", Font.BOLD, 16));

		JLabel image = new JLabel(playerCard ? "?" : "?", SwingConstants.CENTER);
		image.setFont(new Font("SansSerif", Font.BOLD, 28));

		JLabel choiceText = new JLabel("Henüz seçim yok", SwingConstants.CENTER);
		choiceText.setFont(new Font("SansSerif", Font.PLAIN, 15));

		card.add(title, BorderLayout.NORTH);
		card.add(image, BorderLayout.CENTER);
		card.add(choiceText, BorderLayout.SOUTH);

		if (playerCard) {
			playerChoiceLabel = image;
			playerChoiceTextLabel = choiceText;
		} else {
			botChoiceLabel = image;
			botChoiceTextLabel = choiceText;
		}
		return card;
	}

	private void createChoiceButtons() {
		choicesPanel = new JPanel(new GridLayout(1, 3, 12, 0));
		choicesPanel.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));

		rockButton = createChoiceButton("Rock", choiceIcons.get("Rock"), ROCK);
		paperButton = createChoiceButton("Paper", choiceIcons.get("Paper"), PAPER);
		scissorsButton = createChoiceButton("Scissors", choiceIcons.get("Scissors"), SCISSORS);

		rockButton.setEnabled(false);
		paperButton.setEnabled(false);
		scissorsButton.setEnabled(false);

		choicesPanel.add(rockButton);
		choicesPanel.add(paperButton);
		choicesPanel.add(scissorsButton);
		rootPanel.add(choicesPanel, BorderLayout.SOUTH);
	}

	private JButton createChoiceButton(String text, ImageIcon icon, int playerChoice) {
		JButton button = new JButton(text);
		button.setFont(new Font("SansSerif", Font.BOLD, 15));
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		if (icon != null) {
			button.setIcon(icon);
			button.setHorizontalTextPosition(SwingConstants.CENTER);
			button.setVerticalTextPosition(SwingConstants.BOTTOM);
		}
		button.addActionListener(e -> playRound(playerChoice));
		return button;
	}

	private void startGame() {
		started = true;
		rockButton.setEnabled(true);
		paperButton.setEnabled(true);
		scissorsButton.setEnabled(true);
		startButton.setEnabled(false);

		isThemeLight = save.loadTheGame(game);
		refreshScoreLabels();
		applyTheme();
		roundResultLabel.setText("Hamleni seç ve oyunu başlat!");
	}

	private void playRound(int playerChoice) {
		if (!started) {
			return;
		}
		String result = game.playGame(playerChoice);
		refreshScoreLabels();
		updateChoicePanels(playerChoice, game.getSelectionOfBot());
		roundResultLabel.setText(localizeRoundResult(result));
	}

	private void updateChoicePanels(int playerChoice, String botChoice) {
		String playerChoiceKey = toChoiceString(playerChoice);
		setChoiceVisual(playerChoiceLabel, playerChoiceTextLabel, playerChoiceKey);
		setChoiceVisual(botChoiceLabel, botChoiceTextLabel, botChoice);
	}

	private void setChoiceVisual(JLabel imageLabel, JLabel textLabel, String choiceText) {
		ImageIcon icon = choiceIcons.get(choiceText);
		if (icon != null) {
			imageLabel.setText("");
			imageLabel.setIcon(icon);
		} else {
			imageLabel.setIcon(null);
			imageLabel.setText(choiceText == null ? "?" : choiceText.substring(0, 1));
		}
		textLabel.setText(choiceText == null ? "Henüz seçim yok" : choiceText);
	}

	private void refreshScoreLabels() {
		playerScoreLabel.setText("Oyuncu Skoru: " + game.getPlayerStats());
		botScoreLabel.setText("Bot Skoru: " + game.getBotStats());
	}

	private void resetRoundUi(boolean fullReset) {
		game.restartGame();
		started = false;
		startButton.setEnabled(true);
		rockButton.setEnabled(false);
		paperButton.setEnabled(false);
		scissorsButton.setEnabled(false);

		playerChoiceLabel.setIcon(null);
		playerChoiceLabel.setText("?");
		botChoiceLabel.setIcon(null);
		botChoiceLabel.setText("?");
		playerChoiceTextLabel.setText("Henüz seçim yok");
		botChoiceTextLabel.setText("Henüz seçim yok");

		roundResultLabel.setText(fullReset ? "Oyun sıfırlandı. Start'a tıkla." : "Hamleni seç!");
		refreshScoreLabels();
	}

	private String localizeRoundResult(String result) {
		if ("Player won!".equals(result)) {
			return "Kazandın!";
		}
		if ("Bot won!".equals(result)) {
			return "Bot kazandı!";
		}
		return "Berabere!";
	}

	private String toChoiceString(int playerChoice) {
		switch (playerChoice) {
		case ROCK:
			return "Rock";
		case PAPER:
			return "Paper";
		case SCISSORS:
			return "Scissors";
		default:
			return null;
		}
	}

	private JLabel createCardLabel(String text) {
		JLabel label = new JLabel(text, SwingConstants.CENTER);
		label.setFont(new Font("SansSerif", Font.BOLD, 17));
		label.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(148, 163, 184), 1),
				BorderFactory.createEmptyBorder(10, 14, 10, 14)));
		return label;
	}

	private void applyTheme() {
		Color bg = isThemeLight ? LIGHT_BG : DARK_BG;
		Color card = isThemeLight ? LIGHT_CARD : DARK_CARD;
		Color text = isThemeLight ? LIGHT_TEXT : DARK_TEXT;
		Color accent = isThemeLight ? LIGHT_ACCENT : DARK_ACCENT;

		frame.getContentPane().setBackground(bg);
		rootPanel.setBackground(bg);
		scorePanel.setBackground(bg);
		centerPanel.setBackground(bg);
		choicesPanel.setBackground(bg);

		applyCardStyle(playerScoreLabel, card, text);
		applyCardStyle(botScoreLabel, card, text);
		roundResultLabel.setForeground(accent);
		roundResultLabel.setBackground(card);
		roundResultLabel.setOpaque(true);

		styleChoiceButton(rockButton, card, text, accent);
		styleChoiceButton(paperButton, card, text, accent);
		styleChoiceButton(scissorsButton, card, text, accent);
		styleChoiceButton(startButton, card, text, accent);

		updateTextColor(centerPanel, text);
	}

	private void applyCardStyle(JLabel label, Color bg, Color fg) {
		label.setOpaque(true);
		label.setBackground(bg);
		label.setForeground(fg);
	}

	private void styleChoiceButton(JButton button, Color bg, Color fg, Color border) {
		if (button == null) {
			return;
		}
		button.setBackground(bg);
		button.setForeground(fg);
		button.setOpaque(true);
		button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(border, 1),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
	}

	private void updateTextColor(JComponent parent, Color color) {
		for (int i = 0; i < parent.getComponentCount(); i++) {
			if (parent.getComponent(i) instanceof JLabel) {
				parent.getComponent(i).setForeground(color);
			}
			if (parent.getComponent(i) instanceof JPanel) {
				JPanel panel = (JPanel) parent.getComponent(i);
				panel.setBackground(isThemeLight ? LIGHT_CARD : DARK_CARD);
				updateTextColor(panel, color);
			}
		}
	}

	private ImageIcon loadIcon(String path, int width, int height) {
		URL url = GameUI.class.getResource(path);
		if (url == null) {
			return null;
		}
		ImageIcon raw = new ImageIcon(url);
		Image scaled = raw.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(scaled);
	}
}
