function gotoPage(pageNumber) {
	var pageInput = document.getElementById("targetPage");
	if (pageInput) {
		pageInput.value = pageNumber;
		// Tìm form cha và submit.
		// form[0] là cách truy cập nhanh nếu trang chỉ có 1 form,
		// hoặc bạn có thể dùng document.forms['tên_form'].submit();
		document.forms[0].submit();
	}
}