package com.example.jcdecaux.models

data class Station(
    val number: Int,
    val name: String,
    val address: String,
    val position: Position,
    val banking: Boolean,
    val bonus: Boolean,
    val status: String,
    val lastUpdate: String,
    val connected: Boolean,
    val overflow: Boolean,
    val totalStands: TotalStands,
    val mainStands: MainStands
)

data class Position(val latitude: Double, val longitude: Double)

data class TotalStands(val availabilities: Availabilities, var capacity: Int)

data class MainStands(val availabilities: Availabilities, var capacity: Int)

data class Availabilities(
    val bikes: Int,
    var stands: Int,
    var mechanicalBikes: Int,
    var electricalBikes: Int,
    var electricalInternalBatteryBikes: Int,
    var electricalRemovableBatteryBikes: Int
)
