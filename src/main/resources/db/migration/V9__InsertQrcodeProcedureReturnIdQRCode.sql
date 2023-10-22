CREATE OR REPLACE PROCEDURE INSERT_QRCODE(
    p_creation_date IN TIMESTAMP,
    p_description IN VARCHAR2,
    p_image IN BLOB,
    p_name IN VARCHAR2,
    p_profile_id IN NUMBER,
    p_success OUT NUMBER
)
AS
BEGIN
    INSERT INTO QRCODE (CREATION_DATE, DESCRIPTION, IMAGE, NAME, PROFILE_ID)
    VALUES (p_creation_date, p_description, p_image, p_name, p_profile_id)
    RETURNING ID INTO p_success;


EXCEPTION
    WHEN OTHERS THEN
        p_success := 0; -- Set the output parameter to 0 if an exception occurs
        RAISE; -- Reraise the exception for error handling at the caller level
END;
/