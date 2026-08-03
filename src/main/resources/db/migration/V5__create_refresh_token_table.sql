CREATE TABLE admincrm.refresh_tokens
(
    id              BIGSERIAL PRIMARY KEY,

    token           VARCHAR(512) NOT NULL UNIQUE,

    user_id         BIGINT NOT NULL,

    expiry_date     TIMESTAMP NOT NULL,

    revoked         BOOLEAN NOT NULL DEFAULT FALSE,

    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at      TIMESTAMP,

    CONSTRAINT fk_refresh_token_user
        FOREIGN KEY (user_id)
        REFERENCES admincrm.users(id)
);