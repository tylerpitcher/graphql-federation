FROM maven:3.9.9-amazoncorretto-21

WORKDIR /app

COPY target/school-course-api*.jar app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]
