package com.example.myapppokedex

import com.squareup.sqldelight.db.SqlDriver

actual fun initLogger() {
}

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        TODO("Not yet implemented")
    }
}