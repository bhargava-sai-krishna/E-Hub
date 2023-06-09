package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date; //To retrieve the Current Date

import project.project;
import person.Person;
import client.client;
import employee.employee;
import GUI.secret;


public class admin extends Person {
	secret s=new secret();
    private String adminID;
    private String password;

    public admin(){
        super();
        adminID = "ADM000";
        password = "admin";
    }

    
    public String getID() {
        return adminID;
    }

    public void setID(String adminID) {
        this.adminID = adminID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*

    This function takes a sorted array as input and finds first missing
    number in the array. In this function, there is a loop whose iterant's
    value starts from 1. It checks whether the iterant's value is equal to
    the array at the i-1th location. If it is different, it will return the
    value of i, which is the missing number.
    If there is no missing number, it will return the next number after all
    the numbers in the array.

    */
    protected int findMissingNumber(int[] arr){
        System.out.println("\n\nFinding the missing number");
        int i;
        for (i = 1; i <= arr.length; i++) {
            System.out.println(arr[i-1] +"\t"+i);
            if(i!=arr[i-1]){
                return i;
            }
        }
        return i;
    }

    public String assignID(){
    	int count = 0;
        String ogtype = "admin";
        String queryid = "admin_id";
        String countString;
        String query = "select count(distinct(" +queryid+")) from " + ogtype;
        String query2 = "select substring(" + queryid +",4,6) from " + ogtype + " order by "+queryid +" desc";
        System.out.println(query+"\n"+query2);
        Statement stmt = null;
        Connection c = null;
        ResultSet rs = null;
        int missingID = 0;
        try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try{
            c = DriverManager.getConnection(s.url, s.uname, s.password);
            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            if(rs.next())
                count = rs.getInt("count");
            int[] sortedArray = new int[count];
            rs = stmt.executeQuery(query2);
            int i = count-1;
            /*
                This while loop creates an array from the list of 
                data entries we recieved. The array is sorted in ascending 
                order.
            */
            while(rs.next()){
                sortedArray[i] = Integer.parseInt(rs.getString(1));
                i--;
            }
            missingID = findMissingNumber(sortedArray);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        countString = String.format("%d", missingID);
        if(countString.length()<3){
            for (int i = countString.length(); i < 3; i++){
                countString = "0"+countString;
            }
        }
        String finalID = "";
        //compareToIgnoreCase() -> 0 = same string, 1 = different strings
        finalID = "ADM" + countString;
        return finalID;
    }

    public String[] PersonList(client c){
    	String[] list = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query  = "select client_id from client order by client_id";
        String query2 = "select count(distinct(client_id)) from client";
        try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try{
            con = DriverManager.getConnection(s.url, s.uname, s.password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query2);
            if(rs.next())
                list = new String[rs.getInt(1)];
            rs = stmt.executeQuery(query);
            
            int i = 0;
            while(rs.next()){
                /* test = rs.getString(1);
                System.out.println(test); */
                list[i] = rs.getString(1);
                i++;
            }
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error");
        }
        return list;
        
    }
    
    public String[] PersonList(employee emp){
    	String[] list = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query  = "select emp_id from employee order by emp_id";
        String query2 = "select count(distinct(emp_id)) from employee";
        try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try{
            con = DriverManager.getConnection(s.url, s.uname, s.password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query2);
            
            if(rs.next())
                list = new String[rs.getInt(1)];
            rs = stmt.executeQuery(query);
            
            int i = 0;
            while(rs.next()){
                /* test = rs.getString(1);
                System.out.println(test); */
                list[i] = rs.getString(1);
                i++;
            }
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error");
        }
        return list;
    }

    public int AddPerson(employee E){
    	try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	Connection c = null;
        Statement stmt = null;
        Date date = new Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        System.out.println(sqldate);

        //We need to add the data in the Person table first
        String personQuery = "insert into person values (?,?,?,?,?,?,?,?,?)";
        
        String Empquery = "insert into employee values" + 
        "( ?, ?, ?, 'N',? )";
        
        try{
            c = DriverManager.getConnection(s.url, s.uname, s.password);
            stmt = c.createStatement();

            //Add the information in the Person table
            PreparedStatement ps = c.prepareStatement(personQuery);
            ps.setString(1, E.assignID());;
            ps.setString(2, E.getName());
            ps.setString(3, E.getAddress()[0]);
            ps.setString(4, E.getAddress()[1]);
            ps.setString(5, E.getAddress()[2]);
            ps.setString(6, E.getAddress()[3]);
            ps.setString(7, String.format("%d", E.getPINCODE()));
            ps.setString(8, E.getNationality());
            ps.setDate(9, java.sql.Date.valueOf(E.getDOB())); //DOB of Employee: YYYY-MM-DD
            
            int output = ps.executeUpdate();
            System.out.println(output + " Rows Updated");
            
            //Add the Employee information in the Employee Table
            ps = c.prepareStatement(Empquery);
            ps.setString(1, E.assignID());
            ps.setInt(2,E.getExperience());
            ps.setString(3,E.getDomain());
            //To set the date as the current date
            ps.setDate(4, new java.sql.Date(date.getTime())); //Date's constructor is invoked here, which accepts a parameter in milliseconds and converts it into SQL Date
            output = ps.executeUpdate();                      //getTime will return the number of milliseconds from 1st Jan 1970 till today
            System.out.println(output + " Rows Updated");
            stmt.close();
            c.close();
            return 0;
                
        }catch(Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    public int AddPerson(client C){
    	try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	Connection c = null;
        Statement stmt = null;
        Date date = new Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        System.out.println(sqldate);

        //We need to add the data in the Person table first
        String personQuery = "insert into person values (?,?,?,?,?,?,?,?,?)";
        
        String Cliquery = "insert into client values" + 
        "(?, ?, 0)";

        String loginQuery = "insert into login values (?,?,'ehub','ehub is the password')";
        
        try{
            c = DriverManager.getConnection(s.url, s.uname, s.password);
            stmt = c.createStatement();
            String ID = C.assignID();
            
            //Add the information in the Person table
            PreparedStatement ps = c.prepareStatement(personQuery);
            ps.setString(1, ID);
            ps.setString(2, C.getName());
            ps.setString(3, C.getAddress()[0]);
            ps.setString(4, C.getAddress()[1]);
            ps.setString(5, C.getAddress()[2]);
            ps.setString(6, C.getAddress()[3]);
            ps.setString(7, String.format("%d", C.getPINCODE()));
            ps.setString(8, C.getNationality());
            ps.setDate(9, java.sql.Date.valueOf(C.getDOB()));
            
            int output = ps.executeUpdate();
            System.out.println(output + " Rows Updated");
            
            ps = c.prepareStatement(Cliquery);
            System.out.println("Assigning Client in Client Database: ");
            ps.setString(1, ID);
            ps.setString(2, C.getCompany());
            output = ps.executeUpdate();
            System.out.println(output + " Row(s) Updated");

            ps = c.prepareStatement(loginQuery);
            ps.setString(1, ID);
            ps.setString(2, C.getName());
            output = ps.executeUpdate();
            System.out.println(output + " Row(s) Updated");
            stmt.close();
            c.close();
            return 0;

        }catch(Exception e){
            e.printStackTrace();
            return 1;
        }
    }
    
    
    public void removePerson(client C){
    	try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	Connection c = null;
        String query = "delete from person where id = ?";
        //The following lines of code are temporary:
        String client_id = C.getID();

        try{
            c = DriverManager.getConnection(s.url, s.uname, s.password);
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, client_id);
            int output = ps.executeUpdate();
            System.out.println(output + " Row(s) Removed");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void removePerson(employee E){
    	try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	Connection c = null;
        String query = "delete from person where id = ?";
        //The following line of code is temporary:
        String emp_id = E.getID();

        try{
            c = DriverManager.getConnection(s.url, s.uname, s.password);
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, emp_id);
            int output = ps.executeUpdate();
            System.out.println(output + " Row(s) Removed");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public String[] ProjectList(){
    	try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	String[] list = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query  = "select project_id from project where status_of_software !='NOT APPROVED' order by project_id";
        String query2 = "select count(project_id) from project";
        try{
            con = DriverManager.getConnection(s.url, s.uname, s.password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query2);
            if(rs.next())
                list = new String[rs.getInt(1)];
            rs = stmt.executeQuery(query);
            
            int i = 0;
            while(rs.next()){
                /* test = rs.getString(1);
                System.out.println(test); */
                list[i] = rs.getString(1);
                i++;
            }
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error");
        }
        return list;
    }

    public String[] ProjectListAll(){
    	try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	String[] list = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query  = "select project_id from project";
        String query2 = "select count(project_id) from project";
        try{
            con = DriverManager.getConnection(s.url, s.uname, s.password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query2);
            if(rs.next())
                list = new String[rs.getInt(1)];
            rs = stmt.executeQuery(query);
            
            int i = 0;
            while(rs.next()){
                /* test = rs.getString(1);
                System.out.println(test); */
                list[i] = rs.getString(1);
                i++;
            }
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error");
        }
        return list;
    }

    public String[] ProjectListNotApproved(){
    	try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	System.out.println("Project List Not Approved");
        String[] list = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query  = "select project_id from project where status_of_software = 'NOT APPROVED' order by project_id";
        String query2 = "select count(project_id) from project where status_of_software = 'NOT APPROVED'";
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(s.url, s.uname, s.password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query2);
            int size;
            if(rs.next()){
                size = rs.getInt(1);
                if(size == 0)
                    return null;
                list = new String[size];
            }
            rs = stmt.executeQuery(query);
            
            int i = 0;
            while(rs.next()){
                /* test = rs.getString(1);
                System.out.println(test); */
                list[i] = rs.getString(1);
                i++;
            }
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error");
        }
        return list;
    }

    public String[] ProjectListChangesRequested(){
    	try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	System.out.println("Project List Changes Requested");
        String[] list = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query  = "select project_id from project where status_of_software = 'CHANGES REQUESTED' order by project_id";
        String query2 = "select count(project_id) from project where status_of_software = 'CHANGES REQUESTED'";
        try{
            con = DriverManager.getConnection(s.url, s.uname, s.password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query2);
            int size;
            if(rs.next()){
                size = rs.getInt(1);
                if(size == 0)
                    return null;
                list = new String[size];
            }
            rs = stmt.executeQuery(query);
            
            int i = 0;
            while(rs.next()){
                /* test = rs.getString(1);
                System.out.println(test); */
                list[i] = rs.getString(1);
                i++;
            }
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error");
        }
        return list;
    }


    public void approveProject(project P){
    	try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	Connection con = null;
        Statement stmt = null;
        String query  = "update project set status_of_software = 'APPROVED' where project_id = '" + P.getProjectID()+"'";
        try{
            con = DriverManager.getConnection(s.url, s.uname, s.password);
            stmt = con.createStatement();
            int output = stmt.executeUpdate(query);
            System.out.println(output + " Row(s) Updated");

        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error");
        }
    }

    public void ProjectChanges(project P,String status){
    	try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	Connection con = null;
        Statement stmt = null;
        System.out.println(P.getProjectID());
        String query  = "update project set status_of_software = '"+status+"' where project_id = '" + P.getProjectID()+"'";
        try{
            con = DriverManager.getConnection(s.url, s.uname, s.password);
            stmt = con.createStatement();
            int output = stmt.executeUpdate(query);
            System.out.println(output + " Row(s) Updated");

        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error");
        }
    }

    public void addProjectLog(project P,String message){
    	try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	Connection con = null;
        //Statement stmt = null;
        String query = "update project set project_log=concat(project_log,?) where project_id=?";
        //String query2 = "select project_log from project where project_id = '" + P.getProjectID() + "'";
        //ResultSet rs = null;
        //String ogmessage = null;
        try{
            con = DriverManager.getConnection(s.url, s.uname, s.password);
            PreparedStatement ps = con.prepareStatement(query);
            String final_log = "Admin:\n" + message + "\n#";
            ps.setString(1, final_log);
            ps.setString(2,P.getProjectID());
            int output = ps.executeUpdate();
            System.out.println(output + " Row(s) Updated");
            
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error");
        }
    }

    public void updateProjectStatus(project P, String status)
    {
    	try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	Connection con = null;
        Statement stmt = null;
        System.out.println(P.getProjectID());
        String query  = "update project set status_of_software = '" + status +"' where project_id = '" + P.getProjectID() + "'";
        //System.out.println(query);
        try{
            con = DriverManager.getConnection(s.url, s.uname, s.password);
            stmt = con.createStatement();
            int output = stmt.executeUpdate(query);
            System.out.println(output + "Rows Updated");

        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error");
        }
    }

}