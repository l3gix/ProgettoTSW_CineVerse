# 🎬 CineVerse

**CineVerse** è una web application per la gestione e la prenotazione di spettacoli cinematografici, sviluppata come progetto per il corso di **Tecnologie Software per il Web**.

L’applicazione permette agli utenti di consultare i film disponibili, visualizzare le relative proiezioni, scegliere i posti in sala, gestire il carrello e completare una prenotazione. È inoltre presente un’area amministrativa dedicata alla gestione di film, sale, proiezioni e utenti.

## Autori

* **Giovanni Latella**
* **Michele Milione**

---

## Funzionalità principali

### Area pubblica

* visualizzazione dei film attualmente in programmazione;
* visualizzazione dei film in prossima uscita;
* ricerca dinamica dei film;
* consultazione della pagina di dettaglio di un film;
* visualizzazione di date, orari, formati e sale disponibili;
* registrazione di un nuovo account;
* autenticazione e logout.

### Area utente

* visualizzazione e modifica del profilo;
* scelta della proiezione;
* selezione dei posti tramite la piantina della sala;
* aggiunta e rimozione dei biglietti dal carrello;
* gestione del carrello;
* pagamento e conferma della prenotazione;
* consultazione dei biglietti acquistati.

### Area amministrativa

* inserimento e gestione dei film;
* inserimento e gestione delle proiezioni;
* modifica dello stato delle proiezioni;
* gestione delle sale cinematografiche;
* consultazione e gestione degli utenti;
* accesso protetto tramite controllo del ruolo.

---

## Tecnologie utilizzate

### Backend

* Java
* Jakarta Servlet
* JSP
* JDBC
* JavaBean
* DAO Pattern
* Apache Tomcat

### Frontend

* HTML5
* CSS3
* JavaScript
* AJAX
* design responsive e mobile-first

### Database

* MySQL
* DataSource configurato tramite JNDI

### Strumenti di sviluppo

* Eclipse IDE
* Git
* GitHub

---

## Architettura

Il progetto segue una struttura ispirata al pattern **MVC**:

* le **Servlet** gestiscono le richieste HTTP e il flusso di navigazione;
* le pagine **JSP** costituiscono il livello di presentazione;
* i **JavaBean** rappresentano le entità del dominio;
* i **DAO** gestiscono l’accesso e le operazioni sul database;
* i **Filter** controllano l’accesso alle risorse protette;
* JavaScript e AJAX permettono di aggiornare alcune sezioni senza ricaricare completamente la pagina.

---

## Struttura del progetto

```text
ProgettoTSW_CineVerse/
├── src/main/java/it/unisa/cineverse/
│   ├── controller/
│   │   ├── admin/          # Servlet riservate agli amministratori
│   │   └── common/         # Servlet riservate agli utenti autenticati
│   ├── filter/             # Filtri di autenticazione e autorizzazione
│   ├── model/
│   │   ├── bean/           # JavaBean delle entità
│   │   ├── dao/            # Interfacce DAO
│   │   └── dao/impl/       # Implementazioni JDBC dei DAO
│   └── util/               # Classi di utilità
│
├── WebContent/
│   ├── WEB-INF/
│   │   ├── views/          # Pagine JSP non accessibili direttamente
│   │   └── web.xml         # Configurazione dell’applicazione
│   ├── img/                # Immagini, loghi e poster
│   ├── script/             # File JavaScript
│   └── style/              # Fogli di stile CSS
│
├── .classpath
├── .project
└── README.md
```

---

## Requisiti

Per eseguire il progetto sono necessari:

* un JDK compatibile con Jakarta Servlet;
* Apache Tomcat 10 o versione compatibile;
* MySQL Server;
* MySQL Connector/J;
* Eclipse IDE for Enterprise Java and Web Developers, oppure un IDE equivalente;
* un database denominato `cineverse`.

---

## Installazione

### 1. Clonare la repository

```bash
git clone https://github.com/l3gix/ProgettoTSW_CineVerse.git
```

Entrare nella cartella del progetto:

```bash
cd ProgettoTSW_CineVerse
```

### 2. Importare il progetto in Eclipse

1. Aprire Eclipse.
2. Selezionare **File → Import**.
3. Scegliere **Existing Projects into Workspace**.
4. Selezionare la cartella della repository.
5. Confermare l’importazione.
6. Associare al progetto un runtime Apache Tomcat compatibile.

### 3. Configurare il database

Creare il database:

```sql
CREATE DATABASE cineverse;
```

Importare successivamente lo schema e i dati necessari al funzionamento dell’applicazione.

La configurazione utilizza una risorsa JNDI con nome:

```text
jdbc/cineverse
```

Esempio di configurazione del `DataSource` nel file `context.xml` di Tomcat:

```xml
<Context>
    <Resource
        name="jdbc/cineverse"
        auth="Container"
        type="javax.sql.DataSource"
        driverClassName="com.mysql.cj.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/cineverse?useSSL=false&amp;serverTimezone=Europe/Rome"
        username="root"
        password="PASSWORD_DATABASE"
        maxTotal="20"
        maxIdle="10"
        maxWaitMillis="-1"
    />
</Context>
```

Sostituire:

* `root` con il proprio username MySQL;
* `PASSWORD_DATABASE` con la propria password MySQL.

### 4. Configurare Tomcat

1. Aggiungere Apache Tomcat nella sezione **Servers** di Eclipse.
2. Associare CineVerse al server.
3. Verificare che MySQL sia avviato.
4. Avviare Tomcat.

### 5. Aprire l’applicazione

In base al context path configurato, l’applicazione sarà disponibile a un indirizzo simile al seguente:

```text
http://localhost:8080/ProgettoTSW_CineVerse/
```

La pagina iniziale viene gestita dalla servlet:

```text
/WelcomeIndex
```

---

## Flusso principale di prenotazione

```text
Catalogo film
    ↓
Dettaglio film
    ↓
Scelta della proiezione
    ↓
Selezione dei posti
    ↓
Carrello
    ↓
Pagamento
    ↓
Conferma e visualizzazione dei biglietti
```

---

## Sicurezza

L’applicazione utilizza:

* sessioni HTTP per mantenere lo stato dell’utente;
* filtri per proteggere le risorse riservate;
* controllo dei ruoli per distinguere utenti e amministratori;
* query parametrizzate tramite `PreparedStatement`;
* validazione dei dati lato client e lato server;
* gestione delle pagine JSP protette all’interno di `WEB-INF`.

Le credenziali reali del database non devono essere inserite nella repository.

---

## Principali entità

Tra le principali entità gestite dal sistema sono presenti:

* utente;
* film;
* genere;
* poster;
* sala;
* posto;
* categoria del posto;
* formato del film;
* proiezione;
* biglietto;
* prenotazione;
* pagamento.


---

## Contributori

<a href="https://github.com/l3gix">
  <img src="https://img.shields.io/badge/GitHub-l3gix-181717?logo=github" alt="Profilo GitHub l3gix">
</a>

---

## Licenza

Questo progetto è stato realizzato esclusivamente a scopo didattico.

Salvo diversa indicazione, il codice non è distribuito con una licenza open source esplicita.
::: 

