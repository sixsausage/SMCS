package com.sixsausage.java.ai.langchain4j;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.URL;

@SpringBootTest(classes = {LLMTest.class})
@SpringBootApplication
public class LLMTest {

    /**
     * gpt-4o-mini语言模型接入测试
     */
    @Test
    public void testGPTDemo() {
        // 初始化模型
        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("http://langchain4j.dev/demo/openai/v1") // 添加 baseUrl
                .apiKey("demo") // 设置模型apiKey
                .modelName("gpt-4o-mini") // 设置模型名称
                .build();
        // 向模型提问
        String answer = model.chat("你好");
        // 输出结果
        System.out.println(answer);
    }
    @Autowired
    private OpenAiChatModel openAiChatModel;
    @Test
    public void OpenAISpringTest(){
        String answer=openAiChatModel.chat("你几岁了");
        System.out.println(answer);
    }

    @Autowired
    private OllamaChatModel ollamaChatModel;
    @Test
    public void testOllama(){
        String answer=ollamaChatModel.chat("如何找java实习？");
        System.out.println(answer);
    }


    @Autowired
    private QwenChatModel qwenChatModel;
    @Test
    public void testDashScopeQwen() {
//向模型提问
        String answer = qwenChatModel.chat("阿里java开发实习有什么要求？");
//输出结果
        System.out.println(answer);
    }

    @Test
    public void testDashScopeWanx(){
        WanxImageModel wanxImageModel = WanxImageModel.builder()
                .modelName("wanx2.1-t2i-plus")
                .apiKey(System.getenv("DASH_SCOPE_API_KEY"))
                .build();

        Response<Image> response = wanxImageModel.generate("一位高贵神秘的精灵女王，站立在充满魔法气息的森林中。她的银色长发如流水般飘逸，身穿由藤蔓与花朵编织而成的华美长袍，双眼闪烁着古老的智慧光芒。周围漂浮着萤火虫和发光蘑菇，整个场景被梦幻般的光晕笼罩。画面采用电影级光影效果，超精细奇幻艺术风格，8K分辨率，使用虚幻引擎渲染。");
        URI url=response.content().url();
        System.out.println(url);
    }
}
