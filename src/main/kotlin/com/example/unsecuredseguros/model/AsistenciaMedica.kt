package com.example.unsecuredseguros.model

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.time.LocalDate
import java.time.LocalTime

data class AsistenciaMedica(
    @Column(name = "id_asistencia")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idAsistenciaMedica: Int,

    @OneToMany
    val seguro: Seguro, // Relación con Seguro

    @Column(nullable = false)
    val breveDescripcion: String,
    @Column(nullable = false)
    val lugar: String,
    @Column(nullable = false)
    val explicacion: String,
    @Column(nullable = false)
    val tipoAsistencia: String,
    @Column(nullable = false)
    val fecha: LocalDate,
    @Column(nullable = false)
    val hora: LocalTime,
    @Column(nullable = false)
    val importe: Double
)