Crear la base de datos y las tablas

-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS inventario_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE inventario_db;

-- Tabla de categorías
CREATE TABLE categorias (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de productos
CREATE TABLE productos (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    nombre         VARCHAR(200) NOT NULL,
    precio         DECIMAL(10, 2) NOT NULL CHECK (precio >= 0),
    cantidad       INT NOT NULL DEFAULT 0 CHECK (cantidad >= 0),
    categoria_id   INT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_producto_categoria 
        FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

Insertar datos de prueba

-- Categorías
INSERT INTO categorias (nombre, descripcion) VALUES
('Electrónica', 'Dispositivos electrónicos y accesorios'),
('Herramientas', 'Herramientas manuales y eléctricas'),
('Oficina', 'Artículos de oficina y papelería'),
('Limpieza', 'Productos de limpieza e higiene');

-- Productos
INSERT INTO productos (nombre, precio, cantidad, categoria_id) VALUES
('Laptop Dell Inspiron 15', 2599.99, 10, 1),
('Mouse inalámbrico Logitech', 89.90, 45, 1),
('Teclado mecánico', 199.50, 20, 1),
('Taladro Bosch 500W', 349.00, 15, 2),
('Destornillador set 12 piezas', 45.00, 60, 2),
('Resma de papel A4', 18.50, 200, 3),
('Grapadora metálica', 25.00, 35, 3),
('Desinfectante multiusos 1L', 12.00, 150, 4),
('Escoba de cerda', 22.00, 40, 4),
('Cable HDMI 2m', 35.00, 75, 1);


consultas

-- 1. Ver todos los productos
SELECT * FROM productos;

-- 2. Ver productos con su categoría (JOIN)
SELECT p.nombre, p.precio, p.cantidad, c.nombre AS categoria
FROM productos p
INNER JOIN categorias c ON p.categoria_id = c.id;

-- 3. Productos ordenados por precio de mayor a menor
SELECT nombre, precio FROM productos
ORDER BY precio DESC;

-- 4. Los 3 productos más baratos
SELECT nombre, precio FROM productos
ORDER BY precio ASC
LIMIT 3;

-- 5. Productos de la categoría Electrónica
SELECT p.nombre, p.precio
FROM productos p
INNER JOIN categorias c ON p.categoria_id = c.id
WHERE c.nombre = 'Electrónica';

-- 6. Cuántos productos hay por categoría
SELECT c.nombre AS categoria, COUNT(p.id) AS total_productos
FROM categorias c
LEFT JOIN productos p ON c.id = p.categoria_id
GROUP BY c.id, c.nombre;