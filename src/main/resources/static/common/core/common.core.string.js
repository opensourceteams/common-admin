
/**
 * 判断字符串以什么结尾
 * @param endStr
 * @returns {boolean}
 */
String.prototype.endWith=function(endStr){
    var d=this.length-endStr.length;
    return (d>=0&&this.lastIndexOf(endStr)==d);
}

/**
 * 去掉字符串最后字符
 * @param splitStr
 * @returns {*}
 */
String.prototype.removeEndWith=function(splitStr){
    if(this.endWith(splitStr)){
        return this.substring(0,this.length-1);
    }
    return this;
}
