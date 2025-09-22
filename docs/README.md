# Opis projektu

## Cel

Projekt miał za zadanie zaprojektowanie i zaimplementowanie intereaktywnego panelu wyświetląjące aktualne statusy zamówień oraz formularza umożliwiającego dodanie nowych zamówień.

## Podjęte rozwiązania

### Baza danych

Jako baze danych wybrałem MongoDB, z chęci poćwiczenia działania z tą bazą danych. Dodatkowo przyjęty model danych nie wymaga ani SQL ani noSQL

### Podział projektu

Zastosowałem monorepo z użyciem Gradle, gdyż pozwala na czytelny podział aplikacji na podprojekty (microservice) który pozwala na rozwój aplikacji z przyszłości

### Worker

Jest to microservive który zajmuje się obsługą zamówień i symuluje działania na zamówieniach


## Brakujące funkcjonalności/technikalia

- Odświeżanie listy zamówień po dodaniu nowego zamówienia lub zmiany statusu na zamówieniu
- Automatycznego wyjścia z dialogu po dodaniu zamówienia
- Styli CSS całej aplikacji
- Testy integracyjne i jednostkowe po stronie BE i FE
- Obsługa błędów po stronie BE i FE

## Rozwój

- Dodanie paginacji dla listy zamówień
- Dodanie filtrowania listy statusów po stronie FE
- Generator numerów zamówień działający w określonych schemacie