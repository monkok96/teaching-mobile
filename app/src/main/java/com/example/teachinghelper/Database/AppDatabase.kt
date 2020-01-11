package com.example.teachinghelper.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.teachinghelper.Dao.QuestionDao
import com.example.teachinghelper.Entities.Question
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Question::class), version = 1)
abstract class AppDatabase  : RoomDatabase() {
    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {


            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "teachingApp_database"
                )
                    .addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance

            }
        }


        private class AppDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.questionDao())
                    }
                }
            }

            suspend fun populateDatabase(questionDao: QuestionDao) {
                // Delete all content here.
                questionDao.deleteAll()

                // Add sample words.
                var question = Question(1, "Pytanie 1")
                questionDao.insert(question)
                question = Question(2, "Pytanie 2")
                questionDao.insert(question)

                // TODO: Add your own words!
            }
        }

//        suspend fun populateDatabase(questionDao: QuestionDao) {
//            // Start the app with a clean database every time.
//            // Not needed if you only populate on creation.
//            questionDao.deleteAll()
//
//            var word = Word("Hello")
//            wordDao.insert(word)
//            word = Word("World!")
//            wordDao.insert(word)
//        }
    }
}