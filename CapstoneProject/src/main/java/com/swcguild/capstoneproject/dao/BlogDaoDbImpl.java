package com.swcguild.capstoneproject.dao;

import com.swcguild.capstoneproject.model.Authority;
import com.swcguild.capstoneproject.model.PinPost;
import com.swcguild.capstoneproject.model.Post;
import com.swcguild.capstoneproject.model.Tag;
import com.swcguild.capstoneproject.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * select * from tags
 *
 *
 *
 * @author apprentice
 *
 */
public class BlogDaoDbImpl implements BlogDao {

    private static final String SQL_INSERT_POST
            = "insert into posts (title, author, content, postDate, expirationDate, status) values (?,?,?,?,?,?) ";

    private static final String SQL_INSERT_TAGS = "insert into tags (tag, post_ID) values (?, ?)";

    private static final String SQL_DELETE_POST
            = "delete posts, tags from posts inner join tags where posts.post_ID = tags.post_ID and posts.post_ID = ?";

    private static final String SQL_SELECT_POST = "SELECT * FROM posts WHERE post_ID = ?";

    private static final String SQL_GET_TAGS = "select * from tags where post_ID = ?";

    private static final String SQL_GET_ACTIVE_TAGS = "select DISTINCT tag from tags where post_ID in (Select post_ID from posts where status = 1)";

    private static final String SQL_SELECT_ALL_TAGS = "select * from tags group by tag";

    private static final String SQL_SELECT_ALL_POSTS = "select * from posts";

    private static final String SQL_SELECT_ACTIVE_POSTS = "select * from posts where status = 1";

    private static final String SQL_UPDATE_POST
            = "update posts set title = ?, author = ?, content = ?, postDate = ?, expirationDate =?, status = ? where post_ID = ?";

    private static final String SQL_DELETE_TAGS = "delete from tags where post_ID = ?";

    private static final String SQL_SEARCH_BY_TAG = "SELECT * FROM `posts` WHERE post_ID in (SELECT post_ID from tags where tag = ? and status = 1)";

    private static final String SQL_INSERT_PINPOST
            = "insert into pinposts (title, author, content, postDate, expirationDate, status) values (?,?,?,?,?,?) ";

    private static final String SQL_DELETE_PINPOST = "DELETE FROM pinposts WHERE post_ID = ?";

    private static final String SQL_SELECT_PINPOST = "SELECT * FROM pinposts WHERE post_ID = ?";

    private static final String SQL_SELECT_ALL_PINPOSTS = "select * from pinposts";

    private static final String SQL_SELECT_ACTIVE_PINPOSTS = "select * from pinposts where status = 1";

    private static final String SQL_UPDATE_PINPOST
            = "update pinposts set title = ?, author = ?, content = ?, postDate = ?, expirationDate =?, status = ? where post_ID = ?";

    private static final String SQL_SELECT_USER = "SELECT * FROM users WHERE user_ID = ?";

    private static final String SQL_NEW_USER = "INSERT INTO `users`(`username`, `password`, `enabled`) VALUES (?,?,?)";

    private static final String SQL_UPDATE_USER
            = "update users set username = ?, password = ?, enabled = ? where user_ID = ?";

    private static final String SQL_DELETE_USER
            = "delete from users where user_ID = ?";
    
    private static final String SQL_SELECT_ALL_USERS = "select * from users";

    private static final String SQL_NEW_AUTH = "INSERT INTO `authorities`(`username`, `authority`) VALUES (?,?)";

    private static final String SQL_UPDATE_AUTH
            = "update users set username = ?, authority = ? where user_ID = ?";

    private static final String SQL_SELECT_AUTH = "select * from authorities where username = ?";

    private static final String SQL_DELETE_AUTH = "delete from authorities where username = ?";

    private static final String SQL_SELECT_ALL_AUTH = "select * from authorities";

    private JdbcTemplate jdbcTemplate;

