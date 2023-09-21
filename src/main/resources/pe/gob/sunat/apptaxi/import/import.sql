CREATE TABLE USUARIOS
(
    ID             int auto_increment,
    NUMERO         VARCHAR(12)  null,
    PASSWORD       VARCHAR(50)  null,
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

