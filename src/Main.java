import checker.Clase.Input;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String inPath = args[0];
        String outPath = args[1];

        ObjectMapper objectmapper = new ObjectMapper();
        Input inputData = objectmapper.readValue(new File(inPath), Input.class);
        ArrayNode output = objectmapper.createArrayNode();
        ObjectWriter objectWriter = objectmapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(outPath), output);

    }
}
