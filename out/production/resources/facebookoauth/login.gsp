<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <g:render template="../header" />
</head>
<body>
<font color="grey" face="arail">
    <center>
        <div id="welmsg">

            <div class="row">


                <br><BR>
                <h2>Hello ${name}</h2>
    choose a password for your  todo account<BR><br><BR><br></center>
    <div class="container">
        <div class="row">

            <div class="col-md-6 col-md-offset-3">

                <div style="padding: 20px;" id="form-olvidado">
                    <g:form  method="post" url="[action:'setup',controller:'facebookoauth']">
                        <fieldset>
                            <div class="form-group input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-lock">
                                    </i>
                                </span>
                                <input class="form-control" placeholder="Password" name="password" type="password"  required>
                                <input type="hidden" value="${id}" name="username">
                                <input type="hidden" value="${email}" name="email">
                                <input type="hidden" value="${name}" name="uname">

                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-success btn-block">
                                    Continue to dashboard
                                </button>

                            </div>
                        </fieldset>
                    </g:form>
                </div>

            </div>
        </div>
    </div>

</fieldset>
</form>
</div>
</div>
</div>

</font>



</body>
</html>