-- Drop tables if they exist (in reverse order of dependencies)
DROP TABLE IF EXISTS authority CASCADE;
DROP TABLE IF EXISTS admin CASCADE;
DROP TABLE IF EXISTS permission CASCADE;
DROP TABLE IF EXISTS role CASCADE;

create table role(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table admin (
    id uuid PRIMARY KEY,
    username VARCHAR(256) NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL,
    nickname VARCHAR(256) DEFAULT NULL,
    role_id INTEGER NOT NULL DEFAULT 0,
    enabled BOOLEAN DEFAULT TRUE,
    super_admin BOOLEAN DEFAULT FALSE,
    last_login_time TIMESTAMP,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    constraint fk_admin_role foreign key(role_id) references role(id)
);

create table permission(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    permission_name varchar(256) not null unique,
    menu_id id not null,
    enabled boolean default true,
    constraint fk_menu_key foreign key(menu_id) references menu(id)
);

create table menu(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    menu_sig varchar(256) not null unique,
    parent_id integer null default null
)

create table authority(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role_id INTEGER NOT NULL,
    permission_type VARCHAR(256) NOT NULL,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    constraint fk_authority_role foreign key(role_id) references role(id),
    constraint fk_permission_type foreign key(permission_type) references permission(type)
);

