package com.armandoalvear.mathvein

class DateConvert {
    var timestamp: String? = null

    constructor(timestamp: String?) {
        this.timestamp = timestamp
    }


    fun dateCon(timestamp: String?): String{
        val year = timestamp.toString().substring(0,4)
        val month = timestamp.toString().substring(5,7).toInt()
        val day = timestamp.toString().substring(8,10).toInt()

        var monthText = ""
        if(month == 1){
            monthText = "Jan"
        }
        else if (month == 2){
            monthText = "Feb"
        }
        else if (month == 3){
            monthText = "Mar"
        }
        else if (month == 4){
            monthText = "Apr"
        }
        else if (month == 5){
            monthText = "May"
        }
        else if (month == 6){
            monthText = "Jun"
        }
        else if (month == 7){
            monthText = "Jul"
        }
        else if (month == 8){
            monthText = "Aug"
        }
        else if (month == 9){
            monthText = "Sep"
        }
        else if (month == 10){
            monthText = "Oct"
        }
        else if (month == 11){
            monthText = "Nov"
        }
        else if (month == 12){
            monthText = "Dec"
        }

        //Date Function: return value (string)
        val date = "Date: $monthText $day, $year"

        return date
    }
}