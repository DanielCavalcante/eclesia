CREATE TABLE members (
	id BIGSERIAL NOT NULL,
	name TEXT NOT NULL,
	phone TEXT,
	email TEXT,
	ministry TEXT,
	conversion_date DATE,
	image TEXT,
	birth_date DATE NOT NULL,
	address_id BIGINT,	
	CONSTRAINT pk_members PRIMARY KEY (id)
);

CREATE TABLE users (
	id BIGSERIAL NOT NULL,
	name TEXT NOT NULL,
	login TEXT NOT NULL,
	password TEXT NOT NULL,
	root BOOLEAN DEFAULT FALSE,
	CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE addresses (
	id BIGSERIAL NOT NULL,
	street TEXT,
	cep TEXT,
	city TEXT,
	state TEXT,
	CONSTRAINT pk_addresses PRIMARY KEY (id)
);

ALTER TABLE members ADD CONSTRAINT fk_members_address_id FOREIGN KEY (address_id) REFERENCES addresses (id);
