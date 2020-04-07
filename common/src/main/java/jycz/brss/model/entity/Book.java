package jycz.brss.model.entity;

import java.util.Date;

public class Book {
    private Integer id;

    private String name;

    private String author;

    private String introduction;

    private Date commitDate;

    private Integer commitUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public Date getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
    }

    public Integer getCommitUser() {
        return commitUser;
    }

    public void setCommitUser(Integer commitUser) {
        this.commitUser = commitUser;
    }
}