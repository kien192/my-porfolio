<c:set var="page" value="${pagination.currentPage}" />
<c:set var="total" value="${pagination.totalPages}" />

<div class="pagination">
    <form method="get" action="customer/search">
        <input type="hidden" name="name" value="${param.name}">
        <input type="hidden" name="sex" value="${param.sex}">
        <input type="hidden" name="from" value="${param.from}">
        <input type="hidden" name="to" value="${param.to}">

        <button type="submit" name="page" value="1" ${pagination.firstPage ? "disabled" : ""}>⏮ First</button>
        <button type="submit" name="page" value="${page - 1}" ${pagination.firstPage ? "disabled" : ""}>◀ Prev</button>
        <span>Page ${page} / ${total}</span>
        <button type="submit" name="page" value="${page + 1}" ${pagination.lastPage ? "disabled" : ""}>Next ▶</button>
        <button type="submit" name="page" value="${total}" ${pagination.lastPage ? "disabled" : ""}>Last ⏭</button>
    </form>
</div>
