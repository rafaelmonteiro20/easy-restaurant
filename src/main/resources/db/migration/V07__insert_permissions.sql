ALTER TABLE permission ADD COLUMN role VARCHAR(50) NOT NULL;

INSERT INTO permission (name, role) VALUES ('permission.create_product',   'ROLE_CREATE_PRODUCT');
INSERT INTO permission (name, role) VALUES ('permission.read_products',    'ROLE_READ_PRODUCTS');
INSERT INTO permission (name, role) VALUES ('permission.create_user',      'ROLE_CREATE_USER');
INSERT INTO permission (name, role) VALUES ('permission.read_users', 	   'ROLE_READ_USERS');
INSERT INTO permission (name, role) VALUES ('permission.create_customer',  'ROLE_CREATE_CUSTOMER');
INSERT INTO permission (name, role) VALUES ('permission.read_customers',   'ROLE_READ_CUSTOMERS');
INSERT INTO permission (name, role) VALUES ('permission.create_category',  'ROLE_CREATE_CATEGORY');
INSERT INTO permission (name, role) VALUES ('permission.read_categories',  'ROLE_READ_CATEGORIES');
INSERT INTO permission (name, role) VALUES ('permission.delete_category',  'ROLE_DELETE_CATEGORY');
INSERT INTO permission (name, role) VALUES ('permission.create_order', 	   'ROLE_CREATE_ORDER');
INSERT INTO permission (name, role) VALUES ('permission.read_orders',      'ROLE_READ_ORDERS');
INSERT INTO permission (name, role) VALUES ('permission.update_order',     'ROLE_UPDATE_ORDER');
INSERT INTO permission (name, role) VALUES ('permission.cancel_order',     'ROLE_CANCEL_ORDER');
INSERT INTO permission (name, role) VALUES ('permission.create_group',     'ROLE_CREATE_GROUP');
INSERT INTO permission (name, role) VALUES ('permission.read_groups',      'ROLE_READ_GROUPS');
