package com.portfolio.office;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Article {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String title;

    private String subtitle;

    private String firstContent;

    private String secondContent;

    private String thirdContent;

    private Integer quest;

    private Integer stage;

    public Article(String title, String subtitle, String firstContent, String secondContent, String thirdContent, Integer quest, Integer stage) {
        this.title = title;
        this.subtitle = subtitle;
        this.firstContent = firstContent;
        this.secondContent = secondContent;
        this.thirdContent = thirdContent;
        this.quest = quest;
        this.stage = stage;
    }

    public Article(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getFirstContent() {
        return firstContent;
    }

    public void setFirstContent(String firstContent) {
        this.firstContent = firstContent;
    }

    public String getSecondContent() {
        return secondContent;
    }

    public void setSecondContent(String secondContent) {
        this.secondContent = secondContent;
    }

    public String getThirdContent() {
        return thirdContent;
    }

    public void setThirdContent(String thirdContent) {
        this.thirdContent = thirdContent;
    }

    public Integer getQuest() {
        return quest;
    }

    public void setQuest(Integer quest) {
        this.quest = quest;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }
}