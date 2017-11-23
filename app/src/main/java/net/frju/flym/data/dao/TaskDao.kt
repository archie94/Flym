package net.frju.flym.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import net.frju.flym.data.entities.Task


@Dao
interface TaskDao {
    @get:Query("SELECT * FROM tasks")
    val all: List<Task>

    @get:Query("SELECT * FROM tasks")
    val observeAll: LiveData<List<Task>>

    @get:Query("SELECT * FROM tasks WHERE imageLinkToDl = ''")
    val mobilizeTasks: List<Task>

    @Query("SELECT COUNT(*) FROM tasks WHERE imageLinkToDl = '' AND entryId = :itemId")
    fun countMobilizeTasks(itemId: String): Int

    @get:Query("SELECT * FROM tasks WHERE imageLinkToDl != ''")
    val downloadTasks: List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg tasks: Task)

    @Delete
    fun delete(vararg tasks: Task)
}