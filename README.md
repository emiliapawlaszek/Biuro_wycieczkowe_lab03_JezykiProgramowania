# Biuro_wycieczkowe_lab03_JezykiProgramowania

Na trzecich zajęciach należy zbudować aplikację użytkową przetwarzającą dane, pozwalającą na interakcje z użytkownikiem z poziomu konsoli. W aplikacji powinny istnieć klasy odpowiedzialne za przetwarzanie danych i klasy odpowiedzialne za wyświetlanie komunikatów i pobieranie danych od użytkownika (logika biznesowa powinna być oddzielona od interfejsu użytkownika by dało się bez problemu podmienić interfejs tekstowy na interfejs graficzny). Utrwalanie danych powinno być zrealizowane z wykorzystaniem plików. W aplikacji będzie trzeba obsłużyć wyjątki (pojawiające się podczas operacji na strumieniach, opcjonalnie - pojawiające się podczas operacji na strukturach danych) oraz zaprojektować odpowiednie struktury danych (można wykorzystać kolekcje i mapy).

Zadanie polega na zaprojektowaniu aplikacji pozwalającej obsłużyć klientów biura wycieczkowego. Zakładamy, że w aplikacji wykorzystane zostaną dostępne struktury danych i pliki (choć jest to przypadek nadający się do implementacji z wykorzystaniem baz danych, to jednak użycie baz danych wychodzi poza zakres laboratorium).
Aplikacja powinna obsłużyć następujące zestawy danych:
- dane osobowe klientów (wystarczy imię i nazwisko),
- oferty wycieczek (termin od do, opis, kierunek, liczba miejsc),
- rezerwacje (kto dokonał rezerwacji, na jaką wycieczką, kto w wycieczce uczestniczy, termin płatności, status rezerwacji).
Aplikacja powinna umożliwiać:
- zarządzanie ofertami (stworzenie, aktualizacja, usunięcie),
- przeszukiwanie ofert (ze względu na termin, kierunek, liczbę miejsc),
- zarządzanie rezerwacjami (założenie, opłacenie, anulowanie),
- generowanie zestawień (aktywność uczestników w zadanym okresie, najpopularniejsze wycieczki na podstawie liczby wykupionych miejsc),
- zmianę "bieżącego czasu" (chodzi o to, by dało się zasymulować sytuację przedawnienia rezerwacji).
Pozostałe szczegóły mają być zgodne z ustaleniami poczynionymi na początku zajęć.
