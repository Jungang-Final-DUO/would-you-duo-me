export function scrollPaging($wrapper, renderingCallback, callbackParam) {

    $wrapper.addEventListener('scroll', e => {

        if ($wrapper.scrollTop + $wrapper.offsetHeight >= $wrapper.scrollHeight - 1) {
            renderingCallback(callbackParam);
        }
    });

}