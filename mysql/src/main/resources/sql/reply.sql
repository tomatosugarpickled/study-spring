create table tbl_reply(
    id bigint unsigned auto_increment primary key,
    parent_id bigint unsigned,
    reply_content varchar(255) not null,
    depth int default 1,
    member_id bigint unsigned not null,
    review_id bigint unsigned not null,
    created_datetime datetime default current_timestamp,
    updated_datetime datetime default current_timestamp,
    constraint fk_reply_member foreign key (member_id)
    references tbl_member(id),
    constraint fk_reply_review foreign key (review_id)
    references tbl_review(id)
);

select * from tbl_reply;