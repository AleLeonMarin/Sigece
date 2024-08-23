-- Sequences section -------------------------------------------------

-- CREATE SEQUENCE sis_usuarios_seq01
CREATE SEQUENCE sis_usuarios_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

-- CREATE SEQUENCE sis_roles_seq01
CREATE SEQUENCE sis_roles_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

-- CREATE SEQUENCE sis_mensajes_seq01
CREATE SEQUENCE sis_mensajes_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

-- CREATE SEQUENCE sis_chats_seq01
CREATE SEQUENCE sis_chats_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

-- CREATE SEQUENCE sis_notificacion_seq01
CREATE SEQUENCE sis_notificacion_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

-- CREATE SEQUENCE sis_correos_seq01
CREATE SEQUENCE sis_correos_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

-- CREATE SEQUENCE sis_parametros_seq01
CREATE SEQUENCE sis_parametros_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

-- CREATE SEQUENCE sis_sistemas_seq01
CREATE SEQUENCE sis_sistemas_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

-- CREATE SEQUENCE sis_sistemas_roles_usuarios_seq01
CREATE SEQUENCE sis_sistemas_roles_usuarios_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

-- CREATE SEQUENCE sis_variables_seq01
CREATE SEQUENCE sis_variables_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

-- CREATE SEQUENCE sis_variables_condicionales_seq01
CREATE SEQUENCE sis_variables_condicionales_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

-- CREATE SEQUENCE sis_variables_multimedia_seq01
CREATE SEQUENCE sis_variables_multimedia_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

-- Tables section -------------------------------------------------

-- CREATE TABLE sis_sistemas -------------------------------

CREATE TABLE sis_sistemas(
    sis_id NUMBER NOT NULL,
    sis_nombre VARCHAR2(200) NOT NULL,
    sis_version NUMBER DEFAULT 1 NOT NULL
);

CREATE UNIQUE INDEX sis_sistemas_ind01 ON sis_sistemas (sis_nombre);

ALTER TABLE sis_sistemas ADD CONSTRAINT sis_sistemas_pk PRIMARY KEY (sis_id);

-- CREATE TABLE sis_roles -------------------------------

CREATE TABLE sis_roles(
    rol_id NUMBER NOT NULL,
    rol_nombre VARCHAR2(300) NOT NULL,
    rol_sis_id NUMBER NOT NULL,
    rol_version NUMBER DEFAULT 1 NOT NULL
);

CREATE UNIQUE INDEX sis_roles_ind01 ON sis_roles (rol_nombre);

ALTER TABLE sis_roles ADD CONSTRAINT sis_roles_pk PRIMARY KEY (rol_id);

-- CREATE TABLE sis_usuarios -------------------------------

CREATE TABLE sis_usuarios(
    usu_id NUMBER NOT NULL,
    usu_nombre VARCHAR2(300) NOT NULL,
    usu_apellidos VARCHAR2(300) NOT NULL,
    usu_correo VARCHAR2(200) NOT NULL,
    usu_telefono VARCHAR2(300) NOT NULL,
    usu_celular VARCHAR2(300) NOT NULL,
    usu_idioma VARCHAR2(100) NOT NULL,
    usu_foto BLOB NOT NULL,
    usu_usuario VARCHAR2(100) NOT NULL,
    usu_clave VARCHAR2(100) NOT NULL,
    usu_estado VARCHAR2(1) DEFAULT 'I' NOT NULL
        CONSTRAINT sis_usuarios_ck01 CHECK (usu_estado in ('A','I')),
    usu_status VARCHAR2(200) DEFAULT 'En Linea' NOT NULL,
    usu_version NUMBER DEFAULT 1 NOT NULL
);

CREATE UNIQUE INDEX sis_usuarios_ind01 ON sis_usuarios (usu_usuario);

ALTER TABLE sis_usuarios ADD CONSTRAINT sis_usuarios_pk PRIMARY KEY (usu_id);

-- CREATE TABLE sis_sistemas_roles_usuarios -------------------------------

