package pl.kinga.wristapp.dto

import pl.kinga.wristapp.domain.*
import java.time.Instant

class ExamineSessionExportDto(
    val id: String,
    val deviceHeight: Int,
    val deviceWidth: Int,
    val examines: Map<Int, ExamineExportDto>
)

class ExamineExportDto(
    val num: Int,
    val hand: Hand,
    val start: Instant,
    val end: Instant,
    val recordings: Map<Int, RecordingExportDto>
)

class RecordingExportDto(
    val num: Int,
    val ellipse: Ellipse,
    val drawing: List<TimePositionExportDto>,
    val start: Instant?,
    val end: Instant?,
    val referenceEllipse: List<Array<Double>>?
)

class TimePositionExportDto(
    val x: Float,
    val y: Float,
    val dt: Long
)

class Wtf(
    val examineInSession: Int?,
    val hand: Hand?,
    val ellipse: Int?,
    val deviceHeight: Int?,
    val deviceWidth: Int?,
    val points: List<TimePositionExportDto>,
    val referenceEllipse: List<Array<Double>>?
)

fun Collection<ExamineSession>.export() =
    this.map { session ->
        session.examines.map { examine ->
            examine.recordings.mapIndexed { index, recording ->
                val firstTimestamp = recording.drawing.firstOrNull()

                val drawingExport = firstTimestamp?.timestamp?.let { tm ->
                    recording.drawing.mapIndexed { _, it ->
                        TimePositionExportDto(
                            x = it.x,
                            y = it.y,
                            dt = it.timestamp.toEpochMilli() - tm.toEpochMilli()
                        )
                    }
                }
                    ?: listOf()

                Wtf(
                    examineInSession = examine.number,
                    hand = examine.hand,
                    ellipse = recording.ellipse.id,
                    deviceHeight = session.deviceHeight,
                    deviceWidth = session.deviceWidth,
                    points = drawingExport,
                    referenceEllipse = examine.renders?.getOrNull(index)?.referenceEllipse
                )
            }
        }
    }
        .flatten()
        .flatten()
        .groupBy { it.hand }

fun ExamineSession.toDto() = ExamineSessionExportDto(id!!,
    deviceHeight, deviceWidth, examines.sortedBy { it.start }.mapIndexed { idx, it -> it.toDto(idx) }
        .associateBy { it.num }
)

fun Examine.toDto(num: Int) = ExamineExportDto(num, hand, start, end,
    recordings.sortedBy { it.start }
        .mapIndexed { idx, it -> it.toDto(idx, renders?.getOrNull(idx)) }
        .associateBy { it.num }
)

fun Recording.toDto(idx: Int, render: EllipseRender?): RecordingExportDto  {

    val firstTimestamp = drawing.firstOrNull()

    val drawingExport = firstTimestamp?.timestamp?.let { tm ->
        drawing.mapIndexed { _, it ->
            TimePositionExportDto(
                x = it.x,
                y = it.y,
                dt = it.timestamp.toEpochMilli() - tm.toEpochMilli()
            )
        }
    }
        ?: listOf()

    return RecordingExportDto(
        num = idx,
        ellipse = ellipse,
        drawing = drawingExport,
        start = start,
        end = end,
        referenceEllipse = render?.referenceEllipse
    )
}
