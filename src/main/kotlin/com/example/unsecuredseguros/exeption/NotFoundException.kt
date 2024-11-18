package com.example.unsecuredseguros.exeption

class NotFoundException(mensaje: String) :Exception("Not found exception (404): $mensaje")
