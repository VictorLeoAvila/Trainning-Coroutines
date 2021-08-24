package com.example.coroutinesmovies.ui.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


class MainRepository {

// utilização sem corotinas - passando callback

    fun getMovie(callback: (movie: List<Movie>)-> Unit ){
        Thread(Runnable {
            Thread.sleep(3000)
            callback.invoke(
                listOf(
                    Movie(1, "Título 1"),
                    Movie(2, "Título 2")
                )
            )
        }).start()
    }

// usando corotinas - veja como a fica mais simples, a api trata dos callbaks por trás e gerencia melhor as theads

    suspend fun getMovieCoroutines(): List<Movie>{
        return withContext(Dispatchers.Default){
            delay(3000)
            listOf(
                Movie(1, "Título 1"),
                Movie(2, "Título 2")
            )
        }
    }
}