/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.dao;

import com.swcguild.capstoneproject.model.PinPost;
import com.swcguild.capstoneproject.model.Post;
import com.swcguild.capstoneproject.model.Tag;
import com.swcguild.capstoneproject.model.User;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface BlogDao {

    public Post addPost(Post post);
    public void makeNewUser(User user);

    public List<Post> getSearchPosts(String tag);

    // remove the Post with the given id from the data store
    public void removePost(int postId);

    // update the given Post in the data store
    public void updatePost(Post post);

    // retrieve all Contacts from the data store
    public List<Post> getAllPosts();

    // retrieve the Post with the given id from the data store
    public Post getPostById(int postId);

    public void publishPost(int id, Post data);

    public String getTagsById(int postID);

    public List<Tag> getActiveTags();

    public List<Post> getActivePosts();

    public List<Tag> getAllTags();

    public PinPost addPinPost(PinPost pinPost);

    public void removePinPost(int pinPostId);

    public void updatePinPost(PinPost pinPost);

    public List<PinPost> getAllPinPosts();

    public PinPost getPinPostById(int pinPostId);

    public void publishPinPost(int id, PinPost data);
    
    public List<PinPost> getActivePinPosts();
}
