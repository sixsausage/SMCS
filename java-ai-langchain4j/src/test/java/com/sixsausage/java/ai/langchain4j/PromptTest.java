package com.sixsausage.java.ai.langchain4j;

import com.sixsausage.java.ai.langchain4j.assistant.MemoryChatAssistant;
import com.sixsausage.java.ai.langchain4j.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {PromptTest.class})
@SpringBootApplication
public class PromptTest {
    @Autowired
    private SeparateChatAssistant separateChatAssistant;
    @Test
    public void testSystemMessage(){
        String answer=separateChatAssistant.chat(3,"你是谁？");
        System.out.println(answer);
    }
    @Test
    public void testSystemMessagedate(){
        String answer=separateChatAssistant.chat(3,"今天几号？");
        System.out.println(answer);
    }
    @Test
    public void testSystemMessagedate1(){
        String answer=separateChatAssistant.chat(4,"今天几号？");
        System.out.println(answer);
    }

    @Autowired
    private MemoryChatAssistant memoryChatAssistant;
    @Test
    public void testUserMessage() {
        String answer = memoryChatAssistant.chat("你的职责是什么");
        System.out.println(answer);
    }

    @Test
    public void testUserInfo() {
        String answer = separateChatAssistant.chat3(9, "我是谁，我多大了", "大谷", 22);
        System.out.println(answer);
    }
}
