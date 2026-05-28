create table tbl_tag(
    id bigint unsigned auto_increment primary key,
    tag_name varchar(255) not null,
    post_id bigint unsigned not null,
    constraint fk_tag_post foreign key (post_id)
    references tbl_post(id)
);

select * from tbl_tag;
delete from tbl_tag;

select * from tbl_tag

# 태그가 모두 포함되었는지 검사
# select *
# from
# (
#     select *
#     from tbl_tag
#     where tag_name = #{1번}
# )
# where tag_name = #{2번}









