package com.example.unsecuredseguros.service

import com.example.unsecuredseguros.exeption.NotFoundException
import com.example.unsecuredseguros.exeption.ValidationException
import com.example.unsecuredseguros.model.Seguro
import com.example.unsecuredseguros.repository.SeguroRepository
import com.example.unsecuredseguros.utils.DniValidator
import com.example.unsecuredseguros.utils.SeguroValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class SeguroService {

    @Autowired
    private lateinit var seguroRepository: SeguroRepository

    fun getSeguroById(id:Int) :Seguro {
        val seguro =  seguroRepository.findById(id)
        return if (seguro.getOrNull() != null) seguro.get()
        else throw NotFoundException("El seguro con id $id no ha sido encontrado")
    }

    fun getAll(): List<Seguro> {
        val seguros = seguroRepository.findAll()
        if (seguros.isNotEmpty()) return seguroRepository.findAll()
        else throw ValidationException("La base de datos no tiene datos")
    }

    fun insertSeguro(seguro: Seguro) :Seguro {
        if (SeguroValidator.check(seguro) && DniValidator.validateDNI(seguro.nif)) {
            val ids = seguroRepository.findAll()
            if (ids.find{ seguro.idSeguro == it.idSeguro } == null ) return seguroRepository.save(seguro)
            else throw ValidationException("Este seguro ya existe")
        }
        else throw ValidationException("Los datos del seguro están mal")
    }

    fun updateSeguro(id:Int, seguro: Seguro):Seguro {
        if (SeguroValidator.check(seguro) && DniValidator.validateDNI(seguro.nif)) {
            val ids = seguroRepository.findAll()
            seguro.idSeguro = id
            if (ids.find { seguro.idSeguro == it.idSeguro } != null) return seguroRepository.save(seguro)
            else throw ValidationException("El id del seguro no existe")
        }
        else throw ValidationException("Los datos del seguro están mal")
    }

    fun deleteSeguro(id: Int):String {
        val seguro = seguroRepository.findById(id)
        if(seguro.getOrNull() != null) {
            seguroRepository.deleteById(id)
            return "Se ha eliminao"
        } else {
            throw ValidationException("No se ha podido eliminar el seguro")
        }
    }

}