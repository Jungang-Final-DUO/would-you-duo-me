export function scrollPaging($wrapper, renderingCallback, callbackParam) {

    $wrapper.addEventListener('scroll', e => {

        console.log('scrollTop:' + $wrapper.scrollTop + ', height:' + $wrapper.offsetHeight + ', scrollHeight:' + $wrapper.scrollHeight + ', clientHeight:');

        if ($wrapper.scrollTop + $wrapper.offsetHeight >= $wrapper.scrollHeight - 1) {
            renderingCallback(callbackParam);
        }
    });

}