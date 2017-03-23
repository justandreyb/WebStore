<%@include file="/WEB-INF/jsp/defines/NavbarDefines.jsp"%>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/welcome">${webStoreImg}</a>
        </div>

        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
            <li><a href="/welcome">${home}</a></li>
                <li>
                    <form class="navbar-form navbar-left">
                        <div class="input-group">
                            <input type="text" class="form-control" name="search" placeholder="${search}">
                            <div class="input-group-btn">
                                <button class="btn btn-default" type="submit" name="command" value="search">
                                    <i class="glyphicon glyphicon-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </li>
                <li><a href="/support">${support}</a></li>
                <li><a href="/contacts">${contacts}</a></li>
            </ul>