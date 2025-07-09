package com.sixsausage.java.ai.langchain4j;

import com.sixsausage.java.ai.langchain4j.bean.ChatMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Queue;

@SpringBootTest(classes = {MongoCrudTest.class})
@SpringBootApplication
public class MongoCrudTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    /*@Test
  public void testInsert(){
       mongoTemplate.insert(new ChatMessages(1L,"聊天记录"));

   }
*/
    @Test
    public void testInsert2(){
        ChatMessages chatMessages=new ChatMessages();
        chatMessages.setContent("聊天记录列表");
        mongoTemplate.insert(chatMessages);
    }

    @Test
    public void testFindById() {
        ChatMessages chatMessages = mongoTemplate.findById("685b4e260e1dab6ca94577ef",
                ChatMessages.class);
        System.out.println(chatMessages);
    }

    @Test
    public void testUpdate() {
      Criteria criteria=Criteria.where("_id").is("685b4e260e1dab6ca94577ef");
        Query query=new Query(criteria);
        Update update=new Update();
        update.set("content","我是沙夫林");
        mongoTemplate.upsert(query,update,ChatMessages.class);
    }

    @Test
    public void testDelete(){
        Criteria criteria=Criteria.where("_id").is("685b4e260e1dab6ca94577ef");
        Query query=new Query(criteria);
        mongoTemplate.remove(query,ChatMessages.class);
    }
}
