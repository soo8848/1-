$(document).ready(function() {
	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth() + 1;

	function updateCalendar() {
		$('.year').text(year + '년');
		$('.month').text(month + '월');

		var firstDay = new Date(year, month - 1, 1).getDay(); // Date객체의
																// month는
																// 0월~11월로 동작
		var lastDate = new Date(year, month, 0).getDate(); // Date(년,월,일)여기서 일이
															// 0이면 이전달의 마지막날을 의미
		// console.log(firstDay);
		// console.log(lastDate);

		var tbody = $('#calendarTbody');
		tbody.empty();

		var html = '<tr>';
		var dayCount = 0;

		for (var i = 1; i < firstDay; i++) {
			html += '<td><div class="day empty"></div></td>';
			dayCount++;
		}

		for (var date = 1; date <= lastDate; date++) {
			html += '<td><div class="day">' + date + '</div></td>';
			dayCount++;

			if (dayCount % 7 === 0) {
				html += '</tr><tr>';
			}
		}

		while (dayCount % 7 !== 0) {
			html += '<td><div class="day empty"></div></td>';
			dayCount++;
		}

		html += '</tr>';
		tbody.append(html);
	}
	
	$('#prevMonth').click(function() {
		month--;
		if (month < 1) {
			month = 12;
			year--;
		}
		updateCalendar();
	});
	$('#nextMonth').click(function() {
		month++;
		if (month > 12) {
			month = 1;
			year++;
		}
		updateCalendar();
	});
	
	updateCalendar();
});
