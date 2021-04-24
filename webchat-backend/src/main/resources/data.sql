DROP TABLE IF EXISTS chat_message;
DROP TABLE IF EXISTS chat;
DROP TABLE IF EXISTS customer cascade;
DROP TABLE IF EXISTS agent_role;
DROP TABLE IF EXISTS agent cascade;
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
    last_name VARCHAR(250),
    created_by VARCHAR(250),
    created_at VARCHAR(250),
    updated_by VARCHAR(250),
    updated_at VARCHAR(250)
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

CREATE TABLE chat (
    chat_id VARCHAR(255) PRIMARY KEY,
    customer_id int NOT NULL,
    chat_status VARCHAR(250),
    created_by VARCHAR(250),
    created_at VARCHAR(250),
    updated_by VARCHAR(250),
    updated_at VARCHAR(250),
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
);

CREATE TABLE chat_message (
    chat_message_id int PRIMARY KEY,
    request_ind CHAR,
    message VARCHAR(250),
    agent_id int,
    chat_id VARCHAR(255) NOT NULL,
    auto_message_id int,
    created_by VARCHAR(250),
    created_at VARCHAR(250),
    updated_by VARCHAR(250),
    updated_at VARCHAR(250),
    FOREIGN KEY (agent_id) REFERENCES agent(agent_id),
    FOREIGN KEY (chat_id) REFERENCES chat(chat_id)
);

INSERT INTO role (role_id, role_desc) VALUES (1, 'Supervisor');
INSERT INTO role (role_id, role_desc) VALUES (2, 'Agent');
