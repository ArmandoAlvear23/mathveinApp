package com.armandoalvear.mathvein

class Meal {
    var mainDish: String? = null
    var appetizer: String? = null
    var snack: String? = null
    var drink: String? = null
    var dessert: String? = null
    var timestamp: String? = null
    
    constructor(mainDish: String?, appetizer: String?, snack: String?, drink: String?, dessert: String?, timestamp: String?) {
        this.mainDish = mainDish
        this.appetizer = appetizer
        this.snack = snack
        this.drink = drink
        this.dessert = dessert
        this.timestamp = timestamp
    }
}