create table POSTS
(
    ID             NUMBER(19) generated as identity
        primary key,
    CREATION_DATE  TIMESTAMP(6),
    DESCRIPTION    VARCHAR2(255 char),
    IS_PUBLIC      NUMBER(1)
        check (is_public in (0, 1)),
    REACTION_COUNT NUMBER(19),
    QR_ID          NUMBER(19)
        constraint fr_key_qr_id
            references QRCODE  ON DELETE CASCADE,
    profile_ID          NUMBER(19)
        constraint fr_key_post_profile_id
            references PROFILES  ON DELETE CASCADE
)
/

create table COMMENTS
(
    ID            NUMBER(19) generated as identity
        primary key,
    CREATION_DATE TIMESTAMP(6),
    TEXT          VARCHAR2(255 char),
    POST_ID       NUMBER(19)
        constraint fr_key_POST_ID
            references POSTS  ON DELETE CASCADE,
    PROFILE_ID    NUMBER(19)
        constraint fr_key_PROFILE_ID
            references PROFILES  ON DELETE CASCADE
)
/


create table POST_PROFILE
(
    POST_ID    NUMBER(19) not null
        constraint POST_ID_fk_Key
            references POSTS  ON DELETE CASCADE,
    PROFILE_ID NUMBER(19) not null
        constraint  profile_ID_fk_Key
            references PROFILES  ON DELETE CASCADE
)
/