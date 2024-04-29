-- add insert for car table and columns CAR_ID, CAR_AVAILABILITY, PRICE_PER_HOUR, BRAND, CAR_MODEL, REGISTRATION_NUMBER, THUMBNAIL
INSERT INTO car (CAR_ID, CAR_AVAILABILITY, PRICE_PER_HOUR, BRAND, CAR_MODEL, REGISTRATION_NUMBER, THUMBNAIL) VALUES (1, 1, 10.0, 'Toyota', 'Corolla', 'ABC-123', 'https://www.toyota.com/content/vehicle-landing/2021/corolla/site-specs/en/1.8l-4-cyl-hybrid/2021-corolla');
INSERT INTO car (CAR_ID, CAR_AVAILABILITY, PRICE_PER_HOUR, BRAND, CAR_MODEL, REGISTRATION_NUMBER, THUMBNAIL) VALUES (2, 1, 15.0, 'Toyota', 'Camry', 'DEF-456', 'https://www.toyota.com/content/vehicle-landing/2021/camry/site-specs/en/2.5l-4-cyl/2021-camry');
INSERT INTO car (CAR_ID, CAR_AVAILABILITY, PRICE_PER_HOUR, BRAND, CAR_MODEL, REGISTRATION_NUMBER, THUMBNAIL) VALUES (3, 1, 20.0, 'Toyota', 'RAV4', 'GHI-789', 'https://www.toyota.com/content/vehicle-landing/2021/rav4/site-specs/en/2.5l-4-cyl/2021-rav4');

-- add 5 more cars to the car table using brand Fiat
INSERT INTO car (CAR_ID, CAR_AVAILABILITY, PRICE_PER_HOUR, BRAND, CAR_MODEL, REGISTRATION_NUMBER, THUMBNAIL) VALUES (4, 1, 25.0, 'Fiat', '500', 'JKL-101', 'https://www.fiat.com/content/vehicle-landing/2021/500/site-specs/en/1.4l-4-cyl/2021-500');
INSERT INTO car (CAR_ID, CAR_AVAILABILITY, PRICE_PER_HOUR, BRAND, CAR_MODEL, REGISTRATION_NUMBER, THUMBNAIL) VALUES (5, 1, 30.0, 'Fiat', '500X', 'MNO-202', 'https://www.fiat.com/content/vehicle-landing/2021/500x/site-specs/en/1.3l-4-cyl/2021-500x');
INSERT INTO car (CAR_ID, CAR_AVAILABILITY, PRICE_PER_HOUR, BRAND, CAR_MODEL, REGISTRATION_NUMBER, THUMBNAIL) VALUES (6, 1, 35.0, 'Fiat', '500L', 'PQR-303', 'https://www.fiat.com/content/vehicle-landing/2021/500l/site-specs/en/1.4l-4-cyl/2021-500l');

-- add 3 more cars to the car table using brand Ford
INSERT INTO car (CAR_ID, CAR_AVAILABILITY, PRICE_PER_HOUR, BRAND, CAR_MODEL, REGISTRATION_NUMBER, THUMBNAIL) VALUES (7, 1, 40.0, 'Ford', 'Fiesta', 'STU-404', 'https://www.ford.com/content/vehicle-landing/2021/fiesta/site-specs/en/1.6l-4-cyl/2021-fiesta');
INSERT INTO car (CAR_ID, CAR_AVAILABILITY, PRICE_PER_HOUR, BRAND, CAR_MODEL, REGISTRATION_NUMBER, THUMBNAIL) VALUES (8, 1, 45.0, 'Ford', 'Focus', 'VWX-505', 'https://www.ford.com/content/vehicle-landing/2021/focus/site-specs/en/2.0l-4-cyl/2021-focus');
INSERT INTO car (CAR_ID, CAR_AVAILABILITY, PRICE_PER_HOUR, BRAND, CAR_MODEL, REGISTRATION_NUMBER, THUMBNAIL) VALUES (9, 1, 50.0, 'Ford', 'Fusion', 'YZA-606', 'https://www.ford.com/content/vehicle-landing/2021/fusion/site-specs/en/2.5l-4-cyl/2021-fusion');

INSERT INTO user (user_name, user_email, proof_id, user_password) VALUES ('admin', 'admin@example.com', 'ID001', '$2a$10$fk73ooUnj.tH.YOEFrskVu18pjxd83/AZreQZOcNlMbz3nZ/fLg5S');
INSERT INTO user (user_name, user_email, proof_id, user_password) VALUES ('user', 'user@example.com', 'ID002', '$2a$10$2vsEVwYnP4/T2i8m76EwfezrGfr1mvx0DkG41CPMTD/BKzJPqEqie');

-- insert 2 roles into the role table
INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');

--insert 3 user roles into the user_role table for user john with role ROLE_USER and ROLE_ADMIN and for user jane with role ROLE_USER
INSERT INTO user_role (user_name, role_id) VALUES ('admin', 1);
INSERT INTO user_role (user_name, role_id) VALUES ('admin', 2);
INSERT INTO user_role (user_name, role_id) VALUES ('user', 1);

