<html>
<title>TODO | HOME</title>
<g:render template="header" />
<body>
<!--navbar for todo app -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">TODO</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#fea">Features</a></li>
            </ul>
        <!--check login-->
            <sec:ifNotLoggedIn>
                <ul class="nav navbar-nav navbar-right">
                    <li><%= link(action:'index',controller:'user') { '<span class="glyphicon glyphicon-user"></span> Signup' } %> </li>
                    <li><%= link(action:'loginservice',controller:'dashboard') { '<span class="glyphicon glyphicon-log-in"></span> Login' } %></li>
                </ul>
            </sec:ifNotLoggedIn>
            <sec:ifLoggedIn>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span>&nbsp;<sec:username/>
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><%= link(action:'index',controller:'dashboard') { 'Dashboard' } %></li>
                            <li><%= link(action:'index',controller:'logout') { 'Logout' } %></li>

                        </ul>
                    </li>
                </ul>

            </sec:ifLoggedIn>
        </div>
    </div>
</nav>
<!--Sliding Todo Carousel -->
<div class="cau">
    <div id="todoCarousel" class="carousel slide">
        <ol class="carousel-indicators">
            <li data-target="#todoCarousel" data-slide-to="0" class="" contenteditable="false"></li>
            <li data-target="#todoCarousel" data-slide-to="1" class="active" contenteditable="false"></li>
            <li data-target="#todoCarousel" data-slide-to="2" class="" contenteditable="false"></li>
        </ol>
        <div class="carousel-inner">
            <div class="item" style="">
                <g:img dir="images" file="bd1.jpg" />
                <div class="carousel-caption">
                    <h4 class="">Manage Your Tasks </h4>
                    <p class="">
                        Manage Your Tasks seamlessly
                    </p>
                </div>
            </div>
            <div class="item active">
                <g:img dir="images" file="bd2.jpg" />
                <div class="carousel-caption">
                    <h4 class="">Set Priorities</h4>
                    <p class="">
                        Set Priorities to Your Tasks
                    </p>
                </div>
            </div>
            <div class="item" style="">
                <g:img dir="images" file="bd3.jpg" />
                <div class="carousel-caption">
                    <h4 class="">Be a Step Ahead </h4>
                    <p class="">
                        Be a step ahead of others
                    </p>
                </div>
            </div>
        </div>
        <a class="left carousel-control" href="#todoCarousel" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
        </a>
        <a class="right carousel-control" href="#todoCarousel" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
    </div>
</div>
<bR /><BR />
<!-- Todo Services-->
<div id="fea">
    <section id="services">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">Features</h2>
                    <h3 class="section-subheading text-muted">Whats new with todo</h3>
                </div>
            </div>
            <br /><BR /><BR />
            <div class="row text-center">
                <div class="col-md-4">
                    <span class="fa-stack fa-4x">
                        <g:img dir="images" file="pri.png" width="200" height="200"/>
                    </span>
                    <h4 class="service-heading">Priority </h4>
                    <p class="text-muted">Schedule your Tasks According to your priority and never miss out an important one </p>
                </div>
                <div class="col-md-4">
                    <span class="fa-stack fa-4x">
                        <g:img dir="images" file="pro.png" width="300" height="200"/>
                    </span>
                    <h4 class="service-heading">Productivity Graph</h4>
                    <p class="text-muted">Measure your productivity with the productivity graph </p>
                </div>
                <div class="col-md-4">
                    <span class="fa-stack fa-4x">
                        <g:img dir="images" file="noti.png" width="200" height="200"/>
                    </span>
                    <h4 class="service-heading">Notification</h4>
                    <p class="text-muted">Get Daily Notifications Of the pending Tasks</p>
                </div>
            </div>
        </div>
    </section>
    <br /><BR />
    <!--developer area -->
    <div id="dev" align="center">
        <font  size="14"color="Grey" face="arial">Developer</font></center>
        <hr />

            <g:img dir="images" file="dev.jpg" class="img-circle" width="200" height="200"/>
            <br /><BR />
            <font  size="5"color="Grey" face="arial">Priyanshu Kumar</font>

    </div>
</div>
</body>
</html>