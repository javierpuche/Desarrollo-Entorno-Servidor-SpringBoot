use springboot_hibernate;

--alter table PROFESOR_MODULO drop foreign key FK_PROFESOR_MODULO_1;
--alter table PROFESOR_MODULO drop foreign key FK_PROFESOR_MODULO_2;

--drop table if exists PROFESOR_MODULO;
--drop table if exists IMAGEN;
--drop table if exists MODULO;
--drop table if exists EMAIL;
--drop table if exists PROFESOR;

drop database if exists springboot_hibernate;
create database springboot_hibernate;

use springboot_hibernate;



CREATE TABLE IMAGEN
(
   ID_IMAGEN BIGINT NOT NULL AUTO_INCREMENT,
   NOMBRE VARCHAR (40) NOT NULL,
   IMAGEN LONGBLOB NOT NULL,
   PRIMARY KEY (ID_IMAGEN)
);

CREATE TABLE PROFESOR
(
   ID_PROFESOR BIGINT NOT NULL AUTO_INCREMENT,
   NOMBRE VARCHAR (40) NOT NULL,
   NICKNAME VARCHAR (40) NOT NULL,
   APELLIDOS VARCHAR (40) NOT NULL,
   PRIMARY KEY (ID_PROFESOR)
);

CREATE TABLE EMAIL
(
   ID_EMAIL BIGINT NOT NULL AUTO_INCREMENT,
   DIRECCION_EMAIL VARCHAR (40) NOT NULL UNIQUE,
   ID_PROFESOR BIGINT NOT NULL,
   PRIMARY KEY (ID_EMAIL),
   CONSTRAINT FK_EMAIL_PROFESOR FOREIGN KEY (ID_PROFESOR) REFERENCES PROFESOR (ID_PROFESOR)
);

CREATE TABLE MODULO
(
   ID_MODULO BIGINT NOT NULL AUTO_INCREMENT,
   NOMBRE_MODULO VARCHAR (40) NOT NULL UNIQUE,
   PRIMARY KEY (ID_MODULO)
);

CREATE TABLE PROFESOR_MODULO
(
   ID_PROFESOR BIGINT NOT NULL,
   ID_MODULO BIGINT NOT NULL,
   PRIMARY KEY
   (
      ID_PROFESOR,
      ID_MODULO
   ),
   CONSTRAINT FK_PROFESOR_MODULO_1 FOREIGN KEY (ID_PROFESOR) REFERENCES PROFESOR (ID_PROFESOR) ON DELETE CASCADE,
   CONSTRAINT FK_PROFESOR_MODULO_2 FOREIGN KEY (ID_MODULO) REFERENCES MODULO (ID_MODULO)
);
