CREATE TABLE relic (
    id SERIAL PRIMARY KEY,
    type VARCHAR(255),
    location VARCHAR(255),
    is_verified BOOLEAN NOT NULL,
    description TEXT,
    x DOUBLE PRECISION,
    y DOUBLE PRECISION,
    saint_id BIGINT NOT NULL,
    CONSTRAINT fk_saint FOREIGN KEY (saint_id) REFERENCES saint(id)
);