<g:render template="header" />
<!-- sucess Page -->
<div class="container">
    <div class="row text-center">
        <div class="col-sm-6 col-sm-offset-3">
            <br><br> <h2 style="color:#0fad00">Success</h2>
            <g:img dir="images" file="cor.png" width="120" height="150"/>
            <h3>Dear, User</h3>
            <p style="font-size:20px;color:#5C5C5C;">Thank You Your Account Has Been Created .</p>
            <br><br>
            <%= link(action:'loginservice',controller:'dashboard') { '<button class="btn btn-success">     Log in      </button>' } %>
        </div>
    </div>
</div>