## Servicio de Reservas de Hotel 🏨

Este es un sistema de reservas para un hotel, desarrollado en Java usando SpringBoot, JPA, Swagger, Spring Security y Junit para realizar pruebas unitarias.

## Requisitos 📋

- Java 11 o superior
- Gradle
- Base de datos MySQL
- Insomnia (o cualquier otra herramienta de prueba de API REST)

## Instalación 🚀

1. Clonar el repositorio: `git clone https://github.com/Pipe1098/Api-Reservas-Hotel.git`
2. Abra el proyecto con su IDE preferido (Eclipse, IntelliJ, etc.).
3. Configure el archivo application.properties en la ruta src/main/resources para conectarse a la base de datos H2 en memoria. Puede dejar la configuración por defecto o cambiarla según sus necesidades.
4. Ejecute el comando gradle build para descargar las dependencias del proyecto y compilarlo.
5. Ejecute el comando gradle bootRun para iniciar la aplicación.

## Uso 🛠️

### La aplicación está configurada para ejecutarse en http://localhost:8080.
Pudes probar la API mediante el siguiente link: 

- https://api-reservas-hotel-production.up.railway.app/swagger-ui/index.html#/

### Usando los siguientes datos de autenticación:
Perfil 1: solo lectura (peticiones GET)

- Usuario: user

- Contraseña: user123

Perfil 2: todas las (peticiones GET, POST, PUT, DELETE)

- Usuario: admin

- Contraseña: admin123

Tambien puede probar la API utilizando una herramienta como Insomnia. Estos son algunos ejemplos de solicitudes que puede realizar:

## 🧑‍💼 Crear clientes 
- POST /URL: http://localhost:8080/api/v1/cliente: registra un nuevo cliente en el sistema.
- Cuerpo de la solicitud /json: 
```json
{
    "cedula":"cedula",
    "nombre":"nombre",
    "apellido":"apellido",
    "direccion":"direccion",
    "edad":"edad",
    "correoElectronico":"correoElectronico",
}
```
-Ejemplo de respuesta exitosa:
```json
{
    "id": 1,
    "cedula": "123456789",
    "nombre": "Juan",
    "apellido": "Pérez",
    "direccion": "Av. 10 de Agosto N24-17 y Veintimilla",
    "edad": 35,
    "correoElectronico": "juan.perez@example.com"
}
```
## 🛏️Crear habitaciones
- POST /URL: http://localhost:8080/api/v1/habitacion: registra una nueva habitación en el sistema.
- Cuerpo de la solicitud /json: 
``` json
{
"id":"id",
"tipo":"tipo",
"precioBase":"precioBase"
}
```
-Ejemplo de respuesta exitosa:
``` json
{
    "id": 1,
    "tipo": "Premium",
    "precioBase": 120.0
}
```
## 📅Crear reservas
- POST /URL: http://localhost:8080/api/v1/reservas/{fecha}/{id}/{cedula} crea una nueva reserva en el sistema, dada una fecha, un id de habitacion y la cedula del cliente que desea reservar.

-Ejemplo de la solicitud
``` json
{
    "fecha": "2023-04-20",
    "idHabitacion": 1,
    "cedulaCliente": "123456789"
}
```
-Ejemplo de respuesta exitosa:
``` json
{
    "id": 1,
    "fecha": "2023-04-20",
    "habitacion": {
        "id": 1,
        "tipo": "Premium",
        "precioBase": 120.0
    },
    "cliente": {
        "id": 1,
        "cedula": "123456789",
        "nombre": "Juan",
        "apellido": "Pérez",
        "direccion": "Av. 10 de Agosto N24-17 y Veintimilla",
        "edad": 35,
        "correoElectronico": "juan.perez@example.com"
    }
}
```
## 🛏️Ver habitaciones disponibles
- POST /URL: http://localhost:8080/api/v1/reservas/disponibles/{fecha} muestra las habitaciones disponibles en la fecha indicada.

-Ejemplo de respuesta exitosa:
``` json
[
    {
        "id": 1,
        "tipo": "Premium",
        "precioBase": 120.0
    },
    {
        "id": 2,
        "tipo": "Estándar",
        "precioBase": 80.0
    }
]
```
## 💰Ver habitaciones disponibles de tipo premium
- POST /URL: http://localhost:8080/api/v1/reservas/disponibles/premium/{fecha} similar a la anterior peticion pero solo muestra las habitaciones de tipo premium disponibles en la fecha indicada.
## 🏠 Ver habitaciones disponibles de tipo estandar
- POST /URL: http://localhost:8080/api/v1/reservas/disponibles/estandar/{fecha} similar a la anterior peticion pero solo muestra las habitaciones de tipo estandar disponibles en la fecha indicada.
 ## 📝Ver reservas por cliente
- GET  /URL: http://localhost:8080/api/v1/reservas/{cedula} muestra todas las reservas del cliente que tiene la celula indicada en el parametro.

-Ejemplo de respuesta exitosa:
``` json
[
    {
        "id": 1,
        "fechaInicio": "2023-05-01",
        "fechaFin": "2023-05-03",
        "habitacion": {
            "id": 1,
            "tipo": "Premium",
            "precioBase": 200000
        },
        "cliente": {
            "cedula": "12345678",
            "nombre": "Juan",
            "apellido": "Pérez",
            "direccion": "Carrera 10 # 25-45",
            "edad": 35,
            "correoElectronico": "juan.perez@gmail.com"
        }
    },
    {
        "id": 2,
        "fechaInicio": "2023-05-05",
        "fechaFin": "2023-05-06",
        "habitacion": {
            "id": 2,
            "tipo": "Estándar",
            "precioBase": 100000
        },
        "cliente": {
            "cedula": "12345678",
            "nombre": "Juan",
            "apellido": "Pérez",
            "direccion": "Carrera 10 # 25-45",
            "edad": 35,
            "correoElectronico": "juan.perez@gmail.com"
        }
    }
]
```
## 📊📉 Diagrama de clases:
![clase_cliente drawio (1)](https://user-images.githubusercontent.com/90018701/234339097-3c0afdf9-c4a6-4a4b-97cb-039fa0742b92.png)
![clase_habitacion drawio](https://user-images.githubusercontent.com/90018701/234339350-0a294546-1c80-4ae3-8857-cba560715356.png)
![clase_reserva drawio](https://user-images.githubusercontent.com/90018701/234339388-d8bcd887-c227-485a-acb8-27e75d95cdfa.png)

## 🤝Contribuir
Si deseas contribuir al proyecto, por favor sigue los siguientes pasos:
1. Haz un fork del repositorio
2. Crea una nueva rama con la funcionalidad que deseas agregar
3. Realiza tus cambios
4. Haz un pull request hacia la rama principal del repositorio
## 📃Licencia
Este proyecto está bajo la Licencia MIT - mira el archivo [LICENSE.md](LICENSE.md) para más detalles.
