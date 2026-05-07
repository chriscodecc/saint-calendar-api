CREATE TABLE saint_links (
    saint_id BIGINT NOT NULL,
    link_url VARCHAR(255) NOT NULL,
    FOREIGN KEY (saint_id) REFERENCES saint(id) ON DELETE CASCADE
);