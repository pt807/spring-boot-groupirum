DROP TABLE IF EXISTS game CASCADE;
DROP TABLE IF EXISTS member CASCADE;
DROP TABLE IF EXISTS recruit CASCADE;
DROP TABLE IF EXISTS reply CASCADE;

CREATE TABLE game (
    game_id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    name VARCHAR(255),
    PRIMARY KEY (game_id)
);

CREATE TABLE member (
    member_id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    email VARCHAR(50) NOT NULL,
    nickname VARCHAR(12) NOT NULL,
    nickname_orig VARCHAR(255) NOT NULL,
    provider_id VARCHAR(255) NOT NULL,
    social VARCHAR(255) NOT NULL,
    role VARCHAR(255),
    PRIMARY KEY (member_id)
);

CREATE TABLE recruit (
    recruit_id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    title VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    personnel INTEGER NOT NULL,
    contact VARCHAR(255) NOT NULL,
    contact_address VARCHAR (255) NOT NULL,
    status VARCHAR(255),
    views INTEGER DEFAULT 0,
    recruit_date DATE,
    created_date TIMESTAMP,
    modified_date TIMESTAMP,
    game_id BIGINT NOT NULL,
    member_id BIGINT NOT NULL,
    PRIMARY KEY (RECRUIT_ID)
);

CREATE TABLE reply (
    reply_id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    created_date TIMESTAMP,
    modified_date TIMESTAMP,
    content VARCHAR(255) NOT NULL,
    member_id BIGINT NOT NULL,
    parent_id BIGINT,
    recruit_id BIGINT NOT NULL,
    PRIMARY KEY (REPLY_ID)
);

ALTER TABLE recruit ADD CONSTRAINT fk_recruit_game FOREIGN KEY (game_id) REFERENCES game;

ALTER TABLE recruit ADD CONSTRAINT fk_recruit_member FOREIGN KEY (member_id) REFERENCES member;

ALTER TABLE reply ADD CONSTRAINT fk_reply_member FOREIGN KEY (member_id) REFERENCES member;

ALTER TABLE reply ADD CONSTRAINT fk_reply_reply FOREIGN KEY (parent_id) REFERENCES reply;

ALTER TABLE reply ADD CONSTRAINT fk_reply_recruit FOREIGN KEY (recruit_id) REFERENCES recruit;
