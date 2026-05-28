create table tbl_file(
     id bigint unsigned auto_increment primary key,
     file_path varchar(255) not null,
     file_name varchar(255) not null,
     file_original_name varchar(255) not null,
     file_size varchar(255) not null,
     file_content_type enum('image', 'other') not null,
     created_datetime datetime default current_timestamp,
     updated_datetime datetime default current_timestamp
);

select * from tbl_file;
delete from tbl_file;