<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="webjars/jquery/2.1.0/jquery.js"></script>
    <script language="javascript">

        $(document).ready($(function () {
            $("#sub").click(function () {
                $.ajax({
                    url: "/calculator/cal2.action",
                    data: $("#calculator").serialize(),
                    type: "post",
                    success: function (data) {
                        document.getElementById("expression").value = data;
                    }
                });
            })
        }));

        function input(str) {
            document.getElementById("expression").value += str;
        }

        /*function Calculator() {
         var str = document.getElementById("expression").value;
         var result = eval('(' + str + ')');
         document.getElementById("expression").value = result;
         }*/

        function clean() {
            document.getElementById("expression").value = null;
        }

        function back() {
            var expression = document.getElementById("expression").value;
            expression = expression.substring(0, expression.length - 1);
            document.getElementById("expression").value = expression;
        }

    </script>
</head>
<body>
<form id="calculator" method="post">
    <table align="center" border="1" cellspacing="0" width="150px">
        <tr>
            <td colspan="4"><textarea name="expression" id="expression" rows="2" style="width: 95%" readonly="readonly"></textarea>
            </td>
        </tr>
        <tr>
            <td width="25%"><input type="button" value="C" onclick="clean()" style="width: 100%"></td>
            <td colspan="2" width="50%"><input type="button" value="Back" onclick="back()" style="width: 100%"></td>
            <td width="25%"><input type="button" value="+" onclick="input('+')" style="width: 100%"></td>
        </tr>
        <tr>
            <td><input type="button" value="7" onclick="input('7')" style="width: 100%"></td>
            <td><input type="button" value="8" onclick="input('8')" style="width: 100%"></td>
            <td><input type="button" value="9" onclick="input('9')" style="width: 100%"></td>
            <td><input type="button" value="-" onclick="input('-')" style="width: 100%"></td>
        </tr>
        <tr>
            <td><input type="button" value="4" onclick="input('4')" style="width: 100%"></td>
            <td><input type="button" value="5" onclick="input('5')" style="width: 100%"></td>
            <td><input type="button" value="6" onclick="input('6')" style="width: 100%"></td>
            <td><input type="button" value="*" onclick="input('*')" style="width: 100%"></td>
        </tr>
        <tr>
            <td><input type="button" value="1" onclick="input('1')" style="width: 100%"></td>
            <td><input type="button" value="2" onclick="input('2')" style="width: 100%"></td>
            <td><input type="button" value="3" onclick="input('3')" style="width: 100%"></td>
            <td><input type="button" value="/" onclick="input('/')" style="width: 100%"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="button" value="0" onclick="input('0')" style="width: 100%"></td>
            <td><input type="button" value="." onclick="input('.')" style="width: 100%"></td>
            <!--<td><input type="button" value="=" onclick="Calculator()" style="width: 100%"></td>-->
            <td><input type="button" id="sub" value="=" style="width: 100%"></td>
        </tr>
    </table>
</form>
</body>
</html>