package checker.VisitorStuff;

import checker.Clase.Action;
import checker.Clase.Error;
import com.fasterxml.jackson.databind.node.ArrayNode;

public interface Visitor {
    void visit(Page page, DataBase database, Action action, ArrayNode output, Error error);

}
