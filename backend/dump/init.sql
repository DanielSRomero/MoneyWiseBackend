CREATE TABLE `User` (
    id INT AUTO_INCREMENT,
    userName VARCHAR(100) NOT NULL,
    password VARCHAR(255),
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    token VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE KEY (userName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tabla de usuarios';

INSERT INTO User (id, userName, password, name, email, phone, token) VALUES
(1, 'JuanP', 'bd94dcda26fccb4e68d6a31f9b5aac0b571ae266d822620e901ef7ebe3a11d4f', 'Juan Pérez', 'juan.perez@example.com', '5551234567', ''),
(2,'MariaL', 'f1fc54b2f4d2075409d5596c2299f2790dbebce8baf77218a6ad7b0839565975', 'María López', 'maria.lopez@example.com', '5559876543', ''),
(3,'Carlitos', 'e27c097877f25b799c7e3857fd7abcab5b11d95563512d749f15d6506bd2810c', 'Carlos Gómez', 'carlos.gomez@example.com', '5552345678', ''),
(4,'AnaT', 'e82827b00b2ca8620beb37f879778c082b292a52270390cff35b6fe3157f4e8b', 'Ana Torres', 'ana.torres@example.com', '5558765432', ''),
(5,'Lascaras', '303de18123f9c4113a700f0e0403228525af64264c85433a3cdfe12b7eb600e6', 'Pedro Ramírez', 'pedro.ramirez@example.com', '5553456789', ''),
(6,'Luh', '658b8e7eeecb19c79b032e758676bd37d01986e7abfc097138dbd7b7a874cb32', 'Lucía Méndez', 'lucia.mendez@example.com', '5557654321', ''),
(7,'Erjose', '24d19f24bf03d789893f14d6086b428b490d98a3122e61017e409c7c9dbba996', 'José Fernández', 'jose.fernandez@example.com', '5556543210', ''),
(8,'Ele', '9a1e215cb0b1fe5d327304278fc09a618e21d34c9f2af3ddebc6b507a3b28023', 'Elena Castillo', 'elena.castillo@example.com', '5554321098', ''),
(9,'Richard', 'cc818a8fe396ef6056267ebce61822f41f5875e5cab95971f78103ab4623e55d', 'Ricardo Herrera', 'ricardo.herrera@example.com', '5552109876', ''),
(10,'La sofi', 'af05cde477987f351f58007c99e02a12167ef83471ae98d32a2b4c578e95fe16', 'Sofía Duarte', 'sofia.duarte@example.com', '5558901234', '');
--'pass1234'
--'mariaPass'
--'carlosPass'
--'ana123'
--'pedroPass'
--'luciaPass'
--'josePass'
--'elenaPass'
--'ricardoPass'
--'sofiaPass
--bd94dcda26fccb4e68d6a31f9b5aac0b571ae266d822620e901ef7ebe3a11d4f
--f1fc54b2f4d2075409d5596c2299f2790dbebce8baf77218a6ad7b0839565975
--e27c097877f25b799c7e3857fd7abcab5b11d95563512d749f15d6506bd2810c
--e82827b00b2ca8620beb37f879778c082b292a52270390cff35b6fe3157f4e8b
--303de18123f9c4113a700f0e0403228525af64264c85433a3cdfe12b7eb600e6
--658b8e7eeecb19c79b032e758676bd37d01986e7abfc097138dbd7b7a874cb32
--24d19f24bf03d789893f14d6086b428b490d98a3122e61017e409c7c9dbba996
--9a1e215cb0b1fe5d327304278fc09a618e21d34c9f2af3ddebc6b507a3b28023
--cc818a8fe396ef6056267ebce61822f41f5875e5cab95971f78103ab4623e55d
--af05cde477987f351f58007c99e02a12167ef83471ae98d32a2b4c578e95fe16

CREATE TABLE Goals (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      userUserName VARCHAR(100),
                      name VARCHAR(100),
                      money INT,
                      year INT,
                      image VARCHAR(255),
                      FOREIGN KEY (userUserName) REFERENCES User(userName)
                              ON DELETE CASCADE
                              ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tabla de metas';

INSERT INTO Goals (id, userUserName, name, money, year, image) VALUES
(1, 'JuanP', 'Viaje a Europa', 5000, 2025, ''),
(2, 'JuanP', 'Comprar una laptop', 1500, 2026, ''),
(3, 'JuanP', 'Ahorrar para coche', 10000, 2026, ''),
(4, 'MariaL', 'Pagar deudas', 3000, 2026, ''),
(5, 'MariaL', 'Curso de programación', 800, 2026, ''),
(6, 'MariaL', 'Renovación de casa', 7000, 2025, ''),
(7, 'Carlitos', 'Inversión en negocio', 12000, 2026, ''),
(8, 'AnaT', 'Boda', 15000, 2025, ''),
(9, 'Lascaras', 'Fondo de emergencia', 5000, 2026, ''),
(10, 'Luh', 'Comprar bicicleta', 600, 2026, '');