package pl.investtrack.investtrack.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.investtrack.investtrack.DTO.AiRequestDTO;
import pl.investtrack.investtrack.DTO.AiResponseDTO;
import pl.investtrack.investtrack.Service.AiService;

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
