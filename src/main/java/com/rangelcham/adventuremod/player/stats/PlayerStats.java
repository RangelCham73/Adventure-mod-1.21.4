package com.rangelcham.adventuremod.player.stats;

public class PlayerStats {
    public int strength;
    public boolean isMaxStrength;
    public int dexterity;
    public boolean isMaxDexterity;
    public int constitution;
    public boolean isMaxConstitution;
    public int intelligence;
    public boolean isMaxIntelligence;
    public int wisdom;
    public boolean isMaxWisdom;

    public final int CAP_STAT = 18;
    public final int MAX_STAT = 20;

    public PlayerStats() {
        strength = 6;
        isMaxStrength = false;
        dexterity = 6;
        isMaxDexterity = false;
        constitution = 6;
        isMaxConstitution = false;
        intelligence = 6;
        isMaxIntelligence = false;
        wisdom = 6;
        isMaxWisdom = false;
    }
}
