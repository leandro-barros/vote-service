CREATE TABLE topic (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    subject VARCHAR(50) NOT NULL,
    description VARCHAR(200) NOT NULL,
    send_result BOOLEAN DEFAULT false NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;