

<!-- jQuery if needed -->
<script type="text/javascript">
$(document).ready(function(){
	
	$('#loginForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        // Since the Bootstrap Button hides the radio and checkbox
        // We exclude the disabled elements only
        excluded: ':disabled',
        fields: {
        	j_username: {
                validators: {
                    notEmpty: {
                        message:"El nombre de usuario es requerido"
                    }
                }
            },
            j_password: {
                validators: {
                    notEmpty: {
                        message: 'La contraseña es requerida'
                    }
                }
            },
            j_captcha: {
                validators: {
                    notEmpty: {
                        message: 'Por favor introduzca el captcha'
                    }
                }
            }
        }
    });
	
});
</script>
	
		<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
		<c:choose>
			<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message eq 'Bad credentials'}">
		      <div class="row">
					<div class="col-xs-offset-3 col-xs-6">
						<div class="alert alert-danger alert-dismissible" role="alert">
						  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						  <strong>Error!</strong> Credenciales no validas.
						</div>
						
					</div>
				</div>
			</c:when><c:otherwise>
				<div class="row">
					<div class="col-xs-offset-3 col-xs-6">
						<div class="alert alert-danger alert-dismissible" role="alert">
						  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						  <strong>Error!</strong> 
						  <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" escapeXml='false'/>.
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
		</c:if>
	
		<div class="row" >
<!-- 			<div class="col-xs-12 col-sm-12 col-md-6 col-md-offset-3"> -->
			<div class="col-xs-12 col-sm-12 col-md-5 pull-left">
<!-- 				<div class="panel panel-success"> -->
<!-- 				 <div class="panel-heading" style="color:white; background-color:#7d2452"><h3 class="panel-title"><b>Administrador</b></h3></div> -->
<!-- 					<div class="panel-body"> -->
						<form id="loginForm" method="post" action="j_spring_security_check">
						
						  <div class="form-group">
						    <label for="j_username">Usuario</label>
						    <div class="input-group">
							    <div class="input-group-addon"><span class="glyphicon glyphicon-user"/></div>
							    <input required type="text" class="form-control" id="j_username" name="j_username" autocomplete='off' onpaste="return false">
						  	</div>
						  </div>
						  <div class="form-group">
						    <label for="j_password">Contraseña</label>
						    <div class="input-group">
							    <div class="input-group-addon"><span class="glyphicon glyphicon-lock"/></div>
							    <input required type="password" class="form-control" id="j_password" name="j_password" autocomplete='off' onpaste="return false">
						  	</div>
						  </div>
						  
						 
						  <div class="form-group">
						    <label for="j_captcha">Captcha</label>
						    <div class="input-group">
<%-- 							    <div class="input-group-addon"><span class="glyphicon glyphicon-eye-open"/></div> --%>
							    <input required type="text" class="form-control" id="j_captcha" name="j_captcha" autocomplete='off' onpaste="return false">
						  		<div class="input-group-addon"><img name="captcha" src="captcha/captcha.png"/></div>
						  	</div>
						  	
						  </div>					
						 
						  <button type="submit" class="btn btn-primary pull-right btnLogin">Enviar</button>
						</form>
<!-- 					</div> -->
<!-- 				</div> -->
			</div>
		</div>
  		