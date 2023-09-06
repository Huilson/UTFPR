package br.edu.utfpr.jogadaforca.service;

import br.edu.utfpr.jogadaforca.model.JogoDaForca;

import java.io.IOException;
import java.text.Normalizer;
import java.util.Random;

public class Service {

    private final String[] Facil = {"alce","anta","asno","atum","boa","bode","boi","boto","degu","dodo","egua","ema","enho","foca","galo","gamo","gato","geco","gnu","grou","ibex","ibis","jacu","kea","kiwi","kudu","leao","lobo","lula","mico","mono","mula","naja","neon","onca","orca","orix","osga","pato","peru","prea","ra","raia","rato","rena","sapo","siri","tatu","unau","urso","uru","vaca","xaja","xaru","xero","xuri","","yak","zebu"};
    private final String[] Medio = {"abelha","abutre","acaro","aguia","albatroz","alpaca","anaconda","anchova","andorinha","antilope","aranha","arara","avestruz","babuino","bacalhau","bacuri","badejo","bagre","baiacu","baleia","barata","barbo","barracuda","beija-flor","besouro","bem-te-vi","bezerro","bisonte","borboleta","bufalo","burro","cabra","cachalote","cachorro","cagado","camaleao","camarao","camelo","camundongo","canario","canguru","capivara","caracol","caranguejo","carneiro","carrapato","cascavel","castor","cavalo","cegonha","centopeia","chimpanze","chinchila","chita","cigarra","cisne","coala","cobra","codorna","coelho","coiote","coruja","corvo","crocodilo","cupim","cutia","damao","dancador","degolado","diablotim","dingo","dinossauro","doninha","dourado","dromedario","dugongo","elefante","enchova","enferrujado","enguia","escaravelho","escorpiao","esmerilhao","espadarte","esponja","esquilo","estorninho","esturjao","faisao","falcao","ferreirinho","flamingo","flautim","formiga","fossa","freirinha","fuinha","furao","gafanhoto","gaivota","galinha","gamba","ganso","garca","garoupa","gaviao","gazela","gerbo","gibao","girafa","girino","golfinho","gorila","gralha","grifo","grilo","guara:","guaxinim","hadoque","hamster","harpia","hiena","hipopotamo","hirax","iaque","iguana","iguanara","impala","indicador","indri","inhacoso","inhala","inhambu","irapua","irara","iratim","itapema","jabiru","jabuti","jacana","jacare","jacupara","jaguar","jamanta","jararaca","javali","jegue","jiboia","joaninha","jumento","kakapo","kinguio","koala","kookaburra","kowari","krill","lacraia","lagarta","lagartixa","lagarto","lagosta","lampreia","lavadeira","lavagante","lebre","lemure","leopardo","lesma","lhama","libelula","lince","linguado","lombriga","lontra","macaco","mamute","manatim","mandril","manganga","maracana","marimbondo","mariposa","marisco","marmota","marreco","marta","massarongo","medusa","melro","mergulhao","merluza","mexilhao","milhafre","mineirinho","minhoca","mocho","morcego","moreia","morsa","mosca","mosquito","mucurana","musaranho","nambu","nandaia","narceja","narval","nautilo","negrinho","nhacunda","nhandia","niala","niquim","noitibo","numbat","nutria","ocapi","olho-de-boi","orangotango","ostra","otaria","ourico","ovelha","panda","pantera","papagaio","pardal","passaro","pavao","peixe","peixe-boi","pelicano","percevejo","perereca","periquito","pernilongo","pescada","pica-pau","pinguim","piolho","piranha","pirarucu","polvo","pombo","ponei","porco","preguica","pulga","quati","quatimirim","quatipuru","queixada","quero-quero","quetzal","quiriquiri","rabudinho","raposa","ratazana","remora","rendeira","rinoceronte","robalo","rouxinol","sabia","sagui","salamandra","salmao","sanguessuga","sardao","sardinha","sauva","seriema","serpente","sucuarana","sucuri","suiriri","suricate","surubi","surucucu","tainha","tamandua","tamboril","tapir","tarantula","tartaruga","tatui","tentilhao","tetra","texugo","tico-tico","tigre","tilapia","tordo","tororo","toupeira","touro","tritao","truta","tubarao","tucano","tucuxi","uacari","unha-longa","unicornio","urso panda","urubu","urutaurana","urutu","vaga-lume","veado","verdilhao","verdinho","vespa","vibora","vieira","vira-bosta","vison","vitela","viuva","viuva-negra","wallaby","weimaraner","wombat","xango","xarelete","xareu","xauim","xexeu","ximango","ximbure","xofrango","ynambu","yorkshire","zabele","zangao","zaragateiro","zarro","zebra","zidede","zombeteiro","zorrilho"};
    private final String[] Dificil = {"bicho-da-seda","cavalo-marinho","diabo-da-tasmania","diamante-de-gould","dragao-de-komodo","dragao-do-banhado","dragao-voador","elefante-marinho","escrevedeira","estrela-do-mar","fraca-da-guine","frango-dagua","joao-de-barro","leao-marinho","ornitorrinco","ourico-do-mar","porco-espinho","porquinho-da-india","quebra-nozes","quebra-ossos","quem-te-vestiu","vaca-marinha"};
    public Boolean checkLetra(String palavra, String letra) {
        letra = letra.toLowerCase();
        return palavra.contains(letra);
    }

    public Integer checkLetrasRestantes(JogoDaForca jogo, String letra) {
        String palavra = jogo.getPalavra();
        Integer qtd = jogo.getTentativasRestantes();
        for (int i=0; i<palavra.length(); i++) {
            char c = palavra.charAt(i);
            String s =String.valueOf(c);
            if (s.equalsIgnoreCase(letra)){
                qtd = qtd--;
            }
        }
        return qtd;
    }

    public String getPalavraByDificuldade(String dificuldade) throws IOException {
        Random random = new Random();
        if (dificuldade.equalsIgnoreCase("facil")){
            return Facil[random.nextInt(Facil.length)];
        } else if (dificuldade.equalsIgnoreCase("medio")){
            return Medio[random.nextInt(Medio.length)];
        } else {
            return Dificil[random.nextInt(Dificil.length)];
        }
    }
}
