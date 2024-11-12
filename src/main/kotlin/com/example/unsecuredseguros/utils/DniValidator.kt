package com.example.unsecuredseguros.utils

object DniValidator {

    private val dni_format_regex = "^\\d{9}[A-Za-z]$";
    private val tabla_validez_dni = mapOf<Char, Int>(
        'T' to 0,
        'R' to 1,
        'W' to 2,
        'A' to 3,
        'G' to 4,
        'M' to 5,
        'Y' to 6,
        'F' to 7,
        'P' to 8,
        'D' to 9,
        'X' to 10,
        'B' to 11,
        'N' to 12,
        'J' to 13,
        'Z' to 14,
        'S' to 15,
        'Q' to 16,
        'V' to 17,
        'H' to 18,
        'L' to 19,
        'C' to 20,
        'K' to 21,
        'E' to 22,
    )

    /**
     * Comprueba que el formato sea 8 numeros seguidos de una letra
     */
    fun validateDNI(dni: String) : Boolean {
        return checkFormatDni(dni) && checkValidDni(dni)
    }

    private fun checkFormatDni(dni: String) : Boolean {
        return dni_format_regex.toRegex(RegexOption.IGNORE_CASE).matches(dni.trim())
    }

    private fun checkValidDni(dni: String) : Boolean{
        val dniTemp = dni.trim()
        val nums: Int = dniTemp.substring(0, 8).toInt()
        val letra: Char = dniTemp.last().uppercaseChar()
        return tabla_validez_dni[letra] == nums % 23
    }



}