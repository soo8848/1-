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

    function updateCalendar() {
        $('.year').text(year + '년');
        $('.month').text(month + '월');

        var firstDay = new Date(year, month - 1, 1).getDay();
        var lastDate = new Date(year, month, 0).getDate();

        var tbody = $('.calendar tbody');
        tbody.empty();

        var tr = '<tr>';
        var dayCount = 0;

        for (var i = 0; i < firstDay; i++) {
            tr += '<td><div class="day empty"></div></td>';
            dayCount++;
        }

        for (var date = 1; date <= lastDate; date++) {
            var todayClass = (year === todayYear && month === todayMonth && date === todayDate) ? ' today' : '';
            var spanDot = '';
            var count = 0;

            for (var i = 0; i < dotList.length; i++) {
                var schedule = dotList[i];
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

            var dateStr = year + '-' + (month < 10 ? '0' + month : month) + '-' + (date < 10 ? '0' + date : date);
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
    }

    function showScheduleList(list, selectedDate) {
        var ul = document.querySelector('.sched-ul');
        ul.innerHTML = '';

        var filteredList = [];
        if (selectedDate) {
            for (var i = 0; i < list.length; i++) {
                if (list[i].schedule_date === selectedDate) {
                    filteredList.push(list[i]);
                }
            }
        } else {
            filteredList = list;
        }

        if (filteredList.length === 0) {
            var li = document.createElement('li');
            li.textContent = '일정이 없습니다.';
            ul.appendChild(li);
        } else {
            for (var i = 0; i < filteredList.length; i++) {
                var item = filteredList[i];
                var li = document.createElement('li');
                li.innerHTML =
                    '<span class="dot ' + item.schedule_type + '"></span>' +
                    '<div class="sched-line">' +
                        '<div class="when">' +
                            formatDate(item.schedule_date) + ' ' +
                            (item.start_time ? item.start_time + (item.end_time ? ' ~ ' + item.end_time : '') : '') +
                        '</div>' +
                        '<div class="what">' + item.title + '</div>' +
                    '</div>';
                ul.appendChild(li);
            }
        }

        document.querySelector('.sched-add-form').style.display = 'block';
        document.querySelector('.add-btn').style.display = 'block';
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
        showScheduleList(dotList, selectedDate);
    });

    $('#prevMonth').click(function() {
        month--;
        if (month < 1) { month = 12; year--; }
        updateCalendar();
        showScheduleList(dotList);
    });

    $('#nextMonth').click(function() {
        month++;
        if (month > 12) { month = 1; year++; }
        updateCalendar();
        showScheduleList(dotList);
    });

    $.ajax({
        url: "controller",
        method: 'GET',
        dataType: "json",
        data: { cmd: "dotSchedule" },
        success: function(responseText) {
            dotList = responseText;
            updateCalendar();
            showScheduleList(dotList); // 전체 일정 표시
        },
        error: function() {
            console.log('일정 dot 객체를 가져오는데 실패했습니다.');
        }
    });

    $('.add-btn').click(function() {
        $('.sched-add-form').toggle();
    });

    $('.btn-xsr').click(function() {
        var title = $('.sched-add-form .input-s').val().trim();
        var color = $('.sched-add-form input[name="color"]:checked').val();
        var startHour = $('.sched-add-form select').eq(0).val();
        var startMin = $('.sched-add-form select').eq(1).val();
        var endHour = $('.sched-add-form select').eq(2).val();
        var endMin = $('.sched-add-form select').eq(3).val();

        if (!title || !color || !selectedDate) {
            alert('일정명과 색상을 선택하고 날짜를 선택해주세요.');
            return;
        }

        var startTime = startHour + ':' + startMin;
        var endTime = endHour + ':' + endMin;

        dotList.push({
            schedule_date: selectedDate,
            schedule_type: color,
            title: title,
            start_time: startTime,
            end_time: endTime
        });

        updateCalendar();
        showScheduleList(dotList, selectedDate);

        $('.sched-add-form .input-s').val('');
        $('.sched-add-form input[name="color"]').prop('checked', false);
        $('.sched-add-form').hide();
    });

    $('.btn-xs').click(function() {
        $('.sched-add-form').hide();
        $('.sched-add-form .input-s').val('');
        $('.sched-add-form input[name="color"]').prop('checked', false);
    });

    updateCalendar();
});
