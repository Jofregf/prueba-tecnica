# Prueba Técnica

## Descripción
El proyecto se realizó con el fin de cumplir con una prueba técnica. El mismo permite crear un producto en una base de 
datos H2, actualizar, borrar, buscar ese producto por id o por nombre, y además permite ordenar los productos según el 
precio.

## Requisitos
* Java JDK 17 o superior
* Maven
* Spring Boot

## Construcción y Ejecución
1. Clona el repositorio
```
   git clone https://github.com/Jofregf/prueba-tecnica.git
```
2. Navegue al directorio del proyecto.
3. Si tienes maven instalado ejecuta
```
mvn clean install
```
4. Ejecuta la aplicación
```bash
java -jar target/pt-paygoal-0.0.1.jar
```
5. En caso de que no tengas maven, abre la aplicación con el IDE y recarga las dependencias ubicadas en el 
archivo pom.xml en caso de que el IDE no lo haga automáticamente. Ve hasta el archivo utilizado como punto de entrada de
la aplicación, en este caso llamado PtPaygoalApplication, y ejecutalo.
6. Con la aplicación corriendo realiza las pruebas ubicadas en la documentación de swagger o la de postman según 
tu preferencia.

## Documentación Swagger
La documentación Swagger para esta aplicación está disponible [aquí](http://localhost:8080/doc/swagger-ui/index.html#/)

## Documentación Postman
La documentación Postman para esta aplicación está disponible [aquí](https://documenter.getpostman.com/view/18289711/2s9YyvC1e8)

