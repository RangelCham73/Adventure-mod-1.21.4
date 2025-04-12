package com.rangelcham.adventuremod.quests;

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

    public Quest(String title, String description, QuestType type, String[] steps, int currentStep, Optional<Integer> experienceReward, Optional<Integer> goldReward, Optional<Integer> pointsReward) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.steps = steps;
        this.currentStep = currentStep;
        this.experienceReward = experienceReward;
        this.goldReward = goldReward;
        this.pointsReward = pointsReward;
    }

    public void doStep(int step) {
        currentStep = step;
        if (currentStep > steps.length) {
            type = QuestType.COMPLETE;
            completeQuest();
        }
    }

    public void completeQuest() {}
}
