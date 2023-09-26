package com.bookapp.backend.books.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String titolo;
    private String autore;
    private Long codiceISBN;
    private String trama;
    private int numeroLetture;

    private LocalDateTime dataAggiunta;
    private LocalDateTime dataEliminazione;
    private String utenteAggiunta;
    private String utenteEliminazione;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public Long getCodiceISBN() {
        return codiceISBN;
    }

    public void setCodiceISBN(Long codiceISBN) {
        this.codiceISBN = codiceISBN;
    }

    public String getTrama() {
        return trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public int getNumeroLetture() {
        return numeroLetture;
    }

    public void setNumeroLetture(int numeroLetture) {
        this.numeroLetture = numeroLetture;
    }

    public LocalDateTime getDataAggiunta() {
        return dataAggiunta;
    }

    public void setDataAggiunta(LocalDateTime dataAggiunta) {
        this.dataAggiunta = dataAggiunta;
    }

    public LocalDateTime getDataEliminazione() {
        return dataEliminazione;
    }

    public void setDataEliminazione(LocalDateTime dataEliminazione) {
        this.dataEliminazione = dataEliminazione;
    }

    public String getUtenteAggiunta() {
        return utenteAggiunta;
    }

    public void setUtenteAggiunta(String utenteAggiunta) {
        this.utenteAggiunta = utenteAggiunta;
    }

    public String getUtenteEliminazione() {
        return utenteEliminazione;
    }

    public void setUtenteEliminazione(String utenteEliminazione) {
        this.utenteEliminazione = utenteEliminazione;
    }
}
