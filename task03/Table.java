package by.itransition.training.task03;

import dnl.utils.text.table.TextTable;

public class Table {

    public static void viewTable(String[] moves) {

        Referee referee = new Referee();
        String[] columnNames = new String[moves.length + 1];
        String[][] results = new String[moves.length][moves.length + 1];

        columnNames[0] = "PC / You =>";
        for (int i = 1; i < columnNames.length; i++) {
            do {
                moves[i - 1] += " ";
            } while (moves[i - 1].length() < 6);
            columnNames[i] = moves[i - 1];
        }

        for (int i = 0; i < results.length; i++) {
            results[i][0] = columnNames[i + 1];
        }

        for (int i = 0; i < results.length; i++) {
            for (int j = 1; j < results[i].length; j++) {
                int temp = referee.getUserResult(moves.length, j, i + 1);
                if (temp == 1) {
                    results[i][j] = "WIN";
                } else if (temp == -1) {
                    results[i][j] = "LOSE";
                } else {
                    results[i][j] = "DRAW";
                }
            }
        }
        TextTable tt = new TextTable(columnNames, results);
        tt.printTable();
    }
}