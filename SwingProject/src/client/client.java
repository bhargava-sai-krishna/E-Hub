package client;

import person.Person;
import project.project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import GUI.secret;

import java.sql.PreparedStatement;

public class client extends Person {
	secret s=new secret();
    public client() {
        super();
    }

    private String clientID;
    private int totalProjectsRequested;
    private int priority;
    private int Total_Orders;
    private String Company;
    private String[] person_id_list;
    private String[] person_name_list;

    public String getID() {
        return this.clientID;
    }

    public void setID(String clientID) {
        this.clientID = clientID;
    }

    public int getTotalProjectsRequested() {
        return this.totalProjectsRequested;
    }

    public void setTotalProjectsRequested(int totalProjectsRequested) {
        this.totalProjectsRequested = totalProjectsRequested;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getTotal_Orders() {
        return this.Total_Orders;
    }

    public void setTotal_Orders(int Total_Orders) {
        this.Total_Orders = Total_Orders;
    }

    public String getCompany() {
        return this.Company;
    }

    public String[] getPersonIdList() {
        return this.person_id_list;
    }

    public String[] getPersonNameList() {
        return this.person_name_list;
    }

    public void setCompany(String Company) {
        this.Company = Company;
    }

    protected int findMissingNumber(int[] arr) { 
    	// arr is sorted in ascending order, the arrays will have the numbers
        int i;
        for (i = 1; i <= arr.length; i++) {
            System.out.println(arr[i - 1] + "\t" + i);
            if (i != arr[i - 1]) {
                return i;
            }
        }
        return i;
    }

    public String assignID() {
    	int count = 0;
        String ogtype = "client";
        String queryid = "client_id";
        String countString;
        String query = "select count(distinct(" + queryid + ")) from " + ogtype;
        String query2 = "select substring(" + queryid + ",4,6) from " + ogtype + " order by " + queryid + " desc";
        System.out.println(query + "\n" + query2);
        Statement stmt = null;
        Connection c = null;
        ResultSet rs = null;
        int missingID = 0;
        try {
            c = DriverManager.getConnection(s.url, s.uname, s.password);
            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next())
                count = rs.getInt("count");
            int[] sortedArray = new int[count];
            rs = stmt.executeQuery(query2);
            int i = count - 1;
            /*
             * This while loop creates an array from the list of
             * data entries we recieved. The array is sorted in ascending
             * order.
             */
            while (rs.next()) {
                sortedArray[i] = Integer.parseInt(rs.getString(1));
                i--;
            }
            missingID = findMissingNumber(sortedArray);
        } catch (Exception e) {
            e.printStackTrace();
        }

        countString = String.format("%d", missingID);
        if (countString.length() < 3) {
            for (int i = countString.length(); i < 3; i++) {
                countString = "0" + countString;
            }
        }
        String finalID = "";
        finalID = "CLI" + countString;
        return finalID;

    }

