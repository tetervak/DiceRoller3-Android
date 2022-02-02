package ca.tetervak.diceroller3.model

// Makes a RollData object from the Game object
fun Game.asRollData() = RollData(this)