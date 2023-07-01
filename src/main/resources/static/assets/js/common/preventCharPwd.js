function chkCharCode(event) {
    const keyCode = event.keyCode;
    const isValidKey = (
        (keyCode >= 48 && keyCode <= 57) || // Numbers
        (keyCode >= 97 && keyCode <= 122) || // Numbers, Keypad
        (keyCode >= 65 && keyCode <= 90) || // Alphabet
        (keyCode === 32) || // Space
        (keyCode === 8) || // BackSpace
        (keyCode === 189) // Dash
    );
    if (!isValidKey) {
        event.target.value = '';
    }
}

export function preventCharPwd() {
    [...document.querySelectorAll('input[type="password"]')].forEach(
        $p => $p.onkeyup = chkCharCode
    );
}