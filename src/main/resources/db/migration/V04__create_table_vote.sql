CREATE TABLE vote (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    topic_id BIGINT(20),
    associate_id BIGINT(20),
    vote BOOLEAN NOT NULL,
    FOREIGN KEY (topic_id) REFERENCES topic(id),
    FOREIGN KEY (associate_id) REFERENCES associate(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;