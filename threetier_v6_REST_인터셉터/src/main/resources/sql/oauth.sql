create table tbl_oauth(
    id bigint unsigned primary key,
    provider enum('threetier', 'kakao') not null,
    constraint fk_oauth_member foreign key (id)
    references tbl_member(id)
);

select * from tbl_oauth;

