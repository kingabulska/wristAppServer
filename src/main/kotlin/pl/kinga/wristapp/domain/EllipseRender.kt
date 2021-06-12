package pl.kinga.wristapp.domain

import pl.kinga.wristapp.controller.RecordingView
import pl.kinga.wristapp.util.cosineArray
import java.util.*

class EllipseRender(
    var examineNumber: Int? = null,
    var ellipseNumber: Int? = null,
    var hand: Hand? = null,
    val deviceHeight: Int,
    val deviceWidth: Int,
    val recording: Recording,
    val scale: Double = 1.0,
    referenceEllipse: List<Array<Double>>? = null,
    recordedPoints: List<Array<Double>>? = null,
    cosineArray: List<Double>? = null,
    id: String? = null,
) {
    var id = id ?: UUID.randomUUID().toString()

    var canvasHeight = deviceHeight * scale
    var canvasWidth = deviceWidth * scale

    var absTop = recording.ellipse.top * canvasHeight
    var absBottom = recording.ellipse.bottom * canvasHeight
    var absLeft = recording.ellipse.left * canvasWidth
    var absRight = recording.ellipse.right * canvasWidth

    var rVertical = (absBottom - absTop) / 2
    var rHorizontal = (absRight - absLeft) / 2

    var xCenter = absRight - rHorizontal
    var yCenter = absBottom - rVertical

    var referenceEllipse =
        referenceEllipse ?: generateEllipse(rVertical, rHorizontal, xCenter, yCenter, recording.ellipse.rotation)

    var recordedPoints = recordedPoints ?: recording.drawing.map {
        arrayOf(it.x * scale, it.y * scale)
    }

    var cosineArray = cosineArray ?: cosineArray(this.referenceEllipse, this.recordedPoints)

    var cosArrayMean = cosineArray?.average()

    fun scaled(scale: Double) =
        EllipseRender(examineNumber, ellipseNumber, hand, deviceHeight, deviceWidth, recording, scale, null, null, cosineArray, id)
}
