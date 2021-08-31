
$(document).ready(function () {
    $("#frmCreateCustomer").validate({
        onkeyup: function(element) { $(element).valid() },
        onclick: false,
        onfocusout: false,
        rules: {
            fullName: {
                required: true,
                minlength: 5,
                maxlength: 50
            },
            email: {
                required: true,
                email: true,
                minlength: 10,
                maxlength: 50
            },
            address: {
                required: false,
                minlength: 5,
                maxlength: 50
            }
        },
        errorLabelContainer: ".alert-danger",
        errorPlacement: function (error, element) {
            error.appendTo(".alert-danger");
        },
        showErrors: function(errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#message-alert .alert-danger").removeClass("hide");
            } else {
                $("#message-alert .alert-danger").addClass("hide");
            }
            this.defaultShowErrors();
        },
        messages: {
            fullName: {
                required: "Vui lòng nhập tên đầy đủ",
                minlength: "Họ tên tối thiểu 5 ký tự",
                maxlength: "Họ tên tối đa 50 ký tự"
            },
            email: {
                required: "Vui lòng nhập email đầy đủ",
                email: "Vui lòng nhập đúng định dạng email",
                minlength: "Email tối thiểu 10 ký tự",
                maxlength: "Email tối đa 50 ký tự"
            },
            address: {
                minlength: "Địa chỉ tối thiểu 5 ký tự",
                maxlength: "Địa chỉ tối đa 50 ký tự"
            }
        }
    });

    $("#frmUpdateCustomer").validate({
        onkeyup: function(element) {$(element).valid()},
        onclick: false,
        rules: {
            upFullName: {
                required: true,
                minlength: 5,
                maxlength: 50
            }
        },
        messages: {
            upFullName: {
                required: "Bắt buộc nhập tên đầy đủ",
                minlength: "Hãy nhập tối thiểu 5 ký tự",
                maxlength: "Hãy nhập tối đa 50 ký tự"
            }
        },
        submitHandler: function() {
            updateCustomer();
        }
    });

    $("#frmWithdraw").validate({
        onkeyup: function(element) {$(element).valid()},
        onclick: false,
        onfocusout: false,
        rules: {
            transactionAmount: {
                required: true,
                maxlength: 7
            }
        },
        errorLabelContainer: ".alert-danger",
        errorPlacement: function (error, element) {
            error.appendTo(".alert-danger");
        },
        showErrors: function(errorMap, errorList) {
            if (this.numberOfInvalids() > 0) {
                $("#message-alert .alert-danger").removeClass("hide");
            } else {
                $("#message-alert .alert-danger").addClass("hide");
            }
            this.defaultShowErrors();
        },
        messages: {
            transactionAmount: {
                required: "Không được để trống trường này",
                maxlength: "Số tiền tối đa là 9.999.999"
            }
        }
    });
});