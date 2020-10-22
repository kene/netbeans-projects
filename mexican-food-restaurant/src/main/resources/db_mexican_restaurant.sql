
-- crea schema de base de datos
-- CREATE SCHEMA IF NOT EXISTS db_mexican_food_restaurant;

-- crea base de datos
CREATE DATABASE IF NOT EXISTS db_mexican_food_restaurant;


-- usa la base de datos creada
USE db_mexican_food_restaurant;

SET SQL_SAFE_UPDATES = 0;

-- tabla restaurantes, 
CREATE TABLE IF NOT EXISTS `RESTAURANTE`(
    `codigo_restaurante` VARCHAR(10) NOT NULL,
    `nombre` VARCHAR(50) NOT NULL,
    `ciudad` VARCHAR(50) NOT NULL,
    `telefono` VARCHAR(10) NOT NULL,
    `direccion` VARCHAR(50) NOT NULL,
    `pais` VARCHAR(50) NOT NULL,
    `codigo_postal` VARCHAR(15) NOT NULL,
    `colonia` VARCHAR(50) NOT NULL,
    PRIMARY KEY(`codigo_restaurante`),
    INDEX(`ciudad`),
    INDEX(`telefono`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- tabla empleados, llave primaria = numero_empleado
CREATE TABLE IF NOT EXISTS `EMPLEADO` (
    `numero_empleado` INT NOT NULL AUTO_INCREMENT,
    `rfc` VARCHAR(20) NOT NULL,
    `curp` VARCHAR(20) NOT NULL,
    `nombre` 	VARCHAR(50) NOT NULL,
    `apellido_paterno` VARCHAR(50) NOT NULL,
    `apellido_materno` VARCHAR(50) NOT NULL,
    `correo_electronico`  VARCHAR(50) NOT NULL,
    `telefono` VARCHAR(10) NOT NULL,
    `direccion` VARCHAR(70) NOT NULL,
    `salario` DECIMAL(8,2) NOT NULL,
    `incremento` DECIMAL(8,2) NOT NULL,
    `codigo_restaurante` VARCHAR(10),
    PRIMARY KEY (`numero_empleado`),
    INDEX (`apellido_paterno`),
    INDEX (`nombre`),
    FOREIGN KEY (`codigo_restaurante`) REFERENCES `RESTAURANTE` (`codigo_restaurante`)
      ON DELETE RESTRICT ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- tabla nomina, llave primaria = numero_nomina, llave foranea = numero_nomina , tabla referenciada = empleados
CREATE TABLE IF NOT EXISTS `NOMINA`(
    `numero_nomina` INT NOT NULL AUTO_INCREMENT,
    `razon_social` VARCHAR(50) NOT NULL,
    `domicilio` VARCHAR(50) NOT NULL,
    `fecha` DATETIME NOT NULL,
    `inicio_periodo` DATETIME NOT NULL, 
    `fin_periodo` DATETIME NOT NULL,
    `dias` INT NOT NULL,
	 `numero_empleado` INT,
    PRIMARY KEY(`numero_nomina`),
	FOREIGN KEY (`numero_empleado`) REFERENCES `EMPLEADO` (`numero_empleado`)
		ON DELETE RESTRICT ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- table conceptos, llave primaria = id_concepto, llave foranea = id_concepto, tabla referenciada = nomina
CREATE TABLE IF NOT EXISTS `CONCEPTO`(
    `id_concepto` INT NOT NULL AUTO_INCREMENT,
    `concepto` VARCHAR(50) NOT NULL,
    `cantidad` DECIMAL(8,2) NOT NULL,
    `numero_nomina` INT,
    PRIMARY KEY (`id_concepto`),
	FOREIGN KEY (`numero_nomina`) REFERENCES `NOMINA` (`numero_nomina`)
		ON DELETE RESTRICT ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- table deducciones, llave primaria = id_deduccion, llave foranea = id_deduccion, tabla referenciada = nomina
CREATE TABLE IF NOT EXISTS `DEDUCCION`(
    `id_deduccion` INT NOT NULL AUTO_INCREMENT,
    `deduccion` VARCHAR(50) NOT NULL,
    `cantidad` DECIMAL(8,2) NOT NULL,
	`numero_nomina` INT,
    PRIMARY KEY (`id_deduccion`),
	FOREIGN KEY (`numero_nomina`) REFERENCES `NOMINA` (`numero_nomina`)
		ON DELETE RESTRICT ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- table aportaciones, llave primaria = id_aportacion, llave foranea = id_aportacion, tabla referenciada = nomina
CREATE TABLE IF NOT EXISTS `APORTACION`(
    `id_aportacion` INT NOT NULL AUTO_INCREMENT,
    `aportacion` VARCHAR(50) NOT NULL,
    `cantidad` DECIMAL(8,2) NOT NULL,
	`numero_nomina` INT,
    PRIMARY KEY (`id_aportacion`),
	FOREIGN KEY (`numero_nomina`) REFERENCES `NOMINA` (`numero_nomina`)
		ON DELETE RESTRICT ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `PRODUCTO`(
    `codigo_producto` VARCHAR(10) NOT NULL,
    `nombre` VARCHAR(50) NOT NULL,
    `descripcion` VARCHAR(50) NOT NULL,
    `cantidad` VARCHAR(50) NOT NULL,
    `precio` DECIMAL(8,2) NOT NULL,
    `codigo_restaurante` VARCHAR(10),
    PRIMARY KEY (`codigo_producto`),
    INDEX(`nombre`),
    FOREIGN KEY (`codigo_restaurante`) REFERENCES `RESTAURANTE` (`codigo_restaurante`)
      ON DELETE RESTRICT ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- inserts restaurantes
INSERT INTO RESTAURANTE(codigo_restaurante, nombre, ciudad, telefono, direccion, pais, codigo_postal, colonia) 
VALUES ('R001', 'Valiente Mexicano', 'Ciudad Mexico', '5599345902', 'Sur 3 Calle Londres Num 23', 'Mexico', '45003', 'Diamante');
INSERT INTO RESTAURANTE(codigo_restaurante, nombre, ciudad, telefono, direccion, pais, codigo_postal, colonia) 
VALUES ('R002', 'Picoso Mexicano', 'Guadalajara', '2299345902', 'Norte 5 Calle Dore Num 33', 'Mexico','55003', 'Centro');
INSERT INTO RESTAURANTE(codigo_restaurante, nombre, ciudad, telefono, direccion, pais, codigo_postal, colonia) 
VALUES ('R003', 'El Mexicano', 'Monterrey', '3399345902', 'Reforma Calle Centro Num 43', 'Mexico', '65003', 'Reforma Norte');
INSERT INTO RESTAURANTE(codigo_restaurante, nombre, ciudad, telefono, direccion, pais, codigo_postal, colonia) 
VALUES ('R004', 'Taco Loco', 'Tijuana', '4499345902', 'Lores 30 Calle Madrid Num 53', 'Mexico', '75003', 'Lores Centro');


-- inserts empleado
INSERT INTO EMPLEADO(rfc, curp, nombre, apellido_paterno, apellido_materno, correo_electronico, telefono, direccion, salario, incremento)
VALUES('CUPU800825569', 'CUPU80082556909','Carlos', 'Ramirez', 'Lopez', 'lopez@gmail.com', '5534219029', 'Norte 10 NUm 345', 10450.56, 0.0);

INSERT INTO EMPLEADO(rfc, curp, nombre, apellido_paterno, apellido_materno, correo_electronico, telefono, direccion, salario, incremento)
VALUES('KRMU800825569', 'KRMU80082556909','Karla', 'Ramirez', 'Martinez', 'kramirez@gmail.com', '3334219029', 'Sur 20 NUm 320', 5450.56, 0.0);


INSERT INTO EMPLEADO(rfc, curp, nombre, apellido_paterno, apellido_materno, correo_electronico, telefono, direccion, salario, incremento)
VALUES('KRMU800825569', 'CRMU80082556909','Carolina', 'Ramirez', 'Martinez', 'cramirez@gmail.com', '3334219030', 'Centro 20 NUm 45', 15450.56, 0.0);


INSERT INTO EMPLEADO(rfc, curp, nombre, apellido_paterno, apellido_materno, correo_electronico, telefono, direccion, salario, incremento)
VALUES('RGMU800825569', 'RGMU800825569','Roberto', 'Gomez', 'Martinez', 'rramirez@gmail.com', '4434219029', 'Poniente 20 NUm 450', 6450.56, 0.0);


INSERT INTO EMPLEADO(rfc, curp, nombre, apellido_paterno, apellido_materno, correo_electronico, telefono, direccion, salario, incremento)
VALUES('CSU800825569', 'CSU800825569','Camilo', 'Solano', 'Uberto', 'csolano@gmail.com', '2734219029', 'Espinal 20 NUm 43', 7450.56, 0.0);


INSERT INTO EMPLEADO(rfc, curp, nombre, apellido_paterno, apellido_materno, correo_electronico, telefono, direccion, salario, incremento)
VALUES('DLVU800825569', 'DLVU800825569','Daniel', 'Lozano', 'Vasquez', 'dlozano@gmail.com', '666219029', 'Centro 78 NUm 450', 7450.56, 0.0);


-- inserts nominas 
INSERT INTO NOMINA(razon_social, domicilio, fecha, inicio_periodo, fin_periodo, dias) 
VALUES ('Restaurante Mexicano Asociados A.C', 'Centro 34 Ciudad de Mexico', '2020-04-01', '2020-03-01', '2020-03-30', 30);

INSERT INTO NOMINA(razon_social, domicilio, fecha, inicio_periodo, fin_periodo, dias) 
VALUES ('Restaurante Mexicano Asociados A.C', 'Centro 34 Ciudad de Mexico', '2020-05-01', '2020-04-01', '2020-04-30', 30);

INSERT INTO NOMINA(razon_social, domicilio, fecha, inicio_periodo, fin_periodo, dias) 
VALUES ('Restaurante Mexicano Asociados A.C', 'Centro 34 Ciudad de Mexico', '2020-06-01', '2020-05-01', '2020-05-30', 30);

INSERT INTO NOMINA(razon_social, domicilio, fecha, inicio_periodo, fin_periodo, dias) 
VALUES ('Restaurante Mexicano Asociados A.C', 'Centro 34 Ciudad de Mexico', '2020-03-01', '2020-02-01', '2020-02-28', 30);

INSERT INTO NOMINA(razon_social, domicilio, fecha, inicio_periodo, fin_periodo, dias) 
VALUES ('Restaurante Mexicano Asociados A.C', 'Centro 34 Ciudad de Mexico', '2020-02-01', '2020-01-01', '2020-01-31', 30);

INSERT INTO NOMINA(razon_social, domicilio, fecha, inicio_periodo, fin_periodo, dias) 
VALUES ('Restaurante Mexicano Asociados A.C', 'Centro 34 Ciudad de Mexico', '2020-07-01', '2020-06-01', '2020-06-30', 30);



-- inserts conceptos
INSERT INTO CONCEPTO(concepto, cantidad)VALUES('Salario Base', 3500.45);
INSERT INTO CONCEPTO(concepto, cantidad)VALUES('Complemento Salarial', 1200.50);
INSERT INTO CONCEPTO(concepto, cantidad)VALUES('Pago Extra', 100.50);


-- inserts deducciones
INSERT INTO DEDUCCION(deduccion, cantidad) VALUES('ISR', 1000);
INSERT INTO DEDUCCION(deduccion, cantidad) VALUES('Seguro Social', 300.60);
INSERT INTO DEDUCCION(deduccion, cantidad) VALUES('Retencion Adicional', 500.40);
INSERT INTO DEDUCCION(deduccion, cantidad) VALUES('Retencion Adicional', 500.40);



-- inserts aportaciones
INSERT INTO APORTACION(aportacion, cantidad) VALUES('Cotizacion Desempleo', 350.40);
INSERT INTO APORTACION(aportacion, cantidad) VALUES('Horas Extras', 500.60);
INSERT INTO APORTACION(aportacion, cantidad) VALUES('Horas Extraordinarias', 200.80);
INSERT INTO APORTACION(aportacion, cantidad) VALUES('Formacion Profesional', 150.80);


-- inserts productos
INSERT INTO PRODUCTO(codigo_producto, nombre, descripcion, cantidad, precio)
VALUES('10001', 'Atun', 'Producto Enlatado', 40, 530.90);
INSERT INTO PRODUCTO(codigo_producto, nombre, descripcion, cantidad, precio)
VALUES('10002', 'Chiles chipotles', 'Producto Enlatado', 50, 630.90);
INSERT INTO PRODUCTO(codigo_producto, nombre, descripcion, cantidad, precio)
VALUES('10003', 'Sal', 'Producto en kilos', 80, 930.90);
INSERT INTO PRODUCTO(codigo_producto, nombre, descripcion, cantidad, precio)
VALUES('10004', 'Azucar', 'Producto en kilos', 60, 830.90);
INSERT INTO PRODUCTO(codigo_producto, nombre, descripcion, cantidad, precio)
VALUES('10005', 'Limon', 'Producto en kilos', 60, 830.90);
INSERT INTO PRODUCTO(codigo_producto, nombre, descripcion, cantidad, precio)
VALUES('10006', 'Tortillas', 'Producto en kilos', 30, 130.90);
INSERT INTO PRODUCTO(codigo_producto, nombre, descripcion, cantidad, precio)
VALUES('10007', 'Carne de res', 'Producto en kilos', 50, 830.90);
INSERT INTO PRODUCTO(codigo_producto, nombre, descripcion, cantidad, precio)
VALUES('10008', 'Jitomate', 'Producto en kilos', 60, 540.90);
INSERT INTO PRODUCTO(codigo_producto, nombre, descripcion, cantidad, precio)
VALUES('10009', 'Cebolla', 'Producto en kilos', 30, 340.50);
INSERT INTO PRODUCTO(codigo_producto, nombre, descripcion, cantidad, precio)
VALUES('10010', 'Carne de puerco', 'Producto en kilos', 50, 940.50);
INSERT INTO PRODUCTO(codigo_producto, nombre, descripcion, cantidad, precio)
VALUES('10011', 'Salsa verde', 'Producto en kilos', 50, 340.50);



-- updates empleados
use db_mexican_food_restaurant;
UPDATE EMPLEADO
SET 
codigo_restaurante = 'R001'
WHERE 
numero_empleado = 1;
	
UPDATE EMPLEADO
SET 
codigo_restaurante = 'R002'
WHERE 
numero_empleado = 2;

UPDATE EMPLEADO
SET 
codigo_restaurante = 'R002'
WHERE 
numero_empleado = 3;

UPDATE EMPLEADO
SET 
codigo_restaurante = 'R001'
WHERE 
numero_empleado = 1;

UPDATE EMPLEADO
SET 
codigo_restaurante = 'R003'
WHERE 
numero_empleado = 5;
    
UPDATE EMPLEADO
SET 
codigo_restaurante = 'R004'
WHERE 
numero_empleado = 6;  
    
    
    
-- updates nomina 
UPDATE NOMINA
SET 
numero_empleado = 1
WHERE 
numero_nomina = 1;

UPDATE NOMINA
SET 
numero_empleado = 1
WHERE 
numero_nomina = 2;


UPDATE NOMINA
SET 
numero_empleado = 2
WHERE 
numero_nomina = 3;

UPDATE NOMINA
SET 
numero_empleado = 3
WHERE 
numero_nomina = 4;
    

UPDATE NOMINA
SET 
numero_empleado = 4
WHERE 
numero_nomina = 5;    

UPDATE NOMINA
SET 
numero_empleado = 5
WHERE 
numero_nomina = 6;


-- updates concepto

UPDATE CONCEPTO
SET 
numero_nomina = 1
WHERE 
id_concepto = 1;

UPDATE CONCEPTO
SET 
numero_nomina = 1
WHERE 
id_concepto = 2;

UPDATE CONCEPTO
SET 
numero_nomina = 1
WHERE 
id_concepto = 3;


-- updates deducciones
UPDATE DEDUCCION
SET 
numero_nomina = 1
WHERE 
id_deduccion = 1;


UPDATE DEDUCCION
SET 
numero_nomina = 1
WHERE 
id_deduccion = 2;

UPDATE DEDUCCION
SET 
numero_nomina = 1
WHERE 
id_deduccion = 3;

UPDATE DEDUCCION
SET 
numero_nomina = 2
WHERE 
id_deduccion = 4;

-- updates aportaciones
UPDATE APORTACION
SET 
numero_nomina = 1
WHERE 
id_aportacion = 1;

UPDATE APORTACION
SET 
numero_nomina = 1
WHERE 
id_aportacion = 2;

UPDATE APORTACION
SET 
numero_nomina = 1
WHERE 
id_aportacion = 3;

UPDATE APORTACION
SET 
numero_nomina = 2
WHERE 
id_aportacion = 4;


-- updates producto
UPDATE PRODUCTO
SET 
codigo_restaurante = 'R001'
WHERE 
codigo_producto = 10001;


UPDATE PRODUCTO
SET 
codigo_restaurante = 'R001'
WHERE 
codigo_producto = 10002;

UPDATE PRODUCTO
SET 
codigo_restaurante = 'R001'
WHERE 
codigo_producto = 10003;

UPDATE PRODUCTO
SET 
codigo_restaurante = 'R001'
WHERE 
codigo_producto = 10004;

UPDATE PRODUCTO
SET 
codigo_restaurante = 'R002'
WHERE 
codigo_producto = 10005;

UPDATE PRODUCTO
SET 
codigo_restaurante = 'R002'
WHERE 
codigo_producto = 10006;

UPDATE PRODUCTO
SET 
codigo_restaurante = 'R002'
WHERE 
codigo_producto = 10007;

UPDATE PRODUCTO
SET 
codigo_restaurante = 'R002'
WHERE 
codigo_producto = 10008;

UPDATE PRODUCTO
SET 
codigo_restaurante = 'R002'
WHERE 
codigo_producto = 10009;

UPDATE PRODUCTO
SET 
codigo_restaurante = 'R002'
WHERE 
codigo_producto = 10010;

UPDATE PRODUCTO
SET 
codigo_restaurante = 'R003'
WHERE 
codigo_producto = 10011;