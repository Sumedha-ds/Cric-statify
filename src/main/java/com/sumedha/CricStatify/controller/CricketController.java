package com.sumedha.CricStatify.controller;

import com.sumedha.CricStatify.model.PlayerStat;
import com.sumedha.CricStatify.repository.PlayerStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Controller
public class CricketController {

    @Autowired
    private PlayerStatRepository playerStatRepository;

    private static final Logger logger = Logger.getLogger(CricketController.class.getName());

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("num_players") int numPlayers,
            @RequestParam Map<String, String> params,
            Model model) {

        try {
            for (int i = 0; i < numPlayers; i++) {
                String name = params.get("name_" + i);
                if (name == null || name.trim().isEmpty()) {
                    throw new IllegalArgumentException("Player name cannot be null or empty");
                }

                int ballsFaced = parseInteger(params.get("balls_faced_" + i));
                int runsScored = parseInteger(params.get("runs_scored_" + i));
                int wicketsTaken = parseInteger(params.get("wickets_taken_" + i));
                float oversBowled = parseFloat(params.get("overs_bowled_" + i));

                float strikeRate = ballsFaced > 0 ? (float) runsScored / ballsFaced * 100 : 0;
                float battingAverage = ballsFaced > 0 ? (float) runsScored / ballsFaced : 0;
                float economyRate = oversBowled > 0 ? (float) runsScored / oversBowled : 0;
                float bowlingAverage = wicketsTaken > 0 ? (float) runsScored / wicketsTaken : 0;

                Optional<PlayerStat> existingPlayer = playerStatRepository.findByName(name);
                if (existingPlayer.isPresent()) {
                    PlayerStat playerStat = existingPlayer.get();
                    playerStat.setBallsFaced(ballsFaced);
                    playerStat.setRunsScored(runsScored);
                    playerStat.setWicketsTaken(wicketsTaken);
                    playerStat.setOversBowled(oversBowled);
                    playerStat.setStrikeRate(strikeRate);
                    playerStat.setBattingAverage(battingAverage);
                    playerStat.setEconomyRate(economyRate);
                    playerStat.setBowlingAverage(bowlingAverage);
                    playerStatRepository.save(playerStat);
                } else {
                    PlayerStat playerStat = new PlayerStat();
                    playerStat.setName(name);
                    playerStat.setBallsFaced(ballsFaced);
                    playerStat.setRunsScored(runsScored);
                    playerStat.setWicketsTaken(wicketsTaken);
                    playerStat.setOversBowled(oversBowled);
                    playerStat.setStrikeRate(strikeRate);
                    playerStat.setBattingAverage(battingAverage);
                    playerStat.setEconomyRate(economyRate);
                    playerStat.setBowlingAverage(bowlingAverage);
                    playerStatRepository.save(playerStat);
                }
            }

            // Create CSV file from database
            String outputFile = "src/main/resources/static/cricket_stats.csv";
            List<PlayerStat> players = playerStatRepository.findAll();
            Set<String> seenNames = new HashSet<>();
            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)))) {
                writer.println("Name,Balls Faced,Runs Scored,Wickets Taken,Overs Bowled,Strike Rate,Batting Average,Economy Rate,Bowling Average");
                for (PlayerStat player : players) {
                    if (seenNames.add(player.getName())) {
                        writer.printf("%s,%d,%d,%d,%.1f,%.2f,%.2f,%.2f,%.2f%n",
                                player.getName(), player.getBallsFaced(), player.getRunsScored(),
                                player.getWicketsTaken(), player.getOversBowled(), player.getStrikeRate(),
                                player.getBattingAverage(), player.getEconomyRate(), player.getBowlingAverage());
                    }
                }
            }

            model.addAttribute("filename", "cricket_stats.csv");
            return "result.html";

        } catch (IllegalArgumentException e) {
            logger.severe("Error occurred: " + e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "error.html";
        } catch (Exception e) {
            logger.severe("Unexpected error: " + e.getMessage());
            model.addAttribute("error", "An unexpected error occurred.");
            return "error.html";
        }
    }

    private int parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0; // Default value if parsing fails
        }
    }

    private float parseFloat(String value) {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return 0; // Default value if parsing fails
        }
    }
}
