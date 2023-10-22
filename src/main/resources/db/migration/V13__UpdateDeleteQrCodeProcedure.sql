CREATE OR REPLACE PROCEDURE DELETE_QRCODE(
    p_id IN NUMBER,
    p_success OUT NUMBER
)
AS
BEGIN
    DELETE
    FROM QRCODE
    WHERE ID = p_id;

    DELETE
    FROM CONTENTS
    WHERE CONTENTS.QR_CODE_ID = p_id;

    IF SQL%ROWCOUNT > 0 THEN
        p_success := 1; -- Set the output parameter to 1 if the deletion is successful
    ELSE
        p_success := 0; -- Set the output parameter to 0 if no rows were affected
    END IF;
    commit ;
EXCEPTION
    WHEN OTHERS THEN

        p_success := 0; -- Set the output parameter to 0 if an exception occurs
        Rollback;
        RAISE; -- Reraise the exception for error handling at the caller level
END;
/
