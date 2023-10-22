create table logger
(
    ID            NUMBER(19) generated as identity
        primary key,
    QRCOUNT NUMBER(19),
    logTime TIMESTAMP(6),
    profile_id    NUMBER(19)
        constraint logger_PROFILE_ID_FORKEY
            references PROFILES
)
/
