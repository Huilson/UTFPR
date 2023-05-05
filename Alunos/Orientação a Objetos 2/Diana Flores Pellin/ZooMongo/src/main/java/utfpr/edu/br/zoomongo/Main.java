package utfpr.edu.br.zoomongo;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Conexão com o Mongo
//        MongoClient client = new MongoClient();
        MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017");
        MongoClient mongoClient = new MongoClient(uri);

        //Conexão com a DB
        MongoDatabase db = mongoClient.getDatabase("Zoo");

        //conectar a coleção
        MongoCollection<Document> animais = db.getCollection("animais");
        MongoCollection<Document> funcionarios = db.getCollection("funcionarios");
        MongoCollection<Document> servicos = db.getCollection("servicos");

        //Valida se foi encontrado algum registro existente para ser editado
        UpdateOptions opcoes = new UpdateOptions().upsert(false); //O upsert indica que não será criado um novo documento caso não exista um para ser editado

        //Menu
        Scanner scan = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("=============Sistema de Zoo UltraModerno=============");
            System.out.println("Selecione a opção desejada: ");
            System.out.println("1 - Cadastrar Funcionário");
            System.out.println("2 - Cadastrar Animal");
            System.out.println("3 - Cadastrar Serviço");
            System.out.println("4 - Exibir Lista de Serviço Diária");
            System.out.println("5 - Atualizar Funcionário");
            System.out.println("6 - Atualizar Animal");
            System.out.println("7 - Excluir Funcionário");
            System.out.println("8 - Excluir Animal");
            System.out.println("9 - Excluir Serviço");
            System.out.println("0 - Sair");
            System.out.println("");
            opcao = scan.nextInt();

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    String nome_funcionario;
                    String cpf;
                    String endereco;
                    BigDecimal salario;

                    System.out.println("=============Sistema de Zoo UltraModerno : Cadastro de Funcionario=============");
                    System.out.println("Nome do Funcionário: ");
                    nome_funcionario = scan.next();
                    System.out.println("CPF: ");
                    cpf = scan.next();
                    System.out.println("Endereço: ");
                    endereco = scan.next();
                    System.out.println("Salário: ");
                    scan.nextLine();
                    salario = scan.nextBigDecimal();

                    Document novoFuncionario = new Document("nome", nome_funcionario)
                            .append("cpf", cpf)
                            .append("endereco", endereco)
                            .append("salario", salario);

                    funcionarios.insertOne(novoFuncionario);
                    break;

                case 2:
                    String nome_animal;
                    String especie;

                    //Descrição
                    String nome_cientifico;
                    String familia;
                    String comportamentos;

                    String nome_responsavel;

                    System.out.println("=============Sistema de Zoo UltraModerno : Cadastro de Animal=============");
                    System.out.println("Nome do Animal: ");
                    nome_animal = scan.next();
                    System.out.println("Espécie do Animal: ");
                    especie = scan.next();

                    System.out.println("Descrição do Animal: ");
                    System.out.println("Digite o nome científico: ");
                    nome_cientifico = scan.next();
                    System.out.println("Imforme a familia: ");
                    familia = scan.next();
                    System.out.println("Digite os comportamentos característicos da espécie: ");
                    comportamentos = scan.next();

                    System.out.println("Defina um responsável: ");
                    scan.nextLine();
                    nome_responsavel = scan.next();

                    if (nome_responsavel == "") {
                        System.out.println("Favor digite um responsável válido: ");
                        nome_responsavel = scan.next();
                    }

                    Document novoAnimal = new Document("especie", especie)
                            .append("nomeAnimal", nome_animal)
                            .append("descricao", new Document("nomeCientifico", nome_cientifico)
                                    .append("familia", familia)
                                    .append("comportamentos", comportamentos))
                            .append("nomeResponsavel", nome_responsavel);

                    animais.insertOne(novoAnimal);
                    break;

                case 3:
                    String descricao_servico;
                    String data;
                    String nome_animal_servico;
                    String nome_responsavel_servico;

                    System.out.println("=============Sistema de Zoo UltraModerno : Cadastro de Serviço=============");
                    System.out.println("Descreva o serviço: ");
                    descricao_servico = scan.next();
                    System.out.println("Defina uma data: ");
                    scan.nextLine();
                    data = scan.next();
                    System.out.println("Informe o animal: ");
                    nome_animal_servico = scan.next();
                    System.out.println("Defina um responsável: ");
                    nome_responsavel_servico = scan.next();

                    if (nome_responsavel_servico == "") {
                        System.out.println("Favor digite um responsável válido: ");
                        nome_responsavel_servico = scan.next();
                    }

                    Document novoServico = new Document("descricao", descricao_servico)
                            .append("data", data)
                            .append("nomeAnimal", nome_animal_servico)
                            .append("nomeResponsavel", nome_responsavel_servico);

                    servicos.insertOne(novoServico);
                    break;
                case 4:
                    MongoCursor<Document> lista_servicos = servicos.find(Filters.eq("data", "05/05/2023")).iterator();
                    int i = 1;
                    while (lista_servicos.hasNext()) {
                        System.out.println("Serviço " + i + ": " + lista_servicos.next());
                        i++;
                    }
                    break;
                case 5:
                    System.out.println("=============Digite o CPF do Funcionário a ser editado =============");
                    System.out.println("CPF: ");
                    cpf = scan.next();
                    //Filtra o funcionario por CPF para que possa ser feita a edição
                    Bson filtro = Filters.eq("cpf", cpf);

                    if (cpf != null) {
                        System.out.println("=============Sistema de Zoo UltraModerno : Atualizar Funcionário=============");
                        System.out.println("Nome do Funcionário: ");
                        nome_funcionario = scan.next();
                        System.out.println("Endereço: ");
                        endereco = scan.next();
                        System.out.println("Salário: ");
                        salario = scan.nextBigDecimal();

                        //Validação dos campos que vão ser atualizados
                        Document campos_atualizados = new Document();
                        if (nome_funcionario != null) {
                            campos_atualizados.append("nome", nome_funcionario);
                        }
                        if (endereco != null) {
                            campos_atualizados.append("endereco", endereco);
                        }
                        if (salario != null) {
                            campos_atualizados.append("salario", salario);
                        }


                        //Atualiza o registro de funcionário
                        UpdateResult resultado = funcionarios.updateOne(filtro, new Document("$set", campos_atualizados), opcoes);

                        //Verificação do resultado da atualização
                        if (resultado.getModifiedCount() > 0) {
                            System.out.println("Funcionário atualizado com sucesso!");
                        } else {
                            System.out.println("Não foi possível atualizar o registro de funcionário.");
                        }
                    }
                    break;
                case 6:
                    System.out.println("=============Digite o nome do Animal a ser editado =============");
                    System.out.println("Nome do animal: ");
                    nome_animal = scan.next();

                    //Filtra o Animal por nome
                    Bson filtro_atualiza_animal = Filters.eq("nomeAnimal", nome_animal);

                    if (nome_animal != null) {
                        System.out.println("=============Sistema de Zoo UltraModerno : Atualizar Animal=============");
                        System.out.println("Nome do Animal: ");
                        nome_animal = scan.next();
                        System.out.println("Espécie do Animal: ");
                        especie = scan.next();

                        System.out.println("Descrição do Animal: ");
                        System.out.println("Digite o nome científico: ");
                        nome_cientifico = scan.next();
                        System.out.println("Imforme a familia: ");
                        familia = scan.next();
                        System.out.println("Digite os comportamentos característicos da espécie: ");
                        comportamentos = scan.next();

                        System.out.println("Defina um responsável: ");
                        scan.nextLine();
                        nome_responsavel = scan.next();

                        //Validação dos campos que vão ser atualizados
                        Document campos_atualizados_animal = new Document();
                        if (nome_animal != null) {
                            campos_atualizados_animal.append("nome", nome_animal);
                        }
                        if (especie != null) {
                            campos_atualizados_animal.append("especie", especie);
                        }
                        if (nome_cientifico != null) {
                            campos_atualizados_animal.append("descricao.$[elem].nomeCientifico", nome_cientifico);
                        }
                        if (familia != null) {
                            campos_atualizados_animal.append("descricao.$[elem].familia", familia);
                        }
                        if (familia != null) {
                            campos_atualizados_animal.append("descricao.$[elem].comportamentos", comportamentos);
                        }
                        if (nome_responsavel != null) {
                            campos_atualizados_animal.append("nomeResponsavel", nome_responsavel);
                        }

                        //Atualiza o registro de animais
                        UpdateResult resultado_att_animais = funcionarios.updateOne(filtro_atualiza_animal, new Document("$set", campos_atualizados_animal), opcoes);

                        //Verificação do resultado da atualização
                        if (resultado_att_animais.getModifiedCount() > 0) {
                            System.out.println("Animal atualizado com sucesso!");
                        } else {
                            System.out.println("Não foi possível atualizar o registro de animal.");
                        }
                    }
                    break;
                case 7:
                    System.out.println("=============Digite o CPF do funcionário a ser excluido=============");
                    System.out.println("CPF: ");
                    cpf = scan.next();

                    funcionarios.deleteOne(Filters.eq("cpf", cpf));

                    break;
                case 8:
                    System.out.println("=============Digite o nome do animal a ser excluido=============");
                    System.out.println("Nome do animal: ");
                    nome_animal = scan.next();

                    funcionarios.deleteOne(Filters.eq("nomeAnimal", nome_animal));
                    break;
                default:
                    System.out.println("Opção inválida!");
                    System.out.println("Digite novamente: ");
                    opcao = scan.nextInt();
            }
        }
        mongoClient.close();
    }
}
