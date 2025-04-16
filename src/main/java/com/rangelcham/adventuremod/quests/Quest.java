package com.rangelcham.adventuremod.quests;

import java.util.List;

public class Quest {
    public final String title;
    public final String description;
    public final QuestType type;
    public final List<String> steps;
    public int currentStep;

    public final int experience;
    public final int gold;
    public final int points;

    public boolean isActive;
    public boolean isCompleted;

    public Quest(
            String title,
            String description,
            QuestType type,
            List<String> steps,
            int currentStep,
            int experience,
            int gold,
            int points,
            boolean isActive,
            boolean isCompleted
    ) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.steps = List.copyOf(steps); // protección contra modificaciones
        this.currentStep = currentStep;
        this.experience = experience;
        this.gold = gold;
        this.points = points;
        this.isActive = isActive;
        this.isCompleted = isCompleted;
    }
}
