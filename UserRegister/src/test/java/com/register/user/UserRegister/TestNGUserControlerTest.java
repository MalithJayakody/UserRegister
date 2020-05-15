package com.register.user.UserRegister;

import com.register.user.UserRegister.controllers.UserController;
import com.register.user.UserRegister.models.User;
import com.register.user.UserRegister.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

@SpringBootTest(classes = UserRegisterApplication.class)
public class TestNGUserControlerTest  extends AbstractTestNGSpringContextTests {
    @Autowired
    private UserController userController;

    private TestRestTemplate testRestTemplate;
    private String baseUri;

    @Test
    public void getUserService_notnull_then_ok(){
        Assert.assertNotNull(userController);
    }

    @Test
    public void getAllUser_not_emptylist_ok(){
        ResponseEntity<User[]> results = testRestTemplate.getForEntity(baseUri, User[].class);
        User[] userList =results.getBody();
        Assert.assertNotNull(userList);
        Assert.assertTrue(userList.length>0);
    }

    @Test
    public void insertUser_status_y_ok(){
        User newUser = new User("TESTNGUSER",12,12000,"Y");
        String out = testRestTemplate.postForObject(baseUri, newUser, String.class);
        Assert.assertNotNull(out);
        Assert.assertTrue(out.contains("Y"));
    }

    @Test
    public void getUser_lastuser_ok(){
        ResponseEntity<User[]> forEntity = testRestTemplate.getForEntity(baseUri, User[].class);
        User[] userList =forEntity.getBody();
        User lastUser = userList[userList.length-1];
        System.out.println("user name "+lastUser.getName());
        Assert.assertEquals(lastUser.getName(),"TESTNGUSER");
    }

    @Test(enabled = true)
    public void deleteuser_listcount_down_ok(){
        ResponseEntity<User[]> results = testRestTemplate.getForEntity(baseUri, User[].class);
        User[] userList =results.getBody();
        int initUserCount = userList.length;
        User lastUser = userList[userList.length-1];

        testRestTemplate.delete(baseUri+"/"+lastUser.getId());

        results = testRestTemplate.getForEntity(baseUri, User[].class);
        userList =results.getBody();
        Assert.assertEquals(initUserCount-1,userList.length);
    }

    @BeforeClass
    public void init_test(){
        testRestTemplate = new TestRestTemplate();
        baseUri = "http://localhost:8081/UserRegister/user/";
    }

    @AfterClass
    public void clean_test(){
        System.out.println("Cleaning...!");
    }
}
