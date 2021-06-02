# springApi
API для системы опросов пользователей.

java 16

Собрать jar файл для запуска
mvn clean install

Запуск приложения
java -jar target/springApi-1.0-SNAPSHOT.jar

Авторизация
curl -X GET "http://localhost:8080/login?id=1"
1 - логин админа
2 - логин пользователя
другая строка - аноним

Добавить опрос
curl -X POST -H "Content-type: application/json" -d "{\"name\":\"newQuizName\",\"description\":\"newQuiz\",\"questions\":[{\"text\":\"ccc\",\"type\":\"TEXT\",\"answers\":[{\"selected\":false,\"text\":\"ggg\"}]}]}" http://localhost:8080/add

Редактировать опрос
curl -X POST -H "Content-type: application/json" -d "{\"name\":\"newQuizName\",\"description\":\"newQuiz\",\"questions\":[{\"text\":\"ccc\",\"type\":\"TEXT\",\"answers\":[{\"selected\":false,\"text\":\"vvv\"}]}]}" http://localhost:8080/change

Удалить опрос
curl -X GET "http://localhost:8080/remove?name=newQuizName"

вывести все опросы
curl -X GET http://localhost:8080/list/all

Начать опрос
curl -X GET "http://localhost:8080/selectquiz?name=newQuizName&start=true"

Установить ответ
curl -X GET "http://localhost:8080/setanswer?name=newQuizName&question=ccc&answer=ggg"

Установить ответ свободным текстом (Если опрос позволяет)
curl -X GET "http://localhost:8080/setanswer?name=newQuizName&question=ccc&answer=ggg&answerText=fin"

Завершить опрос 
curl -X GET "http://localhost:8080/selectquiz?name=newQuizName&start=false"

вывести опросы текущего пользователя
curl -X GET http://localhost:8080/list/user
