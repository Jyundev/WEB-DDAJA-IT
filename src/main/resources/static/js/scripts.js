/*!
* Start Bootstrap - Full Width Pics v5.0.6 (https://startbootstrap.com/template/full-width-pics)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-full-width-pics/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project

function animateValue(id, start, end, duration) {
    let range = end - start;
    let current = start;
    let increment = end > start? 1 : -1;
    let stepTime = Math.abs(Math.floor(duration / range));
    let obj = document.getElementById(id);
    let timer = setInterval(function() {
        current += increment;
        obj.innerHTML = current;
        if (current == end) {
            clearInterval(timer);
        }
    }, stepTime);
}

window.onload = function() {
    animateValue("emotion-count", 0, 150, 2000); // 0에서 150까지 2초 동안 증가
    animateValue("post-count", 0, 5, 3000); // 0에서 5까지 2초 동안 증가
    animateValue("comment-count", 0, 2000, 50); // 0에서 2000까지 2초 동안 증가
};

// 로그아웃 모달 열기
document.getElementById('logoutModalBtn').addEventListener('click', function() {
    document.getElementById('logoutModal').style.display = 'block';
});

// 로그아웃 모달 닫기
document.getElementsByClassName('close')[0].addEventListener('click', function() {
    document.getElementById('logoutModal').style.display = 'none';
});

// 모달 외부를 클릭하여 모달 닫기
window.addEventListener('click', function(event) {
    var modal = document.getElementById('logoutModal');
    if (event.target == modal) {
        modal.style.display = 'none';
    }
});

// 로그아웃 확인
document.getElementById('confirmLogout').addEventListener('click', function() {
    // 여기에 로그아웃 처리 로직을 추가합니다.
    console.log('Logged out');
    // 로그아웃 후 모달 닫기
    document.getElementById('logoutModal').style.display = 'none';
});

document.addEventListener('DOMContentLoaded', function() {
    const posts = ["내가 쓴 글 1", "내가 쓴 글 2", "내가 쓴 글 3", "내가 쓴 글 4", "내가 쓴 글 5"];
    const postsContainer = document.getElementById('my-posts');

    posts.forEach(post => {
        const li = document.createElement('li');
        li.classList.add('list-group-item');
        li.textContent = post;
        li.addEventListener('mouseenter', () => {
            li.style.backgroundColor = "#f8f9fa";
        });
        li.addEventListener('mouseleave', () => {
            li.style.backgroundColor = "";
        });
        postsContainer.appendChild(li);
    });
});


document.addEventListener('DOMContentLoaded', function () {
    // 감정별 트렌드 그래프 데이터셋
    const trendData = {
        labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        datasets: [
            {
                label: '행복',
                data: [2, 3, 4, 5, 6, 4, 3, 2, 5, 6, 7, 5],
                borderColor: 'rgba(0, 0, 255, 1)',  // 행복 - 파란색
                fill: false
            },
            {
                label: '놀람',
                data: [3, 2, 5, 4, 6, 5, 6, 4, 6, 5, 4, 3],
                borderColor: 'rgba(255, 255, 0, 1)',  // 놀람 - 노란색
                fill: false
            },
            {
                label: '보통',
                data: [3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3],
                borderColor: 'rgba(0, 128, 0, 1)',  // 보통 - 초록색
                fill: false
            },
            {
                label: '슬픔',
                data: [1, 2, 1, 3, 2, 1, 3, 2, 1, 2, 3, 1],
                borderColor: 'rgba(128, 128, 128, 1)',  // 슬픔 - 회색
                fill: false
            },
            {
                label: '분노',
                data: [4, 5, 3, 6, 5, 4, 3, 5, 4, 6, 5, 4],
                borderColor: 'rgba(255, 0, 0, 1)',  // 분노 - 빨간색
                fill: false
            }
        
        ]
    };

    // 감정별 색상 설정
    const colors = {
        '행복': 'blue',    // 파란색
        '놀람': 'yellow',   // 노란색
        '보통': 'green',   // 초록색
        '슬픔': 'grey',    // 회색
        '분노': 'red'      // 빨간색
    };
    
    const dailyEmotionData = {
        labels: ['월', '화', '수', '목', '금', '토', '일'],
        datasets: [
            {
                label: '행복',
                data: [2, 3, 4, 5, 3, 2, 4], // 예시 데이터
                backgroundColor: colors['행복'],
                stack: 'Stack 0',
            },
            {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
                label: '놀람',
                data: [3, 2, 3, 2, 4, 2, 3], // 예시 데이터
                backgroundColor: colors['놀람'],
                stack: 'Stack 0',
            },
            {
                label: '보통',
                data: [4, 3, 4, 3, 3, 3, 4], // 예시 데이터
                backgroundColor: colors['보통'],
                stack: 'Stack 0',
            },
            {
                label: '슬픔',
                data: [1, 2, 1, 2, 2, 3, 1], // 예시 데이터
                backgroundColor: colors['슬픔'],
                stack: 'Stack 0',
            },
            {
                label: '분노',
                data: [2, 3, 2, 3, 2, 4, 2], // 예시 데이터
                backgroundColor: colors['분노'],
                stack: 'Stack 0',
            }
        ]
    };

    // 전체 나의 감정 분포 그래프 데이터셋
    const overallEmotionData = {
        labels: ['행복', '놀람', '보통', '슬픔', '분노'],
        datasets: [{
            data: [50, 10, 5, 25, 10], // 데이터 순서를 감정 순서에 맞게 업데이트합니다.
            backgroundColor: [
                'blue', // 행복: 파란색
                'yellow', // 놀람: 노란색
                'green', // 보통: 초록색
                'grey', // 슬픔: 회색
                'red', // 분노: 빨간색
            ],
        }]
    };

    const trendChartCtx = document.getElementById('trendChart').getContext('2d');
    new Chart(trendChartCtx, {
        type: 'line',
        data: trendData,
        options: {}
    });

    const dailyEmotionChartCtx = document.getElementById('dailyEmotionChart').getContext('2d');
    new Chart(dailyEmotionChartCtx, {
        type: 'bar',
        data: dailyEmotionData,
        options: {}
    });

    const overallEmotionChartCtx = document.getElementById('overallEmotionChart').getContext('2d');
    new Chart(overallEmotionChartCtx, {
        type: 'doughnut',
        data: overallEmotionData,
        options: {}
    });
});


const overallEmotionChartCtx = document.getElementById('overallEmotionChart').getContext('2d');
const myDoughnutChart = new Chart(overallEmotionChartCtx, {
    type: 'doughnut',
    data: overallEmotionData,
    options: {
        legend: {
            display: false // 차트 내부 레전드를 비활성화
        },
        // 그래프의 비율을 유지하지 않으려면 다음 옵션을 추가합니다.
        //maintainAspectRatio: false,
        // 레전드 클릭 이벤트
        onClick: function(event, legendItem) {
            // 여기에 레전드 클릭 이벤트를 처리하는 코드를 추가합니다.
        }
    }
});

