package com.armandoalvear.mathvein

class Exercise {
    var desc: String? = null
    var intense: Int = 0
    var startTime: String? = null
    var endTime: String? = null
    var startAmPm: Int = 0
    var endAmPm: Int = 0

    constructor(desc: String?, intense: Int, startTime: String?, endTime: String?, startAmPm: Int, endAmPm: Int) {
        this.desc = desc
        this.intense = intense
        this.startTime = startTime
        this.endTime = endTime
        this.startAmPm = startAmPm
        this.endAmPm = endAmPm
    }

}