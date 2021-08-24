package com.example.coroutinesmovies.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val movieLivedata = MutableLiveData<List<Movie>>()

// chamada sem o uso de coroutinas - precisa gerenciar a thread usando o postValue
    fun getMovie(){
        repository.getMovie { movies ->
            movieLivedata.postValue(movies)
        }
    }

// usando coroutinas - trabalhando com contextos de forma sequencial, mais simples
    fun getMovieCoroutines(){
        CoroutineScope(Dispatchers.Main).launch {
            val movie = withContext(Dispatchers.Default) {
                repository.getMovieCoroutines()
            }

            movieLivedata.value = movie
        }
    }

    class MainViewModelFactory(
        private val repository: MainRepository
    ): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }

    }

}