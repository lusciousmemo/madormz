package kenji.pilotprojects.madormz.ui.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kenji.pilotprojects.madormz.ui.data.entity.RoomEntity

@Dao
interface RoomDao {

    @Insert
    suspend fun insertRoom(room: RoomEntity)

    @Query("SELECT * FROM rooms")
    fun getAllRooms(): LiveData<List<RoomEntity>>
}