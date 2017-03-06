<%@include file="template/Settings.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Web store">
    <meta name="author" content="justandreyb@gmail.com">

    <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico">

    <title>Cover Template for Bootstrap</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <style>
        <%--<%@include file="../../resources/css/cover.css" %>--%>
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">WebSiteName</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#">Page 1</a></li>
            <li><a href="#">Page 2</a></li>
            <li><button class="btn btn-danger navbar-btn">Button</button></li>
            <li>
                <form class="navbar-form navbar-left">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search">
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="submit">
                                <i class="glyphicon glyphicon-search"></i>
                            </button>
                        </div>
                    </div>
                </form>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </ul>
    </div>
</nav>

<div class="container-fluid">

    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
            <li data-target="#myCarousel" data-slide-to="3"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="img_chania.jpg" alt="Chania">
            </div>

            <div class="item">
                <img src="img_chania2.jpg" alt="Chania">
            </div>

            <div class="item">
                <img src="img_flower.jpg" alt="Flower">
            </div>

            <div class="item">
                <img src="img_flower2.jpg" alt="Flower">
            </div>
        </div>

        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>

    <div class="container-fluid">
        <h1>Hello World!</h1>
        <p>Resize the browser window to see the effect.</p>
        <div class="row">
            <div class="col-sm-4" style="background-color:lavender;">.col-sm-4</div>
            <div class="col-sm-4" style="background-color:lavenderblush;">.col-sm-4</div>
            <div class="col-sm-4" style="background-color:lavender;">.col-sm-4</div>
        </div>
    </div>

    <br>

    <div class="container-fluid">
        <div class="btn-group">
            <button type="button" class="btn btn-primary">
                <span class="glyphicon glyphicon-search"></span>  Sony
            </button>
            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu">
                <li><a href="#">Tablet</a></li>
                <li><a href="#">Smartphone</a></li>
            </ul>
        </div>
    </div>

    <br>

    <div class="container-fluid">
        <ul class="pagination">
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
        </ul>
    </div>

    <br>

    <div class="container-fluid">
        <ul class="breadcrumb">
            <li><a href="#">Home</a></li>
            <li><a href="#">Private</a></li>
            <li><a href="#">Pictures</a></li>
            <li class="active">Vacation</li>
        </ul>
    </div>

    <br>

    <div class="container-fluid">
        <div class="panel panel-default">
            <div class="panel-body">A Basic Panel</div>
        </div>
    </div>

    <br>

    <div class="container-fluid">
        <div class="panel-group">
            <div class="panel panel-default">
                <div class="panel-heading">Panel Header</div>
                <div class="panel-body">Panel Content</div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">Panel Header</div>
                <div class="panel-body">Panel Content</div>
            </div>
        </div>
    </div>

    <br>

    <div class="container-fluid">
        <div class="panel-group">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" href="#collapse1">Collapsible panel</a>
                    </h4>
                </div>
                <div id="collapse1" class="panel-collapse collapse">
                    <div class="panel-body">Panel Body</div>
                    <div class="panel-footer">Panel Footer</div>
                </div>
            </div>
        </div>
    </div>

    <br>

    <div class="container">
        <h2>Dropdowns</h2>
        <p>The .dropdown-header class is used to add headers inside the dropdown menu:</p>
        <div class="dropdown">
            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Tutorials
                <span class="caret"></span></button>
            <ul class="dropdown-menu">
                <li class="dropdown-header">Dropdown header 1</li>
                <li><a href="#">HTML</a></li>
                <li><a href="#">CSS</a></li>
                <li><a href="#">JavaScript</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Dropdown header 2</li>
                <li><a href="#">About Us</a></li>
            </ul>
        </div>
    </div>

    <br>

    <div class="container-fluid">
        <form>
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <input id="email" type="text" class="form-control" name="email" placeholder="Email">
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                <input id="password" type="password" class="form-control" name="password" placeholder="Password">
            </div>
            <div class="input-group">
                <span class="input-group-addon">Text</span>
                <input id="msg" type="text" class="form-control" name="msg" placeholder="Additional Info">
            </div>
        </form>
    </div>

    <br>

    <div class="container-fluid">
        <div class="col-xs-2">
            <label for="ex1">col-xs-2</label>
            <input class="form-control" id="ex1" type="text">
        </div>
        <div class="col-xs-3">
            <label for="ex2">col-xs-3</label>
            <input class="form-control" id="ex2" type="text">
        </div>
        <div class="col-xs-4">
            <label for="ex3">col-xs-4</label>
            <input class="form-control" id="ex3" type="text">
        </div>
    </div>

    <br>

    <div class="container-fluid">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-sm-2" for="email">Email:</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="email" placeholder="Enter email">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="pwd">Password:</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="pwd" placeholder="Enter password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label><input type="checkbox"> Remember me</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Submit</button>
                </div>
            </div>
            <div class="form-group">
                <label for="sel1">Select list:</label>
                <select class="form-control" id="sel1">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                </select>
            </div>
        </form>
    </div>

    <br>

    <div class="container-fluid">
        <!-- Trigger the modal with a button -->
        <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>

        <!-- Modal -->
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Modal Header</h4>
                    </div>
                    <div class="modal-body">
                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#sign-up">Sing up</a></li>
                            <li><a data-toggle="tab" href="#sign-in">Sign in</a></li>
                        </ul>

                        <div class="tab-content">
                            <div id="sign-up" class="tab-pane fade in active">
                                <h3>Sign up</h3>
                                <div class="container-fluid">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" for="email">Email:</label>
                                            <div class="col-sm-10">
                                                <input type="email" class="form-control" id="email" placeholder="Enter email">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" for="pwd">Password:</label>
                                            <div class="col-sm-10">
                                                <input type="password" class="form-control" id="pwd" placeholder="Enter password">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-2 col-sm-10">
                                                <div class="checkbox">
                                                    <label><input type="checkbox"> Remember me</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-2 col-sm-10">
                                                <button type="submit" class="btn btn-default">Submit</button>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sel1">Select list:</label>
                                            <select class="form-control" id="sel1">
                                                <option>1</option>
                                                <option>2</option>
                                                <option>3</option>
                                                <option>4</option>
                                            </select>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div id="sign-in" class="tab-pane fade">
                                <h3>Sign in</h3>
                                <div class="container-fluid">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" for="email">Email:</label>
                                            <div class="col-sm-10">
                                                <input type="email" class="form-control" id="email" placeholder="Enter email">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" for="pwd">Password:</label>
                                            <div class="col-sm-10">
                                                <input type="password" class="form-control" id="pwd" placeholder="Enter password">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-2 col-sm-10">
                                                <div class="checkbox">
                                                    <label><input type="checkbox"> Remember me</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-2 col-sm-10">
                                                <button type="submit" class="btn btn-default">Submit</button>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sel1">Select list:</label>
                                            <select class="form-control" id="sel1">
                                                <option>1</option>
                                                <option>2</option>
                                                <option>3</option>
                                                <option>4</option>
                                            </select>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <br>

    <div class="container-fluid">

    </div>

    <footer class="container-fluid bg-4 text-center">
        <p>Bootstrap Theme Made By <a href="https://www.w3schools.com">www.w3schools.com</a></p>
    </footer>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>