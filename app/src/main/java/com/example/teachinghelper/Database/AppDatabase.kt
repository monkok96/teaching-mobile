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

@Database(
    version = 5,
    entities = [
        Question::class,
        Area::class,
        Subject::class,
        Answer::class,
        DifficultyLevel::class
    ]
)
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
                var home = Area(3, "Dom", english.id)
                areaDao.insert(home)

                var easyLevel = DifficultyLevel(1, "łatwy", 1)
                difficultyLevelDao.insert(easyLevel)
                var mediumLevel = DifficultyLevel(2, "średniozaawansowany", 2)
                difficultyLevelDao.insert(mediumLevel)
                var hardLevel = DifficultyLevel(3, "trudny", 3)
                difficultyLevelDao.insert(hardLevel)

                var questionId = questionDao.insert(
                    Question(
                        null,
                         geometry.id,
                        "Ile wynosi suma kątów w trójkącie?",
                        1
                    )
                )
                answerDao.insert(Answer(null, "180", questionId , true))
                answerDao.insert(Answer(null, "120", questionId, false))
                answerDao.insert(Answer(null, "360", questionId, false))
                answerDao.insert(Answer(null, "900", questionId,false))

                questionId = questionDao.insert(
                    Question(
                        null,
                        percentage.id,
                        "Koszulka kosztowała 100zł. Jej cena została obniżona o 10%, a nastepnie podwyższona o 10%. Ile wynosi aktualna cena koszulki?",
                        hardLevel.id
                    )
                )
                answerDao.insert(Answer(null, "99", questionId, true))
                answerDao.insert(Answer(null, "100", questionId, false))
                answerDao.insert(Answer(null, "90", questionId, false))
                answerDao.insert(Answer(null, "110", questionId,false))

                questionId = questionDao.insert(
                    Question(
                        null,
                        geometry.id,
                        "Ile wynosi objętość sześcianu o boku długości 3?",
                        mediumLevel.id
                    )
                )
                answerDao.insert(Answer(null, "9", questionId, false))
                answerDao.insert(Answer(null, "30", questionId, false))
                answerDao.insert(Answer(null, "krowa", questionId, false))
                answerDao.insert(Answer(null, "27", questionId,true))

                questionId = questionDao.insert(
                    Question(null, home.id, "Jak powiesz po angielsku na zmywarkę?", mediumLevel.id)
                )
                answerDao.insert(Answer(null, "dishwasher", questionId, true))
                answerDao.insert(Answer(null, "plateswasher", questionId, false))
                answerDao.insert(Answer(null, "kitchen machine", questionId, false))
                answerDao.insert(Answer(null, "WOMAN", questionId,false))

                questionId = questionDao.insert(
                    Question(null, home.id, "Po angielsku łóżko to...",easyLevel.id )
                )
                answerDao.insert(Answer(null, "bed", questionId, true))
                answerDao.insert(Answer(null, "bad", questionId, false))
                answerDao.insert(Answer(null, "sleep", questionId, false))
                answerDao.insert(Answer(null, "desk", questionId,false))

                questionId = questionDao.insert(
                    Question(
                        null,
                        home.id,
                        "Jaki angielski idiom odpowiada polskiemu 'Nie ma to jak w domu'??",
                        hardLevel.id
                    )
                )
                answerDao.insert(Answer(null, "There is no place like home!", questionId, true))
                answerDao.insert(Answer(null, "Doesn't have like in home!", questionId, false))
                answerDao.insert(Answer(null, "Home is a holy place!", questionId, false))
                answerDao.insert(Answer(null, "Welcome to my home!", questionId,false))
            }
        }
    }
}