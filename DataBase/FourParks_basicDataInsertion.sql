INSERT INTO ciudad VALUES (default,"Cali",-76.5225,3.43722, NOW());
INSERT INTO ciudad VALUES (default,"Medellín",-75.56359,6.25184, NOW());
INSERT INTO ciudad VALUES (default,"Bogotá",-74.0467,4.6569, NOW());
INSERT INTO ciudad VALUES (default,"popo",-76.5225,3.43722, NOW());

INSERT INTO tipo_parqueadero VALUES (default, "descubierto",NOW());
INSERT INTO tipo_parqueadero VALUES (default, "cubierto",NOW());
INSERT INTO tipo_parqueadero VALUES (default, "Semicubierto",NOW());

INSERT INTO Tipo_usuario (Tipo) VALUES ('cliente');
INSERT INTO Tipo_usuario (Tipo) VALUES ('administrador');
INSERT INTO Tipo_usuario (Tipo) VALUES ('gerente');

INSERT INTO usuario VALUES (default, "pedro","identificacion","contraseña","correo@correo.co",1,"usuario1",NOW(),0,"123456",1);

INSERT INTO parqueadero VALUES (default,"parqueadero 1", 10,10,10,3,1,-76.6225,3.33722,5,5,5,now());

INSERT INTO Tipo_usuario_usuario (Tipo_usuario_fk, Usuario_fk) VALUES 
((SELECT Id FROM Tipo_usuario WHERE Tipo = 'cliente'), 1),
((SELECT Id FROM Tipo_usuario WHERE Tipo = 'gerente'), 1);

-- Insertar parqueadero cubierto
INSERT INTO Parqueadero (Nombre, Cupo_moto_total, Cupo_carro_total, Cupo_bici_total, Ciudad_fk, Tipo_fk, Longitud, Latitud, Cupo_uti_moto, Cupo_uti_carro, Cupo_uti_bici)
VALUES ('Parqueadero Cubierto', 100, 50, 20, 1, 1, -74.000001, 4.500001, 0, 0, 0);

-- Insertar parqueadero no cubierto
INSERT INTO Parqueadero (Nombre, Cupo_moto_total, Cupo_carro_total, Cupo_bici_total, Ciudad_fk, Tipo_fk, Longitud, Latitud, Cupo_uti_moto, Cupo_uti_carro, Cupo_uti_bici)
VALUES ('Parqueadero No Cubierto', 150, 75, 30, 2, 2, -74.000002, 4.500002, 0, 0, 0);

-- Cambiar el nombre del primer parqueadero a "Parqueadero 1"
UPDATE Parqueadero SET Nombre = 'Parqueadero 1' WHERE Id = 1;

-- Cambiar el nombre del segundo parqueadero a "Parqueadero 2"
UPDATE Parqueadero SET Nombre = 'Parqueadero 2' WHERE Id = 2;

-- Insertar parqueadero con nombre específico 1 en la ciudad 1
INSERT INTO Parqueadero (Nombre, Cupo_moto_total, Cupo_carro_total, Cupo_bici_total, Ciudad_fk, Tipo_fk, Longitud, Latitud, Cupo_uti_moto, Cupo_uti_carro, Cupo_uti_bici)
VALUES ('Parqueadero Exclusivo', 80, 40, 15, 1, 1, -74.000003, 4.500003, 0, 0, 0);

-- Insertar parqueadero con nombre específico 2 en la ciudad 1
INSERT INTO Parqueadero (Nombre, Cupo_moto_total, Cupo_carro_total, Cupo_bici_total, Ciudad_fk, Tipo_fk, Longitud, Latitud, Cupo_uti_moto, Cupo_uti_carro, Cupo_uti_bici)
VALUES ('Parqueadero VIP', 120, 60, 25, 1, 2, -74.000004, 4.500004, 0, 0, 0);

INSERT INTO Vehiculo (Tipo) VALUES ('Carro');
INSERT INTO Vehiculo (Tipo) VALUES ('Moto');
INSERT INTO Vehiculo (Tipo) VALUES ('Bicicleta');

INSERT INTO Tarifa (Valor_ordinario, Parqueadero_fk, Vehiculo_fk, Valor_mora) 
VALUES (100, 1, 1, 200);  -- Carro

INSERT INTO Tarifa (Valor_ordinario, Parqueadero_fk, Vehiculo_fk, Valor_mora) 
VALUES (50, 1, 2, 100);  -- Moto

INSERT INTO Tarifa (Valor_ordinario, Parqueadero_fk, Vehiculo_fk, Valor_mora) 
VALUES (20, 1, 3, 40);  -- Bicicleta