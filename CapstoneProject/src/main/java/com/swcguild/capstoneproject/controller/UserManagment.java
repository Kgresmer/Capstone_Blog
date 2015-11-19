package com.swcguild.capstoneproject.controller;

import com.swcguild.capstoneproject.dao.BlogDao;
import com.swcguild.capstoneproject.model.Authority;
import com.swcguild.capstoneproject.model.User;
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

@Controller
public class UserManagment {
    private BlogDao dao;

    @Inject
    public UserManagment(@Valid BlogDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/userManagement", method = RequestMethod.GET)
    public String displayCreateNewUserPage() {
        return "userManagement";
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public User createNewUserForm(@Valid @RequestBody User user) {
        dao.makeNewUser(user);
        return user;
    }
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putUser(@PathVariable("id") int id, @Valid @RequestBody User user) {
        dao.updateUser(user);
    }
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable("id") int id) {
        return dao.getUserById(id);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") int id) {
        dao.removeAuthority(dao.getUserById(id).getUsername());
        dao.removeUser(id);
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {

        return dao.getAllUsers();

    }
    
    @RequestMapping(value = "/authority/{username}", method = RequestMethod.GET)
    @ResponseBody
    public List<Authority> getAuthorities(@PathVariable("username") String username) {
        return dao.getAuthoritiesByUsername(username);
    }
    
    @RequestMapping(value = "/authority", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Authority createNewAuthority(@Valid @RequestBody Authority auth) {
        dao.makeNewAuthority(auth);
        return auth;
    }
    
     @RequestMapping(value = "/authority/{username}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthority(@PathVariable("username") String username) {
        dao.removeAuthority(username);
    }
    

}   

