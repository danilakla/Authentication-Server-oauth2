CREATE OR REPLACE PROCEDURE PUT_REACTION_TO_Post(
    p_post_id IN NUMBER,
    p_profile_id IN NUMBER,
    p_success OUT number
) AS
BEGIN
    INSERT INTO POST_PROFILE (POST_ID, PROFILE_ID)
    VALUES (p_post_id,p_profile_id);

    UPDATE  POSTS SET REACTION_COUNT =REACTION_COUNT+1
    WHERE PROFILE_ID = PROFILE_ID;

    p_success := 1;
    COMMIT ;
EXCEPTION
    WHEN OTHERS THEN
        p_success := 0;
END;