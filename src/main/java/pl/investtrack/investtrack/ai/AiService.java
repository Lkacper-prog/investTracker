package pl.investtrack.investtrack.ai;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GoogleSearch;
import com.google.genai.types.Tool;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class AiService {
    private final Client geminiClient;
    @Value("${gemini.model}")
    private String modelName;

    public String askAi(String prompt) {
        try {


            Tool googleSearchTool = Tool.builder().googleSearch(GoogleSearch.builder().build())
                    .build();
            GenerateContentConfig config = GenerateContentConfig.builder()
                    .tools(List.of(googleSearchTool))
                    .build();

            GenerateContentResponse response = geminiClient.models.generateContent(modelName, prompt, config);
            return response.text();
        } catch (Exception e) {
            log.error("Błąd komunikacji z gemini : {}", e.getMessage());
            throw  new RuntimeException("Nie udało się uzyskać odpowiedzi!");
        }
    }
}
