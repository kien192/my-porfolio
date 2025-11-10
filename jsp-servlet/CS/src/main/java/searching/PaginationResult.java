public class PaginationResult<T> {
    private List<T> data;
    private int currentPage;
    private int totalPages;
    private int totalRecords;

    public PaginationResult(List<T> data, int currentPage, int totalPages, int totalRecords) {
        this.data = data;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalRecords = totalRecords;
    }

    public List<T> getData() { return data; }
    public int getCurrentPage() { return currentPage; }
    public int getTotalPages() { return totalPages; }
    public int getTotalRecords() { return totalRecords; }

    public boolean isFirstPage() { return currentPage <= 1; }
    public boolean isLastPage() { return currentPage >= totalPages; }
}
