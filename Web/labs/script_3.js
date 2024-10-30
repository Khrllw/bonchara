const email = document.getElementById("mail");
const form = document.getElementById("my_form");
const validRegex = /^[a-zA-Z0-9-.%+-]+@[a-zA-Z0-9-]+(:?\.[a-zA-Z0-9-]+)*$/

document.getElementById("btn").addEventListener("click", function () {
    if (!email.value.match(validRegex)) {
        document.getElementById("error").removeAttribute("hidden");
        document.getElementById("good").hidden = true;
    } else {
        document.getElementById("good").removeAttribute("hidden");
        document.getElementById("error").hidden = true;
    }
});

addEventListener("dblclick", (event) => {
    if (form.style.backgroundColor != "lightgrey"){
        form.style.backgroundColor = "lightgrey";}
    else{
        form.style.backgroundColor = "lightblue";
    }
});