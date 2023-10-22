CREATE OR REPLACE PROCEDURE CREATE_POST(
    p_description IN VARCHAR2,
    p_is_public IN NUMBER,
    p_qr_id IN NUMBER,
    p_profile_id IN NUMBER,
    p_success  OUT NUMBER
) AS
BEGIN
    INSERT INTO POSTS (CREATION_DATE, DESCRIPTION, IS_PUBLIC, REACTION_COUNT,PROFILE_ID, QR_ID)
    VALUES (SYSTIMESTAMP, p_description, p_is_public, 0,p_profile_id, p_qr_id);

    p_success := 1;
EXCEPTION
    WHEN OTHERS THEN
        p_success := 0;
END;
/
CREATE OR REPLACE PROCEDURE DELETE_POST(
    p_post_id IN NUMBER,
    p_profile_id IN NUMBER,
    p_success OUT NUMBER
) AS
BEGIN
    DELETE FROM POSTS WHERE ID = p_post_id
                                 AND PROFILE_ID=p_profile_id;

    DELETE FROM COMMENTS
         WHERE  COMMENTS.POST_ID= p_post_id;

    p_success:= 1;
    commit ;
EXCEPTION
    WHEN OTHERS THEN
        p_success := 0;
END;
/
CREATE OR REPLACE PROCEDURE UPDATE_DESCRIPTION(
    p_post_id IN NUMBER,
    p_description IN VARCHAR2,
    p_profile_id IN NUMBER,
    p_success OUT NUMBER
) AS
BEGIN
    UPDATE POSTS SET DESCRIPTION = p_description WHERE ID = p_post_id
                                                            AND PROFILE_ID=p_profile_id;


    p_success := 1;
EXCEPTION
    WHEN OTHERS THEN
        p_success := 0;
END;
/

CREATE OR REPLACE PROCEDURE UPDATE_ACCESS(
    p_post_id IN NUMBER,
    p_is_public IN NUMBER,
    p_profile_id IN NUMBER,

    p_success OUT number
) AS
BEGIN
    UPDATE POSTS SET IS_PUBLIC = p_is_public WHERE ID = p_post_id
                                                        AND PROFILE_ID=p_profile_id;

    p_success := 1;
EXCEPTION
    WHEN OTHERS THEN
        p_success := 0;
END;
/


CREATE OR REPLACE PROCEDURE PUT_REACTION_TO_Post(
    p_post_id IN NUMBER,
    p_profile_id IN NUMBER,
    p_success OUT number
) AS
BEGIN
    INSERT INTO POST_PROFILE (POST_ID, PROFILE_ID)
    VALUES (p_post_id,p_profile_id);

    p_success := 1;
EXCEPTION
    WHEN OTHERS THEN
        p_success := 0;
END;
/
