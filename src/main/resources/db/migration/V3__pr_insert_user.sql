CREATE OR REPLACE PROCEDURE pr_inser_user(
    p_email IN VARCHAR2,
    p_password IN VARCHAR2,
    p_roleid IN NUMBER,
    p_is_add OUT NUMBER
)
    IS
    v_userid NUMBER;
BEGIN
    BEGIN
        INSERT INTO users (email, password)
        VALUES (p_email, p_password)
        RETURNING id INTO v_userid;
    EXCEPTION
        WHEN OTHERS THEN
            p_is_add := 0;
            RAISE;
    END;

    BEGIN
        INSERT INTO users_roles (user_id, role_id)
        VALUES (v_userid, p_roleid);
    EXCEPTION
        WHEN OTHERS THEN
            p_is_add := 0;
            RAISE;
    END;
    COMMIT;
    p_is_add := 10; -- Set the output parameter to indicate success
EXCEPTION
    WHEN OTHERS THEN
        p_is_add := 0; -- Set the output parameter to indicate failure
        ROLLBACK; -- Rollback the transaction
        RAISE; -- Reraise the exception
END;