package com.example.flightitinerary.data.models

data class Track(
    val icao24: String,
    val callsign: String,
    val startTime: Long,
    val endTime: Long,
    val path: Array<Array<Any>>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Track

        if (icao24 != other.icao24) return false
        if (callsign != other.callsign) return false
        if (startTime != other.startTime) return false
        if (endTime != other.endTime) return false
        return path.contentDeepEquals(other.path)
    }

    override fun hashCode(): Int {
        var result = icao24.hashCode()
        result = 31 * result + callsign.hashCode()
        result = 31 * result + startTime.hashCode()
        result = 31 * result + endTime.hashCode()
        result = 31 * result + path.contentDeepHashCode()
        return result
    }
}