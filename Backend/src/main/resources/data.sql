insert into object_type(description,name) values ("Vila","Poveca kuca cesto na obali mora");
insert into object_type(description,name) values ("Hotel","Mnogo dobra stvaar");
insert into object_type(description,name) values ("Motel","Malo losije od hotelaa");
insert into object_type(description,name) values ("Hostel","Uglaavnom je nekkaa babaa gaaazdddaricaaa");

insert into adress(city,latitude,longitude,number,state,street,zip) values ("Novi Sad",45.244575,19.781128,3,"Belgrade","Slobodana Jovanovica",21000);
insert into adress(city,latitude,longitude,number,state,street,zip) values ("Zrenjanin",45.379986,20.383102,27,"Belgrade","Djordjaa Jovaaanovica",20000);
insert into adress(city,latitude,longitude,number,state,street,zip) values ("Sabac",44.755926,19.692607,112,"Belgrade","Nikole Tesle",12000);
insert into adress(city,latitude,longitude,number,state,street,zip) values ("Bor",44.058477,22.101853,91,"Belgrade","Milenko Pavkovic",77000);

insert into object(cancellation,category,description,name,adress_id,object_type_id) values (10,4,"Lepa villa u jjoss lllepsse veternikku, tessskkko zaa oddolleti", "Villaa Mosskkkkvaaa",1,1);
insert into object(cancellation,category,description,name,adress_id,object_type_id) values (20,5,"Jako lep hotel na jako ocajnom mestu", "Prezident",4,2);

insert into accommodation_type(name,description) values ("Apartman", "Sa kuhinjom");
insert into accommodation_type(name,description) values ("Soba", "Bez kuhinje");
insert into accommodation_type(name,description) values ("BedAndBreakfast", "Svedski sto");

insert into unit(adults,beds,size,smoking,accommodation_type_id,object_id) values (4,3,30,b'0',1,1);
insert into unit(adults,beds,size,smoking,accommodation_type_id,object_id) values (5,5,60,b'0',2,1);
insert into unit(adults,beds,size,smoking,accommodation_type_id,object_id) values (4,4,40,b'0',1,2);

insert into role(name) values ("ROLE_ADMIN");
insert into role(name) values ("ROLE_REG");
insert into role(name) values ("ROLE_AGENT");