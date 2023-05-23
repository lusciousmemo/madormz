package kenji.pilotprojects.madormz.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kenji.pilotprojects.madormz.ui.data.DormDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext application: Application): DormDatabase {
        return Room.databaseBuilder(application, DormDatabase::class.java, "dorm_database")
            .build()
    }

    @Provides
    fun provideRoomDao(database: DormDatabase) = database.roomDao()
}