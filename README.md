# PitagorasAPI

Dado a que mi cédula termina en 727; Desarrollé la variante B del examen, la cual abarca tema de inscripciones a cursos

# Endpoints

## Obtener inscripciones

### Petición:
```http request
GET /inscripciones
HOST: http://localhost:8080/PitagorasAPI_war_exploded
```

### Respuesta:

```json
[
  {
    "id": "number",
    "estudiante": "string",
    "documento": "string",
    "carrera": "string",
    "asignatura": "string",
    "semestre": "string",
    "fechaInscripcion": "string",
    "estado": "string",
    "creditos": "number",
    "prioridad": "number",
    "promedioAcumulado": "number"
  }
]
```

## Obtener inscripciones por carrera

### Petición:
```http request
GET /inscripciones/carrera?nombre=nombre_carrera
HOST: http://localhost:8080/PitagorasAPI_war_exploded
```

### Respuesta:

```json
[
  {
    "id": "number",
    "estudiante": "string",
    "documento": "string",
    "carrera": "string",
    "asignatura": "string",
    "semestre": "string",
    "fechaInscripcion": "string",
    "estado": "string",
    "creditos": "number",
    "prioridad": "number",
    "promedioAcumulado": "number"
  }
]
```
o en caso de error:
```json 
"string"
```

## Obtener inscripciones ordenadas por prioridad

### Petición:
```http request
GET /inscripciones/priorizadas
HOST: http://localhost:8080/PitagorasAPI_war_exploded
```

### Respuesta:

```json
[
  {
    "id": "number",
    "estudiante": "string",
    "documento": "string",
    "carrera": "string",
    "asignatura": "string",
    "semestre": "string",
    "fechaInscripcion": "string",
    "estado": "string",
    "creditos": "number",
    "prioridad": "number",
    "promedioAcumulado": "number"
  }
]
```

## Guardar inscripcion

### Petición:
```http request
POST /inscripciones/priorizadas
HOST: http://localhost:8080/PitagorasAPI_war_exploded
Content-Type: application/json
{
    "estudiante": "string",
    "documento": "string",
    "carrera": "string",
    "asignatura": "string",
    "semestre": "string",
    "fechaInscripcion": "string",
    "estado": "string",
    "creditos": "number",
    "prioridad": "number",
    "promedioAcumulado": "number"
}
```

### Respuesta:

```json
{
    "id": "number",
    "estudiante": "string",
    "documento": "string",
    "carrera": "string",
    "asignatura": "string",
    "semestre": "string",
    "fechaInscripcion": "string",
    "estado": "string",
    "creditos": "number",
    "prioridad": "number",
    "promedioAcumulado": "number"
}
```
o en caso de error:
```json 
"string"
```