package com.example.unsecuredseguros.controller

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

    //@GetMapping("/")
    //fun getAllSeguros() {
//
    //}

    @GetMapping("/{id}")
    fun getSeguroById(
        @PathVariable id:String
    ): ResponseEntity<Any> {
        return if(id.toLongOrNull() == null) ResponseEntity<Any>(null, HttpStatus.BAD_REQUEST)
        else ResponseEntity<Any>(seguroService.getSeguroById(id.toLong()), HttpStatus.OK)
    }

    //@PostMapping("/")
    //fun insertSeguro(
    //    @RequestBody seguro:Seguro
    //): ResponseEntity<Seguro> {
    //    return seguroService.insertSeguro(seguro)
    //}
//
    //@PutMapping("/{id}")
    //fun updateSeguro(
    //    @PathVariable id:String,
    //    @RequestBody seguro:Seguro
    //): ResponseEntity<Seguro> {
//
    //    return if(id.toLongOrNull() == null) null
    //    else seguroService.updateSeguro()
    //}
//
    //@DeleteMapping("/{id}")
    //fun deleteSeguro(
    //    @PathVariable id:String,
    //): ResponseEntity<Seguro> {
//
    //    return if(id.toLongOrNull() == null) ""
    //    else seguroService.deleteSeguro()
    //}

}