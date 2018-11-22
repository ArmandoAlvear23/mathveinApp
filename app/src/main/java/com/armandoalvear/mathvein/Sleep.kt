package com.armandoalvear.mathvein

class Sleep {
    var startTime: String? = null
    var endTime: String? = null
    var startAmPm: Int = 0
    var endAmPm: Int = 0
    var timestamp: String? = null

    constructor(startTime: String?, endTime: String?, startAmPm: Int, endAmPm: Int, timestamp: String?) {
        this.startTime = startTime
        this.endTime = endTime
        this.startAmPm = startAmPm
        this.endAmPm = endAmPm
        this.timestamp = timestamp
    }

    constructor()
}