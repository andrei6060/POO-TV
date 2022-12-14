package checker.VisitorStuff;

import checker.Clase.Action;
import checker.Clase.Error;
import com.fasterxml.jackson.databind.node.ArrayNode;


public final class Page {

    private String currentPage;

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final String currentPage) {
        this.currentPage = currentPage;
    }

    public Page() {
        this.currentPage = "HomeUnA";
    }
    public void accept(final Visitor visitor, final DataBase database,
                       final Action action, final ArrayNode output, final Error error) {
        visitor.visit(this, database, action, output, error);
    }


}
