package kenji.pilotprojects.madormz.ui.data.repository

import androidx.lifecycle.LiveData
import kenji.pilotprojects.madormz.ui.data.dao.RoomDao
import kenji.pilotprojects.madormz.ui.data.entity.RoomEntity
import javax.inject.Inject

class RoomRepository @Inject constructor(private val roomDao: RoomDao) {

    suspend fun insertRoom(room: RoomEntity) {
        roomDao.insertRoom(room)
    }

    fun getAllRooms(): LiveData<List<RoomEntity>> {
        return roomDao.getAllRooms()
    }

}