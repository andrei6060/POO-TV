package checker.VisitorStuff;

import checker.Clase.Action;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;

public class Page {

    public String currentPage;

    public Page(){
        this.currentPage = "HomeUnA";
    }
    public void accept(Visitor visitor,DataBase database, Action action, ArrayNode output) {
        visitor.visit(this, database, action, output);
    }


}
