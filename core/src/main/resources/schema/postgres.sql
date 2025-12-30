-- Drop tables if they exist (in reverse order of dependencies)
DROP TABLE IF EXISTS authority CASCADE;
DROP TABLE IF EXISTS admin CASCADE;
DROP TABLE IF EXISTS permission CASCADE;
DROP TABLE IF EXISTS role CASCADE;

create table role(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table admin (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(256) NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL,
    name VARCHAR(256) DEFAULT NULL,
    role_id INTEGER NOT NULL DEFAULT 0,
    is_enabled BOOLEAN DEFAULT TRUE,
    is_super_admin BOOLEAN DEFAULT FALSE,
    last_login_time TIMESTAMP,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    constraint fk_admin_role foreign key(role_id) references role(id)
);

create table permission(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    type varchar(256) not null unique,
    is_enabled boolean default true
);

create table authority(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role_id INTEGER NOT NULL,
    permission_type VARCHAR(256) NOT NULL,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    constraint fk_authority_role foreign key(role_id) references role(id),
    constraint fk_permission_type foreign key(permission_type) references permission(type)
);

