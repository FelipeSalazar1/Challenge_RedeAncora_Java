ALTER TABLE USERS ADD (CLIENT_ID NUMBER(19) NOT NULL);

ALTER TABLE USERS
    ADD CONSTRAINT FK_USER_PERSON
        FOREIGN KEY (CLIENT_ID)
            REFERENCES CLIENT(ID);
