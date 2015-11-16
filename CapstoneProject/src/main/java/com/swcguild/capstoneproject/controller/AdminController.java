/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.controller;

import com.swcguild.capstoneproject.dao.BlogDao;
import com.swcguild.capstoneproject.model.Data;
import com.swcguild.capstoneproject.model.Post;
import com.swcguild.capstoneproject.model.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author apprentice
 */
@Controller
public class AdminController {

    private BlogDao dao;

    @Inject
    public AdminController(@Valid BlogDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String displayAdminPage() {
        return "admin";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Post createPost(@Valid @RequestBody Post post) {
        dao.addPost(post);
        return post;
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Post getPost(@PathVariable("id") int id) {

        return dao.getPostById(id);

    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("id") int id) {

        dao.removePost(id);

    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putPost(@PathVariable("id") int id, @Valid @RequestBody Post address) {

        address.setPostID(id);
        dao.updatePost(address);

    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getAllPosts() {

        return dao.getAllPosts();

    }
    
    @RequestMapping(value = "/activeposts", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getActivePosts() {

        return dao.getActivePosts();

    }

    @RequestMapping(value = "/tag/{tag}", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getSearchPosts(@PathVariable("tag") String tag) {

        return dao.getSearchPosts(tag);

    }

    @RequestMapping(value = "/tags/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getTags(@PathVariable("id") int id) {

        return dao.getTagsById(id);

    }

    @RequestMapping(value = "/alltags", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getTags() {

        return dao.getAllTags();

    }
    
    @RequestMapping(value = "/activetags", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getActiveTags() {

        return dao.getActiveTags();

    }
    

    @RequestMapping(value = "/post/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public void publishPost(@PathVariable("id") int id, @Valid @RequestBody Post data) {

        dao.publishPost(id, data);

    }
}
