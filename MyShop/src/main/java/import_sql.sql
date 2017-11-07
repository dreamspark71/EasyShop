insert into person (firstname,lastname,email,role) values ('Atul','takekar','takekaratul@gmail.com','ADMIN');
insert into person (firstname,lastname,email,role) values ('Pushkar','khedekar','khedekarpushkar@gmail.com','MEMBER');
insert into person (firstname,lastname,email,role) values ('Mihir','patil','patilmihir@gmail.com','MEMBER');
insert into person (firstname,lastname,email,role) values ('Karan','thevar','thevarkaran@gmail.com','GUEST');

insert into user values ('pushkar','857-900-9898','pushkar',2);
insert into user values ('mihir','857-900-8989','mihir',3);

insert into administrator values ('atul','atul',1);

insert into address values('mumbai','india','maharashtra','akruti-a-19',421201,2);
insert into address values('mumbai','india','maharashtra','thane',421202,3);

insert into category (name) values('appearals');
insert into category (name) values('electronics');
insert into category (name) values('home');
insert into category (name) values('furniture');
insert into category (name) values('book');


insert into product(currqty,description,image,name,price,quantity,categoryid) values (50,'samsung phone description','/resources/images/samsung-galaxy-on-nxt.jpeg','SAMSUNGGalaxyOnNxt',500.34,50,2);

insert into product(currqty,description,image,name,price,quantity,categoryid) values (80,'Raymond Cotton Abstract Double Bedsheet','/resources/images/raymond-fitted-flat-double-bedsheet.jpeg','Raymond Cotton Abstract Double Bedsheet',12,499,3);

insert into product(currqty,description,image,name,price,quantity,categoryid) values (90,'Kurlon Bullet Leatherette 3 Seater Sofa','/resources/images/blueparticle.jpeg','Kurlon Bullet Leatherette 3 Seater Sofa',100.78,45,4);

insert into product(currqty,description,image,name,price,quantity,categoryid) values (40,'Indian History 1st Edition','/resources/images/indian-history-original.jpeg','Indian History 1st Edition',6.7,190,5);

insert into product(currqty,description,image,name,price,quantity,categoryid) values (3,'Livie Embroidered Bollywood Net Saree','/resources/images/pinksaree.jpeg','Livie Embroidered Bollywood Net Saree',50,3,1);

insert into ordertable (orderdate,status,totalamount,totalqty,user_user_id) values (subdate(now(),1),'PENDING',300.67,3,2);
insert into ordertable (orderdate,status,totalamount,totalqty,user_user_id) values (subdate(now(),1),'COMPLETED',123,4,2);
insert into ordertable (orderdate,status,totalamount,totalqty,user_user_id) values (subdate(now(),1),'COMPLETED',356.67,6,3);
insert into ordertable (orderdate,status,totalamount,totalqty,user_user_id) values (subdate(now(),1),'PENDING',46.67,4,3);
insert into ordertable (orderdate,status,totalamount,totalqty,user_user_id) values (subdate(now(),1),'PENDING',30.67,1,3);

insert into orderline (price,quantity, subtotal,order_orderid,product_productid) values (23,3,34,2,1);
insert into orderline (price,quantity, subtotal,order_orderid,product_productid) values (34,1,34,2,2);
insert into orderline (price,quantity, subtotal,order_orderid,product_productid) values (23,3,34,2,3);
insert into orderline (price,quantity, subtotal,order_orderid,product_productid) values (23,3,34,3,4);
insert into orderline (price,quantity, subtotal,order_orderid,product_productid) values (23,3,34,4,5);
insert into orderline (price,quantity, subtotal,order_orderid,product_productid) values (23,3,34,4,4);
insert into orderline (price,quantity, subtotal,order_orderid,product_productid) values (23,3,34,5,1);
insert into orderline (price,quantity, subtotal,order_orderid,product_productid) values (23,3,34,1,4);
insert into orderline (price,quantity, subtotal,order_orderid,product_productid) values (23,3,34,1,5);
insert into orderline (price,quantity, subtotal,order_orderid,product_productid) values (23,3,34,1,2);






