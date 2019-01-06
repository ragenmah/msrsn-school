package com.example.ragenmah99.msrsnproject;

public class Album {
    private String memberName;
    private String emailId;
    private int images;
   private String post;
    public Album(String memberName, String emailId, int images,String post) {
        this.memberName = memberName;
        this.emailId = emailId;
        this.images=images;
        this.post=post;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String artistName) {
        this.emailId = emailId;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }
    public String getPost(){return post;}

    public void setPost(String post){this.post=post;}
}
