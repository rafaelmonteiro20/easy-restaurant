ALTER TABLE user ADD COLUMN root BOOLEAN DEFAULT FALSE;

INSERT INTO user (name, mail, password, active, root) 
	VALUES ('Admin', 'admin@easyrestaurant.com', '$2a$10$g.wT4R0Wnfel1jc/k84OXuwZE02BlACSLfWy6TycGPvvEKvIm86SG', 1, 1);
