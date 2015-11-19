


package com.swcguild.capstoneproject.model;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


public class Tag {
    private int postID;
    @NotEmpty(message = "You must supply a text value for the tag.")
    @Length(max = 50, message = "Tag must be no more than 50 characters in length.")
    private String tag;

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    
    
    
    
}
