$(document).ready(function () {
    var today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth() + 1;
    var date = today.getDate();

    // 년-월 표시 (상단)
    $('.year').text(year + '년');
    $('.month').text(month + '월');

    // 달력 셀에 날짜 채우기
    $('.calendar td .day').each(function(index){
        if (!$(this).hasClass('empty')) {
            $(this).text(index + 1);
        }
    });

    // 이전달 버튼 클릭
    $('#prevMonth').click(function(){
        month--;
        if(month < 1){
            month = 12;
            year--;
        }
        updateCalendar(year, month);
    });

    // 다음달 버튼 클릭
    $('#nextMonth').click(function(){
        month++;
        if(month > 12){
            month = 1;
            year++;
        }
        updateCalendar(year, month);
    });

    // 달력 업데이트 함수
    function updateCalendar(y, m){
        $('.year-month .year').text(y + '년');
        $('.year-month .month').text(m + '월');

        // 여기서 실제 DB에서 일정 가져오거나
        // 달력 셀 내용 갱신 가능
    }
});
