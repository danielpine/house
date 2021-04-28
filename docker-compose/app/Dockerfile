FROM java:8
LABEL danielpine danielpine@sina.com
VOLUME /tmp 
ADD house_zzq-1.0-SNAPSHOT.jar app.jar 
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java" ,"-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=docker"]
#ENTRYPOINT ["java" ,"-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","-Dspring.profiles.active=docker"]
