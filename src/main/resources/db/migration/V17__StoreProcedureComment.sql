CREATE OR REPLACE PROCEDURE ADD_COMMENT(
    p_text IN VARCHAR2,
    p_post_id IN NUMBER,
    p_profile_id IN NUMBER,
    p_success OUT NUMBER
) AS
BEGIN
    INSERT INTO COMMENTS (CREATION_DATE, TEXT, POST_ID, PROFILE_ID)
    VALUES (SYSTIMESTAMP, p_text, p_post_id, p_profile_id);

    p_success := 1;
EXCEPTION
    WHEN OTHERS THEN
        p_success := 0;
END;
/