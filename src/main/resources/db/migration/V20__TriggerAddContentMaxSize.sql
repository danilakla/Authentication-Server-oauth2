CREATE OR REPLACE TRIGGER qr_code_trigger
    AFTER INSERT ON C##ADMIN.QRCODE
    FOR EACH ROW
DECLARE

    isExitRow NUMBER(1);

BEGIN


    select count(*)
    into isExitRow
    from logger
    where PROFILE_ID  = :  new.PROFILE_ID;

    IF isExitRow=0
    THEN
        INSERT INTO LOGGER (QRCOUNT, LOGTIME, PROFILE_ID)
        VALUES (0, SYSTIMESTAMP, :new.PROFILE_ID);
    END IF;

    IF isExitRow=1
    THEN
        UPDATE LOGGER
        SET QRCOUNT= QRCOUNT+1,
            LOGTIME=SYSTIMESTAMP
        where PROFILE_ID  = :new.PROFILE_ID;
    end if;
END;