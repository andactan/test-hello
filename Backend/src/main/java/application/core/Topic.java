package application.core;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue
    int id;

    @Column(name = "title")
    private String title;

    @Column(name = "topic_owner")
    private int topicOwner;

    // Change generic type to Post!!
    @Transient
    private ArrayList<String> postList = new ArrayList<String>();

    // Change generic type to Tag!!
    @Transient
    private ArrayList<String> tagList = new ArrayList<String>();

    // Change generic type to Relation
    @Transient
    private ArrayList<String> relations = new ArrayList<String>();

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTopicOwner(int topicOwner) {
        this.topicOwner = topicOwner;
    }

    public int getTopicOwner() {
        return topicOwner;
    }

    public void setPostList(ArrayList<String> postList) {
        this.postList = postList;
    }

    public ArrayList<String> getPostList() {
        return postList;
    }

    public void setTagList(ArrayList<String> tagList) {
        this.tagList = tagList;
    }

    public ArrayList<String> getTagList() {
        return tagList;
    }

    public void setRelations(ArrayList<String> relations) {
        this.relations = relations;
    }

    public ArrayList<String> getRelations() {
        return relations;
    }
}
