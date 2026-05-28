use candm;

drop table tbl_members;
create table tbl_members
(
    id                  bigint unsigned auto_increment primary key,
    member_email        varchar(255) unique not null,
    member_password     varchar(255)        not null,
    member_name         varchar(255)        not null,
    member_phone_number varchar(255)        not null,
    member_verified     boolean             not null default false,
    created_datetime    datetime                     default current_timestamp(),
    edited_datetime     datetime                     default current_timestamp()
);

# create table tbl_member_feed (
#     id bigint unsigned auto_increment primary key,
#     member_position varchar(255) not null,
#     member_ varchar(255) not null,
#
#
# );

create table tbl_messages
(
    id                    bigint unsigned auto_increment primary key,
    message_room_id       bigint unsigned not null,
    sender_id             bigint unsigned not null,
    receiver_id           bigint unsigned not null,
    read_or_not           boolean         not null default false,
    notification_datetime datetime                 default current_timestamp()
);

create table tbl_message_rooms
(
    id                bigint unsigned auto_increment primary key,
    created_member_id bigint unsigned not null,
    invited_member_id bigint unsigned not null
);

create table tbl_teams
(
    id                   bigint unsigned auto_increment primary key,
    team_name            varchar(255) unique not null,
    team_url             varchar(255) unique not null,
    team_leader_email    varchar(255) unique not null,
    team_approved_or_not boolean             not null default false,
    created_datetime     datetime                     default current_timestamp(),
    team_deleted         enum ('disable','able')      default 'disable'
);

create table tbl_team_feeds
(
    id                         bigint unsigned auto_increment primary key,
    team_feed_profile          varchar(255) unique not null,
    team_technology_stack      varchar(255) unique not null,
    team_Detailed_introduction varchar(65535)      not null,
    team_size,
    team_current_level,
    team_recruit_position
);