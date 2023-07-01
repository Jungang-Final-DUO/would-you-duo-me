function chkCharCode(event) {
    const keyCode = event.keyCode;
    const isValidKey = (
        (keyCode >= 48 && keyCode <= 57) || // Numbers
        (keyCode >= 97 && keyCode <= 122) || // Numbers, Keypad
        (keyCode >= 65 && keyCode <= 90) || // Alphabet
        (keyCode === 32) || // Space
        (keyCode === 8) || // BackSpace
        (keyCode === 189) || // Dash
        (keyCode === 161 && keyCode === 64 && keyCode === 163 && keyCode === 164 && keyCode === 165 && keyCode === 166
            && keyCode === 167 && keyCode === 160 && keyCode === 170 && keyCode === 63 && keyCode === 176)
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