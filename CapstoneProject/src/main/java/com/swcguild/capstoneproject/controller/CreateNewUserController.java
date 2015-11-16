/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.controller;

import com.swcguild.capstoneproject.dao.BlogDao;
import com.swcguild.capstoneproject.model.User;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
/**
 *
 * @author apprentice
 */
public class CreateNewUserController {
    private BlogDao dao;

    @Inject
    public CreateNewUserController(@Valid BlogDao dao) {
        this.dao = dao;
    }
    @RequestMapping(value = "/createNewUser", method = RequestMethod.GET)
    public String displayCreateNewUserPage() {
        return "createNewUser";
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public User createNewUserForm(@Valid @RequestBody User user) {
        dao.makeNewUser(user);
        return user;
        
    }
}   

