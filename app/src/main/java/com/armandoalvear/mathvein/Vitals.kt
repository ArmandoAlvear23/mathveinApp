package com.armandoalvear.mathvein

class Vitals {
    var systolic: String? = null
    var diastolic: String? = null
    var blood_sugar: String? = null
    var timestamp: String? = null

    constructor(systolic: String?, diastolic: String?, blood_sugar: String?, timestamp: String?) {
        this.systolic = systolic
        this.diastolic = diastolic
        this.blood_sugar = blood_sugar
        this.timestamp = timestamp
    }

    constructor()

}