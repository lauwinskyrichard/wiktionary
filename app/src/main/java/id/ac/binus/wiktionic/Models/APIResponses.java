package id.ac.binus.wiktionic.Models;

import java.util.ArrayList;
import java.util.List;

public class APIResponses {
    String words = "";
    List<Phonetics> phonetics = null;
    List<Meanings> meanings = null;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public List<Phonetics> getPhonetics() {
        return phonetics;
    }

    public void setPhonetics(List<Phonetics> phonetics) {
        this.phonetics = phonetics;
    }

    public List<Meanings> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<Meanings> meanings) {
        this.meanings = meanings;
    }
}
