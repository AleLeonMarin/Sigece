--Sequence for tables-------------------------------------------------

--CREATE SEQUENCE sis_usuarios_seq01
CREATE SEQUENCE sis_usuarios_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

--CREATE SEQUENCE sis_roles_seq01
CREATE SEQUENCE sis_roles_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

--CREATE SEQUENCE sis_mensajes_seq01
CREATE SEQUENCE sis_mensajes_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

--CREATE SEQUENCE sis_chats_seq01
CREATE SEQUENCE sis_chats_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

--CREATE SEQUENCE sis_notificacion_seq01
CREATE SEQUENCE sis_notificacion_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

--CREATE SEQUENCE sis_correos_seq01
CREATE SEQUENCE sis_correos_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

--CREATE SEQUENCE sis_parametros_seq01
CREATE SEQUENCE sis_parametros_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;


-- CREATE TABLE sis_usuarios-------------------------------

-- Table sis_roles

CREATE TABLE sis_roles(
    rol_id Number NOT NULL.
    rol_nombre Varchar2(100 ) NOT NULL,
    rol_version Number DEFAULT 1 NOT NULL
)
;

CREATE UNIQUE INDEX sis_roles_ind01 ON sis_roles (rol_nombre)
;

ALTER TABLE sis_roles ADD CONSTRAINT sis_roles_pk PRIMARY KEY (rol_id)
;

-- CREATE TABLE sis_usuarios-------------------------------

CREATE TABLE sis_usuarios(
    usu_id Number NOT NULL,
    usu_nombre Varchar2(300) NOT NULL,
    usu_apellidos Varchar2(300 ) NOT NULL,
    usu_correo Varchar2(200 ) NOT NULL,
    usu_telefono Varchar2(300 ) NOT NULL,
    usu_celular Varchar2(300 ) NOT NULL,
    usu_idioma Varchar2(100 ) NOT NULL,
    usu_foto BLOB NOT NULL,
    usu_usuario Varchar2(100 ) NOT NULL,
    usu_clave Varchar2(100 ) NOT NULL,
    usu_estado Varchar2(1 ) DEFAULT 'I' NOT NULL
        CONSTRAINT sis_usuarios_ck01 CHECK (usu_estado in ('A','I')),
    usu_rol_id Number NOT NULL,
    usu_status Varchar2(200) DEFAULT 'En Linea' NOT NULL,
    usu_version Number DEFAULT 1 NOT NULL
)
;

CREATE UNIQUE INDEX sis_usuarios_ind01 ON sis_usuarios (usu_usuario)
;

ALTER TABLE sis_usuarios ADD CONSTRAINT sis_usuarios_pk PRIMARY KEY (usu_id)
;

--CREATE TABLE sis_mensajes-------------------------------

CREATE TABLE sis_mensajes(
    sms_id Number NOT NULL,
    sms_texto CLOB NOT NULL,
    sms_tiempo Date NOT NULL,
    sms_usu_id Number NOT NULL,
    sms_version Number DEFAULT 1 NOT NULL
)
;

ALTER TABLE sis_mensajes ADD CONSTRAINT sis_mensajes_pk PRIMARY KEY (sms_id)
;

--CREATE TABLE sis_chats-------------------------------

CREATE TABLE sis_chats(
    cht_id Number NOT NULL,
    cht_inicio Date NOT NULL,
    cht_final Date NOT NULL,
    cht_usu_id Number NOT NULL,
    cht_usu_id2 Number NOT NULL,
    cht_version Number DEFAULT 1 NOT NULL
)
;

ALTER TABLE sis_chats ADD CONSTRAINT sis_chats_pk PRIMARY KEY (cht_id)
;

CREATE TABLE sis_notificacion(
    not_id Number NOT NULL,
    not_nombre Varchar2(300 ) NOT NULL,
    not_plantilla CLOB NOT NULL,
    not_variables CLOB NOT NULL,
    not_version Number DEFAULT 1 NOT NULL
)

CREATE UNIQUE INDEX sis_notificacion_ind01 ON sis_notificacion (not_nombre)
;

ALTER TABLE sis_notificacion ADD CONSTRAINT sis_notificacion_pk PRIMARY KEY (not_id)
;

CREATE TABLE sis_correos(
    cor_id Number NOT NULL,
    cor_plantilla CLOB NOT NULL,
    cor_estado Varchar2(1 ) DEFAULT 'P' NOT NULL
        CONSTRAINT sis_correos_ck01 CHECK (cor_estado in ('E','P')),
    cor_notificacion NVarchar2(300) NOT NULL,
    cor_not_id Number NOT NULL,
    cor_version Number DEFAULT 1 NOT NULL
)
;

ALTER TABLE sis_correos ADD CONSTRAINT sis_correos_pk PRIMARY KEY (cor_id)
;

-- Table sis_parametros

