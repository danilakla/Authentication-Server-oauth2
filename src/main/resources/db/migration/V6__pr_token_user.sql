CREATE OR REPLACE PROCEDURE sp_insert_token(
    p_userid IN NUMBER,
    p_token IN VARCHAR2,
    p_is_add OUT NUMBER
)
    IS
BEGIN
    BEGIN
        INSERT INTO tokens (expired, revoked, token, user_id)
        VALUES (0, 0, p_token, p_userid);
        p_is_add := 10; -- Set the output parameter to indicate success
    EXCEPTION
        WHEN OTHERS THEN
            p_is_add := 0; -- Set the output parameter to indicate failure
            RAISE; -- Reraise the exception
    END;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        p_is_add := 0; -- Set the output parameter to indicate failure
        ROLLBACK; -- Rollback the transaction
        RAISE; -- Reraise the exception
END;