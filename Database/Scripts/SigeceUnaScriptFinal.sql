CREATE SEQUENCE sis_usuarios_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

CREATE SEQUENCE sis_roles_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

CREATE SEQUENCE sis_mensajes_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

CREATE SEQUENCE sis_chats_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

CREATE SEQUENCE sis_notificacion_seq01
 INCREMENT BY 1
 START WITH 3
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

CREATE SEQUENCE sis_correos_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

CREATE SEQUENCE sis_parametros_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

CREATE SEQUENCE sis_sistemas_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

CREATE SEQUENCE sis_variables_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

CREATE SEQUENCE sis_variables_condicionales_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

CREATE SEQUENCE sis_variables_multimedia_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 NOCACHE
;

CREATE TABLE sis_sistemas(
    sis_id NUMBER NOT NULL,
    sis_nombre VARCHAR2(200) NOT NULL,
    sis_version NUMBER DEFAULT 1 NOT NULL
);

CREATE UNIQUE INDEX sis_sistemas_ind01 ON sis_sistemas (sis_nombre);

ALTER TABLE sis_sistemas ADD CONSTRAINT sis_sistemas_pk PRIMARY KEY (sis_id);

CREATE TABLE sis_roles(
    rol_id NUMBER NOT NULL,
    rol_nombre VARCHAR2(300) NOT NULL,
    rol_sis_id NUMBER NOT NULL,
    rol_version NUMBER DEFAULT 1 NOT NULL
);

CREATE UNIQUE INDEX sis_roles_ind01 ON sis_roles (rol_nombre);

ALTER TABLE sis_roles ADD CONSTRAINT sis_roles_pk PRIMARY KEY (rol_id);

