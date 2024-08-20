package com.zja.sxau.govenmentadmin.fliter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *@Author: 张建安
 *@CreateTime: 2024-08-20
 */
@Component
public class SensitiveWordFilter {

    private Set<String> sensitiveWords;

    public SensitiveWordFilter() {
        this.sensitiveWords = loadSensitiveWordsFromFile();
    }

    // 从JSON文件中加载敏感词列表
    private Set<String> loadSensitiveWordsFromFile() {
        Set<String> words = new HashSet<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            ClassPathResource resource = new ClassPathResource("./static/SensitiveLexicon.json");
            JsonNode rootNode = mapper.readTree(resource.getFile());
            JsonNode wordsNode = rootNode.get("words");
            Iterator<JsonNode> elements = wordsNode.elements();

            while (elements.hasNext()) {
                words.add(elements.next().asText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    // 脱敏处理方法
    public String sanitizeContent(String content) {
        for (String word : sensitiveWords) {
            content = content.replaceAll(word, "***");
        }
        return content;
    }


}
