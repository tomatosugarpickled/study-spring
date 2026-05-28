create table tbl_reply(
    id bigint unsigned auto_increment primary key,
    reply_content text not null,
    member_id bigint unsigned not null,
    post_id bigint unsigned not null,
    created_datetime datetime default current_timestamp,
    updated_datetime datetime default current_timestamp,
    constraint fk_reply_member foreign key (member_id)
        references tbl_member(id),
    constraint fk_reply_post foreign key (post_id)
        references tbl_post(id)
);

select * from tbl_reply;