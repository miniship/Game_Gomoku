package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileProcessor {
	private String fileName;

	public FileProcessor(String fileName) {
		this.fileName = fileName;
	}

	public List<int[][]> readMoves() {
		List<int[][]> moves = new LinkedList<int[][]>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String line;
			while ((line = reader.readLine()) != null) {
				if (!line.matches("[0-9]{1,2}.[0-9]+")) {
					continue;
				}

				int[][] move = readMove(reader);
				if (move != null) {
					moves.add(move);
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println(new PropertiesGetter().getProperty("errorMessageFileNotFound"));
		} catch (IOException e) {
			System.out.println(new PropertiesGetter().getProperty("errorMessageIO"));
		}
		return moves;
	}

	private int[][] readMove(BufferedReader reader) throws IOException {
		int[][] move = new int[5][5];

		for (int i = 0; i < 5; i++) {
			String line = reader.readLine();
			if (!line.matches("[0-4]{5}")) {
				return null;
			}

			for (int j = 0; j < 5; j++) {
				move[i][j] = Integer.parseInt(line.substring(j, j + 1));
			}
		}

		return move;
	}

	public int writeMoves(List<int[][]> moves, List<String> statusLines) {
		int count = 0;
		if (moves.size() != statusLines.size()) {
			return count;
		}

		File file = new File(fileName);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			for (int i = 0; i < moves.size(); i++) {
				String statusLine = statusLines.get(i);
				if (statusLine == null || !statusLine.matches("[0-9]{1,2}.[0-9]+")) {
					continue;
				}

				int[][] move = moves.get(i);
				if (move == null || move.length != 5 || move[0].length != 5) {
					continue;
				}

				if (writeMove(writer, move, statusLine)) {
					count++;
				}
			}
			writer.close();
		} catch (IOException e) {
			System.out.println(new PropertiesGetter().getProperty("errorMessageIO"));
		}
		return count;
	}

	private boolean writeMove(BufferedWriter writer, int[][] move, String statusLine) throws IOException {
		String[] stringMove = new String[5];
		for (int i = 0; i < 5; i++) {
			StringBuilder line = new StringBuilder();

			for (int j = 0; j < 5; j++) {
				if (move[i][j] < 0 || move[i][j] > 4) {
					return false;
				}

				line.append(move[i][j]);
			}

			stringMove[i] = line.toString();
		}

		writer.write(statusLine);
		writer.newLine();
		for (String line : stringMove) {
			writer.write(line);
			writer.newLine();
		}
		return true;
	}
}
