<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html style="height:100%">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<!-- IEXPLORER -->
		<meta http-equiv="X-UA-Compatible" content="IE=8">
		<title>DSLAM STUDIO</title>
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
		
		<link type="text/css" rel="stylesheet" href="bootstrap-3.1.1-dist/css/bootstrap.min.css">
		<link type="text/css" rel="stylesheet" href="jquery/perfect-scrollbar-0.4.10/perfect-scrollbar.css">
		<link type="text/css" rel="stylesheet" href="css/jquery.datetimepicker.css">
		<link type="text/css" rel="stylesheet" href="dslamBusDesktop/css/SeleneCommonModule.css">
		<link type="text/css" rel="stylesheet" href="dslamBusDesktop/css/SeleneDesktopModule.css">
		<link type="text/css" rel="stylesheet" href="dslamBusDesktop/css/BusinessCommonModule.css">
		<link type="text/css" rel="stylesheet" href="dslamBusDesktop/css/BusinessDesktopModule.css">
		<link type="text/css" rel="stylesheet" href="css/DSLAMCommonModule.css">
		<link type="text/css" rel="stylesheet" href="css/DSLAMDesktopModule.css">
		

		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="gwt:property" content="locale=<%=request.getLocale()%>">
		<!-- <meta name='viewport' id='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'> -->
		
		<!-- THIRD PARTY JS -->
		<!-- <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script> -->
		<!-- <script type="text/javascript" src="js/cordova-2.6.0.js" charset="utf-8"></script> -->
		<!-- <script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script> -->
		
		<!-- ACE Editor -->
		<script src="dslamBusDesktop/ace/ace.js" type="text/javascript" charset="utf-8"></script>
		<script src="dslamBusDesktop/ace/theme-eclipse.js" type="text/javascript" charset="utf-8"></script>
		<script src="dslamBusDesktop/ace/mode-java.js" type="text/javascript" charset="utf-8"></script>
		<script src="dslamBusDesktop/ace/theme-monokai.js" type="text/javascript" charset="utf-8"></script>
		<script src="dslamBusDesktop/ace/mode-dslam.js" type="text/javascript" charset="utf-8"></script>
		<script src="dslamBusDesktop/ace/snippets/dslam.js" type="text/javascript" charset="utf-8"></script>
		<script src="dslamBusDesktop/ace/ext-language_tools.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/atmosphere.js" type="text/javascript" charset="utf-8"></script>
		
		
		<!-- GWT-MODULE -->
		<script type="text/javascript" src="dslamBusDesktop/dslamBusDesktop.nocache.js"></script>
	</head>
	<body>
		<div id="progressIcon">
			<table align="center">
				<tr>
					<td>
						<img height="auto" src="../images/nologo.png" />
					</td>
				</tr>
				<tr>
					<td>
						<img height="auto" src="../images/progressBarLoader.gif" />
					</td>
				</tr>
			</table>
		</div>
		<iframe id="__gwt_historyFrame" style="width:0;height:0;border:0;display:none"></iframe>
		<!--[if IE]>
		<h3>Tu navegador no es compatible con DSLAM Studio, te recomendamos descargar la última versión de Chrome o Firefox:</h3>
		<table width='400px'>
			<tr>
				<td>
					<a href='http://www.mozilla.org/es-ES/firefox/new/'><img height='60px' title='Descargar Firefox' alt='Descargar Firefox' src='http://mozorg.cdn.mozilla.net/media/img/firefox/new/header-firefox.png?2013-06' /></a>
				</td>
				<td>
				</td>
				<td>
					<a href='http://www.google.es/intl/es/chrome/browser/'><img height='60px' title='Descargar Chrome' alt='Descargar Chrome' src='http://www.google.es/intl/es/chrome/assets/common/images/chrome_logo_2x.png' /></a>
				</td>
			</tr>		
		</table>
	<![endif]-->
	</body>
</html>
