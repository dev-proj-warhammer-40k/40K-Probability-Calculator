package com.example.warhammer40k;

/*
 * SESSION CLASS
 * -------------------------
 * DESCRIPTION:
 * This class is essentially a container for operands while the
 * calculation is taking place, so as to not modify them directly.
 *
 * Once they are all here, a session object is passed to a history object
 * for storage.
 * -------------------------
 */

public class Session {

    // User Inputs
    int attacks;
    int skill;
    int hitMod;
    int strength;
    int toughness;
    int woundMod;
    int armPen;
    int armSave;
    int invulnSave = 100;
    int feelNoPain;

    // Results
    double hits;
    double wounds;
    int damage;
    double finalDamage;
}
