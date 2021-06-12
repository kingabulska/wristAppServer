package pl.kinga.wristapp.util

import kotlin.math.sqrt

fun distanceBetweenPoints(vectorA: Array<Double>, vectorB: Array<Double>): Double {
    val x1 = vectorA[0]
    val y1 = vectorA[1]

    val x2 = vectorB[0]
    val y2 = vectorB[0]

    return sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1))
}

fun theClosestPointIndex(referenceEllipse: List<Array<Double>>, recordedPoints: List<Array<Double>>,): Int {
    var theClosest = distanceBetweenPoints(referenceEllipse[0], recordedPoints[0])
    var theClosestPointIndex = 0
    println(theClosest)


    for (j in 1 until recordedPoints.size) {
        val newClosest = distanceBetweenPoints(referenceEllipse[0], recordedPoints[j])
        println(newClosest)
        if (newClosest < theClosest) {
            theClosestPointIndex = j
            theClosest = newClosest
        }
    }
    return theClosestPointIndex
}

fun cosineSimilarity(vectorA: Array<Double>, vectorB: Array<Double>): Double {
    var dotProduct = 0.0
    var normA = 0.0
    var normB = 0.0
    for (i in vectorA.indices) {
        dotProduct += vectorA[i] * vectorB[i]
        normA += Math.pow(vectorA[i], 2.0)
        normB += Math.pow(vectorB[i], 2.0)
    }
    return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB))
}
fun eukilideanDistance(vectorA: Array<Double>, vectorB:  Array<Double>): Double {
    return Math.sqrt(Math.pow((vectorB[0] - vectorA[0]), 2.0) + Math.pow((vectorB[1] - vectorA[1]), 2.0))
}

fun cosineArray(referenceEllipse: List<Array<Double>>, recordedPoints: List<Array<Double>>): List<Double> {
    val newRefArray = newRefArray(referenceEllipse, recordedPoints)

    var cosineList = mutableListOf<Double>()

    for (i in recordedPoints.indices) {
        val cosine = distanceBetweenPoints(recordedPoints[i], newRefArray[i])
        cosineList.add(cosine)
    }

    return cosineList
}

fun newRefArray(referenceEllipse: List<Array<Double>>, recordedPoints: List<Array<Double>>) : List<Array<Double>> {

    val newRefArray: MutableList<Array<Double>> = mutableListOf()
    var theClosestIndex = 0

    for (i in recordedPoints.indices) {
        var theClosest = distanceBetweenPoints(recordedPoints[i], referenceEllipse[0])


        for(j in 1 until referenceEllipse.size) {
            var newClosest = distanceBetweenPoints(recordedPoints[i], referenceEllipse[j])
            if (newClosest < theClosest) {
                theClosest = newClosest
                theClosestIndex = j
            }
        }

        newRefArray.add(referenceEllipse[theClosestIndex])
        theClosestIndex = 0
    }

    return newRefArray
}