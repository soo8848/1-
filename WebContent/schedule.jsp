<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<head>
<title>일정관리 - ProMeet</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!-- 공통 스타일 -->
<link rel="stylesheet" href="common.css" />
</head>
<style>
.pm-schedule .calendar .dot { width:8px; height:8px; border-radius:999px; display:inline-block; }
.pm-schedule .dot { width:10px; height:10px; border-radius:50%; margin-top:4px; flex-shrink:0; }
.pm-schedule .dot.red    { background: #e74c3c; }
.pm-schedule .dot.orange { background:#ff9800; }
.pm-schedule .dot.yellow { background:#ffc107; }
.pm-schedule .dot.green  { background:#25d366; }
.pm-schedule .dot.blue   { background:#2196f3; }
.pm-schedule .dot.navy   { background: #34495e; }
.pm-schedule .dot.purple { background:#8a5be5; }
.pm-schedule .dot.black  { background:#222; }
.pm-schedule .dot.white  { background: #fff; border: 1px solid #ccc; }


</style>
<body class="pm-schedule">
	<div class="container">

		<!-- 사이드바 -->
		<div class="sidebar">
			<div class="logo-wrap">
				<img
					src="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='240' height='60'><rect width='100%' height='100%' rx='12' ry='12' fill='%23b14dd6'/><text x='50%' y='55%' dominant-baseline='middle' text-anchor='middle' font-family='Arial' font-size='28' fill='white'>ProMeet</text></svg>"
					alt="ProMeet 로고" />
			</div>
			<div class="avatar">
				<img
					src="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='120' height='120'><defs><linearGradient id='g' x1='0' x2='1'><stop offset='0' stop-color='%23ffd1dc'/><stop offset='1' stop-color='%23c7b9ff'/></linearGradient></defs><rect width='100%' height='100%' fill='url(%23g)'/><circle cx='60' cy='50' r='22' fill='white'/><rect x='30' y='78' width='60' height='26' rx='13' fill='white'/></svg>"
					alt="프로필 이미지(가상)" />
			</div>
			<div class="username">홍길동 팀장</div>

			<div class="status-board">
				<div class="row">
					<label for="status">상태</label> <select id="status" name="status">
						<option>출근</option>
						<option>자리비움</option>
						<option>외근</option>
						<option>퇴근</option>
					</select>
				</div>
			</div>

			<nav class="nav">
				<a href="#">프로젝트</a> <a href="#">파일함</a> <a class="active" href="#">일정관리</a>
				<a href="#">마이페이지</a>

			</nav>

			<button class="logout">로그아웃</button>
		</div>

		<!-- 메인 -->
		<div class="main">
			<div class="page-header">
				<div class="page-title">일정관리</div>
			</div>

			<!-- 캘린더 + 우측 일정목록 -->
			<div class="sched-layout">
				<!-- 캘린더 -->
				<section class="panel">
					<div class="calendar-header">
						<button id="prevMonth" class="btn-outline s" aria-label="이전달">&lt;</button>
						<div class="year-month">
							<span class="year"></span>
							<h2 class="month"></h2>
						</div>
						<button id="nextMonth" class="btn-outline s" aria-label="다음달">&gt;</button>
					</div>
					<table class="calendar">
						<thead>
							<tr>
								<th>월</th>
								<th>화</th>
								<th>수</th>
								<th>목</th>
								<th>금</th>
								<th>토</th>
								<th>일</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>

				</section>

				<!-- 일정목록 -->rr
				<aside class="panel sched-list">
					<h3 class="panel-title">일정목록</h3>
					<ul class="sched-ul">
						<li><span class="dot purple"></span>
							<div class="sched-line">
								<div class="when">23일 (화) 10:15 ~ 11:30</div>
								<div class="what">B프로젝트 회의</div>
							</div>
							<div class="actions">
								<button class="btn-xs">X</button>
							</div></li>
						<li><span class="dot yellow"></span>
							<div class="sched-line">
								<div class="when">23일 (화) 14:00</div>
								<div class="what">D프로젝트 중간보고</div>
							</div>
							<div class="actions">
								<button class="btn-xs">X</button>
							</div></li>
						<li><span class="dot black"></span>
							<div class="sched-line">
								<div class="when">23일 (화) 16:00</div>
								<div class="what">E프로젝트 마감</div>
							</div>
							<div class="actions">

								<button class="btn-xs">X</button>rr
							</div></li>
					</ul>



					<!-- 일정 추가 폼 -->
					<div class="sched-add-form">
						<input type="text" class="input-s" placeholder="일정명 입력" />

						<!-- dot 선택 -->
						<div class="dot-picker">
							<label><input type="radio" name="color" value="green"><span
								class="dot green"></span></label> <label><input type="radio"
								name="color" value="purple"><span class="dot purple"></span></label>
							<label><input type="radio" name="color" value="yellow"><span
								class="dot yellow"></span></label> <label><input type="radio"
								name="color" value="black"><span class="dot black"></span></label>
						</div>

						<!-- 시간 선택 -->
						<div class="time-picker">
							<select><option>14</option>
								<option>15</option>
								<option>16</option></select> : <select><option>00</option>
								<option>15</option>
								<option>30</option></select> ~ <select><option>14</option>
								<option>15</option>
								<option>16</option></select> : <select><option>00</option>
								<option>15</option>
								<option>30</option></select>
						</div>

						<div class="actions">
							<button class="btn-xs">취소</button>
							<button class="btn-xs">저장</button>
						</div>
					</div>

					<button class="btn secondary add-btn active">+ 일정 추가</button>
				</aside>
			</div>
		</div>
	</div>
	<script src="JS/schedule.js"></script>
</body>