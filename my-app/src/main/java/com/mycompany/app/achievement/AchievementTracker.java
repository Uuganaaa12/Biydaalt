package com.mycompany.app.achievement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AchievementTracker {
    private Set<String> unlockedAchievements = new HashSet<>();
    private List<Double> responseTimes = new ArrayList<>();
    
//add comment
    public void unlockAchievement(String achievement) {
        unlockedAchievements.add(achievement);
    }

    public void trackResponseTime(double seconds) {
        responseTimes.add(seconds);
    }

    public double getAverageResponseTime() {
        if (responseTimes.isEmpty()) {
            return 0;
        }
        
        double sum = 0;
        for (double time : responseTimes) {
            sum += time;
        }
        return sum / responseTimes.size();
    }

    public void displayAchievements() {
        System.out.println("\n--- Amjiltuud ---");
        
        if (unlockedAchievements.contains("FAST")) {
            System.out.println("FAST: Dundjaar 1 asuultand 5 sekundees baga hugatsaand hariulsan");
        }
        
        if (unlockedAchievements.contains("CORRECT")) {
            System.out.println("CORRECT: Buh kartand anhni oroldlogoor zuw hariulsan");
        }
        
        if (unlockedAchievements.contains("REPEAT")) {
            System.out.println("REPEAT: 5-aas deesh udaa oroldlogo hiisen");
        }
        
        if (unlockedAchievements.contains("CONFIDENT")) {
            System.out.println("CONFIDENT: Kart burt dor hayj 3 udaa zuw hariulsan");
        }
        
        if (unlockedAchievements.isEmpty()) {
            System.out.println("Odoogoor ymar ch amjilt gargaagui baina");
        }
    }
}