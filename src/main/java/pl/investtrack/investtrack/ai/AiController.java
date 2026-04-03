package pl.investtrack.investtrack.ai;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.investtrack.investtrack.ai.dto.AiRequestDTO;
import pl.investtrack.investtrack.ai.dto.AiResponseDTO;

@Log4j2
@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {
    private final AiService aiService;

    @PostMapping("ask")
    public AiResponseDTO askAi(@RequestBody @Validated AiRequestDTO requestDTO) {
        String response = aiService.askAi(requestDTO.prompt());
        log.info("Response Sended");
        return new AiResponseDTO(response);
    }
}
