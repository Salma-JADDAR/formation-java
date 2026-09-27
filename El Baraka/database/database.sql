CREATE DATABASE albaraka;

USE albaraka;

CREATE TABLE client (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE compte (
    id INT PRIMARY KEY AUTO_INCREMENT,
    numero VARCHAR(50) NOT NULL UNIQUE,
    solde DECIMAL(15,2) DEFAULT 0,
    id_client INT NOT NULL,
    typecompte ENUM('COURANT', 'EPARGNE') NOT NULL,
    decouvertautorise DECIMAL(15,2),
    tauxinteret DECIMAL(5,2),

    FOREIGN KEY (id_client)REFERENCES client(id) ON DELETE CASCADE,
    CHECK (decouvertautorise IS NULL OR decouvertautorise >= 0),
    CHECK (tauxinteret IS NULL OR tauxinteret >= 0)
);

CREATE TABLE transactions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    datetransaction DATE NOT NULL,
    montant DECIMAL(15,2) NOT NULL,
    typetransaction ENUM('VERSEMENT', 'RETRAIT', 'VIREMENT') NOT NULL,
    lieu VARCHAR(100) NOT NULL,
    id_compte INT NOT NULL,

    FOREIGN KEY (id_compte)REFERENCES compte(id)ON DELETE RESTRICT,
    CHECK (montant > 0)
);