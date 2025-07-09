package com.sixsausage.java.ai.langchain4j;

import com.sixsausage.java.ai.langchain4j.assistant.Assistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {AIServiceTest.class})
@SpringBootApplication
public class AIServiceTest {
    @Autowired
    private QwenChatModel qwenChatModel;
    @Test
    public void testChat() {
//创建AIService
        Assistant assistant = AiServices.create(Assistant.class, qwenChatModel);
//调用service的接口
        String answer = assistant.chat("你是谁？");
        System.out.println(answer);
    }
    @Autowired
    Assistant assistant;
    @Test
    public void AssistantTest(){
        String answer=assistant.chat("你能做些什么？");
        System.out.println(answer);
    }
}
