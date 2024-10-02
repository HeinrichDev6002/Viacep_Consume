import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class MyApiRequest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String cep;

        //Cria um novo HttpClient
        HttpClient client = HttpClient.newHttpClient();

        //Inicia um loop do-while até que um cep válido seja digitado
        do{
            System.out.println("Digite um cep: ");
            cep = scanner.nextLine();


            if(cep.length() != 8){
                System.out.println("Cep Inválido, o cep deve ter oito digitos");
            }
            if(!cep.matches("\\d+")){
                System.out.println("Cep Inválido, digite apenas números");
            }

        } while (cep.length() != 8 || !cep.matches("\\d+"));

        //Requisição
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

    }
}