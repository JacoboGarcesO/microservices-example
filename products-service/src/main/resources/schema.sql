CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(200) NOT NULL,
    stock INT NOT NULL,
    price NUMERIC NOT NULL
);

INSERT INTO products(name, description, stock, price) VALUES ('Mouse ergonomico', 'Mouse inalámbrico con diseño ergonómico', 10, 45.50);
INSERT INTO products(name, description, stock, price)
VALUES ('Teclado mecánico', 'Teclado gaming con switches Cherry MX Red', 15, 89.99);
INSERT INTO products(name, description, stock, price)
VALUES ('Monitor 27"', 'Monitor LED IPS 2K con 144Hz', 8, 299.99);
INSERT INTO products(name, description, stock, price)
VALUES ('Webcam HD', 'Cámara web 1080p con micrófono incorporado', 20, 59.90);
INSERT INTO products(name, description, stock, price)
VALUES ('SSD 1TB', 'Disco de estado sólido NVME de alta velocidad', 12, 110.00);
INSERT INTO products(name, description, stock, price)
VALUES ('Tarjeta gráfica', 'GPU gaming de última generación 12GB VRAM', 5, 750.00);
INSERT INTO products(name, description, stock, price)
VALUES ('Auriculares', 'Auriculares gaming con sonido envolvente 7.1', 25, 75.50);
INSERT INTO products(name, description, stock, price)
VALUES ('RAM 16GB', 'Memoria RAM DDR4 3200MHz', 30, 85.00);
INSERT INTO products(name, description, stock, price)
VALUES ('Placa base', 'Placa base ATX compatible con procesadores última gen', 7, 150.00);
INSERT INTO products(name, description, stock, price)
VALUES ('Ventilador CPU', 'Disipador de CPU con RGB y alto rendimiento', 18, 49.99);