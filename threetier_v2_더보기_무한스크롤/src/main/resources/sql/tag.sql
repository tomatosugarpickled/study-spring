create table tbl_tag(
    id bigint unsigned auto_increment primary key,
    tag_name varchar(255) not null,
    post_id bigint unsigned not null,
    constraint fk_tag_post foreign key (post_id)
    references tbl_post(id)
);

select * from tbl_tag;
delete from tbl_tag;

select distinct tag_name
from tbl_tag