CREATE TABLE sis_sistemas_roles_usuarios(
    srs_id NUMBER NOT NULL,
    srs_nombre VARCHAR2(300) NOT NULL,
    srs_sis_id NUMBER NOT NULL,
    srs_rol_id NUMBER NOT NULL,
    srs_usu_id NUMBER NOT NULL,
    srs_version NUMBER DEFAULT 1 NOT NULL
);

CREATE UNIQUE INDEX sis_sistemas_roles_usuarios_ind01 ON sis_sistemas_roles_usuarios (srs_nombre);

ALTER TABLE sis_sistemas_roles_usuarios ADD CONSTRAINT sis_sistemas_roles_usuarios_pk PRIMARY KEY (srs_id);

-- CREATE TABLE sis_mensajes -------------------------------

CREATE TABLE sis_mensajes(
    sms_id NUMBER NOT NULL,
    sms_texto CLOB NOT NULL,
    sms_tiempo DATE NOT NULL,
    sms_usu_id NUMBER NOT NULL,
    sms_chat_id NUMBER NOT NULL,
    sms_version NUMBER DEFAULT 1 NOT NULL
);

ALTER TABLE sis_mensajes ADD CONSTRAINT sis_mensajes_pk PRIMARY KEY (sms_id);

-- CREATE TABLE sis_chats -------------------------------

CREATE TABLE sis_chats(
    cht_id NUMBER NOT NULL,
    cht_fecha DATE NOT NULL,
    cht_emisor_id NUMBER NOT NULL,
    cht_receptor_id NUMBER NOT NULL,
    cht_version NUMBER DEFAULT 1 NOT NULL
);

ALTER TABLE sis_chats ADD CONSTRAINT sis_chats_pk PRIMARY KEY (cht_id);

-- CREATE TABLE sis_parametros -------------------------------

CREATE TABLE sis_parametros(
    par_id NUMBER NOT NULL,
    par_correo VARCHAR2(300) NOT NULL,
    par_clave VARCHAR2(300) NOT NULL,
    par_puerto NUMBER NOT NULL,
    par_server VARCHAR2(300) NOT NULL,
    par_protocolo VARCHAR2(300) NOT NULL,
    par_timeout NUMBER NOT NULL,
    par_version NUMBER DEFAULT 1 NOT NULL
);

ALTER TABLE sis_parametros ADD CONSTRAINT sis_parametros_pk PRIMARY KEY (par_id);

-- CREATE TABLE sis_notificacion -------------------------------

CREATE TABLE sis_notificacion(
    not_id NUMBER NOT NULL,
    not_nombre VARCHAR2(300) NOT NULL,
    not_plantilla CLOB NOT NULL,
    not_version NUMBER DEFAULT 1 NOT NULL
);

CREATE UNIQUE INDEX sis_notificacion_ind01 ON sis_notificacion (not_nombre);

ALTER TABLE sis_notificacion ADD CONSTRAINT sis_notificacion_pk PRIMARY KEY (not_id);

-- CREATE TABLE sis_variables -------------------------------

CREATE TABLE sis_variables(
    var_id NUMBER NOT NULL,
    var_nombre VARCHAR2(300) NOT NULL,
    tipo VARCHAR2(300) NOT NULL,
    var_valor CLOB,
    var_not_id NUMBER NOT NULL,
    var_version NUMBER DEFAULT 1 NOT NULL
);

ALTER TABLE sis_variables ADD CONSTRAINT sis_variables_pk PRIMARY KEY (var_id);

-- CREATE TABLE sis_variables_condicionales -------------------------------

CREATE TABLE sis_variables_condicionales(
    vcon_id NUMBER NOT NULL,
    vcond_valor VARCHAR2(300) NOT NULL,
    vcond_resultado CLOB,
    vcon_var_id NUMBER NOT NULL,
    vcon_version NUMBER DEFAULT 1 NOT NULL
);

ALTER TABLE sis_variables_condicionales ADD CONSTRAINT sis_variables_condicionales_pk PRIMARY KEY (vcon_id);

-- CREATE TABLE sis_variables_multimedia -------------------------------

CREATE TABLE sis_variables_multimedia(
    media_id NUMBER NOT NULL,
    media_url BLOB NOT NULL,
    media_tipo VARCHAR2(300) NOT NULL
        CONSTRAINT sis_variables_multimedia_ck01 CHECK (media_tipo in ('Imagen','Video')),
    media_var_id NUMBER NOT NULL,
    media_version NUMBER DEFAULT 1 NOT NULL
);

