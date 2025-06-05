import com.google.genai.Client;
public class VertexClient {

    Client client = Client.builder()
            .project("your-project")
            .location("your-location")
            .vertexAI(true)
            .build();
}
