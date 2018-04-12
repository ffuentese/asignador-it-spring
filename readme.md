# spring-test-asignador
App web: Asignador de equipos informáticos programado en Java con Spring

Esta aplicación web permite ordenar los equipos informáticos (como computadores o de una empresa y luego poder asignarlos a empleados y rastrear dónde se encuentran dentro de la organización.

Requerimientos:

- Java 1.8
- Maven
- Apache Tomcat (generalmente Eclipse y Netbeans lo traen así que sólo hay que liberar el puerto 8080).
- Servidor MySQL u otro similar (ajustar datos en [application.properties](https://github.com/ffuentese/asignador-it-spring/blob/master/src/main/resources/application.properties))
- Navegador web actualizado.


1. Crear una base de datos y un usuario con privilegios para la aplicación 
2. Corregir datos en application.properties
3. Iniciar la aplicación (Spring genera la base de datos) con maven importándolo en Eclipse. 
4. Ejecutar en la base de datos el archivo [data.sql](https://github.com/ffuentese/asignador-it-spring/blob/master/src/main/resources/data.sql)
5. Entrar a la página (normalmente localhost:8080) y crear un usuario.
6. Loguearse y agregar datos (orden sugerido: marcas, tipos de equipo, modelos, sucursales, departamentos, empleados y equipos)

[Devices o Equipos](http://localhost:8080/devices) es la página más importante ya que desde ahí los equipos se asignan a los empleados de acuerdo a los datos ingresados. 
Sólo se puede asignar equipos cuando los demás datos se encuentran ingresados. En [Modelos o Models](http://localhost:8080/models) se puede manejar el stock de los equipos editando la tabla.

