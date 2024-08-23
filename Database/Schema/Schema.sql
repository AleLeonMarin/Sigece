-- create the user
CREATE USER SigeceUNA
  IDENTIFIED BY una
  DEFAULT TABLESPACE USERS
  TEMPORARY TABLESPACE TEMP
  PROFILE DEFAULT;

-- grant/revoke role privileges
GRANT RESOURCE TO SigeceUNA;

GRANT CONNECT TO SigeceUNA;

GRANT DBA TO SigeceUNA;

-- grant/revoke system privileges
GRANT CREATE SESSION TO SigeceUNA;

GRANT UNLIMITED TABLESPACE TO SigeceUNA;