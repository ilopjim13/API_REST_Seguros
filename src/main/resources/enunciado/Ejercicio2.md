
# Ejercicio 2: Relaciones entre Entidades - Seguro y Asistencia Médica

En este ejercicio, vamos a extender la funcionalidad de la aplicación creada en el **Ejercicio 1** para incluir una nueva entidad: **AsistenciaMedica**. También crearemos las relaciones correspondientes entre **Seguro** y **AsistenciaMedica**, donde un seguro puede tener múltiples asistencias médicas asociadas (relación **uno a muchos**).

## Parte 1: Definición de la Entidad AsistenciaMedica

### 1. Entidad AsistenciaMedica

Crea una clase de datos en Kotlin para representar la entidad **AsistenciaMedica** con los siguientes atributos:

```kotlin
data class AsistenciaMedica(
    val idAsistenciaMedica: Int,
    val seguro: Seguro, // Relación con Seguro
    val breveDescripcion: String,
    val lugar: String,
    val explicacion: String,
    val tipoAsistencia: String,
    val fecha: LocalDate,
    val hora: LocalTime,
    val importe: BigDecimal
)
```

**Modelo de la base de datos (Tabla asistencias_medicas):**

```sql
CREATE TABLE asistencias_medicas (
    id_asistencia_medica INTEGER PRIMARY KEY,
    id_seguro INTEGER NOT NULL,
    breve_descripcion VARCHAR(255) NOT NULL,
    lugar VARCHAR(255) NOT NULL,
    explicacion TEXT NOT NULL,
    tipo_asistencia VARCHAR(100) NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    importe DECIMAL(12, 2) NOT NULL CHECK (importe > 0),
    CONSTRAINT FK_Seguro FOREIGN KEY (id_seguro) REFERENCES seguros(id_seguro) ON DELETE CASCADE
);
```

---

## Parte 2: Relación entre Seguro y AsistenciaMedica

### 1. Relación Uno a Muchos

En la entidad `Seguro`, añade una lista de asistencias médicas:

```kotlin
data class Seguro(
    val idSeguro: Int,
    val nif: String,
    val nombre: String,
    val ape1: String,
    val ape2: String?,
    val edad: Int,
    val numHijos: Int,
    val fechaCreacion: Date,
    val sexo: String,
    val casado: Boolean,
    val embarazada: Boolean,
    val asistenciasMedicas: List<AsistenciaMedica> = listOf() // Relación
)
```

### 2. Controladores

Implementa un controlador **AsistenciaMedicaController** con las operaciones CRUD necesarias para gestionar asistencias médicas:

- **GET** `/asistencias`:  
  Devuelve todas las asistencias médicas registradas.

- **GET** `/asistencias/{id}`:  
  Devuelve una asistencia médica específica por su identificador `idAsistenciaMedica`.

- **POST** `/seguros/{idSeguro}/asistencias`:  
  Crea una nueva asistencia médica asociada a un seguro.

- **PUT** `/asistencias/{id}`:  
  Actualiza los datos de una asistencia médica existente.

- **DELETE** `/asistencias/{id}`:  
  Elimina una asistencia médica.

---

## Parte 3: Validaciones

- El campo `breveDescripcion` no puede estar vacío.
- El campo `lugar` no puede estar vacío.
- El campo `explicacion` no puede estar vacío.
- El campo `tipoAsistencia` no puede ser `null`.
- El campo `fecha` no puede ser `null`.
- El campo `hora` no puede ser `null`.
- El campo `importe`:
    - No puede ser `null`.
    - Debe tener hasta **10 dígitos enteros** y **2 decimales**.
    - Debe ser mayor que 0.

---

## Requisitos del Entorno

1. Actualiza el proyecto de **Spring Boot** con las nuevas entidades y relaciones.
2. Utiliza **JPA** para mapear las relaciones entre las entidades.
3. Configura validaciones utilizando anotaciones estándar como `@NotNull`, `@Size`, `@Min`, `@DecimalMin`, y otras según sea necesario.
4. Actualiza el archivo `application.properties` con la configuración de la base de datos.

---

## Entrega

1. Código de las entidades `Seguro` y `AsistenciaMedica`
2. Código de los controladores `SeguroController` y `AsistenciaMedicaController`.
3. Código de los services `SeguroService` y `AsistenciaMedicaService` con las validaciones y relaciones.
4. Pruebas funcionales que incluyan:
    - Creación de seguros y sus asistencias médicas asociadas.
    - Consulta y modificación de asistencias médicas.
    - Eliminación de asistencias médicas y seguros (asegurando la eliminación en cascada).

---

## SQL de Inserción de Datos de Prueba

```sql
-- Insertar seguros
INSERT INTO seguro (id_eguro, nif, nombre, ape1, ape2, edad, num_hijos, fecha_creacion, sexo, casado, embarazada)
VALUES
    (1, '12345678A', 'Juan', 'Pérez', 'García', 35, 2, '2024-11-01 10:00:00', 'Hombre', TRUE, FALSE),
    (2, '87654321B', 'María', 'López', NULL, 28, 1, '2024-10-20 14:30:00', 'Mujer', TRUE, TRUE);

-- Insertar asistencias médicas
INSERT INTO asistencias_medicas (id_asistencia_medica, id_seguro, breve_descripcion, lugar, explicacion, tipo_asistencia, fecha, hora, importe)
VALUES
    (1, 1, 'Consulta médica general', 'Madrid', 'Consulta por síntomas leves', 'Consulta', '2024-11-02', '09:30:00', 50.00),
    (2, 1, 'Urgencia médica', 'Barcelona', 'Dolor abdominal intenso', 'Urgencia', '2024-11-03', '12:15:00', 150.00),
    (3, 2, 'Revisión ginecológica', 'Sevilla', 'Control durante embarazo', 'Consulta', '2024-11-04', '10:00:00', 70.00);
```

Con esta ampliación, los alumnos podrán trabajar con relaciones entre entidades, validaciones avanzadas y operaciones CRUD complejas.
