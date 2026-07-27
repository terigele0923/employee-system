ALTER TABLE employees
    ADD CONSTRAINT ck_employees_employee_no_format
    CHECK (
        CHAR_LENGTH(employee_no) = 10
        AND LEFT(employee_no, 1) = BINARY 'E'
        AND employee_no REGEXP '^[A-Za-z0-9]+$'
    );
