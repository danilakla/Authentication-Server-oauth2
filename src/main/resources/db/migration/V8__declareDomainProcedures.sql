INSERT INTO FILETYPES (NAME)
VALUES ('video');
INSERT INTO FILETYPES (NAME)
VALUES ('audio');
INSERT INTO FILETYPES (NAME)
VALUES ('image');
INSERT INTO FILETYPES (NAME)
VALUES ('doc');

CREATE OR REPLACE PROCEDURE INSERT_PROFILE(
    p_about IN VARCHAR2,
    p_last_name IN VARCHAR2,
    p_name IN VARCHAR2,
    p_success OUT NUMBER
)
AS
BEGIN
    INSERT INTO PROFILES (ABOUT, BGIMAGE, IMAGE, LAST_NAME, NAME)
    VALUES (p_about, EMPTY_BLOB(),EMPTY_BLOB(), p_last_name, p_name);

    p_success := 1; -- Set the output parameter to 1 if the insertion is successful

EXCEPTION
    WHEN OTHERS THEN
        p_success := 0; -- Set the output parameter to 0 if an exception occurs
        RAISE; -- Reraise the exception for error handling at the caller level
END;
/


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
    VALUES (p_creation_date, p_description, p_image, p_name, p_profile_id);

    p_success := 1; -- Set the output parameter to 1 if the insertion is successful

EXCEPTION
    WHEN OTHERS THEN
        p_success := 0; -- Set the output parameter to 0 if an exception occurs
        RAISE; -- Reraise the exception for error handling at the caller level
END;
/

CREATE OR REPLACE PROCEDURE INSERT_CONTENT(
    p_data IN BLOB,
    p_filename IN VARCHAR2,
    p_qrcode_id IN NUMBER,
    p_extension IN VARCHAR2,
    p_filetype_id IN NUMBER,
    p_success OUT NUMBER
)
AS
    v_content_id NUMBER;
BEGIN
    -- Insert into CONTENTS table
    INSERT INTO CONTENTS (DATA, FILENAME, QRCODE_ID, EXTENSION)
    VALUES (p_data, p_filename, p_qrcode_id, p_extension)
    RETURNING ID INTO v_content_id;


    INSERT INTO CONTENTS_FILETYPES (CONTENT_ID, FILETYPE_ID)
    VALUES (v_content_id, p_filetype_id);

    p_success := 1; -- Set the output parameter to 1 if the insertion is successful

EXCEPTION
    WHEN OTHERS THEN
        p_success := 0; -- Set the output parameter to 0 if an exception occurs
        RAISE; -- Reraise the exception for error handling at the caller level
END;
/

CREATE OR REPLACE PROCEDURE DELETE_QRCODE(
    p_id IN NUMBER,
    p_success OUT NUMBER
)
AS
BEGIN
    DELETE
    FROM QRCODE
    WHERE ID = p_id;

    IF SQL%ROWCOUNT > 0 THEN
        p_success := 1; -- Set the output parameter to 1 if the deletion is successful
    ELSE
        p_success := 0; -- Set the output parameter to 0 if no rows were affected
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        p_success := 0; -- Set the output parameter to 0 if an exception occurs
        RAISE; -- Reraise the exception for error handling at the caller level
END;
/

CREATE OR REPLACE PROCEDURE UPDATE_QRCODE(
    p_id IN NUMBER,
    p_description IN VARCHAR2,
    p_name IN VARCHAR2,
    p_success OUT NUMBER
)
AS
BEGIN
    UPDATE QRCODE
    SET DESCRIPTION = p_description,
        NAME        = p_name
    WHERE ID = p_id;

    IF SQL%ROWCOUNT > 0 THEN
        p_success := 1; -- Set the output parameter to 1 if the update is successful
    ELSE
        p_success := 0; -- Set the output parameter to 0 if no rows were affected
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        p_success := 0; -- Set the output parameter to 0 if an exception occurs
        RAISE; -- Reraise the exception for error handling at the caller level
END;
/


CREATE OR REPLACE PROCEDURE DELETE_CONTENTS(
        p_id IN NUMBER,
    p_success OUT NUMBER
)
AS
BEGIN
    DELETE
    FROM CONTENTS
    WHERE ID = p_id;

    IF SQL%ROWCOUNT > 0 THEN
        p_success := 1; -- Set the output parameter to 1 if the deletion is successful
    ELSE
        p_success := 0; -- Set the output parameter to 0 if no rows were affected
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        p_success := 0; -- Set the output parameter to 0 if an exception occurs
        RAISE; -- Reraise the exception for error handling at the caller level
END;
/