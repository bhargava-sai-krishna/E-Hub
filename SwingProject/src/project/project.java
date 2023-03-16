package project;


import exceptions.*;

public class project {

    private String ProjectID;
    private String ProjectName;
    private String ProjectType;
    private String ProjectDeadline;
    private String[] ProjectMembers = new String[5];
    private String ProjectHead;
    private String ProjectStatus;
    private String ProjectLog;
    private String ClientID;

    public String getProjectID() {
        return this.ProjectID;
    }

    public void setProjectID(String ProjectID) {
        this.ProjectID = ProjectID;
    }

    public String getProjectLog() {
        return this.ProjectLog;
    }

    public void setProjectLog(String ProjectLog) {
        this.ProjectLog = ProjectLog;
    }

    public String getProjectName() {
        return this.ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public String getProjectType() {
        return this.ProjectType;
    }

    public void setProjectType(String domain){
        this.ProjectType = domain;
    }

    public void autoAssignProjectType() {
    	/*used to set the project type*/
    }

    public String getProjectDeadline() {
        return this.ProjectDeadline;
    }

    public void setProjectDeadline(String ProjectDeadline) {
        this.ProjectDeadline = ProjectDeadline;
    }

    public String[] autoRetrieveProjectMembers(){
    	/*returns the array of employee names*/
        
    	String[]a={"you"," got"," all"," employee"," names"," using"," SQL"," methods"};
        return a;
    }

    public String[] getProjectMembers() {
        return this.ProjectMembers;
    }

    public String retrieveLog(String id) {
        return "you got the information using the SQL methods";
    }

    public void setProjectMembers() throws noEmployeeAvailable{
    	//sets the members for a project, the members are stored in Project P
        
    }

    public void updateEmpStatus(String[] Emp_IDs,String type) {
    	//type can be Y/N, updates the employee status
      }

    public String getProjectHead() {
        return this.ProjectHead;
    }

    public void setProjectHead(String headid){
        this.ProjectHead = headid;
    }

    public void assignProjectHead() {
    	//sets the project head
            }

    public String getProjectStatus() {
        return this.ProjectStatus;
    }

    public void setProjectStatus(String ProjectStatus) {
        this.ProjectStatus = ProjectStatus;
    }

    public String getClientID() {
        return this.ClientID;
    }

    public void setClientID(String ClientID) {
        this.ClientID = ClientID;
    }

}

class driver {
    public static void main(String[] args) {
        project obj = new project();
        obj.setProjectID("PRO002");
        obj.setProjectType("WEB");
        try{
            obj.setProjectMembers();
            obj.assignProjectHead();
            obj.updateEmpStatus(obj.getProjectMembers(), "Y");
            obj.updateEmpStatus(obj.getProjectMembers(),"N");
            
            System.out.println("The Project Head is: " + obj.getProjectHead());
        /* }catch(noEmployeeAvailable neA){
            neA.displayError(new JFrame()); */
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
