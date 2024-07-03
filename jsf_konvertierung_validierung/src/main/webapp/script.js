/**
 * 
 */

function validateForm() {
    var username = document.getElementById('form:userNameEmail');
    var usernameError = document.getElementById('form:userNameEmail_error');
    
    if (username.value.length < 4) {
        usernameError.innerHTML = 'Der Benutzername muss mindestens 4 Zeichen lang sein.';
        return false;
    } else {
        usernameError.innerHTML = '';
    }
    
    return true;
}