CREATE TABLE sis_usuarios(
    usu_id NUMBER NOT NULL,
    usu_nombre VARCHAR2(300) NOT NULL,
    usu_apellidos VARCHAR2(300) NOT NULL,
    usu_cedula VARCHAR2(100) NOT NULL,
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
CREATE UNIQUE INDEX sis_usuarios_ind02 ON sis_usuarios (usu_correo);
CREATE UNIQUE INDEX sis_usuarios_ind03 ON sis_usuarios (usu_cedula);

ALTER TABLE sis_usuarios ADD CONSTRAINT sis_usuarios_pk PRIMARY KEY (usu_id);

CREATE TABLE sis_sistemas_roles_usuarios(
    srs_rol_id NUMBER NOT NULL,
    srs_usu_id NUMBER NOT NULL
);

CREATE TABLE sis_mensajes(
    sms_id NUMBER NOT NULL,
    sms_texto CLOB NOT NULL,
    sms_tiempo DATE NOT NULL,
    sms_usu_id_emisor NUMBER NOT NULL,
    sms_chat_id NUMBER NOT NULL,
    sms_version NUMBER DEFAULT 1 NOT NULL
);

ALTER TABLE sis_mensajes ADD CONSTRAINT sis_mensajes_pk PRIMARY KEY (sms_id);

CREATE TABLE sis_chats(
    cht_id NUMBER NOT NULL,
    cht_fecha DATE NOT NULL,
    cht_emisor_id NUMBER NOT NULL,
    cht_receptor_id NUMBER NOT NULL,
    cht_version NUMBER DEFAULT 1 NOT NULL
);

ALTER TABLE sis_chats ADD CONSTRAINT sis_chats_pk PRIMARY KEY (cht_id);

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

CREATE TABLE sis_notificacion(
    not_id NUMBER NOT NULL,
    not_nombre VARCHAR2(300) NOT NULL,
    not_plantilla CLOB NOT NULL,
    not_version NUMBER DEFAULT 1 NOT NULL
);

CREATE UNIQUE INDEX sis_notificacion_ind01 ON sis_notificacion (not_nombre);

ALTER TABLE sis_notificacion ADD CONSTRAINT sis_notificacion_pk PRIMARY KEY (not_id);

CREATE TABLE sis_variables(
    var_id NUMBER NOT NULL,
    var_nombre VARCHAR2(300) NOT NULL,
    tipo VARCHAR2(300) NOT NULL,
    var_valor CLOB,
    var_not_id NUMBER NOT NULL,
    var_version NUMBER DEFAULT 1 NOT NULL
);

ALTER TABLE sis_variables ADD CONSTRAINT sis_variables_pk PRIMARY KEY (var_id);

CREATE TABLE sis_variables_condicionales(
    vcon_id NUMBER NOT NULL,
    vcond_valor VARCHAR2(300) NOT NULL,
    vcond_resultado CLOB,
    vcon_var_id NUMBER NOT NULL,
    vcon_version NUMBER DEFAULT 1 NOT NULL
);

ALTER TABLE sis_variables_condicionales ADD CONSTRAINT sis_variables_condicionales_pk PRIMARY KEY (vcon_id);

CREATE TABLE sis_variables_multimedia(
    media_id NUMBER NOT NULL,
    media_url BLOB NOT NULL,
    media_tipo VARCHAR2(300) NOT NULL
        CONSTRAINT sis_variables_multimedia_ck01 CHECK (media_tipo in ('Imagen','Video')),
    media_var_id NUMBER NOT NULL,
    media_version NUMBER DEFAULT 1 NOT NULL
);

ALTER TABLE sis_variables_multimedia ADD CONSTRAINT sis_variables_multimedia_pk PRIMARY KEY (media_id);

CREATE TABLE sis_correos(
    cor_id NUMBER NOT NULL,
    cor_asunto VARCHAR2(300) NOT NULL,
    cor_destinatario VARCHAR2(300) NOT NULL,
    cor_resultado CLOB NOT NULL,
    cor_estado VARCHAR2(1) DEFAULT 'P' NOT NULL
        CONSTRAINT sis_correos_ck01 CHECK (cor_estado in ('E','P')),
    cor_fecha DATE NOT NULL,
    cor_not_id NUMBER NOT NULL,
    cor_version NUMBER DEFAULT 1 NOT NULL
);

ALTER TABLE sis_correos ADD CONSTRAINT sis_correos_pk PRIMARY KEY (cor_id);

ALTER TABLE sis_roles
ADD CONSTRAINT sis_roles_fk01
FOREIGN KEY (rol_sis_id)
REFERENCES sis_sistemas (sis_id);

ALTER TABLE sis_sistemas_roles_usuarios
ADD CONSTRAINT sis_sistemas_roles_usuarios_fk02
FOREIGN KEY (srs_rol_id)
REFERENCES sis_roles (rol_id);

ALTER TABLE sis_sistemas_roles_usuarios
ADD CONSTRAINT sis_sistemas_roles_usuarios_fk03
FOREIGN KEY (srs_usu_id)
REFERENCES sis_usuarios (usu_id);

ALTER TABLE sis_mensajes
ADD CONSTRAINT sis_mensajes_fk01
FOREIGN KEY (sms_usu_id_emisor)
REFERENCES sis_usuarios (usu_id);

ALTER TABLE sis_mensajes
ADD CONSTRAINT sis_mensajes_fk02
FOREIGN KEY (sms_chat_id)
REFERENCES sis_chats (cht_id);

ALTER TABLE sis_chats
ADD CONSTRAINT sis_chats_fk01
FOREIGN KEY (cht_emisor_id)
REFERENCES sis_usuarios (usu_id);

ALTER TABLE sis_chats
ADD CONSTRAINT sis_chats_fk02
FOREIGN KEY (cht_receptor_id)
REFERENCES sis_usuarios (usu_id);

ALTER TABLE sis_variables
ADD CONSTRAINT sis_variables_fk01
FOREIGN KEY (var_not_id)
REFERENCES sis_notificacion (not_id);

ALTER TABLE sis_variables_condicionales
ADD CONSTRAINT sis_variables_condicionales_fk01
FOREIGN KEY (vcon_var_id)
REFERENCES sis_variables (var_id);

ALTER TABLE sis_variables_multimedia
ADD CONSTRAINT sis_variables_multimedia_fk01
FOREIGN KEY (media_var_id)
REFERENCES sis_variables (var_id);

ALTER TABLE sis_correos
ADD CONSTRAINT sis_correos_fk01
FOREIGN KEY (cor_not_id)
REFERENCES sis_notificacion (not_id);

INSERT INTO sis_parametros (par_id, par_correo, par_clave, par_puerto, par_server, par_protocolo, par_timeout, par_version)
VALUES (1, 'sigeceuna87@gmail.com', 'ptwq yxai aigf qpwp', 587, 'smtp.gmail.com', 'smtp', 15, 0);

INSERT INTO sis_notificacion (not_id, not_nombre, not_plantilla, not_version)
VALUES (1, 'Correos Comunes', 'Correos Comunes', 0);

INSERT INTO sis_notificacion (not_id, not_nombre, not_plantilla, not_version)
VALUES (2, 'Activacion de Cuenta', '<!DOCTYPE html> <html lang="es"> <head> <meta charset="UTF-8"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <title>Activacion de Cuenta</title> <style> body { font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif; background-color: #f9f9f9; margin: 0; padding: 0; } .container { max-width: 650px; margin: 30px auto; background-color: #ffffff; border-radius: 10px; overflow: hidden; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); border: 1px solid #e0e0e0; } .header { background-color: #0D47A1; padding: 30px 20px; text-align: center; color: white; } .header img { width: 200px; height: auto; margin-bottom: 15px; } .header h2 { font-size: 26px; margin: 0; font-weight: normal; } .content { padding: 40px 30px; text-align: left; } .content h1 { font-size: 24px; color: #0D47A1; margin-bottom: 15px; } .content p { font-size: 16px; color: #5f6368; margin-bottom: 20px; line-height: 1.6; } .cta { text-align: center; } .cta a { display: inline-block; text-decoration: none; background-color: #0D47A1; color: white; padding: 12px 30px; font-size: 18px; border-radius: 6px; transition: background-color 0.3s ease; } .cta a:hover { background-color: #1565C0; } .footer { background-color: #f1f1f1; padding: 20px; text-align: center; font-size: 14px; color: #7a7a7a; } .footer p { margin: 0; } .footer a { color: #0D47A1; text-decoration: none; } .footer a:hover { text-decoration: underline; } .spacer { height: 40px; } </style> </head> <body> <div class="container"> <div class="header"> <img src="https://i.ibb.co/qdw7zmV/Logo-White.png" alt="Logo SigeceUna"> <h2>Bienvenido a SigeceUna</h2> </div> <div class="content"> <h1>¡Activa tu cuenta ahora!</h1> <p>Estimado/a [Usuario],</p> <p>Nos alegra que te hayas registrado en SigeceUna. Para activar tu cuenta y empezar a disfrutar de nuestros servicios, haz clic en el boton de abajo:</p> <div class="cta"> <a href="http://localhost:8080/ChatAndMailAPI/ws/UsuariosController/activarUsuario/[Usuario]" target="_blank">Activar Cuenta</a> </div> <p>Si no solicitaste esta activacion, puedes ignorar este correo. Tu cuenta estara segura.</p> </div> <div class="spacer"></div> <div class="footer"> <p>&copy; 2024 SigeceUna. Todos los derechos reservados.</p> <p><a href="#">Politica de Privacidad</a> | <a href="#">Terminos y Condiciones</a></p> </div> </div> </body> </html>', 0);
