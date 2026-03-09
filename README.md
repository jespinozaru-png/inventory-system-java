\# Inventory System Java



Sistema de gestión de inventario desarrollado en Java como proyecto de 

aprendizaje progresivo. Evoluciona de CLI con datos en memoria 

hasta API REST con Spring Boot a lo largo de 3 meses.



\## Tecnologías



\- Java 17

\- Maven 3.x

\- MySQL 8.x

\- JUnit 5.10



\## Estado actual



Semana 1 — Sistema CLI con datos en memoria. 

Estructura de paquetes y modelo de dominio establecidos.



\## Estructura del proyecto

```

src/main/java/com/tuapellido/inventory/

├── model/       → Clases del dominio (Producto, Categoria)

├── service/     → Lógica de negocio (ProductoService)

├── repository/  → Interfaces y repositorio en memoria

├── exception/   → Excepciones personalizadas del dominio

├── util/        → Algoritmos y utilidades

└── main/        → Punto de entrada con menú CLI

```



\## Cómo ejecutarlo



1\. Clona el repositorio

2\. Abre con NetBeans o cualquier IDE con soporte Maven

3\. Ejecuta `Main.java`

4\. Interactúa con el menú en consola



\## Base de datos



MySQL con base de datos `inventario\_db`. 

Script de creación en `/sql/schema.sql`.

