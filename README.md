# selection-committee-spring-epam
EpamFinalProject

Приймальна комісія

Система має перелік факультетів, для якого необхідно реалізувати можливість сортування:
- по імені (a-z, z-a);
- за кількістю бюджетних місць;
- за загальною кількістю місць.
Абітурієнт реєструється в системі. Під час реєстрації необхідно ввести ПІБ, email, місто,
область, назву навчального закладу (опціонально: прикріпити скан атестату з оцінками). 
Абітурієнт може зареєструватися на один або декілька факультетів. При реєстрації на факультет 
студент вводить результати з відповідних предметів, а також оцінки з атестату.
Адміністратор системи володіє правами:
- додавання, видалення або редагування факультету;
- блокування або розблокування абітурієнта;
- додавання результатів абітурієнтів до Відомості;
- фіналізації Відомості на зарахування.
Після фіналізації відомості система підраховує суму балів і визначає абітурієнтів, зарахованих
до навчального закладу на бюджетні місця, на контракт. (За бажанням додати оповіщення про
результат зарахування на певну форму навчання, а також не зарахування за допомогою відправки 
email абітурієнтові).



Загальні вимоги до реалізації

1. На основі сутностей предметної області створити класи, які їм відповідають.
2. Класи і методи повинні мати назви, що відображають їх функціональність, і повинні бути
   рознесені по пакетам.
3. Оформлення коду має відповідати Java Code Convention.
4. Інформацію щодо предметної області зберігати у реляційній базі даних (в якості СУБД
   рекомендується використовувати MySQL або PostgreSQL).
5. Застосунок має підтримувати роботу з кирилицею (бути багатомовним), в тому числі при
   зберіганні інформації в базі даних:
   a. повинна бути можливість перемикання мови інтерфейсу;
   b. повинна бути підтримка введення, виведення і зберігання інформації (в базі даних),
   записаної на різних мовах;
   c. в якості мов обрати мінімум дві: одна на основі кирилиці (українська або російська),
   інша на основі латиниці (англійська);
   d. дати повинні бути реалізовані через DataTime бібліотеку (Java8).
6. Реалізувати захист від повторної відправки даних на сервер при оновленні сторінки
   (реалізувати PRG).
7. У застосунку повинні бути реалізовані аутентифікація і авторизація, розмежування прав
   доступу користувачів системи до компонентів програми. Шифрування паролів заохочується.
8. Впровадити у проект журнал подій із використанням бібліотеки log4j.
9. Код повинен містити коментарі документації (всі класи верхнього рівня, нетривіальні методи
   і конструктори).
10. Застосунок має бути покритим модульними тестами (мінімальний відсоток покриття 40%).
    Написання інтеграційних тестів заохочуються. Використання Mockito заохочується.
11. Реалізувати механізм пагінації сторінок з даними.
12. Всі поля введення повинні бути із валідацією даних.
13. Застосунок має коректно реагувати на помилки та виключні ситуації різного роду (кінцевий
    користувач не повинен бачити stack trace на стороні клієнта).
14. Самостійне розширення постановки задачі по функціональності заохочується! (додавання
    капчі, формування звітів у різних форматах, тощо)
15. Використання HTML, CSS, JS фреймворків для інтерфейсу користувача (Bootstrap, Materialize,
    ін.) заохочується!
16. Розробка проектів за допомогою Git заохочується.


До спрингового проекту:
17. Застосунок повинен буду структурованим за архітектурою MVC та Spring Boot.
    Дозволено використання Project Lombok.
18. Використання Spring Resource Bundle заохочується.
19. Повинно бути застосована Spring Authorization.
20. Для доступу до даних використовувати JPA (Spring Data та/або Hibernate).
21. Обробка виключних ситуацій за допомогою Exception Handling with Spring for REST API
    заохочується.
22. Використання ThymeLeaf заохочується.
23. Використання додаткових фреймворків (Swager та інші) заохочується.
![DB](https://user-images.githubusercontent.com/96628715/187993202-68d3319a-d9df-425d-b29d-8af7a08deeef.png)
