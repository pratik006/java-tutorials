

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<link rel="shortcut icon" type="image/ico" href="resources/images/favicon.ico" />	
		<title>Techno India College of Technology</title>		
		<link href="resources/css/login-styles.css" type="text/css" media="screen" rel="stylesheet" />		<style type="text/css">
		img, div { behavior: url(iepngfix.htc) }
		</style>
	</head>
	<body id="login">
		<div id="wrappertop"></div>
			<div id="wrapper">
					<div id="content">
						<div id="header">
							<h1><a href=""><img src="resources/images/ti.jpg" alt="TICT" style="height: 40px;"></a></h1>
						</div>
						<div id="darkbanner" class="banner320">
							<h2>Login</h2>
						</div>
						<div id="darkbannerwrap">
							<div style="color: red; width: 300px; margin-left: 40px; text-align: center;">${errorMsg}</div>
						</div>
						<form name="form1" method="post" action="./FeedbackServlet">
						
						<fieldset class="form">                        	    
                        	    <input name="action" id="action" type="hidden" value="login" />                                      
                        	                                                 <p>
								<label for="username">Username:</label>
								<input name="username" id="username" type="text" value="" />
							</p>
							<p>
								<label for="password">Password:</label>
								<input name="password" id="password" type="password" />
							</p>
							<button type="submit" class="positive" name="Submit">
								<img src="resources/images/key.png" alt="Announcement"/>Login</button>
								<ul id="forgottenpassword">
								<li class="boldtext">|</li>
								<li><a href="forgot.php">Forgotten it?</a></li>
							</ul>
                            						</fieldset>
						
						</form>
					</div>
				</div>   

<div id="wrapperbottom_branding"><div id="wrapperbottom_branding_text"><a href="#" style='text-decoration:none'>Powered By TICT</a>.</div></div>