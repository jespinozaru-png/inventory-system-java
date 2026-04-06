# Inventory System Java

Sistema de gestión de inventario desarrollado en Java como proyecto 
de aprendizaje progresivo. Evoluciona de CLI con JDBC hasta API REST 
con Spring Boot a lo largo de 3 meses.

## Tecnologías

- Java 17
- Maven 3.x
- MySQL 8.x
- JUnit 5.10
- JDBC (sin ORM)

## Estado actual — Mes 1 completado

Sistema CLI funcional conectado a MySQL con:
- CRUD completo de productos persistido en base de datos
- Gestión de categorías con tipos controlados por Enum
- Exportación e importación de datos en formato CSV
- Historial de operaciones con Stack implementado desde cero
- 15 tests unitarios pasando sobre la capa de servicio

## Estructura del proyecto

src/main/java/com/tuapellido/inventory/
├── model/       → Producto, Categoria, TipoCategoria (Enum)
├── service/     → ProductoService, CategoriaService
├── repository/  → Repositorio (interfaz), implementaciones JDBC y Memoria
├── exception/   → ProductoNoEncontradoException, DatoInvalidoException
├── util/        → DatabaseConnection, CsvUtil, Stack, Queue, AlgoritmosEjercicio
└── main/        → Main (menú CLI con 9 opciones)
sql/
└── schema.sql   → Script de creación de base de datos y datos de prueba

## Configuración y ejecución

1. Clona el repositorio
2. Copia `src/main/resources/config.properties.example` 
   como `src/main/resources/config.properties`
3. Configura tus credenciales de MySQL en config.properties
4. Ejecuta el script `sql/schema.sql` en MySQL Workbench
5. Ejecuta `Main.java` desde NetBeans

## Ejecutar los tests

Clic derecho en el proyecto → Test en NetBeans,
o desde terminal: `mvn test`

## Opciones del menú

1. Listar todos los productos
2. Buscar producto por nombre
3. Agregar producto
4. Eliminar producto
5. Exportar inventario a CSV
6. Importar productos desde CSV
7. Ver últimas 5 operaciones
8. Buscar producto por ID
9. Listar categorías disponibles

