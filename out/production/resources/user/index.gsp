<html>
<title>Todo||Signup</title>
<g:render template="header" />
<br><BR><BR><BR>
<body ng-app="todoApp">
<div ng-controller="signupCtrl">
    <div>
    <div class="container">
        <div class="row vertical-offset-100">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-default">
                    <div class="panel-heading">

                        <h3 class="panel-title"><span class="glyphicon glyphicon-edit"></span> &nbsp;Create an Account</h3>
                    </div>

                    <div class="panel-body"></center>
                        <font color="red"> <div ng-bind="nuser.errmsg"></div></font>
                         <br>
                        <form ng-submit="signup()" >
                            <b>Username:<font color="red">*</font><br><b>
                                <input type="text" class="form-control"   ng-model="nuser.username" required minlength="3">
                                <br>
                                <b>Full Name:<font color="red">*</font><br><b>
                                    <input type="text" class="form-control"  ng-model="nuser.uname" required>
                                    <br>
                                    <b>Email:<font color="red">*</font><br><b>
                                        <input type="email" name="email" class="form-control"   ng-model="nuser.email" required>
                                        <span ng-show="form.email.$dirty && form.email.$error.required">Email is required</span>
                                        <br>
                                        <b>Phone:<font color="red">*</font><br><b>
                                            <input type="phone" class="form-control"  ng-model="nuser.uphone" required minlength="12">
                                            <br>
                                            <b>Password:<font color="red">*</font><br><b>
                                                <input type="password" class="form-control"   ng-model="nuser.password" required minlength="8">
                                                <br>

                                                <center><input type="submit" class="btn btn-success btn-lg" value="Create Account" ></center>
                        </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>