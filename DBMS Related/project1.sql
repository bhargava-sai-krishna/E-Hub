create table customer(cust_id varchar(10),cust_name varchar(20),age int,cust_email varchar(50),primary key(cust_id));

insert into customer values('cus101','Tyson',18,'tyson@gmail.com'),
						  ('cus102','Ginga',22,'Ginga@gmail.com'),
						  ('cus103','Kakashi',18,'Kakashi@gmail.com'),
						  ('cus104','Ash',28,'Ash@gmail.com'),
						  ('cus105','Kai',32,'Kai@gmail.com'),
						  ('cus106','Natasha',26,'Natasha@gmail.com'),
						  ('cus107','Henrry',18,'Henrry@gmail.com'),
						  ('cus108','Katara',20,'Kata@gmail.com'),
						  ('cus109','Gwen',32,'gwen201@gmail.com'),
						  ('cus110','Juily',40,'juily@gmail.com');
						  
create table project (proj_id varchar(10),proj_name varchar(100),duration int,project_description varchar(100),lang_id varchar(10),payment int,primary key(proj_id));

INSERT INTO project (proj_id, proj_name, duration, project_description, lang_id, payment)
VALUES ('P001', 'Web Development', 12, 'Build a website using HTML, CSS, and JavaScript', 'L001', 20000),
		('P002', 'Mobile App Development', 16, 'Develop an Android mobile app', 'L002', 25000),
		('P003', 'Data Analysis', 10, 'Conduct data analysis using Python', 'L003', 15000),
		('P004', 'Machine Learning', 20, 'Build a machine learning model', 'L003', 35000),
		('P005', 'Software Development', 18, 'Develop a software application using Java', 'L002', 28000),
		('P006', 'Database Design', 15, 'Design a database using SQL', 'L005', 22000),
		('P007', 'Web Design', 8, 'Design a website using Photoshop and Illustrator', 'L001', 18000),
		('P008', 'Game Development', 24, 'Develop a mobile game using Unity', 'L002', 30000),
		('P009', 'Artificial Intelligence', 22, 'Build an AI system using TensorFlow', 'L007', 33000),
		('P010', 'Cloud Computing', 12, 'Build a cloud-based system using AWS', 'L006', 26000);


create table language(lang_id varchar(10),lang_name varchar(50),primary key(lang_id))

INSERT INTO language (lang_id, lang_name) VALUES ('L001', 'C'),
												('L002', 'C++'),
												('L003', 'Java'),
												('L004', 'Python'),
												('L005', 'Frama-c'),
												('L006', 'Html, Css, JS'),
												 ('L007', 'nodeJS'),
												('L008', 'Angular'),
												 ('L009', 'Ruby'),
												 ('L010', 'SQL');

create table manager(mana_id varchar(10), mana_name varchar(50), mana_email varchar(50),mana_exp int,emp_id varchar(10),primary key(mana_id))


INSERT INTO manager (mana_id, mana_name, mana_email,mana_exp, emp_id) 
VALUES ('M1', 'John Doe', 'johndoe@example.com',10, 'E1'),
		('M2', 'Jane Smith', 'janesmith@example.com',6, 'E2'),
		('M3', 'Bob Brown', 'bobbrown@example.com',5, 'E3'),
		('M4', 'Alice Green', 'alicegreen@example.com',9, 'E4'),
		('M5', 'Michael Johnson', 'michaeljohnson@example.com',12, 'E5'),
		('M6', 'Emily Davis', 'emilydavis@example.com',7, 'E6'),
		('M7', 'William Wilson', 'williamwilson@example.com',20, 'E7'),
		('M8', 'Sarah Anderson', 'sarahanderson@example.com',11, 'E8'),
		('M9', 'David Jackson', 'davidjackson@example.com',3, 'E9'),
		('M10', 'Linda Wright', 'lindawright@example.com',2, 'E10');
		
create table employe(emp_id varchar(10), emp_name varchar(50),emp_exp int,primary key(emp_id))

INSERT INTO employe(emp_id, emp_name,emp_exp) 
VALUES ('E1', 'Jane Doe',1),
		('E2', 'John Smith',5),
		('E3', 'Alice Brown',3),
		('E4', 'Bob Green',2),
		('E5', 'Emily Johnson',1),
		('E6', 'Michael Davis',3),
		('E7', 'Sarah Wilson',4),
		('E8', 'William Anderson',2),
		('E9', 'Linda Jackson',1),
		('E10', 'David Wright',3);
		
create table feedback(cust_id varchar(10), proj_id varchar(10), feedback varchar(100),rating int,
					  primary key(cust_id,proj_id),foreign key(cust_id) REFERENCES customer(cust_id),
					  foreign key(proj_id) references project(proj_id)); 
					  
insert into feedback values ('cus101','P001','It was awesome',5),
							('cus101','P004','It was good but there were few issues with this software',3),
							('cus102','P001','It was really helpful software',5),
							('cus103','P003','Great work',4),
							('cus103','P002','It was really good',4),
							('cus104','P005','Nice work',2),
							('cus105','P007','I really liked it',5),
							('cus106','P003','Well done',5),
							('cus107','P009','good',3),
							('cus108','P007','Nice',2),
							('cus109','P001','Outstanding',5),
							('cus110','P008','Good',2);
							
							
select*from customer;
select*from project;
select*from manager;
select*from language;
select*from feedback;
select*from employe;

SELECT avg(payment)AS avg_payment FROM project where payment>20000;
select proj_id,proj_name,duration from project order by duration;
select proj_id,proj_name,payment from project where payment  between 20000 and 50000 ;

select proj_id from feedback intersect select proj_id from project;
select cust_id from customer union select feedback from feedback;
select mana_id from manager union all select emp_id from employe;

select cust_id,cust_name,age,proj_id,proj_name,feedback from customer natural join 
project natural join feedback where customer.age>18;
select count(cust_id) from customer natural join feedback where customer.age<=18;
select count(cust_id),age from customer natural join feedback group by customer.age;

select * from project where duration>7 and payment>30000;

select * from project join language on project.lang_id=language.lang_id;--using join
select * from project natural join language language;--using natural join
select * from project full outer join language on project.lang_id=language.lang_id;--using full outer join
select * from project left outer join language on project.lang_id=language.lang_id;--using left outer 
select * from customer right outer join feedback on customer.cust_id=feedback.cust_id;--using right outer 
select * from project where lang_id='L001' or lang_id='L002';
select * from project where lang_id='L001' and duration>7;
select sum(payment) as Total_payments from project;

select cust_name from customer where cust_name like 'K%' and cust_name like '%i';
select*from project;
select count(proj_id) from project where proj_name in ('Web Development','Web Design','Mobile App Development');
select proj_id,proj_name,payment from project where payment not between 10000 and 30000;
select proj_id,proj_name from project where proj_name not in 
('Web Development','Web Design','Mobile App Development');
select cust_id,proj_name,rating from customer natural join project natural join feedback where feedback.rating>3;


select proj_name,count(proj_id) from project natural join feedback group by project.proj_name;














