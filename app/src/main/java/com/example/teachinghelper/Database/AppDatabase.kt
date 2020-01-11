package com.example.teachinghelper.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.teachinghelper.Dao.*
import com.example.teachinghelper.Entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Question::class, Area::class, Subject::class,Answer::class, DifficultyLevel::class ), version = 3)
abstract class AppDatabase  : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    abstract fun areaDao(): AreaDao
    abstract fun subjectDao(): SubjectDao
    abstract fun answerDao(): AnswerDao
    abstract fun difficultyLevelDao(): DifficultyLevelDao


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
                        // remove it later because it deletes all data from database!!!!!!!
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
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
                        populateDatabase(database.questionDao(), database.areaDao(), database.answerDao(), database.difficultyLevelDao(), database.subjectDao())
                    }
                }
            }

            suspend fun populateDatabase(questionDao: QuestionDao, areaDao: AreaDao, answerDao: AnswerDao, difficultyLevelDao: DifficultyLevelDao,subjectDao: SubjectDao ) {
                // Delete all content here.
                areaDao.deleteAll()
                questionDao.deleteAll()
                answerDao.deleteAll()
                difficultyLevelDao.deleteAll()
                subjectDao.deleteAll()

                var maths = Subject(1, "Matematyka")
                subjectDao.insert(maths)
                var english = Subject(2, "Angielski")
                subjectDao.insert(english)

                var geometry = Area(1, "Geometria", maths.id)
                areaDao.insert(geometry)
                var percentage = Area(2, "Procenty", maths.id)
                areaDao.insert(percentage)
                var home = Area(3, "dom", english.id)
                areaDao.insert(home)

                var easyLevel = DifficultyLevel(1, "łatwy", 1)
                difficultyLevelDao.insert(easyLevel)
                var mediumLevel = DifficultyLevel(2, "średniozaawansowany", 2)
                difficultyLevelDao.insert(mediumLevel)
                var hardLevel = DifficultyLevel(3, "trudny", 3)
                difficultyLevelDao.insert(hardLevel)

                var question = Question(1, geometry.id, "Ile wynosi suma kątów w trójkącie?", 1)
                questionDao.insert(question)
                var answ1 = Answer(1, "180", question.id, true)
                answerDao.insert(answ1)
                var answ2 = Answer(2, "120", question.id, false)
                answerDao.insert(answ2)
                var answ3 = Answer(3, "360", question.id, false)
                answerDao.insert(answ3)
                var answ4 = Answer(4, "900", question.id,false)
                answerDao.insert(answ4)

                question = Question(2, percentage.id, "Koszulka kosztowała 100zł. Jej cena została obniżona o 10%, a nastepnie podwyższona o 10%." +
                        "Ile wynosi aktualna cena koszulki?", hardLevel.id)
                questionDao.insert(question)
                answ1 = Answer(1, "99", question.id, true)
                answerDao.insert(answ1)
                answ2 = Answer(2, "100", question.id, false)
                answerDao.insert(answ2)
                answ3 = Answer(3, "90", question.id, false)
                answerDao.insert(answ3)
                answ4 = Answer(4, "110", question.id,false)
                answerDao.insert(answ4)

                question = Question(3, geometry.id, "Ile wynosi objętość sześcianu o boku długości 3?", mediumLevel.id)
                questionDao.insert(question)
                answ1 = Answer(5, "9", question.id, false)
                answerDao.insert(answ1)
                answ2 = Answer(6, "30", question.id, false)
                answerDao.insert(answ2)
                answ3 = Answer(7, "krowa", question.id, false)
                answerDao.insert(answ3)
                answ4 = Answer(8, "27", question.id,true)
                answerDao.insert(answ4)

                question = Question(4, home.id, "Jak powiesz po angielsku na zmywarkę?", mediumLevel.id)
                questionDao.insert(question)
                answ1 = Answer(9, "dishwasher", question.id, true)
                answerDao.insert(answ1)
                answ2 = Answer(10, "plateswasher", question.id, false)
                answerDao.insert(answ2)
                answ3 = Answer(11, "kitchen machine", question.id, false)
                answerDao.insert(answ3)
                answ4 = Answer(12, "WOMAN", question.id,false)
                answerDao.insert(answ4)

                question = Question(5, home.id, "Po angielsku łóżko to...",easyLevel.id )
                questionDao.insert(question)
                answ1 = Answer(13, "bed", question.id, true)
                answerDao.insert(answ1)
                answ2 = Answer(14, "bad", question.id, false)
                answerDao.insert(answ2)
                answ3 = Answer(15, "sleep", question.id, false)
                answerDao.insert(answ3)
                answ4 = Answer(16, "desk", question.id,false)
                answerDao.insert(answ4)

                question = Question(6, home.id, "Jaki angielski idiom odpowiada polskiemu 'Nie ma to jak w domu'??", hardLevel.id)
                questionDao.insert(question)
                answ1 = Answer(17, "There is no place like home!", question.id, true)
                answerDao.insert(answ1)
                answ2 = Answer(18, "Doesn't have like in home!", question.id, false)
                answerDao.insert(answ2)
                answ3 = Answer(19, "Home is a holy place!", question.id, false)
                answerDao.insert(answ3)
                answ4 = Answer(20, "Welcome to my home!", question.id,false)
                answerDao.insert(answ4)

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