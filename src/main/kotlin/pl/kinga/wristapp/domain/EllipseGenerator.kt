package pl.kinga.wristapp.domain

import kotlin.math.cos
import kotlin.math.sin

fun generateEllipse(
    rVertical: Double,
    rHorizontal: Double,
    x: Double = 0.0,
    y: Double = 0.0,
    degrees: Float = 0f,
): List<Array<Double>> {
    val sin = sin(degrees.toDouble().toRadians())
    val cos = cos(degrees.toDouble().toRadians())

    return List(360) { num ->
        val it = num + 1
        arrayOf(
            rHorizontal * cos(it.toRadians()),
            rVertical * sin(it.toRadians())
        )
    }
        .map {
            arrayOf(
                it[0] * cos - it[1] * sin + x,
                it[0] * sin + it[1] * cos + y
            )
        }

}

fun Double.toRadians() = Math.toRadians(this)
fun Int.toRadians() = Math.toRadians(this.toDouble())