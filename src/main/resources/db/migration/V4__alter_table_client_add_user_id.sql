ALTER TABLE client
    ADD user_id NUMBER NOT NULL;

ALTER TABLE client
    ADD CONSTRAINT fk_client_user
        FOREIGN KEY (user_id)
            REFERENCES users(id);