INSERT INTO challenge_chapter (chapter_id, chapter_link, chapter_mission, chapter_name, chapter_num, mission_day, challenge_id) VALUES
(1,  'https://example.com/chapter1', '리눅스의 개요: 운영체제의 개요 리눅스 기초', '리눅스 실무의 이해', 1, '2024-05-27', 1),
(2,  'https://example.com/chapter1', '리눅스 시스템의 이해: 리눅스와 하드웨어, 리눅스의 구조, X 윈도우, SHELL, 프로세스', '리눅스 실무의 이해', 1, '2024-05-27', 1),
(3,  'https://example.com/chapter1', '네트워크의 이해: 네트워크 기초 및 설정', '리눅스 실무의 이해', 1, '2024-05-27', 1),


INSERT INTO challenge_info (challenge_id,  book, challengeName, challenge_detail, chapter, pass_rate, member_pass_rate, totalprogress_rate, certificate_id) VALUES
(1, '리눅스 2주 챌린지', '2024 이기적 리눅스마스터 1급 기본서', '리눅스 실무의 이해 공부하기', '리눅스 실무의 이해', '70%', '60%', '40%', 1),
(2, '리눅스 2주 챌린지', '2024 이기적 리눅스마스터 1급 기본서', '리눅스 시스템 관리 이해 공부하기', '리눅스 시스템 관리', '70%', '60%', '50%', 1),

(2, 'ADsP Challenge', 'Study and complete ADsP certification2', 'ADsP Certification2', 'Data Analysis for Everyone', '70%', '60%', '40%', 1),

INSERT INTO certificate_info (certificate_id, certificate_name, certificate_full_name, difficulty, eligibility, exam_content, field, overview, pass_criterial, registayion_link, related_job, types) VALUES
(1, '리눅스마스터 1급', '리눅스마스터 1급', "상", '{
    "시험응시기준": [
        {
            "구분": "1차",
            "기준": "제한없음"
        },
        {
            "구분": "2차",
            "기준": "1차 시험 합격자에 한해 성적공개일 기준으로 2년 이내 응시"
        }
    ]
}', '{
    "시험": [
        {
            "검정과목": "1급",
            "차수": "1차",
            "검정방법": "필기",
            "출제방식": "사지선다",
            "문항수": "100문항",
            "시험시간": "100분",
            "배점": "100점"
        },
        {
            "검정과목": "1급",
            "차수": "2차",
            "검정방법": "필기(40%)",
            "출제방식": "단답, 서술식",
            "문항수": "10문항",
            "시험시간": "100분",
            "배점": "100점"
        },
        {
            "검정과목": "1급",
            "차수": "2차",
            "검정방법": "실기(60%)",
            "출제방식": "관리 및 설정",
            "문항수": "5~7문항",
            "시험시간": "-",
            "배점": "-"
        }
    ]
}', '운영체제', '리눅스마스터1급은 리눅스 마스터 지식 및 기술 전문 인력 양성을 위해 제정된 제도이다. 자격증 취득 후 관리, 운영, 장치, 서비스, 보안 등을 수행할 수 있다.', '{
    "합격기준": [
        {
            "차수": "1차",
            "합격기준": "60점 이상"
        },
        {

            "차수": "2차",
            "합격기준": "60점 이상 (과목당 40% 미만 과락)"
        }
    ]
}

    ]
}', 'https://www.ihd.or.kr/memacceptguide.do", "관리, 운영, 장치, 서비스, 보안", "1차 : 필기 , 2차 :필기,실기');
