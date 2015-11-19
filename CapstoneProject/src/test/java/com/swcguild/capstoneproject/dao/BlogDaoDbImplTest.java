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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
public class BlogDaoDbImplTest {

    private BlogDao dao;

    public BlogDaoDbImplTest() {
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
        cleaner.execute("delete from users");
        cleaner.execute("delete from authorities");
        cleaner.execute("delete from tags");
        cleaner.execute("delete from pinposts");

    }

    @After
    public void tearDown() {
    }


    /**
     * Test of getSearchPosts method, of class BlogDaoDbImpl.
     */
    @Test
    public void testGetSearchPosts() {
        System.out.println("getSearchPosts");
        String tag = "super";
        Post post1 = new Post("Supergirl", "Clark Kent", "hot new cousin saves city", "super, girl", LocalDateTime.now().toString(), "2015-12-20");
        Post post2 = new Post("Super", "Kent", "cousin saves city", "super", LocalDateTime.now().toString(), "2015-12-20");
        Post post3 = new Post("girl", "Clar", "new cousin saves city", "girl", LocalDateTime.now().toString(), "2015-12-20");
        post1.setStatus(1);
        post2.setStatus(1);
        post3.setStatus(1);
        dao.addPost(post1);
        dao.addPost(post2);
        dao.addPost(post3);
        List<Post> expResult = new ArrayList();
        expResult.add(post1);
        expResult.add(post2);
        List<Post> result = dao.getAllPosts();
        List<Post> result2 = dao.getSearchPosts("girl");
        assertEquals(expResult.size(), result2.size());

    }

    /**
     * Test of addPost method, of class BlogDaoDbImpl.
     */
    @Test
    public void testAddPost() {
        System.out.println("addPost");
        Post post = new Post("Supergirl", "Clark Kent", "hot new cousin saves city", "super, girl", LocalDateTime.now().toString(), "2015-12-30");

        dao.addPost(post);
        assertEquals(1, dao.getAllPosts().size());
        Post post2 = new Post("Supergirl", "Clark Kent", "hot new cousin saves city", "super, girl", LocalDateTime.now().toString(), "2015-12-30");

        dao.addPost(post2);
        assertEquals(2, dao.getAllPosts().size());
    }

    /**
     * Test of removePost method, of class BlogDaoDbImpl.
     */
    @Test
    public void testRemovePost() {
        System.out.println("removePost");
        Post post1 = new Post("Supergirl", "Clark Kent", "hot new cousin saves city", "super, girl", LocalDateTime.now().toString(), "2015-12-30");
        Post post2 = new Post("Supeergirl", "Clark Kentd", "hot5 new cousin saves city", "super, girl", LocalDateTime.now().toString(), "2015-12-30");
        Post post3 = new Post("Superfgirl", "Clark Kentd", "hot 4new cousin saves city", "super, girl", LocalDateTime.now().toString(), "2015-12-30");

        dao.addPost(post1);
    //    System.out.println(post1.getPostID());
        dao.addPost(post2);
    //   System.out.println(post2.getPostID());
        dao.addPost(post3);
    //    System.out.println(post3.getPostID());
        dao.removePost(post2.getPostID());
        assertEquals(2, dao.getAllPosts().size());
    }

    /**
     * Test of updatePost method, of class BlogDaoDbImpl.
     */
    @Test
    public void testUpdatePost() {
        System.out.println("updatePost");
        Post post1 = new Post("SuperMAN", "Clark Kent", "hot new cousin saves city", "super, girl", LocalDateTime.now().toString(), "2015-12-30");

        dao.updatePost(post1);
        assertEquals("SuperMAN", post1.getTitle());
    }

