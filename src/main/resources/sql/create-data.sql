-- delete old data
delete from invoice_position;
delete from position;
delete from invoice;
 
-- add few positions
insert into position values(1, 'Lunch', 29);
insert into position values(2, 'Computer', 2999);
insert into position values(3, 'Computer maintence', 1200);
insert into position values(4, 'Legal advice', 500);
 
-- add some invoices
insert into invoice values(1, '7bulls', 'seller1');
insert into invoice values(2, '7bulls', 'seller2');
 
-- connect some entities
insert into invoice_position values (1, 1);
insert into invoice_position values (1, 3);
insert into invoice_position values (1, 4);
insert into invoice_position values (2, 2);
insert into invoice_position values (2, 1);
