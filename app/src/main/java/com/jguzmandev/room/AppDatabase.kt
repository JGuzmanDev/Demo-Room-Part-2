package com.jguzmandev.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jguzmandev.room.dao.CarDAO
import com.jguzmandev.room.dao.ContactDAO
import com.jguzmandev.room.dao.PersonDAO
import com.jguzmandev.room.entity.Car
import com.jguzmandev.room.entity.Contact
import com.jguzmandev.room.entity.Person

@Database(
    entities = [
        Person::class,
        Car::class,
        Contact::class
    ], version = 2
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDAO
    abstract fun carDao(): CarDAO
    abstract fun contactDao(): ContactDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDataBase(context).also { INSTANCE = it }
            }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "my-db-app-romm")
                //.addMigrations(MIGRATION_FROM_1_TO_2.invoke())
                //.addMigrations(MIGRATION_FROM_2_TO_3.invoke())
                //.addMigrations(MIGRATION_FROM_3_TO_4.invoke())
                .addMigrations(MIGRATION_1_TO_2.invoke())
                .allowMainThreadQueries()
                .build()

        //Old migration
        private val MIGRATION_FROM_1_TO_2 = {
            object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL(
                        "CREATE TABLE Cars(" +
                                "Id INTEGER PRIMARY KEY NOT NULL, " +
                                "Brand TEXT NOT NULL, " +
                                "PersonFK INTEGER NOT NULL, " +
                                "foreign key (PersonFK) references People(Id) " +
                                "ON DELETE CASCADE" +
                                ")"
                    )
                }
            }
        }
        private val MIGRATION_FROM_2_TO_3 = {
            object : Migration(2, 3) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL(
                        "CREATE TABLE Contacts(" +
                                "Id INTEGER PRIMARY KEY NOT NULL, " +
                                "Name TEXT NOT NULL, " +
                                "PersonFKData INTEGER NOT NULL, " +
                                "foreign key (PersonFKData) references People(Id)" +
                                "ON DELETE CASCADE" +
                                ")"
                    )
                    database.execSQL("CREATE UNIQUE INDEX index_Contacts_PersonFKData ON Contacts(PersonFKData)")
                }
            }
        }

        //New
        private val MIGRATION_1_TO_2 = {
            object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL("ALTER TABLE people  ADD COLUMN personCreated INTEGER")
                }
            }
        }

    }
}
