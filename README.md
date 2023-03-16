# E-Hub

## Sumary:
E-hub is a software company that provides various types of software solutions to clients across India. Design a system that enables the end user to view, add, edit or delete employee details of the organisation. The user can retrieve employee details just by entering their employee ID. Similarly, maintain their client details so that the end user can view, add, edit or delete client details. Also they can generate reports on their deliverables, employee and client details.<br>The application we made is primarily aimed towards the company admin and the various clients working with the company.

<br>

# Database Resource Setting For Connection:
```java
package GUI;

public class secret {
	public String uname="Give Your Username Here";
	public String password="Give Your Password Here";
	public String url="Give Your Driver URL Here";
}
```

* The secret file is not removed from the location in the folders so that you can edit it
* Create a new Database using pgAdmin or any postgresql client for your E-Hub application. Execute the following queries to set up the Database for usage:

```sql
create table Person
(
	ID varchar(6),
	name varchar(40),
	Street_Name varchar(30),
	Locality varchar(30),
	District varchar(30),
	State varchar(30),
	PINCODE varchar(10),
	Nationality varchar(20),
	DOB date,
	primary key(ID)
);
create table Employee
(
	Emp_ID varchar(6),
	Experience int,
	Specialisation_ID varchar(30) not null,
	Engaged_In_Project char,
	Emp_Join_Date date,
	primary key(Emp_ID),
	foreign key (Emp_ID) references Person(ID) on delete cascade
);
create table Client
(
	Client_ID varchar(6) primary key,
	Company varchar(30),
	Total_Orders int default 0,
	foreign key(Client_ID) references Person(ID) on delete cascade
);
create table Login
(
	ID varchar(6),
	Username varchar(30),
	Password varchar(30),
	Password_Hint varchar(60),
	primary key(ID),
	foreign key (ID) references Person(ID) on delete cascade
);
create table Project
(
	Project_ID varchar(6),
	Client_ID varchar(20),
	Project_Name varchar(20),
	project_log varchar(10000),
	Date_of_Release varchar(20),
	Status_of_Software varchar(30),
	Domain varchar(20),
	Date_of_Commencement varchar(20),
	Project_leader varchar(6), 
	foreign key (Client_ID) references Client on delete cascade,
	FOREIGN KEY (Project_leader) references Employee(Emp_ID),
	primary key(Project_ID)
);
create table Project_Team
(
  Project_id varchar(6),
  Emp_ID varchar(6),
  Project_leader varchar(6),
	primary key(Project_id, Emp_ID),
	foreign key (Project_leader) references Employee(Emp_ID) on delete cascade
);
						 
```

# OOPS Concepts used: 
* Inheritance &rarr; Heirarchical Inheritance
* Polymorphism &rarr; Function Overloading
* Abstraction &rarr; Person class is abstract and there is an interface in the GUI

> #### <b>Note</b>: The Project is no Longer being maintained by the contributers as it was a University Semester Project to familiarise with the Java Programming Language.
