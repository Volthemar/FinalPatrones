drop database parqueadero;
create database parqueadero;
use parqueadero;

-- Creación de la tabla Usuario
CREATE TABLE Usuario (
    Id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL,
    Identificacion VARCHAR(50) NOT NULL,
    Contrasena VARCHAR(50) NOT NULL,
    Correo VARCHAR(50) NOT NULL,
    Estado BOOLEAN NOT NULL DEFAULT TRUE,
    Usuario VARCHAR(50) NOT NULL,
    Fecha_creacion DATE DEFAULT (CURRENT_DATE)
);

-- Creación de la tabla Tarjeta_credito
CREATE TABLE Tarjeta_credito (
    Id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Numero VARCHAR(50) NOT NULL,
    Nombre_propietario VARCHAR(50) NOT NULL,
    CVC VARCHAR(3) NOT NULL,
    Fecha_vencimiento DATE NOT NULL,
    Usuario_fk INT UNSIGNED,
    Fecha_creacion DATE DEFAULT (CURRENT_DATE),
    FOREIGN KEY (Usuario_fk) REFERENCES Usuario(Id)
);

-- Creación de la tabla tipo_usuario
CREATE TABLE Tipo_usuario (
    Id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Tipo VARCHAR(30) NOT NULL,
    Fecha_creacion DATE DEFAULT (CURRENT_DATE),
    Usuario_fk INT UNSIGNED,
    FOREIGN KEY (Usuario_fk) REFERENCES Usuario(Id)
);

-- Creación de la tabla Ip
CREATE TABLE Ip (
    Id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Direccion_ip VARCHAR(50) NOT NULL,
    Usuario_fk INT UNSIGNED,
    Fecha_creacion DATE NOT NULL DEFAULT (CURRENT_DATE),
    FOREIGN KEY (Usuario_fk) REFERENCES Usuario(Id)
);