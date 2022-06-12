<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#mainNav{
		background-color:black;
	}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="home.do"><img
				src="assets/img/navbar-logo.svg" alt="..." /></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars ms-1"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
					<li class="nav-item"><a class="nav-link" href="service.do">공지사항</a></li>
					<li class="nav-item"><a class="nav-link" href="portfolio.do">Portfolio</a></li>
					<li class="nav-item"><a class="nav-link" href="about.do">About</a></li>
					<li class="nav-item"><a class="nav-link" href="team.do">Team</a></li>
					<li class="nav-item"><a class="nav-link" href="contact.do">Contact</a></li>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>