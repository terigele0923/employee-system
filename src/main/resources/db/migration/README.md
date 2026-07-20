# Flyway migration files

Copy the five SQL files into:

`src/main/resources/db/migration/`

Execution order:

1. `V1__create_auth_tables.sql` — roles and users
2. `V2__create_employee_master_tables.sql` — skills, clients, employees and employee skills
3. `V3__create_project_assignment_tables.sql` — projects, project skills and assignments
4. `V4__create_interview_matching_tables.sql` — interviews, questions and matching results
5. `V5__insert_initial_data.sql` — initial roles, local administrator, skills and clients

Local administrator:

- Login ID: `admin`
- Password: `password`

Change the password before using the system outside local development.

If the target database already has Flyway V1–V5 history, do not replace the
old files with these files. Create a new empty database or continue with a new
version number.
