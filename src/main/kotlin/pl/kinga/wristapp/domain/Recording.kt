package pl.kinga.wristapp.domain

import java.time.Instant

data class Recording(
    val ellipse: Ellipse,
    val drawing: List<TimePosition>,
    val start: Instant?,
    val end: Instant?
)
