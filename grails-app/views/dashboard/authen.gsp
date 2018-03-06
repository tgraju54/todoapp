<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!--add header for dashboard -->
<g:render template="header"/>
<br><BR><BR>
<asset:javascript src="/controllers/authen.js"/>
<body ng-app="todoApp">
<div ng-controller="tsvCtrl">


<center><b><h4>Second Step Verification</h4></b>
<g:img dir="images" file="mfa.png" height="200" width="200" /><br>

<b><font face="arial" >Enter Otp sent to mobile no </b><br>
XXXXXXXX{{dno}}</font>
<br><BR>
<div class="container col-sm-4"></div>

<div class="container col-sm-4">
<form ng-submit="checkcode(selnum)">
  <div class="form-group">

    <input type="number"class="form-control" ng-model="vcode" required><br>
      <input type="submit" class="btn btn-success" >
  </div>
</form>
    <div class="container col-sm-4"></div>

</center>
</div>
</body>