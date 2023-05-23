package kenji.pilotprojects.madormz.ui.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rooms")
data class RoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val roomNumber: String,
    val waterMeterImage: String,
    val electricityMeterImage: String,
    val waterMeterValue: Double,
    val electricityMeterValue: Double,
    val recordedDate: String,
    var waterUnitsUsed: Double = 0.0,
    var electricityUnitsUsed: Double = 0.0
)
