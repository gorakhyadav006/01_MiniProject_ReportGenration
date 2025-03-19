$(document).ready(function () {
    $(".card").on("click", function () {
        alert("You clicked on a blog post!");
    });
});

// Login form validation
function validateLoginForm() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    if (username.trim() === "" || password.trim() === "") {
        alert("All fields are required!");
        return false;
    }
    return true;
}

// Signup form validation
function validateSignupForm() {
    let username = document.getElementById("username").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirmPassword").value;

    if (username.trim() === "" || email.trim() === "" || password.trim() === "" || confirmPassword.trim() === "") {
        alert("All fields are required!");
        return false;
    }
    if (password !== confirmPassword) {
        alert("Passwords do not match!");
        return false;
    }
    return true;
}
