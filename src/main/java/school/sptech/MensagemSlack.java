package school.sptech;

import com.slack.api.Slack;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.Message;

public class MensagemSlack {
    public static void main(String[] args) throws Exception {
        Slack slack = Slack.getInstance();
//        ApiTestResponse response = slack.methods().apiTest(r -> r.foo("bar"));

        String token = System.getenv("TOKEN");

        // Initialize an API Methods client with the given token
//        MethodsClient methods = slack.methods(token);

        // Build a request object
        ChatPostMessageResponse resposta = slack.methods(token).chatPostMessage(req -> req
                .channel("Infinity-Solutions")
                .text("Conexão com o Slack feita com sucesso!"));
        if (!resposta.isOk()) {
            String errorCode = resposta.getError();
            System.out.println("ERRO:" + errorCode);
        }
    }
}
