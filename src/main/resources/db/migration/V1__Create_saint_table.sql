CREATE TABLE saint (
        id BIGSERIAL PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        day INT NOT NULL,
        month INT NOT NULL,
        patronage TEXT,
        description TEXT,
        tropar TEXT,
        kondak TEXT,
        is_martyr BOOLEAN NOT NULL,
        imageLink VARCHAR(255)

    );