ALTER TABLE sis_variables_multimedia ADD CONSTRAINT sis_variables_multimedia_pk PRIMARY KEY (media_id);

-- CREATE TABLE sis_correos -------------------------------

CREATE TABLE sis_correos(
    cor_id NUMBER NOT NULL,
    cor_asunto VARCHAR2(300) NOT NULL,
    cor_destinatario VARCHAR2(300) NOT NULL,
    cor_plantilla CLOB NOT NULL,
    cor_estado VARCHAR2(1) DEFAULT 'P' NOT NULL
        CONSTRAINT sis_correos_ck01 CHECK (cor_estado in ('E','P')),
    cor_fecha DATE NOT NULL,
    cor_not_id NUMBER NOT NULL,
    cor_version NUMBER DEFAULT 1 NOT NULL
);

ALTER TABLE sis_correos ADD CONSTRAINT sis_correos_pk PRIMARY KEY (cor_id);

-- Triggers section -------------------------------------------------

-- Triggers para la tabla sis_sistemas -------------------------------

CREATE OR REPLACE TRIGGER sis_sistemas_trg01
BEFORE INSERT ON sis_sistemas FOR EACH ROW
BEGIN
    IF :new.sis_id IS NULL OR :new.sis_id <= 0 THEN
       :new.sis_id := sis_sistemas_seq01.NEXTVAL;
    END IF;
END;
;

CREATE OR REPLACE TRIGGER sis_sistemas_trg02
AFTER UPDATE OF sis_id ON sis_sistemas FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010, 'No se puede actualizar el campo sis_id en la tabla sis_sistemas ya que utiliza una secuencia.');
END;

-- Triggers para la tabla sis_roles -------------------------------

CREATE OR REPLACE TRIGGER sis_roles_trg01 
BEFORE INSERT ON sis_roles FOR EACH ROW
BEGIN
    IF :new.rol_id IS NULL OR :new.rol_id <= 0 THEN
       :new.rol_id := sis_roles_seq01.NEXTVAL;
    END IF;
END;
;

CREATE OR REPLACE TRIGGER sis_roles_trg02
AFTER UPDATE OF rol_id ON sis_roles FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20011, 'No se puede actualizar el campo rol_id en la tabla sis_roles ya que utiliza una secuencia.');
END;

-- Triggers para la tabla sis_usuarios -------------------------------

CREATE OR REPLACE TRIGGER sis_usuarios_trg01
BEFORE INSERT ON sis_usuarios FOR EACH ROW
BEGIN
    IF :new.usu_id IS NULL OR :new.usu_id <= 0 THEN
       :new.usu_id := sis_usuarios_seq01.NEXTVAL;
    END IF;
END;
;

CREATE OR REPLACE TRIGGER sis_usuarios_trg02
AFTER UPDATE OF usu_id ON sis_usuarios FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20012, 'No se puede actualizar el campo usu_id en la tabla sis_usuarios ya que utiliza una secuencia.');
END;

-- Triggers para la tabla sis_sistemas_roles_usuarios -------------------------------

CREATE OR REPLACE TRIGGER sis_sistemas_roles_usuarios_trg01
BEFORE INSERT ON sis_sistemas_roles_usuarios FOR EACH ROW
BEGIN
    IF :new.srs_id IS NULL OR :new.srs_id <= 0 THEN
       :new.srs_id := sis_sistemas_roles_usuarios_seq01.NEXTVAL;
    END IF;
END;
;

CREATE OR REPLACE TRIGGER sis_sistemas_roles_usuarios_trg02
AFTER UPDATE OF srs_id ON sis_sistemas_roles_usuarios FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20013, 'No se puede actualizar el campo srs_id en la tabla sis_sistemas_roles_usuarios ya que utiliza una secuencia.');
END;

-- Triggers para la tabla sis_mensajes -------------------------------

