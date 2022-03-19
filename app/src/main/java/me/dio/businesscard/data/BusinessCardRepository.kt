package me.dio.businesscard.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.launch



class BusinessCardRepository (private val dao: BusinessCardDao){

    fun insert(businessCard: BusinessCard) = runBlocking {
        this.launch(Dispatchers.IO) {
            dao.insert(businessCard)
        }
    }

    fun getAll() = dao.getAll()


}