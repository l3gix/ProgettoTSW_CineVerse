<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestione Film Admin</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style/gestionefilm.css">
</head>
<body>
<div class="admin-menu">
        <button onclick="showSection('inser-film')">InserisciFilm</button>
        <button onclick="showSection('modifica-status')">Modifica status film</button>
        <button onclick="showSection('insert-proiezioni')">Nuova Proiezione</button>
    </div>
    <section class="insert-film section" id="inser-film">
        <h1>Inserisci un nuovo film</h1>

        <form 
      action="<%= request.getContextPath() %>/admin/InserimentoFilm"
      method="post"
      enctype="multipart/form-data">

         <div class="form-group">
        <label for="titolo">Titolo</label>
        <input type="text" name="titolo" id="titolo" placeholder="Inserisci il titolo" required>
        </div>

    <div class="form-group">
        <label for="sinossi">Sinossi</label>
        <textarea name="sinossi" id="sinossi" placeholder="Inserisci una descrizione"></textarea>
    </div>
    
     <div class="form-group">
        <label for="sinossi">Cast</label>
        <textarea name="cast" id="cast" placeholder="Inserisci il cast"></textarea>
    </div>

    <div class="form-group">
    <label for="ageRating">Classificazione età</label>
    <select name="ageRating" id="ageRating" required>
        <option value="">Seleziona classificazione</option>
        <option value="T">Per tutti</option>
        <option value="VM6">VM6</option>
        <option value="VM10">VM10</option>
        <option value="VM14">VM14</option>
        <option value="VM18">VM18</option>
    </select>
</div>

     <div class="form-group">
    <label for="durataMinuti">Durata in minuti</label>
    <input type="number"
           name="durataMinuti"
           id="durataMinuti"
           placeholder="Es. 120"
           required>
    </div>

<!-- devi mettere i generi pero dal jsp -->


    <div class="form-group">
    <label for="dataRilascio">Data rilascio</label>
    <input type="date" 
           name="dataRilascio" 
           id="dataRilascio" 
           required>
</div>

    <div class="form-group">
        <label for="trailer">Link trailer</label>
        <input type="text" name="trailer" id="trailer" placeholder="Inserisci il url del trailer" required>
        </div>

    <div class="form-group">
    <label for="status">Classificazione età</label>
    <select name="status" id="status" required>
        <option value="coming_soon">coming_soon</option>
        <option value="now_showing">now_showing</option>
        <option value="archived">archived</option>
    </select>

    <div class="form-group">
        <label for="img1">Immagine Poster</label>
        <input type="file" name="img1" id="img1" accept="image/*" required>
    </div>

    <div class="form-group">
        <label for="img2">Immagine Banner</label>
        <input type="file" name="img2" id="img2" accept="image/*" required>
    </div>

    <button type="submit">Inserisci film</button>
    </form>
    </section>

    <section class="modifica-status section" id="modifica-status">
        <h1>Gestione film</h1>

<table class="film-table">
    <thead>
        <tr>
            <th>Titolo</th>
            <th>Durata</th>
            <th>Stato</th>
            <th>Azione</th>
        </tr>
    </thead>

    <tbody>
           
            <tr>
                <td>Interstellar</td>
                <td>169 min</td>
                <td>active</td>

                <td>
                    <form action="<%= request.getContextPath() %>/CambiaStatoFilm" method="post">
                        <input type="hidden" name="idFilm" >

                       
                            <input type="hidden" name="nuovoStatus" value="active">
                            <button type="submit" class="btn-active">now_showing</button>
                       
                            <input type="hidden" name="nuovoStatus" value="archived">
                            <button type="submit" class="btn-archive">archived</button>
                        
                    </form>
                </td>
            </tr>
    </tbody>
</table>
    </section>

    <section class="insert-proiezioni section" id="insert-proiezioni">
          <h1>Inserisci nuovo spettacolo</h1>

    <form  action="InserisciProiezione" method="post">

        <div class="form-group">
            <label for="id_film">Film</label>
            <select name="id_film" id="id_film" required>
                <option value="">Seleziona film</option>
                <option value="1">Film 1</option>
                <option value="2">Film 2</option>
                <option value="3">Film 3</option>
            </select>
        </div>

        <div class="form-group">
            <label for="id_sale">Sala</label>
            <select name="id_sale" id="id_sale" required>
                <option value="">Seleziona sala</option>
                <option value="1">Sala 1</option>
                <option value="2">Sala 2</option>
                <option value="3">Sala 3</option>
            </select>
        </div>

        <div class="form-group">
            <label for="id_formato">Formato</label>
            <select name="id_formato" id="id_formato" required>
                <option value="">Seleziona formato</option>
                <option value="1">2D</option>
                <option value="2">3D</option>
                <option value="3">IMAX</option>
            </select>
        </div>

        <div class="form-group">
            <label for="starts">Inizio spettacolo</label>
            <input type="datetime-local" name="starts" id="starts" required>
        </div>

        <div class="form-group">
            <label for="ends">Fine spettacolo</label>
            <input type="datetime-local" name="ends" id="ends" required>
        </div>

        <div class="form-group">
            <label for="prezzo_base">Prezzo base</label>
            <input type="number" 
                   name="prezzo_base" 
                   id="prezzo_base" 
                   step="0.01" 
                   min="0" 
                   placeholder="Es. 9.00" 
                   required>
        </div>

        <div class="form-group">
            <label for="status">Stato</label>
            <select name="status" id="status" required>
                <option value="">Seleziona stato</option>
                <option value="scheduled">Scheduled</option>
                <option value="cancelled">Non scheduled</option>
            </select>
        </div>

        <button type="submit">Inserisci spettacolo</button>

    </form>  

    </section>

       <script src="<%= request.getContextPath() %>/script/gestionefilm.js"></script>
</body>
</html>