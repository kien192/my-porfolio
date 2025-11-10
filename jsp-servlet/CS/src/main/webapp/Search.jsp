<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Search</title>

<script type="text/javascript">
// Regex kiểm tra định dạng ngày
const datePattern = /^\d{4}\/\d{2}\/\d{2}$/;

// Regex cho phép ký tự chữ, số, khoảng trắng
const namePattern = /^[a-zA-Z0-9\s\u00C0-\u1EF9]+$/; // có hỗ trợ tiếng Việt

function validateSearchForm() {
    const name = document.getElementById("customerName").value.trim();
    const sex = document.getElementById("sex").value.trim();
    const fromDate = document.getElementById("birthdayFrom").value.trim();
    const toDate = document.getElementById("birthdayTo").value.trim();

    // Check ký tự đặc biệt
    if (name && !namePattern.test(name)) {
        alert("Customer Name không được chứa ký tự đặc biệt!");
        return false;
    }

    // Check định dạng ngày
    if (fromDate && !datePattern.test(fromDate)) {
        alert("Ngày bắt đầu không đúng định dạng (YYYY/MM/DD)");
        return false;
    }
    if (toDate && !datePattern.test(toDate)) {
        alert("Ngày kết thúc không đúng định dạng (YYYY/MM/DD)");
        return false;
    }

    // Check logic ngày
    if (fromDate && toDate) {
        const from = new Date(fromDate.replaceAll('/', '-'));
        const to = new Date(toDate.replaceAll('/', '-'));
        if (to < from) {
            alert("Ngày kết thúc không được nhỏ hơn ngày bắt đầu!");
            return false;
        }
    }

    return true;
}
</script>
</head>

<body>
<h2>Customer Search</h2>

<form action="SearchServlet" method="get" onsubmit="return validateSearchForm();">
    <label>Customer Name:</label>
    <input type="text" name="customerName" id="customerName" maxlength="50"><br><br>

    <label>Sex:</label>
    <select name="sex" id="sex">
        <option value="">--All--</option>
        <option value="0">Male</option>
        <option value="1">Female</option>
    </select><br><br>

    <label>Birthday:</label>
    <input type="text" name="birthdayFrom" id="birthdayFrom" placeholder="YYYY/MM/DD">
    ~
    <input type="text" name="birthdayTo" id="birthdayTo" placeholder="YYYY/MM/DD">
    <br><br>

    <input type="submit" value="Search">
</form>

</body>
</html>
