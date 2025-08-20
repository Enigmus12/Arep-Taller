package Escuelaing.edu.co;

import org.junit.jupiter.api.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class AppServerTest {

    private static Thread serverThread;

    @BeforeAll
    static void startServer() {
        serverThread = new Thread(() -> {
            try {
                AppServer.main(null); // arranca el servidor en otro hilo
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.setDaemon(true); // el test no se bloquea
        serverThread.start();

        // esperar un poquito a que arranque
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }

    private String makeRequest(String path) throws IOException {
        URL url = new URL("http://localhost:35000" + path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();
        assertEquals(200, status);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }

    @Test
    void testHelloDefault() throws IOException {
        String response = makeRequest("/hello");
        assertTrue(response.contains("Hello world!"));
    }

    @Test
    void testHelloWithName() throws IOException {
        String response = makeRequest("/hello?name=Juan");
        assertTrue(response.contains("Hello Juan!"));
    }

    @Test
    void testIndexPage() throws IOException {
        String response = makeRequest("/");
        assertTrue(response.length() > 0); // index.html no vacío
    }

    @Test
    void testNotFoundPage() throws IOException {
        String response = makeRequest("/noexiste.html");
        assertTrue(response.contains("404")); // depende de tu www/404.html
    }
}
