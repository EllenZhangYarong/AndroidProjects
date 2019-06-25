package com.ellen.taskninefragment.models;


/**
 * Created by ellen on 15/12/14.
 */
public class ActorsModel {
    private String name;
    private String actorName;
    private String abriefIntro;
    private int imgUri;

    public ActorsModel() {

    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actWho) {
        this.actorName = actWho;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbriefIntro() {
        return abriefIntro;
    }

    public void setAbriefIntro(String abriefIntro) {
        this.abriefIntro = abriefIntro;
    }

    public int getImgUri() {
        return imgUri;
    }

    public void setImgUri(int imgUri) {
        this.imgUri = imgUri;
    }




    public ActorsModel(String name, String actorName, String abriefIntro, int imgUri) {
        this.name = name;
        this.actorName = actorName;
        this.abriefIntro = abriefIntro;
        this.imgUri = imgUri;
    }
}
