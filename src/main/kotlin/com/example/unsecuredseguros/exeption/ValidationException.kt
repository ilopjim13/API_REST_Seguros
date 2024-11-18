package com.example.unsecuredseguros.exeption

class ValidationException(mensaje: String) :Exception("ERROR en la validación (400): $mensaje")