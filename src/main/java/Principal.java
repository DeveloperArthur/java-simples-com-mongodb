import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Arrays;
import java.util.Date;


public class Principal {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> alunos = database.getCollection("alunos");

        //buscando o primeiro aluno da lista
        Document aluno = alunos.find().first();

        //cadastrando novo aluno
        Document novoAluno = new Document("nome", "Arthur")
                .append("data_nascimento", new Date(1998, 11, 22))
                .append("curso", new Document("nome", "História"))
                .append("notas", Arrays.asList(10, 9, 8))
                .append("habilidades", Arrays.asList(new Document()
                                .append("nome", "Inglês")
                                .append("nível", "Básico"),
                        new Document().append("nome", "Espanhol")
                                .append("nível", "Básico")));
        alunos.insertOne(novoAluno);

        //atualizando aluno
        alunos.updateOne(Filters.eq("nome", "Arthur"),
                new Document("$set", new Document("nome", "Arthur Almeida")));

        //deletando aluno
        alunos.deleteOne(Filters.eq("nome", "Arthur Almeida"));

        mongoClient.close();
    }
}
