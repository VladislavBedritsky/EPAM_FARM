CREATE TABLE employees (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    date_of_birth DATE,
    salary REAL,
    department_id INT,

    PRIMARY KEY (id),
    INDEX department_id (department_id),

    FOREIGN KEY (department_id)
        REFERENCES departments(id)
        ON DELETE CASCADE
);
