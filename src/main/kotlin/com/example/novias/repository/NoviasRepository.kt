package com.example.novias.repository

import com.example.novias.model.Novias
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NoviasRepository : JpaRepository<Novias, Long?> {

    fun findById (id: Long?): Novias?


}