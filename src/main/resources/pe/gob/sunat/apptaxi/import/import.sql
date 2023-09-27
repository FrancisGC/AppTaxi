CREATE DATABASE IF NOT EXISTS APP_TAXI_DB;

CREATE TABLE IF NOT EXISTS USUARIOS
(
    ID             int auto_increment,
    NUMERO         VARCHAR(12)  null,
    PASSWORD       VARCHAR(250) null,
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


CREATE TABLE IF NOT EXISTS VEHICULO
(
    ID         int auto_increment NOT NULL,
    MODELO     varchar(100)       NULL,
    COLOR      varchar(100)       NULL,
    ANIO       varchar(100)       NULL,
    NUM_PLACA  varchar(100)       NULL,
    ID_USUARIO int                NOT NULL,
    CONSTRAINT vehiculo_pk PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (ID_USUARIO) REFERENCES USUARIOS(ID)
);

CREATE TABLE IF NOT EXISTS SOLICITUD
(
    ID int auto_increment not null,
    ORIGEN  VARCHAR(250),
    DESTINO VARCHAR(250),
    PRECIO DECIMAL(8,2),
    ESTADO INT,
    ID_USUARIO INT COMMENT 'USUARIO DE PERFIL USUARIO',
    FEC_REGISTRO DATETIME,
    CONSTRAINT solicitud_pk PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (ID_USUARIO) REFERENCES USUARIOS(ID)
);

CREATE TABLE IF NOT EXISTS SERVICIO
(
    ID int auto_increment not null,
    ID_SOLICITUD VARCHAR(250),
    ID_USUARIO VARCHAR(250) COMMENT 'USUARIO DE PERFIL CONDUCTOR',
    PRECIO DECIMAL(8,2),
    ESTADO INT,
    FEC_REGISTRO DATETIME,
    CONSTRAINT solicitud_pk PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (ID_SOLICITUD) REFERENCES SOLICITUD(ID),
    CONSTRAINT FOREIGN KEY (ID_USUARIO) REFERENCES USUARIOS(ID)
);


CREATE TABLE IF NOT EXISTS CLIENTES
(
    idcliente int auto_increment NOT NULL,
    nombres   varchar(100)       NULL,
    apellidos varchar(100)       NULL,
    email     varchar(100)       NULL,
    telefono  varchar(100)       NULL,
    CONSTRAINT cliente_pk PRIMARY KEY (idcliente)
);


