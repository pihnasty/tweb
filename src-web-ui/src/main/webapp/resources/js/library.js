function checkValue(form, message) {
    
    var userInput = form[form.id + ":username"];
    
    if (userInput.value ==''){
        alert(message)
        userInput.focus();
        return false;
    } 
    
    return true;
}

function showProgress(data) {  // https://stackoverflow.com/questions/27512073/remove-size-attribute-from-html-input
    var a;
    if (data.status == "begin") {
        a=document.getElementById('loading_wrapper').style.display = "block";
        a.setAttribute('size','auto');

    } else if (data.status == "success") {
        a=document.getElementById('loading_wrapper').style.display = "none";
        a.setAttribute('size','auto');
    }
}

