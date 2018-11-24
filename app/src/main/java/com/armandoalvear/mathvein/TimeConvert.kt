package com.armandoalvear.mathvein

class TimeConvert {
    fun getTotalTime(startTime: String, endTime: String, startAmPm: Int, endAmPm: Int):String{
        var finalTime = ""
        var finalHr = 0
        var finalMin = 0
        var minVal = 0
        var minusHr = 0

        val startHr = startTime.substringBefore(':').toInt()
        val startMin = startTime.substring(3,5).toInt()
        val endHr = endTime.substringBefore(':').toInt()
        val endMin = endTime.substring(3,5).toInt()

        if(startAmPm == 0 && endAmPm == 1 || startAmPm == 1 && endAmPm == 0){
            if (startMin <= endMin){
                finalMin = endMin - startMin
            }
            else if (startMin > endMin){
                minVal = (60 - startMin) + endMin
                finalMin = (minVal).mod(60)
                minusHr = 1
            }
            if (startHr > endHr){
                if (startHr == 12){
                    finalHr = startHr + endHr
                }
                else {
                    finalHr = (12 - startHr) + endHr
                }
            }
            else if (startHr < endHr){
                if (endHr == 12){
                    finalHr = endHr - startHr
                }
                else{
                    finalHr = (12 - startHr) + endHr
                }
            }
            else{
                finalHr = 12
            }
        }
        else{
            if (startMin <= endMin){
                finalMin = endMin - startMin
            }
            else{
                minVal = (60 - startMin) + endMin
                finalMin = (minVal).mod(60)
                minusHr = 1
            }
            if (startHr > endHr){
                if (startHr == 12){
                    finalHr = endHr
                }
                else{
                    finalHr = 24 - (startHr - endHr)
                }
            }
            else if (startHr > endHr){
                if (endHr == 12){
                    finalHr = 12 + (endHr - startHr)
                }
                else{
                    finalHr = endHr - startHr
                }
            }
            else{
                finalHr = 0
            }
        }
        if (minVal >= 60){
            finalHr = finalHr + 1
        }
        if (minusHr == 1){
            finalHr = finalHr - 1
        }


        if (finalMin == 0){
            if(finalHr == 0){
                finalTime = "No Sleep"
            }
            else if (finalHr == 1){
                finalTime = "$finalHr hr"
            }
            else{
                finalTime = "$finalHr hrs"
            }
        }
        else{
            if (finalHr == 0){
                finalTime = "$finalMin min"
            }
            else if (finalHr == 1){
                finalTime = "$finalHr hr  $finalMin min"
            }
            else{
                finalTime = "$finalHr hrs  $finalMin min"
            }
        }

        return finalTime
    }

    fun getTime(time: String, amPmFlag: Int, startEndFlag: Int):String{
        var finalTime = ""
        var tempTime = time.substring(0,5)
        //Time Function: check for zero in hr
        if(time.substring(0,1)=="0"){
             tempTime = time.substring(1,5)
        }

        //Time Function: am/pm setter
        if (amPmFlag == 0){
            if (startEndFlag == 0){
                finalTime = "Start: $tempTime a.m."
            }
            else if (startEndFlag == 1){
                finalTime = "End: $tempTime a.m."
            }
        }
        else if (amPmFlag == 1){
            if (startEndFlag == 0){
                finalTime = "Start: $tempTime p.m."
            }
            else if (startEndFlag == 1){
                finalTime = "End: $tempTime p.m."
            }
        }
        return finalTime
    }

    fun getTimeSplit(time:String?):String{
        var finalTime = ""
        finalTime = time.toString().substring(0,5)
        return finalTime
    }
}