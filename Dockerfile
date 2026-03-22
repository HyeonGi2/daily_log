# 1. 자바 17 버전이 깔린 빈 방을 가져옵니다.
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /app

# 2. 내 컴퓨터의 모든 코드를 그 방으로 복사합니다.
COPY . .

# 3. 맥북에서 실행할 수 있게 권한을 주고, 서버를 예쁘게 조립(Build)합니다.
RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test

# 4. 조립된 진짜 실행 파일(jar)만 남기고 나머지 쓰레기는 버립니다.
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*SNAPSHOT.jar app.jar

# 5. 문을 열어두고(8080) 서버를 켭니다!
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]