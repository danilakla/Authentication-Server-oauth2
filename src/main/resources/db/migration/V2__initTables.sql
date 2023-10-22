create sequence TOKENS_SEQ
    increment by 50
/

create table USERS
(
    ID       NUMBER(19) generated as identity
        primary key,
    EMAIL    VARCHAR2(255 char),
    PASSWORD VARCHAR2(255 char)
)
/



create table TOKENS
(

    ID      NUMBER(10) generated as identity
        primary key,
    EXPIRED NUMBER(1) not null
        check (expired in (0, 1)),
    REVOKED NUMBER(1) not null
        check (revoked in (0, 1)),
    TOKEN   VARCHAR2(1000 char)
        constraint TOKEN_constraint
            unique,
    USER_ID NUMBER(19)
        constraint user_id_constraint
            references USERS
)
/


create table ROLES
(
    ID   NUMBER(10) generated as identity
        primary key,
    NAME VARCHAR2(255 char)
)
/


create table USERS_ROLES
(
    ID      NUMBER(10) generated as identity
        primary key,
    USER_ID NUMBER(19) not null
        constraint USER_ID_constraint_us_role
            references USERS,
    ROLE_ID NUMBER(10) not null
        constraint roles_constraint_us_role
            references ROLES
)
/


insert into roles (name) values ('user');
insert into roles (name) values ('admin');
