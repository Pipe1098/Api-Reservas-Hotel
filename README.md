## Servicio de Reservas de Hotel 🏨

Este es un sistema de reservas para un hotel, desarrollado en Java utilizando JPA y Mockito para realizar pruebas unitarias.

## Requisitos 📋

- Java 11 o superior
- Gradle
- Base de datos H2
- Insomnia (o cualquier otra herramienta de prueba de API REST)

## Instalación 🚀

1. Clonar el repositorio: `git clone https://github.com/Pipe1098/JPA.git`
2. Abra el proyecto con su IDE preferido (Eclipse, IntelliJ, etc.).
3. Configure el archivo application.properties en la ruta src/main/resources para conectarse a la base de datos H2 en memoria. Puede dejar la configuración por defecto o cambiarla según sus necesidades.
4. Ejecute el comando gradle build para descargar las dependencias del proyecto y compilarlo.
5. Ejecute el comando gradle bootRun para iniciar la aplicación.

## Uso 🛠️

La aplicación está configurada para ejecutarse en http://localhost:8080.
Puede probar la API utilizando una herramienta como Insomnia. Estos son algunos ejemplos de solicitudes que puede realizar:

## 🧑‍💼 Crear clientes 
- POST /URL: http://localhost:8080/api/v1/cliente: registra un nuevo cliente en el sistema.
- Cuerpo de la solicitud /json: 
``` 
{
"cedula" = cedula,
"nombre" = "nombre",
"apellido" = "apellido",
"direccion" = "direccion",
"edad" = edad,
"correoElectronico" = "correoElectronico",
}
```
## 🛏️Crear habitaciones
- POST /URL: http://localhost:8080/api/v1/habitacion: registra una nueva habitación en el sistema.
- Cuerpo de la solicitud /json: 
``` 
{
"id" = id,
"tipo" = tipo,
"precioBase" = precioBase,
}
```
## 📅Crear reservas
- POST /URL: http://localhost:8080/api/v1/reservas/{fecha}/{id}/{cedula} crea una nueva reserva en el sistema, dada una fecha, un id de habitacion y la cedula del cliente que desea reservar.
## 🛏️Ver habitaciones disponibles
- POST /URL: http://localhost:8080/api/v1/reservas/disponibles/{fecha} muestra las habitaciones disponibles en la fecha indicada.
## 💰Ver habitaciones disponibles de tipo premium
- POST /URL: http://localhost:8080/api/v1/reservas/disponibles/premium/{fecha} muestra las habitaciones de tipo premium disponibles en la fecha indicada.
## 🏠 Ver habitaciones disponibles de tipo estandar
- POST /URL: http://localhost:8080/api/v1/reservas/disponibles/estandar/{fecha} muestra las habitaciones de tipo estandar disponibles en la fecha indicada.
 ## 📝Ver reservas por cliente
- GET  /URL: http://localhost:8080/api/v1/reservas/{cedula} muestra todas las reservas del cliente que tiene la celula indicada en el parametro.

## 🤝Contribuir
Si deseas contribuir al proyecto, por favor sigue los siguientes pasos:
1. Haz un fork del repositorio
2. Crea una nueva rama con la funcionalidad que deseas agregar
3. Realiza tus cambios
4. Haz un pull request hacia la rama principal del repositorio
## 📃Licencia
Este proyecto está bajo la Licencia MIT - mira el archivo [LICENSE.md](LICENSE.md) para más detalles.
