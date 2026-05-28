create table tbl_post_file(
      id bigint unsigned primary key,
      post_id bigint unsigned not null,
      constraint fk_post_file_post foreign key (post_id)
      references tbl_post(id),
      constraint fk_post_file_file foreign key (id)
      references tbl_file(id)
);

select * from tbl_post_file;

create view view_post_file as
(
select f.id,
       file_path,
       file_name,
       file_original_name,
       file_size,
       file_content_type,
       created_datetime,
       updated_datetime,
       post_id
from tbl_file f
join tbl_post_file pf
on f.id = pf.id
    );

select * from tbl_post_file;
select * from view_post_file;
delete from tbl_post_file;




















