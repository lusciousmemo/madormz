package kenji.pilotprojects.madormz.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kenji.pilotprojects.madormz.ui.data.entity.RoomEntity
import kenji.pilotprojects.madormz.ui.data.repository.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoomViewModel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {

    fun insertRoom(room: RoomEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            roomRepository.insertRoom(room)
        }
    }

    fun getAllRooms(): LiveData<List<RoomEntity>> {
        return roomRepository.getAllRooms()
    }
}