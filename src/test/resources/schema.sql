CREATE TABLE `department`(
    `id` INT,
    `dep_name` VARCHAR(20),
    `dep_location` VARCHAR(20)
);

CREATE TABLE `employee_table`(
    `name` VARCHAR(20),
    `email` VARCHAR(20),
    `dep_id` INT,
    `joining_date` DATE,
    `id` INT AUTO_INCREMENT PRIMARY KEY
);