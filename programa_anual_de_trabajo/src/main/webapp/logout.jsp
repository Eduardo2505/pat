<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head>
    <meta charset="utf-8">    
    <meta http-equiv="refresh" content="2;URL=<%= request.getContextPath() %>">
    <title>Sistema Integral de Fiscalización</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- CSS -->
    <link rel="stylesheet" href="app/javax.faces.resource/bootstrap/css/bootstrap.min.css?ln=assets">
    <link rel="stylesheet" href="app/javax.faces.resource/bootstrap/js/bootstrap.min.js?ln=assets">
    <link rel="stylesheet" href="app/javax.faces.resource/js/jquery-1.8.2.min.js?ln=assets">
    <link rel="stylesheet" href="app/javax.faces.resource/css/style.css?ln=assets">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
  </head><body>
    <header class="encabezado">
      <div class="fondoEncabezado navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
          <div class="navbar-header hidden-xs hidden-sm">
            <img class="img-responsive" src="">
            
            <!--  src="assets\img\pendon.png">-->
            <img class="centerImg" src="app\javax.faces.resource\img\id_SIF.png?ln=assets">
          </div>
        </div>
      </div>
      <div class="container">
        <div class="row">
          <div class="col-md-6 col-xs-12 col-md-offset-3 col-sm-12 text-center">
            <h3>Inicio &uacute;nico - Sistema Integral de Fiscalizaci&oacute;n</h3>
          </div>
          <div class="col-md-3 col-xs-12 text-center">
            <img src="app\javax.faces.resource\img\logoINE_blanco.png?ln=assets">
          </div>
        </div>
      </div>
    </header>
    <div class="register-container container">
      <div class="row">
        <br>
      </div>
      <br>
      <div class="row">
        <div class="span3"></div>
        <div class="messageF span6">
          <h2>Cerrando sesi&oacute;n</h2>
          <p>Espere, por favor...</p>
          <div class="pre-loader"></div>
        </div>
        <div class="span3"></div>
      </div>
      <!-- Javascript -->
      <script src="app/javax.faces.resource/js/jquery-1.8.2.min.js?ln=assets "></script>
      <script src="app/javax.faces.resource/bootstrap/js/bootstrap.min.js?ln=assets"></script>
      <script src="app/javax.faces.resource/js/jquery.backstretch.min.js?ln=assets"></script>
      <script src="app/javax.faces.resource/js/scripts.js?ln=assets "></script>
      <!-- Javascript -->
      <script src="app/javax.faces.resource/js/jquery-1.8.2.min.js?ln=assets"></script>
      <script src="app/javax.faces.resource/bootstrap/js/bootstrap.min.js?ln=assets"></script>
      <script src="app/javax.faces.resource/js/jquery.backstretch.min.js?ln=assets"></script>
      <script src="app/javax.faces.resource/js/scripts.js?ln=assets"></script>
    </div>
    <footer>
      <div class="container footer">
        <div class="row" align="center">
          <div class="span12" align="center">
            <p>
              <br>
               <strong>Sistema Integral de Fiscalizaci&oacute;n 2.0</strong>
              <br>Compatibilidad &oacute;ptima con Google Chrome.
              <b>&copy;</b> Derechos Reservados.
              <br>Instituto Nacional Electoral 2015. Unidad T&eacute;cnica de Fiscalizaci&oacute;n.</p>
            <br>
            <div class="row">
              <div class="span3"></div>
              <div class="span2">
                <a href="http://ine.mx/archivos2/tutoriales/sistemas/ApoyoInstitucional/SIF/" target="_blank "><b>Centro de ayuda </b></a>
              </div>
              <div class="span2">
                <a href="http://cau.ife.org.mx" target="_blank">CAU</a>
              </div>
              <div class="span3">
                <a href="http://www.ine.mx/archivos2/portal/avisoDatosPersonales.html" target="_blank "> Aviso de protecci&oacute;n de datos </a>                
              </div>
              <div class="span2"></div>
            </div>
            <br>
          </div>
        </div>
      </div>
    </footer>
  

</body></html>