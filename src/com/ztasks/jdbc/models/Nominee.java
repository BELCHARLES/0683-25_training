package com.ztasks.jdbc.models;

public class Nominee {
    private int nomineeId;
    private int empId;
    private String name;
    private int age;
    private String relationship;

    public int getNomineeId() {
        return nomineeId;
    }

    public void setNomineeId(int nomineeId) {
        this.nomineeId = nomineeId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        return new StringBuilder("Nominee { ")
                .append("Nominee ID=").append(nomineeId)
                .append(", Employee ID=").append(empId)
                .append(", Name='").append(name).append('\'')
                .append(", Age=").append(age)
                .append(", Relationship='").append(relationship).append('\'')
                .append(" }")
                .toString();
    }

}
