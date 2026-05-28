create table tbl_review(
    id bigint unsigned primary key,
    review_rate varchar(255) not null,
    constraint fk_review_post foreign key(id)
                       references tbl_post(id)
);

select * from tbl_review;