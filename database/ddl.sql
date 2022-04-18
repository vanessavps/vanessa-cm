#Create DB
CREATE DATABASE IF NOT EXISTS cm_assignment;
USE cm_assignment;

#Create tables
CREATE TABLE IF NOT EXISTS contact
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(80) NOT NULL,
    email VARCHAR(255) DEFAULT NULL,
    phone VARCHAR(20)  DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS organisation
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(80)  DEFAULT NULL,
    url      VARCHAR(50)  DEFAULT NULL,
    address  VARCHAR(120) DEFAULT NULL,
    suburb   VARCHAR(100) DEFAULT NULL,
    city     VARCHAR(30)  DEFAULT NULL,
    postcode VARCHAR(16)  DEFAULT NULL,
    country  VARCHAR(2)   DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS organisation_contacts
(
    organisation_id INT NOT NULL,
    contact_id      INT NOT NULL,
    PRIMARY KEY (organisation_id, contact_id),
    CONSTRAINT `organisation_contact_fk`
        FOREIGN KEY (organisation_id)
            REFERENCES organisation (id)
            ON DELETE CASCADE,
    FOREIGN KEY (contact_id)
        REFERENCES contact (id)
        ON DELETE CASCADE
);