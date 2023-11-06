package com.example.novias.controller

import com.example.novias.model.Novias
import com.example.novias.model.Persona
import com.example.novias.service.NoviasService
import com.example.novias.service.PersonaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/novias")

class NoviasController {

    @Autowired
    lateinit var noviasService: NoviasService

    @GetMapping
    fun list ():List <Novias>{
        return noviasService.list()
    }
    @PostMapping
    fun save (@RequestBody novias: Novias):ResponseEntity<Novias>{
        return ResponseEntity(noviasService.save(novias), HttpStatus.OK)
    }
    @PutMapping
    fun update (@RequestBody novias: Novias):ResponseEntity<Novias>{
        return ResponseEntity(noviasService.update(novias), HttpStatus.OK)
    }
    @PatchMapping
    fun updateName (@RequestBody novias: Novias):ResponseEntity<Novias>{
        return ResponseEntity(noviasService.updateName(novias), HttpStatus.OK)
    }
    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*>{
        return ResponseEntity(noviasService.listById (id), HttpStatus.OK)

    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return noviasService.delete(id)
    }
}

