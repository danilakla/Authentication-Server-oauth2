

ALTER TABLE PROFILES ADD EMAIL VARCHAR2(255 char);

/
CREATE OR REPLACE PROCEDURE INSERT_PROFILE(
    p_about IN VARCHAR2,
    p_last_name IN VARCHAR2,
    p_name IN VARCHAR2,
    p_email IN VARCHAR2,
    p_success OUT NUMBER
)
AS
BEGIN
    INSERT INTO PROFILES (ABOUT, BGIMAGE, IMAGE, LAST_NAME, NAME, EMAIL)
    VALUES (p_about, EMPTY_BLOB(),EMPTY_BLOB(), p_last_name, p_name, p_email)
    RETURNING ID INTO p_success;

EXCEPTION
    WHEN OTHERS THEN
        p_success := 0; -- Set the output parameter to 0 if an exception occurs
        RAISE; -- Reraise the exception for error handling at the caller level
END;
/

