package com.example.unsecuredseguros.utils

import com.example.unsecuredseguros.model.Seguro

object SeguroValidator {

    /**
     * Los campos nif, nombre, ape1 y sexo no pueden ser null
     * Los campos edad y num_hijos deben ser > 0
     */
    fun checkFieldsNotNull(seguro: Seguro) :Boolean {
        if (seguro.nif.isNullOrEmpty()
            || seguro.nombre.isNullOrEmpty()
            || seguro.ape1.isNullOrEmpty()
            || seguro.edad > 0
            || seguro.numHijos >= 0
            || seguro.sexo.isNullOrEmpty())  return false
        return true
    }

    /**
     * Si el campo embarazada es true, el campo sexo debe ser "Mujer".
     */
    fun checkEmbarazada(seguro: Seguro) : Boolean {
        if(seguro.embarazada && seguro.sexo.lowercase() != "mujer") return false
        return true
    }

    /**
     * Si edad está entre 0 y 17 años, deberá indicar que "No es posible ser menor de edad para hacer un seguro"
     */
    fun checkMenorEdad(seguro: Seguro) : Boolean{
        if (seguro.edad in 0..17) return false
        return true
    }

    /**
     * Si el campo casado es false, el campo numHijos debe ser igual a 0.
     */
    fun checkNumHijos(seguro: Seguro) : Boolean {
        if (!seguro.casado && seguro.numHijos > 0) return false
        return true
    }

}