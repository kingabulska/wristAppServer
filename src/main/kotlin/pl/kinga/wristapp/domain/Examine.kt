package pl.kinga.wristapp.domain

import java.time.Instant

class Examine(
    val deviceHeight: Int,
    val deviceWidth: Int,
    val hand: Hand,
    val start: Instant,
    val end: Instant,
    val recordings: List<Recording>,
    val sessionTimestamp: Instant,
    var number: Int? = null,
    renders: List<EllipseRender>? = null
) {
    var renders: List<EllipseRender>? = renders ?: recordings.toRenders()

    fun recalc() {
        renders = recordings.toRenders()
    }

    private fun List<Recording>.toRenders() = this.mapIndexed { index, it ->
        EllipseRender(number, index, hand, deviceHeight, deviceWidth, it, 1.0)
    }
}
