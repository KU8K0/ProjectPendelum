# Project PENDELUM

## Popis hry

Project PENDELUM je textová adventura vytvořená v programovacím jazyce Java. Hráč se pohybuje futuristickým městem, prozkoumává různé lokace, sbírá předměty a komunikuje s NPC postavami. Cílem hry je postupně odemykat nové oblasti města, řešit různé situace a dostat se do finální lokace.

Hra obsahuje také minihry, například hackování zabezpečených systémů nebo stealth průchod hlídanými oblastmi.

---

## Ovládání hry

Hra se ovládá pomocí textových příkazů zadávaných do konzole.

### Základní příkazy

- `jdi <lokace>`  
  Přesune hráče do jiné lokace, pokud je dostupná.

- `rozhlidni`  
  Zobrazí popis aktuální lokace, předměty a NPC postavy.

- `seber <predmet>`  
  Sebere předmět z aktuální lokace a přidá ho do inventáře.

- `inventar`  
  Zobrazí obsah hráčova inventáře.

- `mluv <npc>`  
  Zahájí dialog s NPC postavou v aktuální lokaci.

- `hack`  
  Spustí hackovací minihru, ve které musí hráč uhodnout správný kód.

- `stealth`  
  Spustí stealth minihru, kde se hráč snaží nepozorovaně projít hlídanou oblastí.

- `pomoc`  
  Zobrazí seznam dostupných příkazů.

- `konec`  
  Ukončí hru.

---

## Herní mechaniky

Hra obsahuje několik základních mechanik:

- **Pohyb mezi lokacemi** – hráč se může pohybovat po mapě města mezi propojenými lokacemi.
- **Inventář** – hráč může sbírat předměty, které mohou být potřebné pro postup ve hře.
- **NPC dialogy** – některé postavy poskytují informace nebo pomáhají s postupem ve hře.
- **Hackovací minihra** – hráč musí uhodnout správný kód k odemčení systému.
- **Stealth minihra** – hráč se musí vyhnout detekci stráží při průchodu hlídanou oblastí.