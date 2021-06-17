// 合成版
/**
   * @desc 函数防抖
   * @param func 目标函数
   * @param wait 延迟执行毫秒数
   * @param immediate true - 立即执行， false - 延迟执行
   */
 function debounce(func, wait, immediate) {
  let timer;
  return function() {
    let context = this,
        args = arguments;
         
    if (timer) clearTimeout(timer);
    if (immediate) {
      let callNow = !timer;
      timer = setTimeout(() => {
        timer = null;
      }, wait);
      if (callNow) func.apply(context, args);
    } else {
      timer  = setTimeout(() => {
        func.apply
      }, wait)
    }
  }
}