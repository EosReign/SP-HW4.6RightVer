--liquibase formatted sql

--changeset eosreign:1
--precondition-sql-check expectedResult:1 select case when count(i.tablename) = 1 then 0 else 1 end from pg_tables t inner join pg_indexes i on t.tablename = i.tablename where t.tablename = 'student' and i.indexname = 'idx_student_name';
--onFail=Mark_RAN
CREATE INDEX idx_student_name ON student (name);

--changeset eosreign:2
--precondition-sql-check expectedResult:1 select case when count(i.tablename) = 1 then 0 else 1 end from pg_tables t inner join pg_indexes i on t.tablename = i.tablename where t.tablename = 'faculty' and i.indexname = 'idx_faculty_name_color';
--onFail=MARK_RAN
CREATE UNIQUE INDEX idx_faculty_name_color ON faculty (name, color);