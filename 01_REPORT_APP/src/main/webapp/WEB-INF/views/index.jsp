<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Registration Form</title>

<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            width: 50%;
            margin: 0 auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0px 0px 10px 0px #ccc;
            border-radius: 10px;
        }
        input, select {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .btn {
            background-color: #28a745;
            color: white;
            padding: 10px;
            border: none;
            cursor: pointer;
            width: 100%;
            border-radius: 5px;
        }
        .btn:hover {
            background-color: #218838;
        }
        .error {
            color: red;
            font-size: 14px;
        }
    </style>
    <script>
        function validateForm() {
            let name = document.forms["customerForm"]["custName"].value;
            let email = document.forms["customerForm"]["custEmail"].value;
            let phone = document.forms["customerForm"]["custMob"].value;
            let gender = document.forms["customerForm"]["gender"].value;
            let startDate = document.forms["customerForm"]["startDate"].value;
            let endDate = document.forms["customerForm"]["endDate"].value;
            let errorMsg = "";

            if (name.length < 2 || name.length > 50) {
                errorMsg += "Name must be between 2 and 50 characters.\n";
            }
            let emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
            if (!emailPattern.test(email)) {
                errorMsg += "Invalid email format.\n";
            }
            if (phone.length !== 10 || isNaN(phone)) {
                errorMsg += "Invalid mobile number (must be 10 digits).\n";
            }
            if (gender === "") {
                errorMsg += "Please select a gender.\n";
            }
            if (new Date(startDate) >= new Date(endDate)) {
                errorMsg += "End date must be after start date.\n";
            }
            if (errorMsg !== "") {
                alert(errorMsg);
                return false;
            }
            return true;
        }
    </script>

</head>
<body>
<div class="container">
        <h2>Customer Registration Form</h2>
        <form name="customerForm" action="index" method="post" onsubmit="return validateForm();">
            <label for="custName">Name:</label>
            <input type="text" id="custName" name="custName" required>
            
            <label for="custEmail">Email:</label>
            <input type="email" id="custEmail" name="custEmail" required>

            <label for="custMob">Mobile:</label>
            <input type="text" id="custMob" name="custMob" required>

            <label for="planName">Plan Name:</label>
            <select id="planName" name="planName" required>
                <option value="">Select Plan</option>
                <option value="CASH">CASH</option>
                <option value="FOOD">FOOD</option>
                <option value="MEDICAL">MEDICAL</option>
                <option value="EMPLOYEMENT">EMPLOYEMENT</option>
            </select>

            <label for="planStatus">Plan Status:</label>
            <select id="planStatus" name="planStatus" required>
                <option value="">Select Status</option>
                <option value="APPROVED">APPROVED</option>
                <option value="DENIED">DENIED</option>
                <option value="TERMINATED">TERMINATED</option>
            </select>

            <label for="gender">Gender:</label>
            <select id="gender" name="gender" required>
                <option value="">Select Gender</option>
                <option value="MALE">Male</option>
                <option value="FEMALE">Female</option>
                <option value="OTHER">Other</option>
            </select>

            <label for="startDate">Start Date:</label>
            <input type="date" id="startDate" name="startDate" required>

            <label for="endDate">End Date:</label>
            <input type="date" id="endDate" name="endDate" required>

            <button type="submit" class="btn">Submit</button>
        </form>
    </div>
    </body>
</html>