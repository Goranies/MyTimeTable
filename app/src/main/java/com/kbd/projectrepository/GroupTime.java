package com.kbd.projectrepository;

public class GroupTime {
    private String professor;
    private String className;
    private String classNumber;
    private String classRoom;
    private String time;
    private String group;

    public GroupTime(String professor, String className, String classNumber, String classRoom, String time, String group) {
        this.professor = professor;
        this.className = className;
        this.classNumber = classNumber;
        this.classRoom = classRoom;
        this.time = time;
        this.group = group;
    }

    public String getProfessor() {
        return professor;
    }

    public String getClassName() {
        return className;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public String getTime() {
        return time;
    }

    public String getGroup() {
        return group;
    }
}
