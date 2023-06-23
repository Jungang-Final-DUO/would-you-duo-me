export function scrollPaging($wrapper, renderingCallback, callbackParam) {

    $wrapper.addEventListener('scroll', e => {
        if ($wrapper.scrollTop === $wrapper.scrollHeight) {
            renderingCallback(callbackParam);
        }
    });

}