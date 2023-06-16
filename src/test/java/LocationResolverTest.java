import org.example.LocationResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LocationResolverTest {

    @Test
    public void testDisplayLocalClient() {
        LocationResolver locationResolver = new LocationResolver();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        locationResolver.displayLocalClient();
        String output = outputStream.toString().trim();

        Assertions.assertTrue(output.contains("IP Address: "));
        Assertions.assertTrue(output.contains("Host Name: "));

        System.setOut(System.out);
    }
}