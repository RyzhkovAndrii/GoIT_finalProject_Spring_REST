DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS events_employees;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS employee_posts;
DROP TABLE IF EXISTS employee_statuses;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS event_types;
DROP TABLE IF EXISTS departments;



CREATE TABLE users (
  id                UUID,
  username          VARCHAR(255) NOT NULL UNIQUE,
  password          VARCHAR(255) NOT NULL,
  registration_date DATE         NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE roles (
  id   UUID,
  name VARCHAR(255) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE event_types (
  id                      UUID,
  name                    VARCHAR(255) NOT NULL UNIQUE,
  hourly_rate_coefficient FLOAT        NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE departments (
  id   UUID,
  name VARCHAR(255) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE employee_posts (
  id   UUID,
  name VARCHAR(255) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE employee_statuses (
  id   UUID,
  name VARCHAR(255) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE users_roles (
  user_id UUID,
  role_id UUID,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE employees (
  id                    UUID,
  email                 VARCHAR(255) NOT NULL UNIQUE,
  first_name            VARCHAR(255) NOT NULL,
  last_name             VARCHAR(255) NOT NULL,
  hourly_rate           FLOAT        NOT NULL,
  department_id         UUID         NOT NULL,
  post_id               UUID         NOT NULL,
  status_id             UUID         NOT NULL,
  user_id               UUID UNIQUE,
  current_working_hours FLOAT        NOT NULL DEFAULT 0,
  FOREIGN KEY (department_id) REFERENCES departments (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (post_id) REFERENCES employee_posts (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (status_id) REFERENCES employee_statuses (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (id)
);

CREATE TABLE payments (
  id            UUID,
  date          DATE  NOT NULL,
  working_hours FLOAT NOT NULL DEFAULT 0,
  salary        FLOAT NOT NULL DEFAULT 0,
  employee_id   UUID  NOT NULL,
  FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (id)
);

CREATE TABLE events (
  id            UUID,
  description   VARCHAR(255),
  date          DATE  NOT NULL,
  duration      FLOAT NOT NULL,
  event_type_id UUID  NOT NULL,
  FOREIGN KEY (event_type_id) REFERENCES event_types (id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (id)
);

CREATE TABLE events_employees (
  event_id    UUID,
  employee_id UUID,
  PRIMARY KEY (event_id, employee_id),
  FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE ON UPDATE CASCADE
);
