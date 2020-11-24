package at.mstubinger.familyplanner.service.data

import java.time.LocalDateTime

@Target(AnnotationTarget.FIELD)
annotation class KlaxonDate

class fpDateTime @JvmOverloads constructor(
    @KlaxonDate
    var date: LocalDateTime
)