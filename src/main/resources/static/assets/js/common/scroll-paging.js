export function scrollPaging($wrapper, renderingCallback, callbackParam) {

    $wrapper.addEventListener('scroll', e => {

        if ($wrapper.scrollTop + 800 === $wrapper.scrollHeight) {
            renderingCallback(callbackParam);
        }
    });

}