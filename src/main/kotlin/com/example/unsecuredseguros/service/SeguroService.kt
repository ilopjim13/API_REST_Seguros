package com.example.unsecuredseguros.service

import com.example.unsecuredseguros.repository.SeguroRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SeguroService {

    @Autowired
    private lateinit var seguroRepository: SeguroRepository

    fun getSeguroById(id:Long) {

    }

}