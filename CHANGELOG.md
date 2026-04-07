# CHANGELOG



## Mes 1 — Sistema CLI con JDBC



### Semana 4

- Agregado TipoCategoria como Enum para control de valores de categoría

- Categoria actualizada para usar TipoCategoria internamente

- CategoriaRepository y CategoriaService implementados

- Optional aplicado consistentemente en toda la base de código

- Opción de buscar por ID y listar categorías agregadas al menú

- 15 tests unitarios pasando sobre ProductoService

- Auditoría de código: refactorizadas 3 clases con problemas de responsabilidad



### Semana 3

- ProductoRepositoryJDBC implementado con PreparedStatement

- ProductoService refactorizado para depender de interfaz Repositorio

- Credenciales protegidas mediante config.properties en .gitignore

- DatabaseConnection con patrón Singleton

- Módulo CSV con exportación e importación funcionales

- Stack y Queue genéricos implementados desde cero

- Historial de operaciones en menú usando Stack



### Semana 2

- Clase Inventario con HashMap y ArrayList para acceso dual eficiente

- DatoInvalidoException agregada al dominio

- Producto refactorizado para usar excepciones del dominio

- 10 tests unitarios con JUnit 5 sobre ProductoService

- BubbleSort y SelectionSort implementados en AlgoritmosEjercicio



### Semana 1

- Estructura Maven del proyecto establecida

- Modelo de dominio: Producto, Categoria con validaciones

- Interfaz Repositorio con genéricos

- ProductoRepositoryMemoria con ArrayList

- ProductoService desacoplado del repositorio

- Menú CLI básico funcional

\- Base de datos inventario\_db con tablas productos y categorias

