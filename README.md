# Weather Tracker
## Описание
Данный проект представляет собой Spring Boot микросервис, который позволяет:
* Искать локации
* Добавлять интересующие локации на главную страницу
* Показывает погоду на добавленных локациях
* Удалять локации с главной
Также в данном проекте реализована простая авторизация.
## Технологии
* Java 21+
* Spring Boot 3+
* Spring Web
* Spring Data JPA
* Spring Security
* Spring Session
* PostgreSQL (В качестве основной БД)
* H2 database (В качестве тестовой БД)
* Thymeleaf
* Flyway
* Lombok
* Maven
## Пример работы
### Регистрация
Чтобы попасть на страницу для регистрации выполняем следующий запрос:
* **Метод:** `GET`
* **URL:** `/register`

Если введенные данные корректны, при нажатии на кнопку произойдет запрос:
* **Метод:** `POST`
* **URL** `/register`
### Авторизация
Чтобы попасть на страницу для авторизации выполняем следующий запрос:
* **Метод:** `GET`
* **URL:** `/login`

Если введенные данные корректны, при нажатии на кнопку произойдет запрос:
* **Метод:** `POST`
* **URL** `/login`
### Главная страница
После успешной регистрации или авторизации вы попадаете на главную страницу:
* **Метод:** `GET`
* **URL:** `/main`
### Добавление локаций
Чтобы попасть на страницу поиска локаций, необходимо выполнить следующий запрос:
* **Метод:** `GET`
* **URL:** `/search`

Если поле для названия локации не пустое:
* **Параметр:** `location`
* **Значение:** `Введенное название`

При нажатии на кнопку add происходит запрос:
* **Метод:** `POST`
* **URL** `/add-location`
### Удаление локаций
При нажатии на кнопку удаления происходит запрос:
* **Метод:** `POST`
* **URL** `/remove-location`
### Идея проекта
Идея для проекта взята из: [Роадмап Сергея Жукова](https://zhukovsd.github.io/java-backend-learning-course/)

Заготовка Front-end части взята там же.