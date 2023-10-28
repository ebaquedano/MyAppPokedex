package com.example.myapppokedex.api

object StringFormatter {

    fun changeFirstLetterToUppercaseAndDeleteMiddleDash(str: String): String {
        return changeFirstLetterToUppercase(str).replace("-", " ")
    }

    private fun changeFirstLetterToUppercase(str: String): String {
        return str.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase() else it.toString()
        }
    }
}