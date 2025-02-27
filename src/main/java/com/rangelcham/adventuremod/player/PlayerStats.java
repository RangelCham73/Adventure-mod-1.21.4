package com.rangelcham.adventuremod.player;

//
//Author: RangelCham73
//

public class PlayerStats {
    public int strength;
    public int dexterity;
    public int constitution;
    public int intelligence;
    public int wisdom;

    public PlayerStats(int strength, int dexterity, int constitution, int intelligence, int wisdom) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
    }

    public void increaseStrength(int n) { this.strength += n; }
    public void increaseDexterity(int n) { this.dexterity += n; }
    public void increaseConstitution(int n) { this.constitution += n; }
    public void increaseIntelligence(int n) { this.intelligence += n; }
    public void increaseWisdom(int n) { this.wisdom += n; }
}
