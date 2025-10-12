$(document).ready(function() {
    var today = new Date();
    var todayYear = today.getFullYear();
    var todayMonth = today.getMonth() + 1;
    var todayDate = today.getDate();

    var year = todayYear;
    var month = todayMonth;
    var selectedDate;
    var dotList = [];
    var MAX_DOTS = 3;
    
    $('.sched-add-form, .add-btn').hide();

    function updateCalendar() {
        $('.year').text(year + '년');
        $('.month').text(month + '월');

        var firstDay = new Date(year, month - 1, 1).getDay();
        var lastDate = new Date(year, month, 0).getDate();

        var tbody = $('.calendar tbody');
        tbody.empty();

        var tr = $('<tr>');
        var dayCount = 0;

        for (var i = 0; i < firstDay; i++) {
            tr.append('<td><div class="day empty"></div></td>');
            dayCount++;
        }

        for (var date = 1; date <= lastDate; date++) {
            var todayClass = (year === todayYear && month === todayMonth && date === todayDate) ? ' today' : '';
            var spanDot = '';
            var count = 0;

            for (var j = 0; j < dotList.length; j++) {
                var schedule = dotList[j];
                var ymd = schedule.schedule_date.split('-').map(Number);
                if (ymd[0] === year && ymd[1] === month && ymd[2] === date) {
                    count++;
                    if (count <= MAX_DOTS) {
                        spanDot += '<span class="dot ' + schedule.schedule_type + '" title="' + schedule.title + '"></span>';
                    }
                }
            }

            if (count > MAX_DOTS) {
                spanDot += '<span class="more">+' + (count - MAX_DOTS) + '</span>';
            }

            var dateStr = year + '-' + String(month).padStart(2, '0') + '-' + String(date).padStart(2, '0');
            tr.append('<td><div class="day' + todayClass + '" data-date="' + dateStr + '">' + date + spanDot + '</div></td>');
            dayCount++;

            if (dayCount % 7 === 0) {
                tbody.append(tr);
                tr = $('<tr>');
            }
        }

        if (dayCount % 7 !== 0) {
            for (var k = dayCount % 7; k < 7; k++) {
                tr.append('<td><div class="day empty"></div></td>');
            }
            tbody.append(tr);
        }
    }

    function showScheduleList(list, selectedDate) {
        const ul = $('.sched-ul').empty();
        const currentMonth = String(month).padStart(2, '0');
        const currentYear = year.toString();

        const filteredList = list.filter(function(item) {
            if (selectedDate) {
            	$('.add-btn').show();
                $('.panel-title').text("일정목록");
                return item.schedule_date === selectedDate;
            } else {
            	$('.add-btn').hide();
                $('.panel-title').text("현재 월 전체일정목록");
                const [itemYear, itemMonth] = item.schedule_date.split('-');
                return itemYear === currentYear && itemMonth === currentMonth;
            }
        });

        if (filteredList.length === 0) {
            $('<li>일정이 없습니다.</li>').appendTo(ul);
            return;
        }

        $.each(filteredList, function(index, item) {
            const li = $('<li>').data('schedule-no', item.schedule_no);
            const dot = $('<span>').addClass('dot ' + item.schedule_type);
            const when = $('<div>')
                .addClass('when')
                .text(formatDate(item.schedule_date) + ' ' +
                    (item.start_time ? item.start_time + (item.end_time ? ' ~ ' + item.end_time : '') : '')
                );
            const what = $('<div>').addClass('what').text(item.title);
            const schedLine = $('<div>').addClass('sched-line').append(when, what);
            const deleteBtn = $('<button>')
                .addClass('sched-delete-btn')
                .html('<i class="fas fa-times"></i>');
            li.append(dot, schedLine, deleteBtn).appendTo(ul);
        });
    }

    function formatDate(dateStr) {
        var date = new Date(dateStr);
        var day = date.getDate();
        var dayNames = ['일', '월', '화', '수', '목', '금', '토'];
        var dayName = dayNames[date.getDay()];
        return day + '일 (' + dayName + ')';
    }

    $(document).on('click', '.calendar .day:not(.empty)', function() {
        $('.calendar .day').removeClass('selected');
        $(this).addClass('selected');
        selectedDate = $(this).data('date');
        
        $('.add-btn').show();
//        $('.sched-add-form').show();
        
        showScheduleList(dotList, selectedDate);
    });

    $('#prevMonth, #nextMonth').click(function() {
        selectedDate = null;
        month += $(this).attr('id') === 'nextMonth' ? 1 : -1;
        if (month < 1) { month = 12; year--; }
        if (month > 12) { month = 1; year++; }
        
        updateCalendar();
        showScheduleList(dotList, selectedDate);
    });

    $.ajax({
        url: "controller",
        method: 'GET',
        dataType: "json",
        data: { cmd: "dotSchedule" },
        success: function(response) {
        	console.log(response)
            dotList = response;
            updateCalendar();
            showScheduleList(dotList);
        },
        error: function() {
            console.log('일정 dot 객체를 가져오는데 실패했습니다.');
        }
    });

    $('.add-btn').click(function() {
    	$(this).toggleClass('active');
    	$('.sched-add-form').slideUp();
        $('.sched-add-form').toggle();
    });
    
    $(document).on('click', '.cancel-xs', function() {
        $('.sched-add-form').slideUp();
        $('.add-btn').removeClass('active');
    });

    $('.save-xs').click(function() {
        var title = $('.sched-add-form .input-s').val().trim();
        
        var onlySpecialChars = /^[^a-zA-Z0-9가-힣]+$/;

        var bannedWords = ['비난', '욕설', '비속어'];

        if (
            !title ||                          // 빈값
            title.length < 2 ||               // 너무 짧은 제목
            onlySpecialChars.test(title) ||   // 특수문자만 입력
            bannedWords.some(function(word) { title.includes(word); })
        ) {
        	showAlert('올바르지 않은 일정명 ');
            return;
        };

        
        var color = $('.sched-add-form input[name="color"]:checked').val();
        var startHour = $('#startHour').val();
        var startMin = $('#startMin').val();
        var endHour = $('#endHour').val();
        var endMin = $('#endMin').val();

        if (!title || !color || !selectedDate) {
        	showAlert('일정명과 색상을 선택해주세요.');
            return;
        }

        var startTime = startHour + ':' + startMin;
        var endTime = endHour + ':' + endMin;
        
//        var sendData = {
//            cmd: "addSchedule",
//            scheduleDate: selectedDate,
//            scheduleType: color,
//            title: title,
//            startTime: startTime,
//            endTime: endTime
//        };
//
//        console.log("서버로 전송할 데이터:", sendData);

        
        $.ajax({
            url: 'controller',
            type: 'POST',
            dataType: "json",
            data: {
                cmd: "addSchedule",
                scheduleDate: selectedDate,
                scheduleType: color,
                title: title,
                startTime: startTime,
                endTime: endTime
            },
            success: function(response) {
                if(response.result) {
                    console.log('일정이 등록되었습니다!');
                    
                    dotList.push({
                        schedule_no: response.schedule_no || Date.now(), // 서버에서 번호 주면 사용
                        schedule_date: selectedDate,
                        schedule_type: color,
                        title: title,
                        start_time: startTime,
                        end_time: endTime
                    });
                    
                    updateCalendar();
                    showScheduleList(dotList, selectedDate);
                    $('.add-btn').addClass('active');
                    $('.sched-add-form .input-s').val('');
                    $('.sched-add-form input[name="color"]').prop('checked', false);
                    $('.sched-add-form').hide();
                } else {
                    console.log('일정 등록에 실패했습니다.');
                }
            },
            error: function() {
            	showAlert('서버 통신 오류가 발생했습니다.');
            }
        });

        
    });

    $('.cancel-xs').click(function() {
        $('.sched-add-form .input-s').val('');
        $('.sched-add-form input[name="color"]').prop('checked', false);
    });

    const hourSelects = ['#startHour', '#endHour'];
    const minSelects = ['#startMin', '#endMin'];

    $.each(hourSelects, function(index, selector) {
        const $select = $(selector);
        for (var h = 0; h < 24; h++) { //국제 표준 시간 0~23
            const value = h.toString().padStart(2, '0');
            $select.append($('<option>', { value: value, text: value }));
        }
    });

    $.each(minSelects, function(index, selector) {
        const $select = $(selector);
        for (var m = 0; m < 60; m++) { //국제 표준 시간 0~59
            const value = m.toString().padStart(2, '0');
            $select.append($('<option>', { value: value, text: value }));
        }
    });
    
    function showAlert(message) {
    	  $('#alertMessage').text(message);
    	  $('#customAlert').show();
    	}
    
    $('.sched-ul').on('click', '.sched-delete-btn', function() {
        li = $(this).closest('li');
        let scheduleNo = li.data('schedule-no');
//        console.log("삭제할 scheduleNo:", scheduleNo);


        $.ajax({
            url: 'controller',
            type: 'POST',
            dataType: 'json',
            data: {
                cmd: 'deleteSchedule',
                scheduleNo: scheduleNo
            },
            success: function(response) {
                if (response.result) {
                	
                	dotList = dotList.filter(function(item) {
                	    return item.schedule_no !== scheduleNo;
                	});

                  
                    li.remove();
                    console.log('일정이 삭제되었습니다.');
                    updateCalendar();
                    showScheduleList(dotList, selectedDate);
                } else {
                    showAlert('삭제 실패했습니다.');
                }
            },
            error: function() {
                showAlert('서버 통신 오류');
            }
        });
    });

    
    updateCalendar();
});