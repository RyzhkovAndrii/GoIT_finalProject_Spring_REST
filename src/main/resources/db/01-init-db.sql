DROP ALL OBJECTS;

CREATE TABLE `users` (
  `id`                VARBINARY(MAX),
  `username`          VARCHAR(255) NOT NULL UNIQUE,
  `password`          VARCHAR(255) NOT NULL,
  `registration_date` DATE         NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `roles` (
  `id`   VARBINARY(MAX),
  `name` VARCHAR(255) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `event_types` (
  `id`                      VARBINARY(MAX),
  `name`                    VARCHAR(255) NOT NULL UNIQUE,
  `hourly_rate_coefficient` FLOAT        NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `departments` (
  `id`   VARBINARY(MAX),
  `name` VARCHAR(255) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `employee_posts` (
  `id`   VARBINARY(MAX),
  `name` VARCHAR(255) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `employee_statuses` (
  `id`   VARBINARY(MAX),
  `name` VARCHAR(255) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `users_roles` (
  `user_id` VARBINARY(MAX),
  `role_id` VARBINARY(MAX),
  PRIMARY KEY (`user_id`, `role_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `employees` (
  `id`                    VARBINARY(MAX),
  `email`                 VARCHAR(255)   NOT NULL UNIQUE,
  `first_name`            VARCHAR(255)   NOT NULL,
  `last_name`             VARCHAR(255)   NOT NULL,
  `hourly_rate`           FLOAT          NOT NULL,
  `department_id`         VARBINARY(MAX) NOT NULL,
  `post_id`               VARBINARY(MAX) NOT NULL,
  `status_id`             VARBINARY(MAX) NOT NULL,
  `user_id`               VARBINARY(MAX) UNIQUE,
  `current_working_hours` FLOAT          NOT NULL DEFAULT 0,
  FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`post_id`) REFERENCES `employee_posts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`status_id`) REFERENCES `employee_statuses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `payments` (
  `id`            VARBINARY(MAX),
  `date`          DATE           NOT NULL,
  `working_hours` FLOAT          NOT NULL DEFAULT 0,
  `salary`        FLOAT          NOT NULL DEFAULT 0,
  `employee_id`   VARBINARY(MAX) NOT NULL,
  FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `events` (
  `id`            VARBINARY(MAX),
  `description`   VARCHAR(255),
  `date`          DATE           NOT NULL,
  `duration`      FLOAT          NOT NULL,
  `event_type_id` VARBINARY(MAX) NOT NULL,
  FOREIGN KEY (`event_type_id`) REFERENCES `event_types` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
);

CREATE TABLE `events_employees` (
  `event_id`    VARBINARY(MAX),
  `employee_id` VARBINARY(MAX),
  PRIMARY KEY (`event_id`, `employee_id`),
  FOREIGN KEY (`event_id`) REFERENCES `events` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO `roles` VALUES ('e6b2df9495074e7097eea54583962aa6', 'ADMIN_ROLE');
INSERT INTO `roles` VALUES ('fc99b7be54bb4a4c9e2236b7805d1a7f', 'MODERATOR_ROLE');
INSERT INTO `roles` VALUES ('d0599670b4e94edf9c25cbd9a271f17e', 'USER_ROLE');

INSERT INTO `users` VALUES ('02deb31e9d5147e5b8d284559326a3f2', 'admin', 'admin', '2018-01-18');
INSERT INTO `users` VALUES ('9373caf647a847fbbae3f36efd8219a8', 'moderatorLastName', 'moderator', '2018-09-17');
INSERT INTO `users` VALUES ('d2129e2edfe449bc85dd3864282aafac', 'user', 'user', '2018-01-01');

INSERT INTO `users_roles` VALUES ('02deb31e9d5147e5b8d284559326a3f2', 'e6b2df9495074e7097eea54583962aa6');
INSERT INTO `users_roles` VALUES ('02deb31e9d5147e5b8d284559326a3f2', 'fc99b7be54bb4a4c9e2236b7805d1a7f');
INSERT INTO `users_roles` VALUES ('02deb31e9d5147e5b8d284559326a3f2', 'd0599670b4e94edf9c25cbd9a271f17e');
INSERT INTO `users_roles` VALUES ('9373caf647a847fbbae3f36efd8219a8', 'fc99b7be54bb4a4c9e2236b7805d1a7f');
INSERT INTO `users_roles` VALUES ('9373caf647a847fbbae3f36efd8219a8', 'd0599670b4e94edf9c25cbd9a271f17e');
INSERT INTO `users_roles` VALUES ('d2129e2edfe449bc85dd3864282aafac', 'd0599670b4e94edf9c25cbd9a271f17e');

INSERT INTO `departments` VALUES ('0011fdaf0f5f4b8394daaa45d03027c0', 'Human resources');
INSERT INTO `departments` VALUES ('b4990c1c811d41daac715e26f6f68aa0', 'Marketing');

INSERT INTO `employee_statuses` VALUES ('d3590bd90db64d7e93ac6ed2826ec537', 'ON_WORK');
INSERT INTO `employee_statuses` VALUES ('30bc2e27ac954cfd87173ce6c9c22d1c', 'ON_HOLIDAY');
INSERT INTO `employee_statuses` VALUES ('776e727a811d4e33833e50ad9122a070', 'ON_HOSPITAL');

INSERT INTO `event_types` VALUES ('20f1eecdbd744a15b9e25b4f16e9c454', 'WORKING_DAY', 1);
INSERT INTO `event_types` VALUES ('e03236394ab94dfba92a39522cab5e4c', 'LEARNING', 0.8);

INSERT INTO `employee_posts` VALUES ('cdf1e4492cca4c3f8851fdc6cd24cf8b', 'Manager');
INSERT INTO `employee_posts` VALUES ('07cfb0f6377e44259b4d330d899c6f14', 'Chief');

INSERT INTO `employees` VALUES ('0df0f9b8dc0440b896a04d812058c42d', 'test@mail', 'testName', 'testLastName',
                                8, '0011fdaf0f5f4b8394daaa45d03027c0', 'cdf1e4492cca4c3f8851fdc6cd24cf8b',
                                'd3590bd90db64d7e93ac6ed2826ec537', 'd2129e2edfe449bc85dd3864282aafac', 6);

INSERT INTO `payments`
VALUES ('f6e7f06455e44c8cadbac9f3f1f2b1c5', '2018-01-01', 10, 80, '0df0f9b8dc0440b896a04d812058c42d');
INSERT INTO `payments`
VALUES ('18adb269470c4d22b9a0cc2c85304182', '2018-01-02', 15, 120, '0df0f9b8dc0440b896a04d812058c42d');

INSERT INTO `events` VALUES ('52a72a2026fe471baab2d26527a6fdec', 'some description', '2018-03-05', 8,
                             '20f1eecdbd744a15b9e25b4f16e9c454');
INSERT INTO `events` VALUES ('bc09857bdc1f44498c122ad04726d1fc', 'some description', '2018-03-08', 4,
                             'e03236394ab94dfba92a39522cab5e4c');

INSERT INTO `events_employees` VALUES ('52a72a2026fe471baab2d26527a6fdec', '0df0f9b8dc0440b896a04d812058c42d');
INSERT INTO `events_employees` VALUES ('bc09857bdc1f44498c122ad04726d1fc', '0df0f9b8dc0440b896a04d812058c42d');