    public void setjdbcTemplate(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    public List<Post> getSearchPosts(String tag) {
        return jdbcTemplate.query(SQL_SEARCH_BY_TAG, new PostMapper(), tag);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Post addPost(Post post) {

        post.setPostDate(LocalDateTime.now().toString());
        jdbcTemplate.update(SQL_INSERT_POST,
                post.getTitle(),
                post.getAuthor(),
                post.getContent(),
                post.getPostDate(),
                post.getExpirationDate(),
                post.getStatus());

        post.setPostID(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        String[] tags = post.getTags().split(",");
        for (String tag : tags) {
            if (!tag.isEmpty()) {
                tag = tag.trim();
                jdbcTemplate.update(SQL_INSERT_TAGS, tag, post.getPostID());
            }
        }
        return post;
    }

    @Override
    public void removePost(int postID) {
        jdbcTemplate.update(SQL_DELETE_POST, postID);
    }

    @Override
    public void updatePost(Post post) {
        jdbcTemplate.update(SQL_UPDATE_POST,
                post.getTitle(),
                post.getAuthor(),
                post.getContent(),
                post.getPostDate(),
                post.getExpirationDate(),
                post.getStatus(),
                post.getPostID());
        jdbcTemplate.update(SQL_DELETE_TAGS, post.getPostID());
        String[] tags = post.getTags().split(",");
        for (String tag : tags) {
            if (!tag.isEmpty()) {
                tag = tag.trim();
                jdbcTemplate.update(SQL_INSERT_TAGS, tag, post.getPostID());
            }
        }

    }

    @Override
    public List<Post> getAllPosts() {

        List<Post> postList = getAllPostObjects();

        for (Post post : postList) {
            if (LocalDate.parse(post.getExpirationDate()).isBefore(getLocalDate())) {
                post.setStatus(0);
                publishPost(post.getPostID(), post);
            }
        }

        return jdbcTemplate.query(SQL_SELECT_ALL_POSTS, new PostMapper());
    }

    @Override
    public List<Post> getAllPostObjects() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POSTS, new PostMapper());
    }

    @Override
    public List<Post> getActivePosts() {
        return jdbcTemplate.query(SQL_SELECT_ACTIVE_POSTS, new PostMapper());
    }

    @Override
    public Post getPostById(int postID) {
        try {
            Post post = jdbcTemplate.queryForObject(SQL_SELECT_POST, new PostMapper(), postID);
            return post;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public User getUserById(int userID) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_USER, new UserMapper(), userID);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User makeNewUser(User user) {
        if (user.isActive()) {
            jdbcTemplate.update(SQL_NEW_USER, user.getUsername(), user.getPassword(), 1);
        } else {
            jdbcTemplate.update(SQL_NEW_USER, user.getUsername(), user.getPassword(), 0);
        }
        user.setUserID(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        if (user.isAdmin()) {
            removeAuthority(user.getUsername());
            makeNewAuthority(new Authority(user.getUsername(), "ROLE_USER"));
            makeNewAuthority(new Authority(user.getUsername(),"ROLE_ADMIN"));
        } else {
            removeAuthority(user.getUsername());
            makeNewAuthority(new Authority(user.getUsername(), "ROLE_USER"));
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        
        if (user.isAdmin()) {
            removeAuthority(user.getUsername());
            makeNewAuthority(new Authority(user.getUsername(), "ROLE_USER"));
            makeNewAuthority(new Authority(user.getUsername(),"ROLE_ADMIN"));
        } else {
            removeAuthority(user.getUsername());
            makeNewAuthority(new Authority(user.getUsername(), "ROLE_USER"));
        }
        
        if (user.isActive()) {
            jdbcTemplate.update(SQL_UPDATE_USER, user.getUsername(), user.getPassword(), 1,  user.getUserID());
        } else {
            jdbcTemplate.update(SQL_UPDATE_USER, user.getUsername(), user.getPassword(), 0, user.getUserID());
        }

        
    }

    @Override
    public void removeUser(int userID) {
        removeAuthority(getUserById(userID).getUsername());
        jdbcTemplate.update(SQL_DELETE_USER, userID);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = jdbcTemplate.query(SQL_SELECT_ALL_USERS, new UserMapper());
        return users;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Authority getAuthorityByUsername(String username) {
        try {
            Authority auth = jdbcTemplate.queryForObject(SQL_SELECT_AUTH, new AuthorityMapper(), username);
            return auth;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<Authority> getAuthoritiesByUsername(String username) {
        try {
            List<Authority> auth = jdbcTemplate.query(SQL_SELECT_AUTH, new AuthorityMapper(), username);
            return auth;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void makeNewAuthority(Authority auth) {
        jdbcTemplate.update(SQL_NEW_AUTH, auth.getUsername(), auth.getAuthority());
    }

    @Override
    public void removeAuthority(String username) {
        jdbcTemplate.update(SQL_DELETE_AUTH, username);
    }

    @Override
    public List<Authority> getAllAuthorities() {
        return jdbcTemplate.query(SQL_SELECT_ALL_AUTH, new AuthorityMapper());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    @Override
    public void publishPost(int id, Post data) {
        if (id != 0) {
            jdbcTemplate.update(SQL_UPDATE_POST,
                    data.getTitle(),
                    data.getAuthor(),
                    data.getContent(),
                    data.getPostDate(),
                    data.getExpirationDate(),
                    data.getStatus(),
                    data.getPostID());
            jdbcTemplate.update(SQL_DELETE_TAGS, data.getPostID());
            if (data.getTags() != null) {
                String[] tags = data.getTags().split(",");
                for (String tag : tags) {
                    if (!tag.isEmpty()) {
                        tag = tag.trim();
                        jdbcTemplate.update(SQL_INSERT_TAGS, tag, data.getPostID());
                    }
                }
            }
        } else {
            data.setPostDate(LocalDateTime.now().toString());
            jdbcTemplate.update(SQL_INSERT_POST,
                    data.getTitle(),
                    data.getAuthor(),
                    data.getContent(),
                    data.getPostDate(),
                    data.getExpirationDate(),
                    data.getStatus());

            data.setPostID(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));

            if (data.getTags() != null) {
                String[] tags = data.getTags().split(",");
                for (String tag : tags) {
                    if (!tag.isEmpty()) {
                        tag = tag.trim();
                        jdbcTemplate.update(SQL_INSERT_TAGS, tag, data.getPostID());
                    }
                }
            }

        }

    }

    @Override
    public String getTagsById(int postID) {
        try {
            List<Tag> tags = jdbcTemplate.query(SQL_GET_TAGS, new TagMapper(), postID);
            String tagList = "";
            for (int i = 0; i < tags.size(); i++) {
                if (i == tags.size() - 1) {
                    tagList += tags.get(i).getTag();
                } else {
                    tagList += tags.get(i).getTag() + ", ";
                }
            }
            return tagList;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query(SQL_SELECT_ALL_TAGS, new TagMapper());
    }

    @Override
    public List<Tag> getActiveTags() {
        return jdbcTemplate.query(SQL_GET_ACTIVE_TAGS, new TagActMapper());
    }

    private LocalDate getLocalDate() {
        return LocalDate.now();
    }

    private static final class PostMapper implements ParameterizedRowMapper<Post> {

        @Override
        public Post mapRow(ResultSet rs, int i) throws SQLException {
            Post post = new Post();
            post.setPostID(rs.getInt("post_id"));
            post.setTitle(rs.getString("title"));
            post.setAuthor(rs.getString("author"));
            post.setContent(rs.getString("content"));
            post.setTags(rs.getString("tags"));
            post.setPostDate(rs.getString("postDate"));
            post.setExpirationDate(rs.getString("expirationDate"));
            post.setStatus(rs.getInt("status"));
            return post;
        }
    }

    private static final class TagMapper implements ParameterizedRowMapper<Tag> {

        @Override
        public Tag mapRow(ResultSet rs, int i) throws SQLException {
            Tag post = new Tag();
            post.setPostID(rs.getInt("post_id"));
            post.setTag(rs.getString("tag"));
            return post;
        }
    }

    private static final class TagActMapper implements ParameterizedRowMapper<Tag> {

        @Override
        public Tag mapRow(ResultSet rs, int i) throws SQLException {
            Tag post = new Tag();
            post.setPostID(1);
            post.setTag(rs.getString("tag"));
            return post;
        }
    }

    private static final class PinPostMapper implements ParameterizedRowMapper<PinPost> {

        @Override
        public PinPost mapRow(ResultSet rs, int i) throws SQLException {
            PinPost post = new PinPost();
            post.setPinPostID(rs.getInt("post_id"));
            post.setTitle(rs.getString("title"));
            post.setAuthor(rs.getString("author"));
            post.setContent(rs.getString("content"));
            post.setPostDate(rs.getString("postDate"));
            post.setExpirationDate(rs.getString("expirationDate"));
            post.setStatus(rs.getInt("status"));
            return post;
        }
    }

    private static final class UserMapper implements ParameterizedRowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setUserID(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            if (rs.getInt("enabled") == 1) {
                user.setActive(true);
            } else {
                user.setActive(false);
            }

            return user;
        }
    }

    private static final class AuthorityMapper implements ParameterizedRowMapper<Authority> {

        @Override
        public Authority mapRow(ResultSet rs, int i) throws SQLException {
            Authority auth = new Authority();
            auth.setUsername(rs.getString("username"));
            auth.setAuthority(rs.getString("authority"));
            return auth;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public PinPost addPinPost(PinPost pinPost) {
        pinPost.setPostDate(LocalDateTime.now().toString());
        jdbcTemplate.update(SQL_INSERT_PINPOST,
                pinPost.getTitle(),
                pinPost.getAuthor(),
                pinPost.getContent(),
                pinPost.getPostDate(),
                pinPost.getExpirationDate(),
                pinPost.getStatus());

        pinPost.setPinPostID(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        return pinPost;
    }

    public void removePinPost(int pinPostId) {
        jdbcTemplate.update(SQL_DELETE_PINPOST, pinPostId);

    }

    public void updatePinPost(PinPost pinPost) {
        jdbcTemplate.update(SQL_UPDATE_PINPOST,
                pinPost.getTitle(),
                pinPost.getAuthor(),
                pinPost.getContent(),
                pinPost.getPostDate(),
                pinPost.getExpirationDate(),
                pinPost.getStatus(),
                pinPost.getPinPostID());
        jdbcTemplate.update(SQL_DELETE_TAGS, pinPost.getPinPostID());

    }

    public List<PinPost> getAllPinPosts() {
        return jdbcTemplate.query(SQL_SELECT_ALL_PINPOSTS, new PinPostMapper());
    }

    public PinPost getPinPostById(int pinPostId) {
        try {
//            Tag tags = jdbcTemplate.queryForObject(SQL_GET_TAGS, new tagMapper(), postID);
//            String tagList = "";
//            for(String tag: tags.getList()) {
//                tagList += ", " + tag;
//            }
            PinPost pinPost = jdbcTemplate.queryForObject(SQL_SELECT_PINPOST, new PinPostMapper(), pinPostId);
//            post.setTags(tagList);
            return pinPost;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<PinPost> getActivePinPosts() {
        return jdbcTemplate.query(SQL_SELECT_ACTIVE_PINPOSTS, new PinPostMapper());
    }

    public void publishPinPost(int id, PinPost data) {
        if (id != 0) {
            jdbcTemplate.update(SQL_UPDATE_PINPOST,
                    data.getTitle(),
                    data.getAuthor(),
                    data.getContent(),
                    data.getPostDate(),
                    data.getExpirationDate(),
                    data.getStatus(),
                    data.getPinPostID());
        } else {
            data.setPostDate(LocalDateTime.now().toString());
            jdbcTemplate.update(SQL_INSERT_PINPOST,
                    data.getTitle(),
                    data.getAuthor(),
                    data.getContent(),
                    data.getPostDate(),
                    data.getExpirationDate(),
                    data.getStatus());

        }

    }

}
