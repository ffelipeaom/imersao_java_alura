import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        //fazer conexão HTTP e buscar os top 250 filmes

        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        
        //String url = "https://api.nasa.gov/planetary/apod?api_key=W2WeehM4tDC40WbhR3tcJyfSc3dhjNwb7o9Sx3Ug&start_date=2022-06-12&end_date=2022-06-14";
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        //String url = "http://localhost:8080/linguagens";
        String url = "https://alura-linguagens-api.herokuapp.com/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        //exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradorDeFigurinhas();

        for (int i = 0; i < 3; i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";
            
            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            //System.out.println(filme.get("image"));
            //System.out.println(filme.get("imDbRating"));
            System.out.println();
        }
    }
}