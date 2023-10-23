package com.example.novias.repository

import com.example.novias.model.Persona
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface PersonaRepository : JpaRepository<Persona, Long?> {

    fun findById (id: Long?): Persona?


}