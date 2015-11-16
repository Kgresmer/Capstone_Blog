/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.dao;

import com.swcguild.capstoneproject.model.Data;
import com.swcguild.capstoneproject.model.PinPost;
import com.swcguild.capstoneproject.model.Post;
import com.swcguild.capstoneproject.model.Tag;
import com.swcguild.capstoneproject.model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class BlogDaoInMemImpl implements BlogDao {
    
    private Map<Integer, Post> blogPosts = new HashMap<>();
    private Map<Integer, PinPost>blogPinPosts = new HashMap<>();
    private static int postIdCounter = 0;
    private static int pinPostIdCounter = 0;
    //Post post1 = new Post("title1", "author1", "wordswordswords", "tags", "postdate", "expdate");
    
    public BlogDaoInMemImpl() {
    }

    @Override
    public void makeNewUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @Override
    public Post addPost(Post post) {
        
        post.setPostID(postIdCounter);
        postIdCounter++;
        
        blogPosts.put(post.getPostID(), post);
        return post;
    }

    public Map<Integer, Post> getBlogPosts() {
        return blogPosts;
    }

    public void setBlogPosts(Map<Integer, Post> blogPosts) {
        this.blogPosts = blogPosts;
    }

    @Override
    public void removePost(int postId) {
       blogPosts.remove(postId);
    }

    @Override
    public void updatePost(Post post) {
       blogPosts.put(post.getPostID(), post);
    }

    @Override
    public List<Post> getAllPosts() {
        Collection<Post> c = blogPosts.values();
        return new ArrayList(c);
    }

    @Override
    public Post getPostById(int postId) {
        return blogPosts.get(postId);
    }

//    @Override
//    public List<Post> searchPost(Map<SearchTerm, String> criteria) {
//        
//        String fnameCriteria = criteria.get(SearchTerm.FIRST_NAME);
//        String lnameCriteria = criteria.get(SearchTerm.LAST_NAME);
//        String streetCriteria = criteria.get(SearchTerm.STREET);
//        String cityCriteria = criteria.get(SearchTerm.CITY);
//        String stateCriteria = criteria.get(SearchTerm.STATE);
//        String zipCriteria = criteria.get(SearchTerm.ZIP);
//        
//        Predicate<Post> firstNameMatches;
//        Predicate<Post> lastNameMatches;
//        Predicate<Post> streetMatches;
//        Predicate<Post> cityMatches;
//        Predicate<Post> stateMatches;
//        Predicate<Post> zipMatches;
//        
//        Predicate<Post> truePredicate = (a) -> {return true;};
//        
//        firstNameMatches = (fnameCriteria == null || fnameCriteria.isEmpty())
//                ? truePredicate
//                : (a) -> a.getFirstName().equals(fnameCriteria);
//        
//        lastNameMatches = (lnameCriteria == null || lnameCriteria.isEmpty())
//                ? truePredicate
//                : (a) -> a.getLastName().equals(lnameCriteria);
//        
//        streetMatches = (streetCriteria == null || streetCriteria.isEmpty())
//                ? truePredicate
//                : (a) -> a.getStreet().equals(streetCriteria);
//        
//        cityMatches = (cityCriteria == null || cityCriteria.isEmpty()) 
//                ? truePredicate
//                : (a) -> a.getCity().equals(cityCriteria);
//        
//        stateMatches = (stateCriteria == null || stateCriteria.isEmpty())
//                ? truePredicate
//                : (a) -> a.getState().equals(stateCriteria);
//        
//        zipMatches = (zipCriteria == null || zipCriteria.isEmpty())
//                ? truePredicate
//                : (a) -> a.getZip().equals(zipCriteria);
//        
//        return post
//                        .values()
//                        .stream()
//                        .filter(firstNameMatches
//                                .and(lastNameMatches)
//                                .and(streetMatches)
//                                .and(cityMatches)
//                                .and(stateMatches)
//                                .and(zipMatches))
//                        .collect(Collectors.toList());
//    }

    @Override
    public String getTagsById(int postID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tag> getAllTags() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getSearchPosts(String tag) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void publishPost(int id, Post data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tag> getActiveTags() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getActivePosts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public PinPost addPinPost(PinPost pinPost){
         pinPost.setPinPostID(pinPostIdCounter);
        pinPostIdCounter++;
        
        blogPinPosts.put(pinPost.getPinPostID(), pinPost);
        return pinPost;
        
    }

    public void removePinPost(int pinPostId){
        blogPinPosts.remove(pinPostId);
        
    }

    public void updatePinPost(PinPost pinPost){
        blogPinPosts.put(pinPost.getPinPostID(), pinPost);
        
    }

    public List<PinPost> getAllPinPosts(){
        Collection<PinPost> c = blogPinPosts.values();
        return new ArrayList(c);
    }

    public PinPost getPinPostById(int pinPostId){
        return blogPinPosts.get(pinPostId);
    }

    public void publishPinPost(int id, PinPost data){
       throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<PinPost> getActivePinPosts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
}
