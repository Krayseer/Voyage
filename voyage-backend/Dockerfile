FROM openjdk:18
ADD build/libs/voyage-backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]


## Используем образ OpenJDK 18 на базе Alpine Linux
#FROM openjdk:18
#
## Устанавливаем утилиту findutils, которая содержит xargs (с помощью apk)
#RUN apk update && apk add --no-cache findutils
#
## Указываем рабочую директорию внутри контейнера
#WORKDIR /app
#
## Копируем Gradle Wrapper файлы (gradlew и gradlew.bat)
#COPY gradlew .
#COPY gradlew.bat .
#
## Копируем каталог Gradle
#COPY gradle gradle
#
## Копируем файл с зависимостями проекта (необходим для ускорения сборки)
#COPY build.gradle .
#
## Копируем каталог src
#COPY src src
#
## Выполняем команду сборки проекта с помощью Gradle Wrapper
#RUN ./gradlew clean assemble
#
## Копируем JAR-файл собранного проекта в контейнер
#COPY build/libs/voyage-backend-0.0.1-SNAPSHOT.jar app.jar
#
## Указываем точку входа для запуска приложения при запуске контейнера
#ENTRYPOINT ["java", "-jar", "app.jar"]
