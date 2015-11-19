/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.dao;

import com.swcguild.capstoneproject.model.Authority;
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
    
    public User getUserById(int userID);

    public User makeNewUser(User user);

    public void updateUser(User user);

    public void removeUser(int userID);

    public List<User> getAllUsers();
    
    public Authority getAuthorityByUsername(String username);
    
    public List<Authority> getAuthoritiesByUsername(String username);
    
    public void makeNewAuthority(Authority auth);
    
    public void removeAuthority(String username);
    
    public List<Authority> getAllAuthorities();

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

    public List<Post> getAllPostObjects();

    public List<Tag> getAllTags();

    public PinPost addPinPost(PinPost pinPost);

    public void removePinPost(int pinPostId);

    public void updatePinPost(PinPost pinPost);

    public List<PinPost> getAllPinPosts();

    public PinPost getPinPostById(int pinPostId);

    public void publishPinPost(int id, PinPost data);

    public List<PinPost> getActivePinPosts();
}
