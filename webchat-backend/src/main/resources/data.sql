DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS agent_role;
DROP TABLE IF EXISTS agent;
DROP TABLE IF EXISTS role;

CREATE TABLE customer (
    customer_id INT PRIMARY KEY,
    ip_address VARCHAR(250),
    alias VARCHAR(250)
);

CREATE TABLE agent (
    agent_id int PRIMARY KEY,
    agent_name VARCHAR(250),
    first_name VARCHAR(250),
    last_name VARCHAR(250)
);

CREATE TABLE role (
    role_id int PRIMARY KEY,
    role_desc VARCHAR(250) NOT NULL
);

CREATE TABLE agent_role (
    agent_role_id int PRIMARY KEY,
    agent_id int NOT NULL,
    role_id int NOT NULL,
    FOREIGN KEY (agent_id) REFERENCES agent(agent_id),
    FOREIGN KEY (role_id) REFERENCES role (role_id)
);