CREATE OR REPLACE TRIGGER sis_mensajes_trg01
BEFORE INSERT ON sis_mensajes FOR EACH ROW
BEGIN
    IF :new.sms_id IS NULL OR :new.sms_id <= 0 THEN
       :new.sms_id := sis_mensajes_seq01.NEXTVAL;
    END IF;
END;
;

CREATE OR REPLACE TRIGGER sis_mensajes_trg02
AFTER UPDATE OF sms_id ON sis_mensajes FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20014, 'No se puede actualizar el campo sms_id en la tabla sis_mensajes ya que utiliza una secuencia.');
END;

-- Triggers para la tabla sis_chats -------------------------------

CREATE OR REPLACE TRIGGER sis_chats_trg01
BEFORE INSERT ON sis_chats FOR EACH ROW
BEGIN
    IF :new.cht_id IS NULL OR :new.cht_id <= 0 THEN
       :new.cht_id := sis_chats_seq01.NEXTVAL;
    END IF;
END;
;

CREATE OR REPLACE TRIGGER sis_chats_trg02
AFTER UPDATE OF cht_id ON sis_chats FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20015, 'No se puede actualizar el campo cht_id en la tabla sis_chats ya que utiliza una secuencia.');
END;

-- Triggers para la tabla sis_parametros -------------------------------

CREATE OR REPLACE TRIGGER sis_parametros_trg01
BEFORE INSERT ON sis_parametros FOR EACH ROW
BEGIN
    IF :new.par_id IS NULL OR :new.par_id <= 0 THEN
       :new.par_id := sis_parametros_seq01.NEXTVAL;
    END IF;
END;
;

CREATE OR REPLACE TRIGGER sis_parametros_trg02
AFTER UPDATE OF par_id ON sis_parametros FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20016, 'No se puede actualizar el campo par_id en la tabla sis_parametros ya que utiliza una secuencia.');
END;

-- Triggers para la tabla sis_notificacion -------------------------------

CREATE OR REPLACE TRIGGER sis_notificacion_trg01
BEFORE INSERT ON sis_notificacion FOR EACH ROW
BEGIN
    IF :new.not_id IS NULL OR :new.not_id <= 0 THEN
       :new.not_id := sis_notificacion_seq01.NEXTVAL;
    END IF;
END;
;

CREATE OR REPLACE TRIGGER sis_notificacion_trg02
AFTER UPDATE OF not_id ON sis_notificacion FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20017, 'No se puede actualizar el campo not_id en la tabla sis_notificacion ya que utiliza una secuencia.');
END;

-- Triggers para la tabla sis_variables -------------------------------

CREATE OR REPLACE TRIGGER sis_variables_trg01
BEFORE INSERT ON sis_variables FOR EACH ROW
BEGIN
    IF :new.var_id IS NULL OR :new.var_id <= 0 THEN
       :new.var_id := sis_variables_seq01.NEXTVAL;
    END IF;
END;
;

CREATE OR REPLACE TRIGGER sis_variables_trg02
AFTER UPDATE OF var_id ON sis_variables FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20018, 'No se puede actualizar el campo var_id en la tabla sis_variables ya que utiliza una secuencia.');
END;

-- Triggers para la tabla sis_variables_condicionales -------------------------------

CREATE OR REPLACE TRIGGER sis_variables_condicionales_trg01
BEFORE INSERT ON sis_variables_condicionales FOR EACH ROW
BEGIN
    IF :new.vcon_id IS NULL OR :new.vcon_id <= 0 THEN
       :new.vcon_id := sis_variables_condicionales_seq01.NEXTVAL;
    END IF;
END;
;

CREATE OR REPLACE TRIGGER sis_variables_condicionales_trg02
AFTER UPDATE OF vcon_id ON sis_variables_condicionales FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20019, 'No se puede actualizar el campo vcon_id en la tabla sis_variables_condicionales ya que utiliza una secuencia.');
END;

-- Triggers para la tabla sis_variables_multimedia -------------------------------

CREATE OR REPLACE TRIGGER sis_variables_multimedia_trg01
BEFORE INSERT ON sis_variables_multimedia FOR EACH ROW
BEGIN
    IF :new.media_id IS NULL OR :new.media_id <= 0 THEN
       :new.media_id := sis_variables_multimedia_seq01.NEXTVAL;
    END IF;
