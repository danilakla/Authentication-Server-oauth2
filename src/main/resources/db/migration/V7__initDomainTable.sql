create table PROFILES
(
    ID        NUMBER(19) generated as identity
        primary key,
    ABOUT     VARCHAR2(255 char),
    BGIMAGE   BLOB,
    IMAGE     BLOB,
    LAST_NAME VARCHAR2(255 char),
    NAME      VARCHAR2(255 char)
)
/


create table QRCODE
(
    ID            NUMBER(19) generated as identity
        primary key,
    CREATION_DATE TIMESTAMP(6),
    DESCRIPTION   VARCHAR2(255 char),
    IMAGE         BLOB,
    NAME          VARCHAR2(255 char),

    PROFILE_ID    NUMBER(19)
        constraint PROFILE_ID_FORKEY
            references PROFILES
)
/

create table CONTENTS
(
    ID        NUMBER(19) generated as identity
        primary key,
    DATA      BLOB,
    FILENAME  VARCHAR2(255 char),
    QRCODE_ID NUMBER(19)
        constraint QRCODE_ID_FORKEY
            references QRCODE,
    EXTENSION VARCHAR2(255 char)
)
/


create table FILETYPES
(
    ID   NUMBER(19) generated as identity
        primary key,
    NAME VARCHAR2(255 char)
)
/


create table CONTENTS_FILETYPES
(
    ID   NUMBER(19) generated as identity
        primary key,
    CONTENT_ID  NUMBER(19) not null
        constraint CONTENT_ID_FORKEY
            references CONTENTS,
    FILETYPE_ID NUMBER(19) not null
        constraint FILETYPE_ID_FORKEY
            references FILETYPES
)
/

