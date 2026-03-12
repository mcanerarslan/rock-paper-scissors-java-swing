package rockpaperscissors;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Save {

	private static final String saveFilePath = System.getProperty("user.dir") + File.separator + "save" + File.separator
			+ "savegame.txt";

	public static String getSaveFilePath() {
		return saveFilePath;
	}

	public boolean loadTheGame(Game game) {
	    File file = new File(saveFilePath);

	    if (!file.exists()) {
	        System.out.println("Log - Kayıt bulunamadı, yeni oyun başlatılıyor.");
	        return true;
	    }

	    try (Scanner reader = new Scanner(file)) {
	        int userStats = Integer.parseInt(reader.nextLine());
	        int botStats = Integer.parseInt(reader.nextLine());
	        boolean theme = Boolean.parseBoolean(reader.nextLine());

	        game.setPlayerStats(userStats);
	        game.setBotStats(botStats);

	        return theme;

	    } catch (IOException | NumberFormatException e) {
	        System.err.println("HATA - Kayıt okunamadı: " + e.getMessage());
	        return true;
	    }
	}

	public void saveTheGame(Game game,Boolean isThemeLight) {

		File directory = new File(System.getProperty("user.dir") + File.separator + "save");
		if (!directory.exists()) {
			directory.mkdir();
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(getSaveFilePath()))) {
			writer.write(game.getPlayerStats() + "\n");
			writer.write(game.getBotStats() + "\n");
			writer.write(isThemeLight + "\n");

			System.out.println("Log - Oyuncu verileri başarıyla kaydedildi.");
		} catch (IOException e) {
			System.err.println("HATA - Kaydederken sorun oluştu: " + e.getMessage());
		}
	}

	public void createFile() {

		String directoryPath = System.getProperty("user.dir") + File.separator + "save";
		File directory = new File(directoryPath);

		if (!directory.exists()) {
			directory.mkdir();
		}

		File file = new File(saveFilePath);

		try {
			if (file.createNewFile()) {
				System.out.println("Log - Dosya oluşturuldu: " + file.getAbsolutePath());
			} else {
				System.out.println("Log - Dosya zaten var. " + file.getAbsolutePath());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
