package com.example.novias.service

import com.example.novias.model.Novias
import com.example.novias.repository.NoviasRepository
import com.example.novias.repository.PersonaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class NoviasService {

    @Autowired
    lateinit var personaRepository: PersonaRepository

    @Autowired
    lateinit var  noviasRepository: NoviasRepository




    fun list ():List<Novias>{
        return noviasRepository.findAll()
    }
    fun save(novias: Novias): Novias{
        try{
            novias.apellido?.takeIf { it.trim().isNotEmpty() }
                    ?: throw Exception("Nombres no debe ser vacio")
            return noviasRepository.save(novias)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun update(novias: Novias): Novias{
        try {
            noviasRepository.findById(novias.id)
                    ?: throw Exception("ID no existe")

            return noviasRepository.save(novias)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateName(novias: Novias): Novias{
        try{
            val response = noviasRepository.findById(novias.id)
                    ?: throw Exception("ID no existe")
            response.apply {
                apellido=novias.apellido //un atributo del modelo
            }
            return noviasRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun listById (id:Long?):Novias?{
        return noviasRepository.findById(id)
    }
    fun delete (id: Long?):Boolean?{
        try{
            val response = noviasRepository.findById(id)
                    ?: throw Exception("ID no existe")
            noviasRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}





