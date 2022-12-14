package checker.Clase;


public final class Filter {

    private Sort Sort;
    private Contains Contains;

    public checker.Clase.Sort getSort() {
        return Sort;
    }

    public void setSort(final checker.Clase.Sort sort) {
        this.Sort = sort;
    }

    public checker.Clase.Contains getContains() {
        return Contains;
    }

    public void setContains(final checker.Clase.Contains contains) {
        this.Contains = contains;
    }
}
