# Tinkoff Incubator

## Описание проекта

Проект представляет собой REST-сервис с методом, который на вход принимает строку и переводит ее на другой язык.

Для хранения данных о запросах и переводах используется in-memory база данных H2. Для взаимодействия с базой данных используется JDBC.

Для перевода текста используется API Яндекс.Переводчика, вызов API осущесвтляется с использованием библиотеки HttpURLConnection.

Многопоточность реализована с использованием ExecutorService с фиксированным пулом потоков, размером 10.

Для сборки приложения в Docker-контейнер будем использовать Dockerfile.

## Быстрый старт

1. Клонируйте репозиторий
```sh 
git clone https://github.com/FeNix784/tinkoffIncubator.git
```
   
2. Выполните компиляцию кода и упаковку в jar-файл
```sh 
mvn clean package
```

3. Запустите процесс создания образа
```sh 
docker build -f Dockerfile -t app
```

4. Запустите новый контейнер 
```sh 
 docker run -p 8080:8080 app
```

## Входные данные
REST-сервис будет написан на Java с использованием Spring Boot и будет иметь один единственный метод:
