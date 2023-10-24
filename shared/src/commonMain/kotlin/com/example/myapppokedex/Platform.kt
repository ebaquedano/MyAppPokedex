package com.example.myapppokedex

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun initLogger()