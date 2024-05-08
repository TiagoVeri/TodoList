INSERT INTO `USER_PROFILE` (name, email, username, password, role, address_id, created_at)
VALUES ('João Silva', 'joao@example.com', 'joaosilva', '123', 1, NULL, CURRENT_TIMESTAMP);

INSERT INTO `address` (street, number, city, country)
VALUES ('Rua das Flores', 123, 'São Paulo', 'Brasil');

UPDATE `USER_PROFILE` SET address_id = 1 WHERE id = 1;
