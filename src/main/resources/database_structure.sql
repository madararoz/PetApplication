create database if not exists PetManager;

use PetManager;

create table if not exists owners(
id int auto_increment not null,
name varchar(50) not null,
primary key(id)
);

select * from owners;

create table if not exists petTypes(
id int auto_increment not null,
type varchar(20) not null,
primary key(id)
);


create table if not exists pets(
id int auto_increment not null,
ownerId int null,
name varchar(50) not null,
age int not null,
pet_type_id int not null,
primary key(id),
foreign key(ownerId) references owners(id),
foreign key(pet_type_id) references petTypes(id)
);




create table if not exists meals(
id int auto_increment not null,
name varchar(50) not null,
weight double not null,
pet_type_id int not null,
primary key(id),
foreign key(pet_type_id) references petTypes(id)
);


create table if not exists toys(
id int auto_increment not null,
material varchar(20) not null,
pet_type_id int not null,
price double not null,
primary key(id),
foreign key(pet_type_id) references petTypes(id)
);


select * from pets left join owners on owners.id = pets.ownerId;
select * from pets where ownerId IS NULL;
select * from meals where pet_Type_Id = 1;
select * from petTypes left join toys on petTypes.id = toys.pet_type_id right join meals on petTypes.id = meals.pet_type_id where petTypes.id = 2;
select * from pets left join owners on ownerId = owners.id where pets.name = 'Frodo';

select * from pets
left join petTypes on pet_type_id = pettypes.id
right join meals on petTypes.id = meals.pet_type_id
right join toys on petTypes.id = toys.pet_type_id
where pets.name = 'Frodo';

SELECT * FROM petmanager.pets;
SELECT * FROM petmanager.pettype;
SELECT * FROM petmanager.owners;


SELECT * FROM petmanager.pets;
SELECT * FROM petmanager.pettype;
SELECT * FROM petmanager.owners;
