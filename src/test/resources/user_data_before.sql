CREATE TABLE IF NOT EXISTS users
(
    id          bigserial          NOT NULL,
    created_at  TIMESTAMP,
    modified_at TIMESTAMP,
    version     INT                NOT NULL,
    first_name  VARCHAR(50),
    last_name   VARCHAR(50),
    email       VARCHAR(50) UNIQUE NOT NULL,
    gender      VARCHAR(50),
    company     VARCHAR(50),
    avatar      VARCHAR(300),
    job_title   VARCHAR(50),
    PRIMARY KEY (id)
);

insert into users (created_at, modified_at, version, first_name, last_name, email, gender, company, avatar, job_title)
values ('2022-07-04T13:08:21.899448819Z', '2022-07-04T13:08:21.899454817Z', 0, 'Odette', 'Bracer', 'obracer0@umn.edu',
        'Female', 'Devify', 'https://robohash.org/quiatqueest.png?size=50x50&set=set1', 'Compensation Analyst');

insert into users (created_at, modified_at, version, first_name, last_name, email, gender, company, avatar, job_title)
values ('2022-07-04T13:08:21.899448819Z', '2022-07-04T13:08:21.899454817Z', 0, 'Anastasie', 'Davidge',
        'adavidge1@japanpost.jp', 'Female', 'Fivechat', 'https://robohash.org/estveniamtotam.png?size=50x50&set=set1',
        'Occupational Therapist');

insert into users (created_at, modified_at, version, first_name, last_name, email, gender, company, avatar, job_title)
values ('2022-07-04T13:08:21.899448819Z', '2022-07-04T13:08:21.899454817Z', 0, 'Tammie', 'Planque',
        'tplanque2@google.de', 'Male', 'Youbridge', 'https://robohash.org/utestaccusantium.png?size=50x50&set=set1',
        'Cost Accountant');

insert into users (created_at, modified_at, version, first_name, last_name, email, gender, company, avatar, job_title)
values ('2022-07-04T13:08:21.899448819Z', '2022-07-04T13:08:21.899454817Z', 0, 'Frazier', 'Sollas',
        'fsollas3@ocn.ne.jp', 'Male', 'Topiczoom', 'https://robohash.org/fugitetconsequatur.png?size=50x50&set=set1',
        'Chemical Engineer');

insert into users (created_at, modified_at, version, first_name, last_name, email, gender, company, avatar, job_title)
values ('2022-07-04T13:08:21.899448819Z', '2022-07-04T13:08:21.899454817Z', 0, 'Raye', 'Paladini',
        'rpaladini4@imdb.com', 'Non-binary', 'Topicshots',
        'https://robohash.org/ipsadelenitidolores.png?size=50x50&set=set1', 'Tax Accountant');
