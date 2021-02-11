create table stock (
    exchange varchar(255) not null,
    symbol varchar(255) not null,
    bid_price double,
    description varchar(255),
    offer_price double,
    primary key (exchange, symbol));