    public void retrieve_TotalOrders(project p) {
    	Connection c = null;
        Statement stmt = null;
        try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            secret obj = new secret();
            c = DriverManager.getConnection(obj.url, obj.uname, obj.password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        try {
            System.out.println("Opened database successfully for retrieving total orders");
            stmt = c.createStatement();
            String sql = String.format(
                    "select Total_Orders from Client where Client_id = '%s';",
                    p.getClientID());
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Before WHILE");
            if (rs.next()) {
                this.Total_Orders = rs.getInt(1);
                System.out.println("Total Orders inside func="+rs.getInt(1));
            }
            
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error in retrieving total orders");
            System.out.println(e.toString());
        }
    }

    public void AddProject(project p) {
    	try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	Connection c = null;
        Statement stmt = null;

        // We need to add the data in the Project table first
        String count = "select count(Project_ID) as id from Project;"; // it is used to give the size of the array
                                                                       // sortedArray
        String query2 = "select substring(Project_ID,4,6) from Project order by Project_ID desc";
        try {
            c = DriverManager.getConnection(s.url, s.uname, s.password);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(count);
            String newId = "";
            rs.next();
            int countNum = Integer.parseInt(rs.getString("id"));
            int j = countNum - 1;
            int[] sortedArray = new int[countNum];

            rs = stmt.executeQuery(query2);
            while (rs.next()) {
                sortedArray[j] = Integer.parseInt(rs.getString(1));
                System.out.println(sortedArray[j]);
                j--;
            }
            int missingID = findMissingNumber(sortedArray);
            System.out.println(missingID);
            newId = String.format("PRO%03d", missingID);
            System.out.println(newId);
            p.setProjectID(newId);

            String ProjectQuery = String.format(
                    "insert into Project(Project_ID, Client_ID, Project_Name,Project_Log ,Date_of_Release, Status_of_software, Domain) values('%s','%s','%s','%s\n#','%s','%s','%s')",
                    p.getProjectID(), p.getClientID(), p.getProjectName(), p.getProjectLog(), p.getProjectDeadline(),
                    "NOT APPROVED",
                    p.getProjectType());

            stmt = c.createStatement();

            // To set the date as the current date
            PreparedStatement ps = c.prepareStatement(ProjectQuery);
            // ps.setDate(1, new java.sql.Date(date.getTime()));

            int output = 0;
            output = ps.executeUpdate();
            System.out.println(output + " Row(s) Updated");
            stmt.close();
            // Total Orders try
            retrieve_TotalOrders(p);
            System.out.println("Total Orders="+Total_Orders);
            this.Total_Orders += 1;
            PreparedStatement ps2 = c.prepareStatement("update Client set Total_Orders=? where client_id=?");
            ps2.setInt(1, Total_Orders);// 1 specifies the first parameter in the query i.e. name
            ps2.setString(2, p.getClientID());

            int i = ps2.executeUpdate();
            System.out.println(i + " records updated");
            // Total Orders
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }

    public void suggestChanges(project P) {
    	try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	Connection c = null;
        String query = "update project set project_log=concat(project_log,?), status_of_software='CHANGES REQUESTED' where project_id=?";
        // The following lines of code are temporary:

        // String project_id = P.getProjectID;

        try {
            c = DriverManager.getConnection(s.url, s.uname, s.password);
            PreparedStatement ps = c.prepareStatement(query);
            String final_log = "Client:\n" + P.getProjectLog() + "\n#";
            ps.setString(1, final_log);
            ps.setString(2, P.getProjectID());
            int output = ps.executeUpdate();
            System.out.println(output + " Row(s) Updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
    
    public void ProjectList(project P, String client_id) {
    	try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
     }

    public void ClientPriority() {
    	try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	System.out.println("Entered Client Priority");
        Connection c = null;
        String query = "select client_id,count(Project_ID) as Totalprojcount from Project where Status_of_Software='PAID' group by client_id order by Totalprojcount desc;";
        Statement ps = null;
        // String client_id = "CLI001";
        Vector<String> Clientvec = new Vector<String>();
        Vector<Integer> Tprojvec = new Vector<Integer>();
        try {
            String x = "asd"; // just for initialisation
            int y = 0;
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(s.url, s.uname, s.password);
            ps = c.createStatement();

            // ps.setString(1, client_id);
            ResultSet rs = ps.executeQuery(query);
            System.out.println("Before While Loop");
            while (rs.next()) {
                x = rs.getString("client_id");
                y = rs.getInt("Totalprojcount");
                // for (int i = 0; i < Clientvec.size(); i++) {
                Clientvec.add(x);
                Tprojvec.add(y);
                // }

                // System.out.println(x);

                // System.out.println(y);
            }
            // priority= Clientvec.indexOf(x);
          //  int val = Clientvec.indexOf(x);
          x = this.getID();
            this.setPriority(Clientvec.indexOf(x) + 1);
            System.out.println("Priority:"+Clientvec.indexOf(x) + 1);
            // System.out.print(Clientvec);
            // System.out.print(Tprojvec);
            // For prioirity thingy
            // update project set priority=? where ID=
            // (1,i)

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}