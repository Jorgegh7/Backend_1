# LearningPlatformValidation

API REST para la gestión de una plataforma de aprendizaje en línea, desarrollada con Spring Boot y Oracle Cloud Database.

## Descripción

Este proyecto implementa un sistema de gestión académica que permite administrar usuarios (profesores y estudiantes), cursos, inscripciones y evaluaciones. Incluye validaciones de negocio como verificación de roles, manejo de excepciones y persistencia en Oracle Cloud.

## Tecnologías

- **Java 21**
- **Spring Boot 4.0.6**
- **Spring Data JPA** - Persistencia y manejo de entidades
- **Oracle Cloud Autonomous Database 19c** - Base de datos en la nube
- **Lombok** - Reducción de código boilerplate
- **Jakarta Validation** - Validaciones de datos (@NotBlank, @Email, @Min, @Max, @FutureOrPresent)
- **Maven** - Gestión de dependencias

## Estructura del Proyecto

```
src/main/java/com/duoc/LearningPlatformValidation/
├── controller/
│   ├── UsuarioController.java
│   ├── CursoController.java
│   ├── InscripcionController.java
│   └── EvaluacionController.java
├── model/
│   ├── Usuario.java
│   ├── Curso.java
│   ├── Inscripcion.java
│   ├── Evaluacion.java
│   └── Rol.java (enum)
├── repository/
│   ├── UsuarioRepository.java
│   ├── CursoRepository.java
│   ├── InscripcionRepository.java
│   └── EvaluacionRepository.java
├── service/
│   ├── contrato/
│   │   ├── UsuarioService.java
│   │   ├── CursoService.java
│   │   ├── InscripcionService.java
│   │   └── EvaluacionService.java
│   └── impl/
│       ├── UsuarioServiceImpl.java
│       ├── CursoServiceImpl.java
│       ├── InscripcionImpl.java
│       └── EvaluacionImpl.java
└── LearningPlatformValidationApplication.java
```

## Modelos y Relaciones

### Usuario
| Campo       | Tipo   | Validaciones                  |
|-------------|--------|-------------------------------|
| id          | Long   | PK, autoincremental           |
| nombre      | String | @NotBlank, max 100 caracteres |
| correo      | String | @NotBlank, @Email, único      |
| contrasenia | String | @NotBlank, oculta en JSON     |
| rol         | Rol    | ESTUDIANTE o PROFESOR         |

### Curso
| Campo       | Tipo    | Validaciones                  |
|-------------|---------|-------------------------------|
| id          | Long    | PK, autoincremental           |
| nombre      | String  | @NotBlank, max 150 caracteres |
| descripcion | String  | @NotBlank, max 250 caracteres |
| profesor    | Usuario | @ManyToOne, FK a usuario      |

### Inscripcion
| Campo            | Tipo    | Validaciones               |
|------------------|---------|----------------------------|
| id               | Long    | PK, autoincremental        |
| curso            | Curso   | @ManyToOne, FK a curso     |
| estudiante       | Usuario | @ManyToOne, FK a usuario   |
| fechaInscripcion | Date    | Generada con @PrePersist   |

### Evaluacion
| Campo            | Tipo    | Validaciones                    |
|------------------|---------|---------------------------------|
| id               | Long    | PK, autoincremental             |
| curso            | Curso   | @ManyToOne, FK a curso          |
| nombre           | String  | @NotBlank, max 100 caracteres   |
| puntajeMaximo    | Integer | @Min(1), @Max(100)              |
| fechaAplicacion  | Date    | @FutureOrPresent                |

### Diagrama de Relaciones

```
Usuario (1) ──── (N) Curso         → Un profesor tiene muchos cursos
Usuario (1) ──── (N) Inscripcion   → Un estudiante tiene muchas inscripciones
Curso   (1) ──── (N) Inscripcion   → Un curso tiene muchas inscripciones
Curso   (1) ──── (N) Evaluacion    → Un curso tiene muchas evaluaciones
```

## Endpoints de la API

### Usuarios (`/api/learning-platform/usuarios`)

| Método | Ruta        | Descripción              |
|--------|-------------|--------------------------|
| GET    | /           | Listar todos los usuarios |
| GET    | /{id}       | Buscar usuario por ID     |
| POST   | /           | Crear usuario             |
| PUT    | /{id}       | Actualizar usuario        |
| DELETE | /{id}       | Eliminar usuario          |

### Cursos (`/api/learning-platform/cursos`)

| Método | Ruta        | Descripción                              |
|--------|-------------|------------------------------------------|
| GET    | /           | Listar todos los cursos                   |
| POST   | /           | Crear curso (valida rol PROFESOR)         |
| PUT    | /{id}       | Actualizar curso (valida rol PROFESOR)    |
| DELETE | /{id}       | Eliminar curso                            |

### Inscripciones (`/api/learning-platform/inscripciones`)

| Método | Ruta        | Descripción                                  |
|--------|-------------|----------------------------------------------|
| GET    | /{id}       | Listar inscripciones por curso                |
| POST   | /           | Crear inscripción (valida rol ESTUDIANTE)     |
| DELETE | /{id}       | Eliminar inscripción                          |

### Evaluaciones (`/api/learning-platform/evaluaciones`)

| Método | Ruta        | Descripción                        |
|--------|-------------|------------------------------------|
| GET    | /           | Listar todas las evaluaciones       |
| GET    | /{id}       | Listar evaluaciones por curso       |
| POST   | /           | Crear evaluación                    |
| PUT    | /{id}       | Actualizar evaluación               |

## Validaciones de Negocio

- Solo usuarios con rol **PROFESOR** pueden ser asignados a un curso.
- Solo usuarios con rol **ESTUDIANTE** pueden inscribirse en un curso.
- La fecha de inscripción se genera automáticamente al registrar.
- La fecha de evaluación debe ser presente o futura.
- El puntaje máximo debe estar entre 1 y 100.
- El correo debe ser único y tener formato válido.
- La contraseña se oculta en las respuestas JSON.

## Ejemplos de Body (JSON)

### Crear Usuario
```json
{
    "nombre": "Jorge Gallardo",
    "correo": "jorge@duoc.cl",
    "contrasenia": "password123",
    "rol": "PROFESOR"
}
```

### Crear Curso
```json
{
    "nombre": "Programación en Java",
    "descripcion": "Curso básico de Java",
    "profesor": { "id": 1 }
}
```

### Crear Inscripción
```json
{
    "curso": { "id": 1 },
    "estudiante": { "id": 3 }
}
```

### Crear Evaluación
```json
{
    "curso": { "id": 1 },
    "nombre": "Prueba 1",
    "puntajeMaximo": 100,
    "fechaAplicacion": "2026-06-15"
}
```

4. La API estará disponible en `http://localhost:8080`

