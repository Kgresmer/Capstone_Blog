/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.capstoneproject.dao;

import com.swcguild.capstoneproject.model.Post;
import com.swcguild.capstoneproject.model.Tag;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class BlogDaoInMemImplTest {
    
    private BlogDao dao;
    
    public BlogDaoInMemImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
dao = (BlogDao) ctx.getBean("BlogDao");
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("delete from posts");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addPost method, of class BlogDaoInMemImpl.
     */
    @Test
    public void testAddPost() {
        System.out.println("addPost");
        Post post = new Post("Supergirl", "Clark Kent", "hot new cousin saves city", "super, girl", LocalDateTime.now().toString(), "12-30-2015");
        BlogDaoInMemImpl instance = new BlogDaoInMemImpl();
        instance.addPost(post);
        assertEquals(1, instance.getBlogPosts().size());
        Post post2 = new Post();
        instance.addPost(post2);
         assertEquals(2, instance.getBlogPosts().size());
    }

    /**
     * Test of removePost method, of class BlogDaoInMemImpl.
     */
    @Test
    public void testRemovePost() {
        System.out.println("removePost");
        Post post1 = new Post("Supergirl", "Clark Kent", "hot new cousin saves city", "super, girl", LocalDateTime.now().toString(), "12-30-2015");
        Post post2 = new Post("Supeergirl", "Clark Kentd", "hot5 new cousin saves city", "super, girl", LocalDateTime.now().toString(), "12-30-2015");
        Post post3 = new Post("Superfgirl", "Clark Kentd", "hot 4new cousin saves city", "super, girl", LocalDateTime.now().toString(), "12-30-2015");
        BlogDaoInMemImpl instance = new BlogDaoInMemImpl();
        instance.addPost(post1);
        System.out.println(post1.getPostID());
        instance.addPost(post2);
        System.out.println(post2.getPostID());
        instance.addPost(post3);
        System.out.println(post3.getPostID());
        instance.removePost(post2.getPostID());
        assertEquals(2, instance.getBlogPosts().size());
    }

    /**
     * Test of updatePost method, of class BlogDaoInMemImpl.
     */
    @Test
    public void testUpdatePost() {
        System.out.println("updatePost");
        Post post1 = new Post("SuperMAN", "Clark Kent", "hot new cousin saves city", "super, girl", LocalDateTime.now().toString(), "12-30-2015");
        BlogDaoInMemImpl instance = new BlogDaoInMemImpl();
        instance.updatePost(post1);
        assertEquals("SuperMAN", post1.getTitle());
    }

    /**
     * Test of getAllPosts method, of class BlogDaoInMemImpl.
     */
    @Test
    public void testGetAllPosts() {
        System.out.println("getAllPosts");
        BlogDaoInMemImpl instance = new BlogDaoInMemImpl();
        List<Post> expResult = new ArrayList<Post> (instance.getBlogPosts().values());
        List<Post> result = instance.getAllPosts();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPostById method, of class BlogDaoInMemImpl.
     */
    @Test
    public void testGetPostById() {
        System.out.println("getPostById");
        int postId = 0;
        BlogDaoInMemImpl instance = new BlogDaoInMemImpl();
        Post expResult = new Post("Supergirl", "Clark Kent", "hot new cousin saves city", "super, girl", LocalDateTime.now().toString(), "12-30-2015");
        instance.addPost(expResult);
        Post result = instance.getPostById(postId);
        assertEquals(expResult, result);
    }

    
}
