package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 250, 240, 300};
    public static int[] heroesDamages = {25, 20, 15, 0};
    public static String[] heroesAttackType = {"Physical", "Magic", "Kinetic", "Medical"};

    public static void changeBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choose " + bossDefenceType);
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void main(String[] args) {

        printStatistics();
        while (!isGameFinished()) {
            round();
        }

    }


    public static void round() {
        if (bossHealth > 0) {
            changeBossDefence();
            bossHits();
        }
        heroesHit();
        medicHelp();
        printStatistics();
    }

    public static void medicHelp() {
        if (heroesHealth[3] > 0) {
            Random r = new Random();
            int random = r.nextInt(50);
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] > 0 && heroesHealth[i] < 100&&heroesHealth[i]!=heroesHealth[3]){
                    heroesHealth[i] = heroesHealth[i] = random;
                    System.out.println("Medic вылечел на "+random+"хп "+heroesAttackType[i]);
                break;
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println("__________________");
        System.out.println("Boss health: " + bossHealth);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i]);
        }
        System.out.println("__________________");
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamages.length; i++) {
            if (heroesHealth[i] > 0) {
                if (bossHealth > 0) {
                    if (bossDefenceType == heroesAttackType[i]) {
                        Random random = new Random();
                        int coeff = random.nextInt(10) + 2; //2,3,4,5,6,7,8,9,10,11
                        System.out.println("Critical damage = " + heroesDamages[i] * coeff);
                        if (bossHealth - heroesDamages[i] * coeff < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - heroesDamages[i] * coeff;
                        }
                    } else {
                        if (bossHealth - heroesDamages[i] < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - heroesDamages[i];
                        }
                    }
                }
            }
        }
    }
}
