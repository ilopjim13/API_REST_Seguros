package com.example.unsecuredseguros.controller

import com.example.unsecuredseguros.exeption.ValidationException
import com.example.unsecuredseguros.model.Seguro
import com.example.unsecuredseguros.service.SeguroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/seguros")
class SeguroController {

    @Autowired
    private lateinit var seguroService:SeguroService

    @GetMapping("/")
    fun getAll(): ResponseEntity<List<Seguro>> {
        return ResponseEntity<List<Seguro>>(seguroService.getAll(), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getById(
        @PathVariable id:String
    ): ResponseEntity<Seguro> {
        return if(id.toIntOrNull() == null) throw ValidationException("El id debe ser un número")
        else {
            ResponseEntity<Seguro>(seguroService.getSeguroById(id.toInt()), HttpStatus.OK)
        }
    }

    @PostMapping("/")
    fun insertSeguro(
        @RequestBody seguro:Seguro
    ): ResponseEntity<Seguro> {
        return ResponseEntity<Seguro>(seguroService.insertSeguro(seguro), HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateSeguro(
        @PathVariable id:String,
        @RequestBody seguro:Seguro
    ): ResponseEntity<Seguro> {

        return if(id.toIntOrNull() == null) throw ValidationException("La id no es una id correcta.")
        else ResponseEntity<Seguro>(seguroService.updateSeguro(id.toInt(), seguro), HttpStatus.OK)

    }

    @DeleteMapping("/{id}")
    fun deleteSeguro(
        @PathVariable id:String,
    ): ResponseEntity<String> {

        return if(id.toIntOrNull() == null) throw ValidationException("La id no es una id correcta.")
        else {
           ResponseEntity<String>(seguroService.deleteSeguro(id.toInt()), HttpStatus.OK)
        }
    }

}