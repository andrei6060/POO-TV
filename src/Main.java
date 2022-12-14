import checker.Clase.*;
import checker.Clase.Error;
import checker.VisitorStuff.DataBase;
import checker.VisitorStuff.HomePageAuVisitor;
import checker.VisitorStuff.HomePageNAuVisitor;
import checker.VisitorStuff.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(final String[] args) throws IOException {
        String inPath = args[0];
        ObjectMapper objectmapper = new ObjectMapper();
        Input inputData = objectmapper.readValue(new File(inPath), Input.class);
        ArrayNode output = objectmapper.createArrayNode();
        Page page = new Page();
        HomePageAuVisitor pageAu = new HomePageAuVisitor();
        HomePageNAuVisitor pageNAu = new HomePageNAuVisitor();
        DataBase dataBase = new DataBase(inputData);
        Error error = Error.getInstance();
        for (Action action : inputData.getActions()) {
           if (dataBase.getCurrentUser() != null) {
                page.accept(pageAu, dataBase, action, output, error);
            } else {
                page.accept(pageNAu, dataBase, action, output, error);
            }

        }

        ObjectWriter objectWriter = objectmapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
    }
}
