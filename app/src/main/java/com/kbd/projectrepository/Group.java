package com.kbd.projectrepository;

public class Group {
    private String groupName;

    public Group(int position) {
        this.groupName = "Group " + position;
    }

    public Group(String name) {
        this.groupName = name;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