CREATE TABLE sis_parametros(
    par_id Number NOT NULL,
    par_correo Varchar2(300 ) NOT NULL,
    par_clave Varchar2(300 ) NOT NULL,
    par_version Number DEFAULT 1 NOT NULL
)
;

ALTER TABLE sis_parametros ADD CONSTRAINT sis_parametros_pk PRIMARY KEY (par_id)
;

--Triggers para la tabla sis_usuarios

CREATE OR REPLACE TRIGGER sis_usuarios_trg01 BEFORE INSERT 
ON sis_usuarios FOR EACH ROW
BEGIN
    IF :new.usu_id IS NULL OR :new.usu_id <= 0 THEN
       :new.usu_id := sis_usuarios_seq01.NEXTVAL;
    END IF;
END;

CREATE OR REPLACE TRIGGER sis_usuarios_trg02 AFTER UPDATE OF usu_id
ON sis_usuarios FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010, 'No se puede actualizar el campo usu_id en la tabla sis_usuarios ya que utiliza una secuencia.');
END;

--Triggers para la tabla sis_roles

CREATE OR REPLACE TRIGGER sis_roles_trg01 BEFORE INSERT
ON sis_roles FOR EACH ROW
BEGIN
    IF :new.rol_id IS NULL OR :new.rol_id <= 0 THEN
       :new.rol_id := sis_roles_seq01.NEXTVAL;
    END IF;
END;

CREATE OR REPLACE TRIGGER sis_roles_trg02 AFTER UPDATE OF rol_id
ON sis_roles FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010, 'No se puede actualizar el campo rol_id en la tabla sis_roles ya que utiliza una secuencia.');
END;

--Triggers para la tabla sis_mensajes

CREATE OR REPLACE TRIGGER sis_mensajes_trg01 BEFORE INSERT
ON sis_mensajes FOR EACH ROW
BEGIN
    IF :new.sms_id IS NULL OR :new.sms_id <= 0 THEN
       :new.sms_id := sis_mensajes_seq01.NEXTVAL;
    END IF;
END;

CREATE OR REPLACE TRIGGER sis_mensajes_trg02 AFTER UPDATE OF sms_id
ON sis_mensajes FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010, 'No se puede actualizar el campo sms_id en la tabla sis_mensajes ya que utiliza una secuencia.');
END;

--Triggers para la tabla sis_chats

CREATE OR REPLACE TRIGGER sis_chats_trg01 BEFORE INSERT
ON sis_chats FOR EACH ROW
BEGIN
    IF :new.cht_id IS NULL OR :new.cht_id <= 0 THEN
       :new.cht_id := sis_chats_seq01.NEXTVAL;
    END IF;
END;

CREATE OR REPLACE TRIGGER sis_chats_trg02 AFTER UPDATE OF cht_id
ON sis_chats FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010, 'No se puede actualizar el campo cht_id en la tabla sis_chats ya que utiliza una secuencia.');
END;

--Triggers para la tabla sis_notificacion

CREATE OR REPLACE TRIGGER sis_notificacion_trg01 BEFORE INSERT
ON sis_notificacion FOR EACH ROW
BEGIN
    IF :new.not_id IS NULL OR :new.not_id <= 0 THEN
       :new.not_id := sis_notificacion_seq01.NEXTVAL;
    END IF;
END;

CREATE OR REPLACE TRIGGER sis_notificacion_trg02 AFTER UPDATE OF not_id
ON sis_notificacion FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010, 'No se puede actualizar el campo not_id en la tabla sis_notificacion ya que utiliza una secuencia.');
END;

--Triggers para la tabla sis_correos

CREATE OR REPLACE TRIGGER sis_correos_trg01 BEFORE INSERT
ON sis_correos FOR EACH ROW
BEGIN
    IF :new.cor_id IS NULL OR :new.cor_id <= 0 THEN
       :new.cor_id := sis_correos_seq01.NEXTVAL;
    END IF;
END;

CREATE OR REPLACE TRIGGER sis_correos_trg02 AFTER UPDATE OF cor_id
ON sis_correos FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010, 'No se puede actualizar el campo cor_id en la tabla sis_correos ya que utiliza una secuencia.');
END;

--Triggers para la tabla sis_parametros

CREATE OR REPLACE TRIGGER sis_parametros_trg01 BEFORE INSERT
ON sis_parametros FOR EACH ROW
BEGIN
    IF :new.par_id IS NULL OR :new.par_id <= 0 THEN
       :new.par_id := sis_parametros_seq01.NEXTVAL;
    END IF;
END;

CREATE OR REPLACE TRIGGER sis_parametros_trg02 AFTER UPDATE OF par_id
ON sis_parametros FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010, 'No se puede actualizar el campo par_id en la tabla sis_parametros ya que utiliza una secuencia.');
END;

-- Create foreign keys (relationships) section -------------------------------------------------


