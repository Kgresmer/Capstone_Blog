/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.controller;

import com.swcguild.capstoneproject.dao.BlogDao;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
/**
 *
 * @author apprentice
 */
public class ContactController {
     private BlogDao dao;
    
    @Inject
    public ContactController (BlogDao dao) {
        this.dao=dao;
    }
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String displayContactPage(){
        return ("contact");
    }
    
}
