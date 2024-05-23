<!-- https://lazymankook.tistory.com/30 -->
DAO (Data Access Object) 
- DB를 사용해 데이터를 조회하거나 조작하는 기능을 담당
- 비즈니스 로직이나 DB와 관련없는 코드들 분리하기 위해 사용 

DTO(Data Transfer Object) 
- Database에서 Data를 얻어 Service나 Controller 등으로 보낼 때 사용하는 객체 
- 데이터를 주고받을 포맷
Service 
- 비지니스 로직이 들어가는 부분
- Controller가 Request를 받으면 적절한 Service에 전달하고, 전달 받은 Service는 비즈니스 로직을 처리

=> DAO로 데이터베이스를 접근하고, DTO로 데이터를 전달받은 다음, 적절한 처리를 해 반환한다. 

<!-- https://velog.io/@limsubin/Spring-Security-JWT-%EC%9D%84-%EA%B5%AC%ED%98%84%ED%95%B4%EB%B3%B4%EC%9E%90 -->
Security & JWT
