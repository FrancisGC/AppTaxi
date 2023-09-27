CREATE TABLE USUARIOS
(
    ID             int auto_increment,
    NUMERO         VARCHAR(12)  null,
    PASSWORD       VARCHAR(250)  null,
    PERFIL         int          null comment '-- 1 - ADMIN
-- 2 - CONDUCTOR
-- 3 - USUARIO',
    APELLIDOS      VARCHAR(250) null,
    NOMBRES        VARCHAR(250) null,
    ESTADO         int          null comment '-- 0 - INACTIVO
-- 1 - ACTIVO',
    FECHA_REGISTRO DATETIME     null,
    constraint USUARIOS_pk
        primary key (ID)
);

CREATE TABLE VEHICULO (
	idvehiculo int auto_increment NOT NULL,
	modelo varchar(100) NULL,
	color varchar(100) NULL,
	anio varchar(100) NULL,
	numplaca varchar(100) NULL,
	CONSTRAINT vehiculo_pk PRIMARY KEY (idvehiculo)
);

CREATE TABLE CLIENTES (
	idcliente int auto_increment NOT NULL,
	nombres varchar(100) NULL,
	apellidos varchar(100) NULL,
	email varchar(100) NULL,
	telefono varchar(100) NULL,
	CONSTRAINT cliente_pk PRIMARY KEY (idcliente)
);
