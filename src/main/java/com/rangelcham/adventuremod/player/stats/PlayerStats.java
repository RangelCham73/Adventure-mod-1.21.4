package com.rangelcham.adventuremod.player.stats;

public class PlayerStats {
    public int strength, dexterity, constitution, intelligence, wisdom;
    private static final int DEFAULT_STAT = 6;
    public static final int MAX_STAT = 20;
    public static final int CAP_STAT = 18;

    public PlayerStats() {
        this.strength = DEFAULT_STAT;
        this.dexterity = DEFAULT_STAT;
        this.constitution = DEFAULT_STAT;
        this.intelligence = DEFAULT_STAT;
        this.wisdom = DEFAULT_STAT;
    }

    // Metodo genérico para verificar si una estadística ha alcanzado el valor máximo
    public boolean isMaxStat(int statValue) {
        return statValue == CAP_STAT;
    }

    // Metodo genérico para verificar si una estadística ha alcanzado el valor máximo
    public boolean isSpecialMaxStat(int statValue) {
        return statValue == MAX_STAT;
    }
}
