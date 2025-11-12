<style>
    table.form-table {
    border-collapse: collapse;
}
    table.form-table td {
    padding: 6px 10px;
    vertical-align: middle;
}
    .label-col {
    text-align: right;
    width: 150px;
    font-weight: bold;
}

    /* input theo độ dài thực tế */
    input.name { width: 300px; }      /* 50 ký tự */
    input.email { width: 250px; }     /* 40 ký tự */
    select.sex { width: 80px; }       /* nhỏ */
    input.birthday { width: 120px; }  /* 10 ký tự */
    textarea.address { width: 400px; height: 60px; } /* 256 ký tự, 3 rows */
</style>

<form action="editCustomer" method="post">
    <table class="form-table">
        <tr>
            <td class="label-col">Customer Name:</td>
            <td><input type="text" name="customerName" maxlength="50" class="name" /></td>
        </tr>
        <tr>
            <td class="label-col">Email:</td>
            <td><input type="email" name="email" maxlength="40" class="email" /></td>
        </tr>
        <tr>
            <td class="label-col">Sex:</td>
            <td>
                <select name="sex" class="sex">
                    <option value="M">Male</option>
                    <option value="F">Female</option>
                </select>
            </td>
        </tr>
        <tr>
            <td class="label-col">Birthday:</td>
            <td><input type="date" name="birthday" class="birthday" /></td>
        </tr>
        <tr>
            <td class="label-col">Address:</td>
            <td><textarea name="address" class="address"></textarea></td>
        </tr>
        <tr>
            <td></td>
            <td><button type="submit">Save</button></td>
        </tr>
    </table>
</form>


// hoặc có tể dùng
input.name { width: 50ch; }
input.email { width: 40ch; }
input.birthday { width: 10ch; }
<textarea name="address" cols="40" rows="3"></textarea>

<script>
    function validateForm() {
    const name = document.forms["editForm"]["customerName"].value.trim();
    const email = document.forms["editForm"]["email"].value.trim();
    const address = document.forms["editForm"]["address"].value.trim();

    // 1. Customer Name (bắt buộc)
    const nameRegex = /^[A-Za-zÀ-ỹ\s\-']{2,50}$/;
    if (name === "") {
    alert("Customer Name is required.");
    return false;
} else if (!nameRegex.test(name)) {
    alert("Customer Name is invalid. Only letters, spaces, and hyphens are allowed.");
    return false;
}

    // 2. Email (nếu có)
    if (email !== "") {
    const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
    if (!emailRegex.test(email)) {
    alert("Email format is invalid.");
    return false;
}
}

    // 3. Address (nếu có)
    if (address !== "") {
    const addrRegex = /^[A-Za-z0-9À-ỹ\s,./\\-]{0,256}$/;
    if (!addrRegex.test(address)) {
    alert("Address contains invalid characters.");
    return false;
}
}

    return true;
}
</script>

