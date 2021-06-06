

package com.whatstoday.core.database.migrations

import androidx.sqlite.db.SupportSQLiteDatabase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MarvelDatabaseMigration1to2Test {

    @MockK
    lateinit var supportSQLiteDatabase: SupportSQLiteDatabase
    private val migration = MIGRATION_1_2

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun checkMigrationDatabaseVersions() {
        assertEquals(1, migration.startVersion)
        assertEquals(2, migration.endVersion)
    }

    @Test
    fun executeMigrationDatabase() {
        migration.migrate(supportSQLiteDatabase)

        verify(exactly = 0) { supportSQLiteDatabase.beginTransaction() }
    }
}
