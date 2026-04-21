CREATE TABLE saint (
        id BIGSERIAL PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        feast_day INT NOT NULL,
        feast_month INT NOT NULL,
        patronage VARCHAR(255),
        description TEXT,
        tropar TEXT,
        kondak TEXT,
        is_martyr BOOLEAN NOT NULL
    );