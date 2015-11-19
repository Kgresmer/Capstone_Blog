/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.controller;
//

import com.swcguild.capstoneproject.dao.BlogDao;
import com.swcguild.capstoneproject.model.PinPost;
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
//
///**
// *
// * @author apprentice
// */

@Controller
public class PinnedPostController {

    private BlogDao dao;

    @Inject
    public PinnedPostController(@Valid BlogDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/pinned", method = RequestMethod.GET)
    public String displayPinnedPage() {
        return "pinned";
    }

    @RequestMapping(value = "/pinpost", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PinPost createPinPost(@Valid @RequestBody PinPost pinPost) {
        dao.addPinPost(pinPost);
        return pinPost;
    }

    @RequestMapping(value = "/pinpost/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PinPost getPinPost(@PathVariable("id") int id) {

        return dao.getPinPostById(id);

    }

    @RequestMapping(value = "/pinpost/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePinPost(@PathVariable("id") int id) {

        dao.removePinPost(id);

    }

    @RequestMapping(value = "/pinpost/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putPost(@PathVariable("id") int id, @Valid @RequestBody PinPost pinPost) {

        pinPost.setPinPostID(id);
        dao.updatePinPost(pinPost);

    }

    @RequestMapping(value = "/pinposts", method = RequestMethod.GET)
    @ResponseBody
    public List<PinPost> getAllPinPosts() {

        return dao.getAllPinPosts();

    }

    @RequestMapping(value = "/activepins", method = RequestMethod.GET)
    @ResponseBody
    public List<PinPost> getActivePinPosts() {

        return dao.getActivePinPosts();

    }
    
//    @RequestMapping(value = "/pinposts", method = RequestMethod.GET)
//    @ResponseBody
//    public List<PinPost> getPinPostById() {
//
//        return dao.getPinPostById();
//
//    }
//
    @RequestMapping(value = "/pinpost/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public void publishPinPost(@PathVariable("id") int id, @RequestBody PinPost data) {

        dao.publishPinPost(id, data);

    }
}
