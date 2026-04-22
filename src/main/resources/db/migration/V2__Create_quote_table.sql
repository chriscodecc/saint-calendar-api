CREATE TABLE quote (
    id BIGSERIAL PRIMARY KEY,
    text TEXT NOT NULL,
    source VARCHAR(255),
    saint_id BIGINT NOT NULL,
    CONSTRAINT fk_saint FOREIGN KEY (saint_id) REFERENCES saint(id) ON DELETE CASCADE
);