package com.example.teachinghelper.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.teachinghelper.Database.Dao.*
import com.example.teachinghelper.Database.Entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.Exception


@Database(
    version = 8,
    entities = [
        Question::class,
        Area::class,
        Subject::class,
        Answer::class,
        DifficultyLevel::class,
        Attempt::class,
        AnswersHistory::class,
        ApplicationInformation::class
    ]
)
@TypeConverters(Converters::class)
abstract class AppDatabase  : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    abstract fun areaDao(): AreaDao
    abstract fun subjectDao(): SubjectDao
    abstract fun answerDao(): AnswerDao
    abstract fun difficultyLevelDao(): DifficultyLevelDao
    abstract fun attemptDao(): AttemptDao
    abstract fun answersHistoryDao(): AnswersHistoryDao
    abstract fun applicationInformationDao(): ApplicationInformationDao


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
                    //only needed if adding new data
                    .addCallback( AppDatabaseCallback(context, scope))
                    // remove it later because it deletes all data from database!!!!!!!
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance

            }
        }


        private class AppDatabaseCallback(
            private val context: Context,
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.questionDao(), database.areaDao(), database.answerDao(), database.difficultyLevelDao(), database.subjectDao(), database.applicationInformationDao())
                    }
                }
            }

            suspend fun populateDatabase(questionDao: QuestionDao, areaDao: AreaDao, answerDao: AnswerDao, difficultyLevelDao: DifficultyLevelDao, subjectDao: SubjectDao, applicationInformationDao: ApplicationInformationDao) {
                val inputStream = context.resources.assets.open("database.json")
                val json = inputStream.bufferedReader().use{it.readText()}
                val databaseFile = JSONObject(json)

                val versionPropertyName = "version"
                val version = databaseFile.getString(versionPropertyName)
                var dbValue: ApplicationInformation? = applicationInformationDao.getByPropertyName(versionPropertyName)

                if (dbValue?.value == version) {
                    return
                }

                areaDao.deleteAll()
                questionDao.deleteAll()
                answerDao.deleteAll()
                difficultyLevelDao.deleteAll()
                subjectDao.deleteAll()

                var easyLevel =
                    DifficultyLevel(1, "łatwy", 1)
                difficultyLevelDao.insert(easyLevel)
                var mediumLevel = DifficultyLevel(
                    2,
                    "średniozaawansowany",
                    2
                )
                difficultyLevelDao.insert(mediumLevel)
                var hardLevel =
                    DifficultyLevel(3, "trudny", 3)
                difficultyLevelDao.insert(hardLevel)

                val database = databaseFile.getJSONObject("database")
                database.keys().forEach { subjectName ->
                    val subjectId = subjectDao.insert(
                        Subject(
                            null,
                            subjectName
                        )
                    )
                    database.getJSONObject(subjectName).keys().forEach { areaName ->
                        val areaId = areaDao.insert(
                            Area(
                                null,
                                areaName,
                                subjectId
                            )
                        )
                        val subjectQuestions = database.getJSONObject(subjectName).getJSONArray(areaName)

                        for (questionIndex in 0 until subjectQuestions.length()) {
                            val currentQuestion = subjectQuestions.getJSONObject(questionIndex)
                            val currentQuestionAnswers = currentQuestion.getJSONArray("answers")
                            val questionId = questionDao.insert(
                                Question(
                                    null,
                                    areaId,
                                    currentQuestion.getString("content"),
                                    currentQuestion.getLong("difficulty")
                                )
                            )

                            for (answerIndex in 0 until currentQuestionAnswers.length()) {
                                val currentAnswer = currentQuestionAnswers.getJSONObject(answerIndex)
                                answerDao.insert(
                                    Answer(
                                        null,
                                        currentAnswer.getString("content"),
                                        questionId,
                                        currentAnswer.getBoolean("isCorrect")
                                    )
                                )
                            }
                        }
                    }
                }

                if (dbValue == null) {
                    applicationInformationDao.insert(
                        ApplicationInformation(
                            null,
                            versionPropertyName,
                            version
                        )
                    )
                } else {
                    dbValue.value = version
                    applicationInformationDao.update(dbValue);
                }

            }
        }
    }
}