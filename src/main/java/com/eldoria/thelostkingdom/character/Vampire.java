package com.eldoria.thelostkingdom.character;

import java.util.Random;

public class Vampire extends Character {
    public static int vampireHealth = 100; // Specific health for the vampire. Override if you wish.
    public static final int BASE_ATTACK = 15;

    // Constructor
    public Vampire() {
        setName("Elder Vampire");
        setHealth(150); // Adjust based on your gameplay balance.
        setAttackDamage(BASE_ATTACK);
    }

    // If the vampire has special attack methods, override the attack() method:
    //This is IF we get to that point
   @Override
    public int attack() {
        // You can add specific logic for the vampire here.
        // For instance, a special attack that sometimes does extra damage:
        if (new Random().nextInt(10) > 7) { // 30% chance to deal an extra 5 damage.
            return BASE_ATTACK + 5;
        }
        return BASE_ATTACK;
    }

    // ... any additional vampire-specific methods ...
}
