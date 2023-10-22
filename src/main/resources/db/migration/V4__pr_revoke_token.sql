CREATE OR REPLACE PROCEDURE sp_revoke_user_token(
    p_user_id IN NUMBER,
    p_is_add OUT NUMBER
)
    IS
BEGIN
    BEGIN
        UPDATE tokens
        SET expired = 1, revoked = 1
        WHERE user_id = p_user_id;

        p_is_add := 10; -- Set the output parameter to indicate success
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            p_is_add := 0; -- Set the output parameter to indicate failure
            ROLLBACK; -- Rollback the transaction
            RAISE; -- Reraise the exception
    END;
EXCEPTION
    WHEN OTHERS THEN
        p_is_add := 0; -- Set the output parameter to indicate failure
        ROLLBACK; -- Rollback the transaction
        RAISE; -- Reraise the exception
END;