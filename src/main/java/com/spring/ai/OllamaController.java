package com.spring.ai;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class OllamaController {
    private final ChatClient chatClient;

    public OllamaController(OllamaChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }

@PostMapping(value = "/chat/stream", produces = "text/plain")
public Flux<String> streamChat(@RequestBody Map<String, String> request) {
        String userMessage = request.get("message");
        return chatClient.prompt(userMessage)
                .stream()
                .content();
    }
}