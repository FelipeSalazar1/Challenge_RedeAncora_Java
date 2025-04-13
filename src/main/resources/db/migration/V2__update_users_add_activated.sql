ALTER TABLE users ADD activated NUMBER(1);
update users set activated = 1;