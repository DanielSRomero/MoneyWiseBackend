CREATE TABLE User (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100),
                          userName VARCHAR(100),
                          email VARCHAR(100),
                          password VARCHAR(255),
                          phone VARCHAR(20),
                          token VARCHAR(255)
);ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tabla de usuarios';

INSERT INTO User (id, name, userName, email, password, phone, token) VALUES (1,'Juan Pérez', 'JuanP', 'juan.perez@example.com', 'pass1234', '5551234567', ''),
                                                                            (2,'María López', 'MariaL', 'maria.lopez@example.com', 'mariaPass', '5559876543', ''),
                                                                            (3,'Carlos Gómez', 'Carlitos', 'carlos.gomez@example.com', 'carlosPass', '5552345678', ''),
                                                                            (4,'Ana Torres', 'AnaT', 'ana.torres@example.com', 'ana123', '5558765432', ''),
                                                                            (5,'Pedro Ramírez', 'Lascaras', 'pedro.ramirez@example.com', 'pedroPass', '5553456789', ''),
                                                                            (6,'Lucía Méndez', 'Luh', 'lucia.mendez@example.com', 'luciaPass', '5557654321', ''),
                                                                            (7,'José Fernández', 'Erjose', 'jose.fernandez@example.com', 'josePass', '5556543210', ''),
                                                                            (8,'Elena Castillo', 'Ele', 'elena.castillo@example.com', 'elenaPass', '5554321098', ''),
                                                                            (9,'Ricardo Herrera', 'Richard', 'ricardo.herrera@example.com', 'ricardoPass', '5552109876', ''),
                                                                            (10,'Sofía Duarte', 'La sofi', 'sofia.duarte@example.com', 'sofiaPass', '5558901234', '');