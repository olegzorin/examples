/*==============================================================*/
/*  Default transaction isolation level must be READ-COMMITTED  */
/*==============================================================*/
-- For MySQL 8.0:
show global variables like 'transaction_isolation';
SET GLOBAL TRANSACTION ISOLATION LEVEL READ COMMITTED;

/*==============================================================*/
/* GreenX Server database                                       */
/*==============================================================*/
CREATE DATABASE IF NOT EXISTS examples
    CHARACTER SET utf8
    COLLATE utf8_bin;

USE examples;

CREATE TABLE `dept`
(
    `dept_no`   integer     NOT NULL COMMENT 'Department\'s identification number',
    `dept_name` varchar(20) NOT NULL COMMENT 'Name of the current department',
    `location`  varchar(20) NOT NULL COMMENT 'Location of the current department',
    PRIMARY KEY (`dept_no`)
) COMMENT 'Company departments, with employees';

ALTER TABLE `dept`
    COMMENT 'All company\'s departments, with employees';

INSERT INTO `dept`
VALUES (10, 'ACCOUNTING', 'NEW YORK');
INSERT INTO `dept`
VALUES (20, 'RESEARCH', 'DALLAS');
INSERT INTO `dept`
VALUES (30, 'SALES', 'CHICAGO');
INSERT INTO `dept`
VALUES (40, 'OPERATIONS', 'BOSTON');


CREATE TABLE `emp`
(
    `emp_no`    integer     NOT NULL,
    `emp_name`  varchar(20) NOT NULL,
    `job`       varchar(20) NOT NULL,
    `mgr`       integer,
    `hire_date` date        NOT NULL,
    `sal`       integer     NOT NULL,
    `comm`      integer,
    `dept_no`   integer     NOT NULL,

    PRIMARY KEY (`emp_no`),
    CONSTRAINT `fk_MGR` FOREIGN KEY (`MGR`) REFERENCES `emp` (`emp_no`)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    CONSTRAINT `fk_dept_no` FOREIGN KEY (`dept_no`) REFERENCES `dept` (`dept_no`)
        ON DELETE RESTRICT
        ON UPDATE NO ACTION
);

INSERT INTO `emp`
VALUES (7839, 'KING', 'PRESIDENT', NULL, '1981-11-17', 5000, NULL, 10);
INSERT INTO `emp`
VALUES (7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01', 2850, NULL, 30);
INSERT INTO `emp`
VALUES (7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28', 1250, 1400, 30);
INSERT INTO `emp`
VALUES (7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20', 1600, 300, 30);
INSERT INTO `emp`
VALUES (7521, 'WARD', 'SALESMAN', 7698, '1981-02-22', 1250, 500, 30);
INSERT INTO `emp`
VALUES (7900, 'JAMES', 'CLERK', 7698, '1981-12-03', 950, NULL, 30);
INSERT INTO `emp`
VALUES (7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08', 1500, 0, 30);
INSERT INTO `emp`
VALUES (7782, 'CLARK', 'MANAGER', 7839, '1981-06-09', 2450, NULL, 10);
INSERT INTO `emp`
VALUES (7934, 'MILLER', 'CLERK', 7782, '1982-01-23', 1300, NULL, 10);
INSERT INTO `emp`
VALUES (7566, 'JONES', 'MANAGER', 7839, '1981-04-02', 2975, NULL, 20);
INSERT INTO `emp`
VALUES (7788, 'SCOTT', 'ANALYST', 7566, '1982-12-09', 3000, NULL, 20);
INSERT INTO `emp`
VALUES (7876, 'ADAMS', 'CLERK', 7788, '1983-01-12', 1100, NULL, 20);
INSERT INTO `emp`
VALUES (7902, 'FORD', 'ANALYST', 7566, '1981-12-03', 3000, NULL, 20);
INSERT INTO `emp`
VALUES (7369, 'SMITH', 'CLERK', 7902, '1980-12-17', 800, NULL, 20);


CREATE TABLE `proj`
(
    `proj_id`    integer NOT NULL,
    `emp_no`     integer NOT NULL,
    `start_date` date    NOT NULL,
    `end_date`   date    NOT NULL,

    PRIMARY KEY (`proj_id`),
    CONSTRAINT `fk_PROJ` FOREIGN KEY (`emp_no`) REFERENCES `emp` (`emp_no`)
        ON DELETE NO ACTION
        ON UPDATE CASCADE
);

INSERT INTO `proj`
VALUES (1, 7782, '2005-06-16', '2005-06-18');
INSERT INTO `proj`
VALUES (4, 7782, '2005-06-19', '2005-06-24');
INSERT INTO `proj`
VALUES (7, 7782, '2005-06-22', '2005-06-25');
INSERT INTO `proj`
VALUES (10, 7782, '2005-06-25', '2005-06-28');
INSERT INTO `proj`
VALUES (13, 7782, '2005-06-28', '2005-07-02');
INSERT INTO `proj`
VALUES (2, 7839, '2005-06-17', '2005-06-21');
INSERT INTO `proj`
VALUES (8, 7839, '2005-06-23', '2005-06-25');
INSERT INTO `proj`
VALUES (14, 7839, '2005-06-29', '2005-06-30');
INSERT INTO `proj`
VALUES (11, 7839, '2005-06-26', '2005-06-27');
INSERT INTO `proj`
VALUES (5, 7839, '2005-06-20', '2005-06-24');
INSERT INTO `proj`
VALUES (3, 7934, '2005-06-18', '2005-06-22');
INSERT INTO `proj`
VALUES (12, 7934, '2005-06-27', '2005-06-28');
INSERT INTO `proj`
VALUES (15, 7934, '2005-06-30', '2005-07-03');
INSERT INTO `proj`
VALUES (9, 7934, '2005-06-24', '2005-06-27');
INSERT INTO `proj`
VALUES (6, 7934, '2005-06-21', '2005-06-23');


CREATE VIEW `managers` AS
SELECT m.`emp_name` AS `manager`, e.`emp_name` AS `employee`
FROM `emp` AS e
         LEFT JOIN `emp` AS m ON e.`mgr` = m.`emp_no`
ORDER BY m.`emp_name`, e.`emp_name`;

CREATE TABLE `locations`
(
    `name`       varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `country`    varchar(255) COLLATE utf8mb3_bin                      NOT NULL,
    `is_deleted` tinyint                                               NOT NULL DEFAULT '0',
    PRIMARY KEY (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin;

alter table dept add CONSTRAINT fk_dept_location
    foreign key (location) references locations(name)
        on delete restrict on update restrict