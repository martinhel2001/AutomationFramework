package Connectors.dataentities.mantis;

public enum Project {
    AUTOMATION_BUGS("Automation Bugs",1);

    private String projectName;
    private int projectId;

    private Project(String projectName, int projectId){
        this.projectName=projectName;
        this.projectId=projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

}
