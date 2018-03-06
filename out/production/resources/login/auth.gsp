<script src="https://use.fontawesome.com/59f07e9971.js"></script>
<g:render template="../header" />

<link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'bootstrap-social.css')}" type="text/css">
<title>Login | Todo</title>
<body bgcolor="#dc143c">

<br><BR><BR><BR><BR><BR><BR>
<div class="container">
    <div class="row vertical-offset-100">

        <div class="col-md-4 col-md-offset-4">
            <g:link action="index" controller="Facebookoauth" >
                <div class="btn btn-block btn-social btn-facebook">
                    <span class="fa fa-facebook"></span> Sign in with Facebook
                </div></g:link><br>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please sign in</h3>

                </div>

                <body bgcolor="#dc143c">
                <div class="panel-body">
                    <form accept-charset="UTF-8" role="form"  action="${postUrl ?: '/login/authenticate'}" method="POST" id="loginForm">
                        <fieldset>
                            <div class="fheader"><g:message code='springSecurity.login.header'/></div>
                            <g:if test='${flash.message}'>
                                <font color="red"><div class="login_message">${flash.message}</div></font>
                            </g:if><br>

                            <form action="${postUrl ?: '/login/authenticate'}" method="POST" id="loginForm" class="cssform" autocomplete="off">
                                <p>
                                    <label for="username"><g:message code='springSecurity.login.username.label'/>:</label>
                                    <input type="text" class="text_" name="${usernameParameter ?: 'username'}" id="username"/>
                                </p>
                                <p>
                                    <label for="password"><g:message code='springSecurity.login.password.label'/>:</label>
                                    <input type="password" class="text_" name="${passwordParameter ?: 'password'}" id="password"/>
                                </p>
                                <p id="remember_me_holder">
                                    <input type="checkbox" class="chk" name="${rememberMeParameter ?: 'remember-me'}" id="remember_me" <g:if test='${hasCookie}'>checked="checked"</g:if>/>
                                    <label for="remember_me"><g:message code='springSecurity.login.remember.me.label'/></label>
                                </p>
                                <p>
                                    <input type="submit"  class="btn btn-danger" id="submit" value="${message(code: 'springSecurity.login.button')}"/>
                                </p>
                            </form>
                </div>
            </div>

        </div>

    </div>
</div>