-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Login-Registro
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Login-Registro
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Login-Registro` DEFAULT CHARACTER SET utf8 ;
USE `Login-Registro` ;

-- -----------------------------------------------------
-- Table `Login-Registro`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Login-Registro`.`usuario` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(50) NOT NULL,
  `Identificacion` VARCHAR(20) NOT NULL,
  `Contrasena` VARCHAR(50) NOT NULL,
  `Correo` VARCHAR(50) NOT NULL,
  `Usuario` VARCHAR(50) NOT NULL,
  `Estado` TINYINT NOT NULL DEFAULT 1,
  `Fecha_creacion` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Login-Registro`.`tarjeta_credito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Login-Registro`.`tarjeta_credito` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `Numero` VARCHAR(16) NOT NULL,
  `Nompre_propietario` VARCHAR(50) NOT NULL,
  `CVC` VARCHAR(3) NOT NULL,
  `Fecha_vencimiento` DATE NOT NULL,
  `Fecha_creacion` DATETIME NOT NULL,
  `usuario_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`, `usuario_id`),
  INDEX `fk_tarjeta_credito_usuario_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_tarjeta_credito_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `Login-Registro`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Login-Registro`.`tipo_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Login-Registro`.`tipo_user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `Tipo` VARCHAR(20) NOT NULL,
  `Fecha_creacion` DATETIME NOT NULL,
  `usuario_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`, `usuario_id`),
  INDEX `fk_tipo_user_usuario1_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_tipo_user_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `Login-Registro`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Login-Registro`.`ip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Login-Registro`.`ip` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `Direccion_IP` VARCHAR(20) NOT NULL,
  `Fecha_creacion` DATETIME NOT NULL,
  `usuario_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`, `usuario_id`),
  INDEX `fk_ip_usuario1_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_ip_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `Login-Registro`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
