/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications Research Laboratory. All Rights Reserved. This
 * software is the proprietary information of Dialog-University of Moratuwa Mobile Communications Research
 * Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update and/or enhance the software as it sees fit.
 * 
 */

$().ready(function() {


//    var fromDate = $("#fromDate").val();
//    var toDate = $("#toDate").val();

    jQuery.validator.addMethod("greaterThan",
            function(value, element, params) {

                //if (!/Invalid|NaN/.test(new Date(value))) {
                //    return new Date(value) > new Date($(params).val());
                //}

                //return isNaN(value) && isNaN($(params).val())
                //        || (Number(value) > Number($(params).val()));
                var end = new Date( value.replace( /(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3") );
                var start = new Date( ($(params).val()).replace( /(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3") );
                
                return start <= end;
                
            }, 'Must be greater than {0}.');

    $("#reportForm").validate({
        errorLabelContainer: "#reportMsg",
        rules: {
            toDate: "required",
            fromDate: "required",
            toDate: {
                required: true,
                greaterThan: "#fromDate"
            },
            fromDate: {
                required: true
            }
        },
        messages: {
            toDate: {
                required: "Give To Date! ",
                greaterThan:"To Date must be after the From Date"
            },
            fromDate: {
                required: "Give From Date! "
            }
        }
    });
    
    $("#companyCallsForm").validate({
        errorLabelContainer: "#reportMsg",
        rules: {
            toDate: "required",
            fromDate: "required",
            toDate: {
                required: true,
                greaterThan: "#fromDate"
            },
            fromDate: {
                required: true
            }
        },
        messages: {
            toDate: {
                required: "Give To Date! ",
                greaterThan:"To Date must be after the From Date"
            },
            fromDate: {
                required: "Give From Date! "
            }
        }
    });
});