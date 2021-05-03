package com.example.fabrikal.common

class Utils {
    companion object{
        private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        fun generateRandomHash() : String{
            val randomString = (1..12)
            .map { _ -> kotlin.random.Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("");

            return randomString
        }

    }

}