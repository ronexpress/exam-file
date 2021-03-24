package com.codecool.chessopen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ChessResults {
    private static Map<String, Integer> competitorsResult = new HashMap<>();

    public List<String> getCompetitorsNamesFromFile(String fileName) {
        competitorsResult.clear();
        // List<String> competitorsRanking = new ArrayList<>();

        getCompetitorsFromFile(fileName);

        List<String> competitorsRanking = new ArrayList<String>(
                competitorsResult.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldValue, newValue) -> oldValue, LinkedHashMap::new))
                        .keySet()
        );


        return competitorsRanking;
    }


    public void getCompetitorsFromFile(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            Scanner scanner = new Scanner(fileReader);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splicedLine = line.split(",");
                int score = 0;
                for (int i = 1; i < splicedLine.length; i++) {
                    score += Integer.parseInt(splicedLine[i]);
                }
                competitorsResult.put(splicedLine[0], score);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

    }

}
