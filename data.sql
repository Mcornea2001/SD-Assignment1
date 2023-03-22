-- we don't know how to generate root <with-no-name> (class Root) :(
create table bands
(
	id INT
		primary key,
	name varchar(45),
	genre varchar(45)
);

create table concerts
(
	id INT
		primary key,
	name varchar(45),
	description varchar(100),
	numberTickets INT,
	time timestamp
);

create table concertbands
(
	id INT
		primary key,
	concertId INT
		references concerts
			on update cascade on delete cascade,
	bandId INT
		references bands
			on update cascade on delete cascade
);

create table user
(
	id INT
		primary key,
	userName varchar(255),
	password varchar(255),
	type INT
);

create table tickets
(
	id INT
		primary key,
	concertId INT
		references concerts
			on update cascade on delete cascade,
	userId INT
		references user
			on update cascade on delete cascade,
	price decimal(6,2),
	name varchar(100)
);

