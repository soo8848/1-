$(document).ready(function() {
	var today = new Date();
	var todayYear = today.getFullYear();
	var todayMonth = today.getMonth() + 1;
	var todayDate = today.getDate();
	var scheduleList;
	var selectedDate;
    var scheduleList = [
                        { schedule_date: "2025-10-05", schedule_type: "red", title: "빨간색미팅" },
                        { schedule_date: "2025-10-05", schedule_type: "blue", title: "파란색미팅" },
                        { schedule_date: "2025-10-05", schedule_type: "yellow", title: "노란색미팅" },
                        { schedule_date: "2025-10-05", schedule_type: "green", title: "미팅" },
                        { schedule_date: "2025-10-05", schedule_type: "green", title: "미팅" },
                        { schedule_date: "2025-10-05", schedule_type: "green", title: "미팅" },
                        { schedule_date: "2025-10-05", schedule_type: "green", title: "미팅" },
                        { schedule_date: "2025-10-05", schedule_type: "green", title: "미팅" },
                        { schedule_date: "2025-10-05", schedule_type: "green", title: "미팅" },
                        { schedule_date: "2025-10-05", schedule_type: "green", title: "미팅" }
                      ];
	
	
	var year = todayYear;
	var month = todayMonth;
	
	function updateCalendar() {
		$('.year').text(year + '년');
		$('.month').text(month + '월');

		var firstDay = new Date(year, month - 1, 1).getDay(); // Date객체의 month는 0월~11월로 동작
		var lastDate = new Date(year, month, 0).getDate(); // Date(년,월,일)여기서 일이 0이면 이전달의 마지막날을 의미
		// console.log(firstDay);
		// console.log(lastDate);

		var tbody = $('.calendar tbody');
		tbody.empty();

		var tr = '<tr>';
		var dayCount = 0;

		for (var i = 1; i < firstDay; i++) {
			tr += '<td><div class="day empty"></div></td>';
			dayCount++;
		}

		for (var date = 1; date <= lastDate; date++) {
			var todayClass = '';

			if(year === todayYear && month === todayMonth && date === todayDate) {
				todayClass = ' today';
			}
			
		    var spanDot = '';
		    var count = 0;
		    (scheduleList || []).forEach(function(schedule) {
		        var yearMonthDay = schedule.schedule_date.split('-').map(Number);
		        var y = yearMonthDay[0];
		        var m = yearMonthDay[1];
		        var d = yearMonthDay[2];
		        
		        if (y === year && m === month && d === date) {
		        	count++;
		        	if (count <= 3) {
		        		spanDot += '<span class="dot ' + schedule.schedule_type + '" title="' + schedule.title + '"></span>';
		        	}
		        }
		    });
			
		    if (count > 3) {
		    	spanDot += '<span class="more">+' + (count - 3) + '</span>';
		    }
		    var dateStr =year + '-' + (month < 10 ? '0' + month : month) + '-' + (date < 10 ? '0' + date : date);
		    tr += '<td><div class="day' + todayClass + '" data-date="' + dateStr + '">' + date + spanDot + '</div></td>';
		    dayCount++;
		    
			if (dayCount % 7 === 0) {
				tr += '</tr><tr>';
			}
		}

		while (dayCount % 7 !== 0) {
			tr += '<td><div class="day empty"></div></td>';
			dayCount++;
		}

		tr += '</tr>';
		tbody.append(tr);
		
		// 페이지 로드 시
		if (year === todayYear && month === todayMonth) {rr
			$('.calendar .day.today').trigger('click');
		}
		
	} //updateCalendar()
	
	$(document).on('click','.calendar .day:not(.empty)', function(){
		$('.calendar .day').removeClass('selected'); //이전에 선택된거 제거용
		$(this).addClass('selected');
		
		selectedDate = $(this).data('date'); //사용자 정의 속성 data-에서 이름이 date인 값
		
		$.ajax({
			url: '/getSchedule',
			method: 'GET',
			data: { date: selectedDate },
			success: function (response) {
				showScheduleList(response, selectedDate);
			},
			error: function() {
				console.log('일정 정보를 가져오는데 실패했습니다.');
			}
		});
		
	    function showScheduleList(list) {
	        var scheduleListContainer = $('#scheduleList');
	        scheduleListContainer.empty();
	        if (!list || list.length === 0) {
	        	scheduleListContainer.append('<p>일정이 없습니다.</p>');
	        } else {
	            var ul = '<ul>';
	            list.forEach(function(item){
	            	ul += '<li>' + item.title + ' (' + item.time + ')</li>';rrr
	            });
	            ul += '</ul>';
	            scheduleListContainer.append(ul);
	        }
	    }
		
	});
	
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

	$.ajax({
	    url: "controller",
	    method: 'GET',
	    data: {
	        cmd: "getDotSchedule",
	        date: selectedDate
	    },
	    success: function(responseText) {
	        let jsonObject = JSON.parse(responseText);
	        console.log(jsonObject);
	    },
	    error: function() {
			console.log('일정 dot객체를 가져오는데 실패했습니다.');
		}
	});
});
