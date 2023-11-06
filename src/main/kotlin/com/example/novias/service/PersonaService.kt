package com.example.novias.service

import com.example.novias.model.Persona
import com.example.novias.repository.PersonaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PersonaService {

    @Autowired
    lateinit var personaRepository: PersonaRepository

    fun list ():List<Persona>{
        return personaRepository.findAll()
    }
    fun save(persona: Persona): Persona{
        try{
            persona.apellido?.takeIf { it.trim().isNotEmpty() }
                    ?: throw Exception("Nombres no debe ser vacio")
            return personaRepository.save(persona)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun update(persona: Persona): Persona{
        try {
            personaRepository.findById(persona.id)
                    ?: throw Exception("ID no existe")

            return personaRepository.save(persona)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(persona: Persona): Persona{
        try{
            val response = personaRepository.findById(persona.id)
                    ?: throw Exception("ID no existe")
            response.apply {
                apellido=persona.apellido //un atributo del modelo
            }
            return personaRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?):Persona?{
        return personaRepository.findById(id)
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = personaRepository.findById(id)
                    ?: throw Exception("ID no existe")
            personaRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}





