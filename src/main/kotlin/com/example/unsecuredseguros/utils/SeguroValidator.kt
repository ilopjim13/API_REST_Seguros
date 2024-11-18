package com.example.unsecuredseguros.utils

import com.example.unsecuredseguros.exeption.ValidationException
import com.example.unsecuredseguros.model.Seguro

object SeguroValidator {

    /**
     * Los campos nif, nombre, ape1 y sexo no pueden ser null
     * Los campos edad y num_hijos deben ser > 0
     */
    fun checkFieldsNotNull(seguro: Seguro) :Boolean {
        return if (seguro.nif.isBlank()
                || seguro.nombre.isBlank()
                || seguro.ape1.isBlank()
                || seguro.edad < 0
                || seguro.numHijos <= 0
                || seguro.sexo.isBlank()) throw ValidationException("No puede haber elementos vacíos en el seguro")
        else true
    }

    /**
     * Si el campo embarazada es true, el campo sexo debe ser "Mujer".
     */
    fun checkEmbarazada(seguro: Seguro) : Boolean {
        return if (seguro.embarazada && seguro.sexo.lowercase() != "mujer") throw ValidationException("El campo embarazada está mal")
        else true
    }

    /**
     * Si edad está entre 0 y 17 años, deberá indicar que "No es posible ser menor de edad para hacer un seguro"
     */
    fun checkMenorEdad(seguro: Seguro) : Boolean{
        return if (seguro.edad in 0..17) throw ValidationException("No es posible ser menor de edad para hacer un seguro")
        else true
    }

    /**
     * Si el campo casado es false, el campo numHijos debe ser igual a 0.
     */
    fun checkNumHijos(seguro: Seguro) : Boolean {
        return if(!seguro.casado && seguro.numHijos > 0) throw ValidationException("El campo casado está mal")
        else true
    }

    fun check(seguro: Seguro):Boolean {
        return checkEmbarazada(seguro) && checkFieldsNotNull(seguro) && checkNumHijos(seguro) && checkMenorEdad(seguro)
    }

}