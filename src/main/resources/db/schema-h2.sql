CREATE TABLE sys_user_demo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(64) NOT NULL,
    email VARCHAR(128) NOT NULL,
    status VARCHAR(32) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);
