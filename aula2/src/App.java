import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {


    public static void main(String[] args) throws Exception {
        //System.out.println("Hello, World!");

        //fazer conexão HTTP e buscar os top 250 filmes
        String urlTopMovies = "https://alura-imdb-api.herokuapp.com/movies";
        /*
        String urlMostPopMovies = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/MostPopularMovies.json";
        String topMoviesBody = doRequest(urlTopMovies);
        String mostPopMoviesBody = doRequest(urlMostPopMovies);
        */
        URI endereco = URI.create(urlTopMovies);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        //parsear: extrair os dados que interessam (título, imagem, nota)
        /*
        List<Map<String, String>> listTopMovies = parseJsonToString(topMoviesBody);
        List<Map<String, String>> listMostPopMovies = parseJsonToString(mostPopMoviesBody);
        printImDbList("IMDB Top Movies List", listTopMovies);
        printImDbList("IMDB Most Popular Movies", listMostPopMovies);
        */

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //exibir e manipular os dados
        var geradora = new GeradorDeFigurinhas();
        for (Map<String,String> filme : listaDeFilmes) {
            
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo +".png";
            
            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            //System.out.println(filme.get("image"));
            //System.out.println(filme.get("imDbRating"));
            //System.out.println();
        }
    }
}