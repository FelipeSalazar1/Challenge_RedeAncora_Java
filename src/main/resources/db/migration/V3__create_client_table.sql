CREATE TABLE client (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(255),
    cpf_cnpj VARCHAR(20),
    phone VARCHAR(20),
    address VARCHAR(255)
);

CREATE SEQUENCE SEQ_CLIENT
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;