package com.rangelcham.adventuremod.quests;

import java.util.List;
import java.util.Optional;

public class Quest {
    public String title;
    public String description;
    public QuestType type;
    public String[] steps;
    public int currentStep;
    public Optional<Integer> experienceReward;
    public Optional<Integer> goldReward;
    public Optional<Integer> pointsReward;
    public boolean isActive;
    public boolean isCompleted;

    public Quest(String title, String description, QuestType type, String[] steps, int currentStep, Optional<Integer> experienceReward, Optional<Integer> goldReward, Optional<Integer> pointsReward, boolean isActive, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.steps = steps;
        this.currentStep = currentStep;
        this.experienceReward = experienceReward;
        this.goldReward = goldReward;
        this.pointsReward = pointsReward;
        this.isActive = isActive;
        this.isCompleted = isCompleted;
    }
}
