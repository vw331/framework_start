$(function(){

    $.fn.extend({
        serializeObject: function(){

            var o={};
            $.each( $(this).serializeArray(),function(index){
                if(o[this['name']]){
                    o[this['name']] = o[this['name']]+","+this['value'];
                }else{
                    if(this['value']){
                        o[this['name']] = this['value'];
                    }
                }
            });
            return o;

        }
    })

    $.ajaxSetup({
        cache:false,
        dataType:"JSON",
        error:function(jqXHR,textStatus,errorThrown){
            switch(jqXHR.status){
                case(500):
                    alert('服务器系统内部错误');
                    break;
                case(401):
                    alert('未登录');
                    break;
                case(403):
                    alert("无权限执行此操作");
                    break;
                case(408):
                    alert("请求超时");
                    break;
                default:
                    alert("未知错误");
            }
        }
    })


    if (typeof(BootstrapDialog) !== 'undefined'){
        BootstrapDialog.defaultOptions = {
            type: BootstrapDialog.TYPE_DEFAULT,
            size: BootstrapDialog.SIZE_NORMAL,
            cssClass: '',
            title: null,
            message: null,
            nl2br: true,
            closable: true,
            closeByBackdrop: true,
            closeByKeyboard: true,
            closeIcon: '&#215;',
            spinicon: BootstrapDialog.ICON_SPINNER,
            autodestroy: true,
            draggable: false,
            animate: true,
            description: '',
            tabindex: -1,
            btnsOrder: BootstrapDialog.BUTTONS_ORDER_CANCEL_OK
        };
    }

    if (typeof($.fn.dataTable) !== 'undefined') {

        //配置DataTables默认参数
        $.extend(true, $.fn.dataTable.defaults, {
            lengthChange: false,
            searching: false,
            autoWidth: false,
            ordering: false,
            processing: false,
            serverSide: true,
            pageLength: 20,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            }
        });
    }

    if (typeof(BootstrapDialog) !== 'undefined') {

        /*bootstrap-dialog 全局配置*/
        BootstrapDialog.DEFAULT_TEXTS.CANCEL = '取消';
        BootstrapDialog.DEFAULT_TEXTS.OK = '确定'
        BootstrapDialog.DEFAULT_TEXTS.CONFIRM = '询问'
        BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_DEFAULT] = '信息';
        BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_INFO] = '提示';
        BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_PRIMARY] = '信息';
        BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_SUCCESS] = '提示';
        BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_WARNING] = '提示';
        BootstrapDialog.DEFAULT_TEXTS[BootstrapDialog.TYPE_DANGER] = '提示';

        BootstrapDialog.configDefaultOptions({
            cssClass: 'inmodal',
            type: BootstrapDialog.TYPE_DEFAULT
        })

    }

    if (typeof(toastr) !== 'undefined') {

        toastr.options = {
            "closeButton": true,
            "debug": false,
            "progressBar": true,
            "preventDuplicates": true,
            "positionClass": "toast-top-right",
            "showDuration": "400",
            "hideDuration": "1000",
            "timeOut": "7000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        }

    }

    if (typeof($.fn.daterangepicker) !== 'undefined') {

        $.fn.daterangepicker.DEFAULT_OPTIONS = {
            singleDatePicker: false, //true（单个的datepicker） false（有区间的datepicker）
            showDropdowns: true, //允许年份和月份通过下拉框的形式选择
            autoApply: false, //不用点击Apply或者应用按钮就可以直接取得选中的日期
            timePicker24Hour: false,
            timePicker: false, //可选中时分
            locale: {
                format: 'YYYY-MM-DD HH:MM:SS',
                separator: " ~ ",//
                applyLabel: "确定",
                cancelLabel: "取消",
                fromLabel: "起始时间",
                toLabel: "结束时间'",
                customRangeLabel: "自定义",
                weekLabel: "W",
                daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
                monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
                firstDay: 1
            }
        }

    }

    if (typeof( $.fn.slimScroll) !== 'undefined') {

        $('[data-slim-scroll]').slimScroll({
            width: 'auto',
            height: 'auto',
            height: '100%',
            railOpacity: 0.4,
            wheelStep: 10

        })

    }

    if (typeof( $.validator) !== 'undefined') {

        $.extend($.validator.messages, {
            required: "这是必填字段",
            remote: "请修正此字段",
            email: "请输入有效的电子邮件地址",
            url: "请输入有效的网址",
            date: "请输入有效的日期",
            dateISO: "请输入有效的日期 (YYYY-MM-DD)",
            number: "请输入有效的数字",
            digits: "只能输入数字",
            creditcard: "请输入有效的信用卡号码",
            equalTo: "你的输入不相同",
            extension: "请输入有效的后缀",
            maxlength: $.validator.format("最多可以输入 {0} 个字符"),
            minlength: $.validator.format("最少要输入 {0} 个字符"),
            rangelength: $.validator.format("请输入长度在 {0} 到 {1} 之间的字符串"),
            range: $.validator.format("请输入范围在 {0} 到 {1} 之间的数值"),
            max: $.validator.format("请输入不大于 {0} 的数值"),
            min: $.validator.format("请输入不小于 {0} 的数值")
        });
        // 手机号码验证
        jQuery.validator.addMethod("isMobile", function(value, element) {
            var length = value.length;
            return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));
        }, "请正确填写您的手机号码。");
        //必须由数字与字母组合,至少8-12位的字符串
        jQuery.validator.addMethod("isPwd", function(value, element) {
            var v=$.trim(value);
            if(v.length<8||v.length>17){
                return false;
            }
            //全数字
            if(/^\d+$/.test(v))
            {
                return false;
            }

            //不包含数字
            if(!(/^(?=.*\d.*\b)/.test(v)))
            {
                return false;
            }
            //不包含大写字母
            if(!(/^(?=.*[A-Z].*\b)/.test(v))){
                return false;
            }
            //不包含小写字母
            if(!(/^(?=.*[a-z].*\b)/.test(v))){
                return false;
            }
            //全字母
            if(/^[a-zA-Z]+$/i.test(v))
            {
                return false;
            }
            //只能含有数字有字母
            if(!/^[A-Za-z0-9]+$/.test(v))
            {
                return false;
            }

            return true;

        }, "密码必须包含大小写字母和数字，长度为8至16位");

    }

    $('body').tooltip({
        selector: "[data-toggle=tooltip]",
        container: "body"
    });

    $(document)
        .on('click', '[data-logout]', function(){

            BootstrapDialog.confirm('确定要退出?', function(result){
                if(result) {
                    $.post( ctxPath + '/com/logout')
                        .done(function(){

                            window.location.reload()
                        })
                }
            });
        })


})