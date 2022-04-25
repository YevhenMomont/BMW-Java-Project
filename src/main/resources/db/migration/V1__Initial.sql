CREATE TABLE address
(
    id      SERIAL PRIMARY KEY NOT NULL,
    street  TEXT               NOT NULL,
    suite   TEXT               NOT NULL,
    city    TEXT               NOT NULL,
    zipcode TEXT               NOT NULL,
    lat     DECIMAL(7, 4)      NOT NULL,
    lng     DECIMAL(7, 4)      NOT NULL,
    CONSTRAINT unq_address_lat_lng_idx UNIQUE (lat, lng)
);

CREATE TABLE company
(
    id           SERIAL PRIMARY KEY NOT NULL,
    name         TEXT               NOT NULL,
    catch_phrase TEXT,
    bs           TEXT,
    CONSTRAINT unq_company_name_idx UNIQUE (name)
);

CREATE TABLE "user"
(
    id         INT PRIMARY KEY NOT NULL,
    name       TEXT            NOT NULL,
    username   TEXT            NOT NULL,
    email      TEXT            NOT NULL,
    phone      TEXT,
    website    TEXT,
    address_id BIGINT          NOT NULL,
    company_id BIGINT          NOT NULL,
    CONSTRAINT fk_user_address_id_to_address_id FOREIGN KEY (address_id) REFERENCES address,
    CONSTRAINT fk_user_company_id_to_company_id FOREIGN KEY (company_id) REFERENCES company,
    CONSTRAINT unq_user_email_idx UNIQUE (email),
    CONSTRAINT unq_user_username_idx UNIQUE (username)
);