    /**
     * Test of getAllPosts method, of class BlogDaoDbImpl.
     */
    @Test
    public void testGetAllPosts() {
        System.out.println("getAllPosts");

        List<Post> expResult = dao.getAllPosts();
        List<Post> result = dao.getAllPosts();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllPostObjects method, of class BlogDaoDbImpl.
     */
    @Test
    public void testGetAllPostObjects() {
        System.out.println("getAllPostObjects");
        Post post1 = new Post("Supergirl", "Clark Kent", "hot new cousin saves city", "super, girl", LocalDateTime.now().toString(), "2015-12-20");
        Post post2 = new Post("Super", "Kent", "cousin saves city", "super", LocalDateTime.now().toString(), "2015-12-20");
        Post post3 = new Post("girl", "Clar", "new cousin saves city", "girl", LocalDateTime.now().toString(), "2015-12-20");
        post1.setStatus(1);
        post2.setStatus(1);
        post3.setStatus(1);
        dao.addPost(post1);
        dao.addPost(post2);
        dao.addPost(post3);
        List<Post> expResult = new ArrayList();
        expResult.add(post1);
        expResult.add(post2);
        expResult.add(post3);
        List<Post> result = dao.getAllPostObjects();
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of getActivePosts method, of class BlogDaoDbImpl.
     */
    @Test
    public void testGetActivePosts() {
        System.out.println("getActivePosts");
        Post post1 = new Post("Supergirl", "Clark Kent", "hot new cousin saves city", "super, girl", LocalDateTime.now().toString(), "2015-12-20");
        Post post2 = new Post("Super", "Kent", "cousin saves city", "super", LocalDateTime.now().toString(), "2015-12-20");
        Post post3 = new Post("girl", "Clar", "new cousin saves city", "girl", LocalDateTime.now().toString(), "2015-12-20");
        post1.setStatus(1);
        post2.setStatus(0);
        post3.setStatus(1);
        dao.addPost(post1);
        dao.addPost(post2);
        dao.addPost(post3);
        List<Post> expResult = new ArrayList();
        expResult.add(post1);
        expResult.add(post3);
        List<Post> result = dao.getActivePosts();
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of getPostById method, of class BlogDaoDbImpl.
     */
    @Test
    public void testGetPostById() {
        System.out.println("getPostById");
        Post expResult = new Post("Supergirl", "Clark Kent", "hot new cousin saves city", "super, girl", LocalDateTime.now().toString(), "2015-12-30");
        dao.addPost(expResult);
        int postId = expResult.getPostID();
        Post result = dao.getPostById(postId);
        assertTrue(expResult.getAuthor().equals(result.getAuthor()) && expResult.getTitle().equals(result.getTitle()));
    }

    /**
     * Test of getUserById method, of class BlogDaoDbImpl.
     */
    @Test
    public void testGetUserById() {
        System.out.println("getUserById");
//        User user1 = new User("Supergirl1", "pass1", true);
//        dao.makeNewUser(user1);
//        User user2 = new User("Supergirl2", "pass2", true);
//        dao.makeNewUser(user2);
//        User user3 = new User("Supergirl3", "pass3", true);
//        dao.makeNewUser(user3);
//        
//        User expResult = user1;
//        System.out.println(user1.getUserID());
//        User result = dao.getUserById(user1.getUserID());
//        assertEquals(expResult, result);
        
        User expResult = new User("Supergirl", "Clark Kent", true);
        dao.makeNewUser(expResult);
        int postId = expResult.getUserID();
        User result = dao.getUserById(postId);
        System.out.println("1"+expResult.getUsername());
        System.out.println("3"+expResult.getPassword());
        System.out.println("2"+result.getUsername());
        System.out.println("4"+result.getPassword());
        
        assertTrue(expResult.getUsername().equals(result.getUsername()) && expResult.getPassword().equals(result.getPassword()));
        
        
    }

    /**
     * Test of makeNewUser method, of class BlogDaoDbImpl.
     */
    @Test
    public void testMakeNewUser() {
        System.out.println("makeNewUser");
        User user = new User("Supergirl", "pass", true);

        dao.makeNewUser(user);
        assertEquals(1, dao.getAllUsers().size());
        User user2 = new User("Supergirl", "pass", true);
        dao.makeNewUser(user2);
        assertEquals(2, dao.getAllUsers().size());
    }

    /**
     * Test of updateUser method, of class BlogDaoDbImpl.
     */
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        User user1 = new User("SuperMAN", "Clark Kent", true);

        dao.updateUser(user1);
        assertEquals("SuperMAN", user1.getUsername());
    }

    /**
     * Test of removeUser method, of class BlogDaoDbImpl.
     */
    @Test
    public void testRemoveUser() {
        System.out.println("removeUser");
        System.out.println("removeUser");
        User user1 = new User("Supergirl", "Clark Kent", true);
        User user2 = new User("Supergirl1", "Clark Kent1", true);
        User user3 = new User("Supergirl2", "Clark Kent2", true);

        dao.makeNewUser(user1);
        System.out.println(user1.getUserID());
        dao.makeNewUser(user2);
        System.out.println(user2.getUserID());
        dao.makeNewUser(user3);
        System.out.println(user3.getUserID());
        dao.removeUser(user2.getUserID());
        assertEquals(2, dao.getAllUsers().size());
    }

    /**
     * Test of getAllUsers method, of class BlogDaoDbImpl.
     */
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        List<User> expresult = new ArrayList<>();
        User user1 = new User("Supergirl", "Clark Kent", true);
        expresult.add(user1);
        User user2 = new User("Supergirl1", "Clark Kent1", true);
        expresult.add(user2);
        User user3 = new User("Supergirl2", "Clark Kent2", true);
        expresult.add(user3);

        assertTrue(expresult.size() == 3);
    }

    /**
     * Test of getAuthoritiesByUsername method, of class BlogDaoDbImpl.
     */
    @Test
    public void testGetAuthoritiesByUsername() {
        System.out.println("getAuthoritiesByUsername");
        Authority auth = new Authority("username", "authority");

        dao.makeNewAuthority(auth);
        List<Authority> expResult = null;
        List<Authority> result = dao.getAuthoritiesByUsername("username");
        assertTrue(result.size() == 1);
    }

    /**
     * Test of makeNewAuthority method, of class BlogDaoDbImpl.
     */
    @Test
    public void testMakeNewAuthority() {
        System.out.println("makeNewAuthority");
        Authority auth = new Authority("username", "authority");

        dao.makeNewAuthority(auth);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(dao.getAllAuthorities().size() == 1);
    }

    /**
     * Test of removeAuthority method, of class BlogDaoDbImpl.
     */
    @Test
    public void testRemoveAuthority() {
        System.out.println("removeAuthority");
        Authority auth = new Authority("username", "authority");

        dao.makeNewAuthority(auth);
        dao.removeAuthority("username");
        assertTrue(dao.getAllAuthorities().isEmpty());
    }

    /**
     * Test of getAllAuthorities method, of class BlogDaoDbImpl.
     */
    @Test
    public void testGetAllAuthorities() {
        System.out.println("getAllAuthorities");
        List<Authority> expResult = new ArrayList<Authority>(dao.getAllAuthorities());
        List<Authority> result = dao.getAllAuthorities();
        assertEquals(expResult, result);

    }

    /**
     * Test of publishPost method, of class BlogDaoDbImpl.
     */
    @Test
    public void testPublishPost() {
        System.out.println("publishPost");
        Post post3 = new Post("girl", "Clar", "new cousin saves city", "girl", LocalDateTime.now().toString(), "2015-12-20");
        post3.setStatus(1);
        dao.addPost(post3);
        dao.publishPost(post3.getPostID(), post3);
        assertEquals(1, post3.getStatus());
    }

    /**
     * Test of getTagsById method, of class BlogDaoDbImpl.
     */
    @Test
    public void testGetTagsById() {
        System.out.println("getTagsById");
        Post p1 = new Post("Supergirl", "Clark Kent", "hot new cousin saves city", "super", LocalDateTime.now().toString(), "2015-12-30");
        Post p2 = new Post("Supergirl", "Clark Kent", "hot new cousin saves city", "super1", LocalDateTime.now().toString(), "2015-12-30");
        Post p3 = new Post("Supergirl", "Clark Kent", "hot new cousin saves city", "super2", LocalDateTime.now().toString(), "2015-12-30");
        dao.addPost(p1);
        dao.addPost(p2);
        dao.addPost(p3);

        int postID = p1.getPostID();
        String expresult = p1.getTags();
        String result = dao.getTagsById(postID);
        assertEquals(expresult, result);
    }

    /**
     * Test of getAllTags method, of class BlogDaoDbImpl.
     */
    @Test
    public void testGetAllTags() {
        System.out.println("getAllTags");
        List<Tag> expResult = new ArrayList<Tag>(dao.getAllTags());
        List<Tag> result = dao.getAllTags();
        assertEquals(expResult, result);
    }

    /**
     * Test of getActiveTags method, of class BlogDaoDbImpl.
     */
    @Test
    public void testGetActiveTags() {
        System.out.println("getActiveTags");
        Post post1 = new Post("Supergirl", "Clark Kent", "hot new cousin saves city", "super, girl", LocalDateTime.now().toString(), "2015-12-20");
        Post post2 = new Post("Super", "Kent", "cousin saves city", "super", LocalDateTime.now().toString(), "2015-12-20");
        Post post3 = new Post("girl", "Clar", "new cousin saves city", "girl", LocalDateTime.now().toString(), "2015-12-20");
        post1.setStatus(1);
        post2.setStatus(1);
        post3.setStatus(1);
        dao.addPost(post1);
        dao.addPost(post2);
        dao.addPost(post3);
        List<Tag> result = dao.getActiveTags();
        String expT = "supergirl";
        String resT = "";
        for (Tag t : result) {
            resT += t.getTag();
        }
        assertEquals(expT, resT);
    }

    /**
     * Test of addPinPost method, of class BlogDaoDbImpl.
     */
    @Test
    public void testAddPinPost() {
        System.out.println("addPinPost");
        PinPost pinPost = new PinPost("Cali", "Shawn", "Won by 14", LocalDateTime.now().toString(), "2016-01-01");

        dao.addPinPost(pinPost);
        assertEquals(1, dao.getAllPinPosts().size());
    }

    /**
     * Test of removePinPost method, of class BlogDaoDbImpl.
     */
    @Test
    public void testRemovePinPost() {
        System.out.println("removePinPost");
        PinPost pinPost1 = new PinPost("Cali", "Shawn", "Won by 14", LocalDateTime.now().toString(), "2016-01-01");
        PinPost pinPost2 = new PinPost("Cali1", "Shawn", "Won by 14", LocalDateTime.now().toString(), "2016-01-01");
        PinPost pinPost3 = new PinPost("Cali2", "Shawn", "Won by 14", LocalDateTime.now().toString(), "2016-01-01");

        dao.addPinPost(pinPost1);
     //   System.out.println(pinPost1.getPinPostID());
        dao.addPinPost(pinPost2);
     //   System.out.println(pinPost2.getPinPostID());
        dao.addPinPost(pinPost3);
     //   System.out.println(pinPost3.getPinPostID());
        dao.removePinPost(pinPost2.getPinPostID());
        assertEquals(2, dao.getAllPinPosts().size());
    }

    /**
     * Test of updatePinPost method, of class BlogDaoDbImpl.
     */
    @Test
    public void testUpdatePinPost() {
        System.out.println("updatePinPost");
        PinPost pinPost = new PinPost("Cali", "Shawn", "Won by 14", LocalDateTime.now().toString(), "2016-01-01");

        dao.updatePinPost(pinPost);
        assertEquals("Cali", pinPost.getTitle());
    }

    /**
     * Test of getAllPinPosts method, of class BlogDaoDbImpl.
     */
    @Test
    public void testGetAllPinPosts() {
        System.out.println("getAllPinPosts");

        List<PinPost> expResult = new ArrayList<PinPost>(dao.getAllPinPosts());
        List<PinPost> result = dao.getAllPinPosts();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPinPostById method, of class BlogDaoDbImpl.
     */
    @Test
    public void testGetPinPostById() {
        System.out.println("getPinPostById");
        PinPost expResult = new PinPost("Cali", "Shawn", "Won by 14", LocalDateTime.now().toString(), "2016-01-01");
        dao.addPinPost(expResult);
        int pinPostId = expResult.getPinPostID();
        PinPost result = dao.getPinPostById(pinPostId);
        assertTrue(expResult.getAuthor().equals(result.getAuthor()) && expResult.getTitle().equals(result.getTitle()));

    }

    /**
     * Test of getActivePinPosts method, of class BlogDaoDbImpl.
     */
    @Test
    public void testGetActivePinPosts() {
        System.out.println("getActivePinPosts");

        List<PinPost> expResult = new ArrayList<PinPost>(dao.getActivePinPosts());;
        List<PinPost> result = dao.getActivePinPosts();
        assertEquals(expResult, result);
    }

    /**
     * Test of publishPinPost method, of class BlogDaoDbImpl.
     */
    @Test
    public void testPublishPinPost() {
        System.out.println("publishPinPost");
        List<PinPost> expresult = new ArrayList<>();
        PinPost pinPost1 = new PinPost("Cali1", "Shawn", "Won by 14", LocalDateTime.now().toString(), "2016-01-01");
        expresult.add(pinPost1);
        PinPost pinPost2 = new PinPost("Cali2", "Shawn", "Won by 14", LocalDateTime.now().toString(), "2016-01-01");
        expresult.add(pinPost2);
        PinPost pinPost3 = new PinPost("Cali3", "Shawn", "Won by 14", LocalDateTime.now().toString(), "2016-01-01");
        expresult.add(pinPost3);
    }

}
