
$.extend($.fn.validatebox.defaults.rules, {
    
    CHS: {
        validator: function (value) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '只支持中文'
    },
    
    mobile: {//手机
        validator: function (value) {
            var reg = /^1[3|4|5|8|9]\d{9}$/;
            return reg.test(value);
        },
        message: '手机格式不正确.'
    },
    phone: {//电话
        validator:function(value,param){     
            if (value){ 
                var reg = /(^(\d{3,4}-)?\d{6,8}$)|(^(\d{3,4}-)?\d{6,8}(-\d{1,5})?$)|(\d{11})/;    
                return reg.test(value);     
            } else {     
                return true;     
            }     
        },     
        message:'请输入正确的电话号码'
    },
    date: {
        validator: function (value) {
            var reg = /^\d{4}(\-|\/|\.)\d{1,2}\1\d{1,2}$/;
            return reg.test(value);
        },
        message: '日期格式不正确 .'
    },
    
    num: {
        validator: function (value) {
            var reg = /^\d{1,5}$/;
            return reg.test(value);
        },
        message: '请输入数字.'
    },
    numlength: {
        validator: function (value) {
            var reg = /^\d{1,5}$/;
            return reg.test(value);
        },
        message: '请输入1-3位数字.'
    },
    
    zipcode: {
        validator: function (value) {
            var reg = /^[1-9]\d{5}$/;
            return reg.test(value);
        },
        message: '邮编格式不正确.'
    },
    
    account: {
        validator: function (value, param) {
            if (value.length < param[0] || value.length > param[1]) {
                $.fn.validatebox.defaults.rules.account.message = 'xxxx' + param[0] + 'xxx' + param[1] + 'xxxx';
                return false;
            } else {
                if (!/^[\w]+$/.test(value)) {
                    $.fn.validatebox.defaults.rules.account.message = 'xxxxxx.';
                    return false;
                } else {
                    return true;
                }
            }
        }, message: ''
    }
})