# Валидатор URL-адресов (Лабораторная работа №15)

**Автор:** Бодя  
**Группа:** ИТП-31

## Описание проекта
Приложение проверяет доступность списка URL из файла, классифицирует HTTP-ответы и выводит отчёт: сначала "мёртвые" ссылки, затем рабочие.

Технологии:
- Java 17+
- java.net.http.HttpClient
- Gradle 8.10+
- Параллельная обработка через parallelStream
- Полное покрытие бизнес-логики тестами и JavaDoc

## Как запустить проект (пошагово)
### **1.** Нажмите Win + R, введите cmd и нажмите Enter. 

### **2.** Перейдите на рабочий стол (или в любую удобную папку)
```YAML 
cd C:\Users\%USERNAME%\Desktop
```

### **3.** Создайте новую папку для проекта и зайдите в нее
```YAML 
mkdir projects
cd projects
```

### **4.** Клонируйте репозиторий путем копирования данной команды
```YAML 
git clone https://github.com/sprBRAVY/URL-Validator-15.git
```
Затем зайдите в папку с проектом
```YAML
cd URL-Validator-15
```
### **5.** Создайте требуемые файлы для программы. 

Напишите данную команду для создание config папка для программы 
```YAML
mkdir config && copy nul config\urls.txt
```
Заполните ссылками файл urls.txt путем копирование данной команды
```YAML
echo https://google.com > config\urls.txt
echo https://github.com >> config\urls.txt
echo https://habr.com >> config\urls.txt
echo https://vk.com >> config\urls.txt
echo https://yandex.ru >> config\urls.txt
echo https://en.wikipedia.org/wiki/Main_Page >> config\urls.txt
echo https://httpbin.org/status/200 >> config\urls.txt
echo https://jsonplaceholder.typicode.com/posts/1 >> config\urls.txt
echo https://bit.ly/3X9kLmP >> config\urls.txt
echo http://shorturl.at/kwDE9 >> config\urls.txt
echo https://google.com/no-such-page-404 >> config\urls.txt
echo https://github.com/nonexistent-repo-404-ever >> config\urls.txt
echo https://httpstat.us/404 >> config\urls.txt
echo https://httpbin.org/status/404 >> config\urls.txt
echo https://httpstat.us/403 >> config\urls.txt
echo https://httpbin.org/status/403 >> config\urls.txt
echo https://httpstat.us/500 >> config\urls.txt
echo https://httpbin.org/status/500 >> config\urls.txt
echo https://httpstat.us/503 >> config\urls.txt
echo https://httpbin.org/status/503 >> config\urls.txt
echo https://httpbin.org/redirect/1 >> config\urls.txt
echo https://google.com/images >> config\urls.txt
echo https://this-domain-does-not-exist-at-all-1234567890.com >> config\urls.txt
echo https://192.0.2.1 >> config\urls.txt
```
Далее командой создать файл app.propreties:
```YAML
mkdir src\main\resources && copy nul src\main\resources\app.properties
```
И заполните этот файл данной командой 
```YAML
echo urls.file.path=./config/urls.txt >src\main\resources\app.properties
echo http.timeout.ms=5000>>src\main\resources\app.properties
echo http.method=GET>>src\main\resources\app.properties
```

### **6.** Соберите проект и запустите с помощью данных команд

Сборка
```YAML
gradle build
```
Запуск
```YAML
gradle run
```
 

