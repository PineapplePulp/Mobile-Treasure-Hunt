/*
Assignment 4
David Li / lidav@oregonstate.edu
CS 492 / Oregon State University (OSU)
 */

package com.example.mobiletreasurehunt.data

import com.example.mobiletreasurehunt.R

object Datasource {

    private val rules = listOf(
        listOf(
            R.string.mark1,
            R.string.rule1
        ),
        listOf(
            R.string.mark2,
            R.string.rule2
        ),
        listOf(
            R.string.mark3,
            R.string.rule3
        ),
        listOf(
            R.string.mark4,
            R.string.rule4
        ),
        listOf(
            R.string.mark5,
            R.string.rule5
        ),
        listOf(
            R.string.mark6,
            R.string.rule6
        ),
        listOf(
            R.string.mark7,
            R.string.rule7
        )
    )

    private val clues = listOf(
        R.string.clue1,
        R.string.clue2,
        R.string.clue3
    )

    private val hints = listOf(
        R.string.hint1,
        R.string.hint2,
        R.string.hint3
    )

    private val descriptions = listOf(
        R.string.desc1,
        R.string.desc2,
        R.string.desc3
    )

    private val names = listOf(
        R.string.target1,
        R.string.target2,
        R.string.target3
    )

    private val locations = listOf(
        listOf(R.string.lat1, R.string.lon1),   // for Crack-in-the-Ground
        listOf(R.string.lat2, R.string.lon2),   // for Chateau at the Oregon Caves
        listOf(R.string.lat3, R.string.lon3)    // for Witches Cauldron
//        Have to use resource values for assignment
//        Geo(lat = stringResource(43.332810).toDouble(), -120.676000),
//        Geo(lat = stringResource(42.09848).toDouble(),-123.40762),
//        Geo(lat = stringResource(42.93863).toDouble(),-122.14680)
    )

    fun loadRules(): List<List<Int>> {
        return rules
    }

    fun loadLocations(): List<List<Int>> {
        return locations
    }

    fun loadClue(i: Int): Int {
        return clues[i]
    }

    fun loadHint(i: Int): Int {
        return hints[i]
    }

    fun loadDesc(i: Int): Int {
        return descriptions[i]
    }

    fun loadName(i: Int): Int {
        return names[i]
    }

    fun totalClues(): Int {
        return clues.size
    }


}