END;
;

CREATE OR REPLACE TRIGGER sis_variables_multimedia_trg02
AFTER UPDATE OF media_id ON sis_variables_multimedia FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20020, 'No se puede actualizar el campo media_id en la tabla sis_variables_multimedia ya que utiliza una secuencia.');
END;

-- Triggers para la tabla sis_correos -------------------------------

CREATE OR REPLACE TRIGGER sis_correos_trg01
BEFORE INSERT ON sis_correos FOR EACH ROW
BEGIN
    IF :new.cor_id IS NULL OR :new.cor_id <= 0 THEN
       :new.cor_id := sis_correos_seq01.NEXTVAL;
    END IF;
END;
;

CREATE OR REPLACE TRIGGER sis_correos_trg02
AFTER UPDATE OF cor_id ON sis_correos FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20021, 'No se puede actualizar el campo cor_id en la tabla sis_correos ya que utiliza una secuencia.');
END;

-- Foreign Key Constraints section -----------------------------------

-- Constraints para la tabla sis_roles -------------------------------

ALTER TABLE sis_roles
ADD CONSTRAINT sis_roles_fk01
FOREIGN KEY (rol_sis_id)
REFERENCES sis_sistemas (sis_id);


-- Constraints para la tabla sis_sistemas_roles_usuarios -------------------------------

ALTER TABLE sis_sistemas_roles_usuarios
ADD CONSTRAINT sis_sistemas_roles_usuarios_fk01
FOREIGN KEY (srs_sis_id)
REFERENCES sis_sistemas (sis_id);

ALTER TABLE sis_sistemas_roles_usuarios
ADD CONSTRAINT sis_sistemas_roles_usuarios_fk02
FOREIGN KEY (srs_rol_id)
REFERENCES sis_roles (rol_id);

ALTER TABLE sis_sistemas_roles_usuarios
ADD CONSTRAINT sis_sistemas_roles_usuarios_fk03
FOREIGN KEY (srs_usu_id)
REFERENCES sis_usuarios (usu_id);

-- Constraints para la tabla sis_mensajes -------------------------------

ALTER TABLE sis_mensajes
ADD CONSTRAINT sis_mensajes_fk01
FOREIGN KEY (sms_usu_id)
REFERENCES sis_usuarios (usu_id);

ALTER TABLE sis_mensajes
ADD CONSTRAINT sis_mensajes_fk02
FOREIGN KEY (sms_chat_id)
REFERENCES sis_chats (cht_id);

-- Constraints para la tabla sis_chats -------------------------------

ALTER TABLE sis_chats
ADD CONSTRAINT sis_chats_fk01
FOREIGN KEY (cht_emisor_id)
REFERENCES sis_usuarios (usu_id);

ALTER TABLE sis_chats
ADD CONSTRAINT sis_chats_fk02
FOREIGN KEY (cht_receptor_id)
REFERENCES sis_usuarios (usu_id);

-- Constraints para la tabla sis_parametros -------------------------------

-- No tiene claves foráneas

-- Constraints para la tabla sis_notificacion -------------------------------

-- No tiene claves foráneas

-- Constraints para la tabla sis_variables -------------------------------

ALTER TABLE sis_variables
ADD CONSTRAINT sis_variables_fk01
FOREIGN KEY (var_not_id)
REFERENCES sis_notificacion (not_id);

-- Constraints para la tabla sis_variables_condicionales -------------------------------

ALTER TABLE sis_variables_condicionales
ADD CONSTRAINT sis_variables_condicionales_fk01
FOREIGN KEY (vcon_var_id)
REFERENCES sis_variables (var_id);

-- Constraints para la tabla sis_variables_multimedia -------------------------------

ALTER TABLE sis_variables_multimedia
ADD CONSTRAINT sis_variables_multimedia_fk01
FOREIGN KEY (media_var_id)
REFERENCES sis_variables (var_id);

-- Constraints para la tabla sis_correos -------------------------------

ALTER TABLE sis_correos
ADD CONSTRAINT sis_correos_fk01
FOREIGN KEY (cor_not_id)
REFERENCES sis_notificacion (not_id);
