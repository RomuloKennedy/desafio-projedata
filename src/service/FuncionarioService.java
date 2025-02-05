package service;

import model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FuncionarioService {
    private List<Funcionario> funcionarios = new ArrayList<>();

    public void inserirFuncionarios(List<String> tabela){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (String dado : tabela) {
            String[] partes = dado.split(";");
            String nome = partes[0];
            LocalDate dataNascimento = LocalDate.parse(partes[1], formatter);
            BigDecimal salario = BigDecimal.valueOf(Double.parseDouble(partes[2]));
            String funcao = partes[3];

            // Criando e adicionando a pessoa na lista
            funcionarios.add(new Funcionario(nome, dataNascimento, salario, funcao));
        }
    }
    public void removerFuncionarioPorNome(String nome){
        funcionarios.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase(nome));
    }

    public void imprimirFuncionarios(){
        for (Funcionario funcionario: funcionarios){
            System.out.println("-----------------------------");
            System.out.println(funcionario.getNome());
            System.out.println(formatarSalario(funcionario.getSalario()));
            System.out.println(funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString().replace("-","/"));
            System.out.println(funcionario.getFuncao());
        }
    }
    private String formatarSalario(BigDecimal salario){
        DecimalFormat formato = new DecimalFormat("#,##0.00");
        formato.setDecimalSeparatorAlwaysShown(true);
        return formato.format(salario);
    }
    public void atualizarSalario(BigDecimal porcentagem){
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salarioAtual = funcionario.getSalario();
            BigDecimal aumento = salarioAtual.multiply(porcentagem).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            BigDecimal salarioNovo = salarioAtual.add(aumento).setScale(2, RoundingMode.HALF_UP);

            funcionario.setSalario(salarioNovo);
        }
    }
    public Map<String, List<Funcionario>> agruparFuncionariosPorFuncao(){
        return funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
    }
    public void imprimirFuncionariosAgrupadosPorFuncao(){
        System.out.println(agruparFuncionariosPorFuncao());
    }
    public void imprimirFuncionariosPorMesDeAniversario(List<Integer> meses){
        for(int mes : meses){
           for(Funcionario funcionario : funcionarios){
               if( funcionario.getDataNascimento().getMonth().getValue() == mes){
                   System.out.println(funcionario);
               }
           }
        }
    }
    public void imprimirFuncionarioMaiorIdade(){
        Funcionario funcionarioMaiorIdade = funcionarios.get(0);
        for(Funcionario funcionario: funcionarios){
            if(funcionario.getDataNascimento().isBefore(funcionarioMaiorIdade.getDataNascimento())){
                funcionarioMaiorIdade = funcionario;
            }
        }
        System.out.println("nome: "+funcionarioMaiorIdade.getNome()+"\nidade: "+calcularIdade(funcionarioMaiorIdade.getDataNascimento()));
    }
    private int calcularIdade(LocalDate dataNascimento){
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public void imprimirOrdenadosPorNome() {
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);
    }

    public void imprimirTotalSalarioFuncionarios(){
        BigDecimal salarioTotal = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios){
            salarioTotal = salarioTotal.add(funcionario.getSalario());
        }
        System.out.println("O Salário total dos funcionários é: "+salarioTotal);
    }

    public void imprimirSalarioMInimoPorFuncionario(){
        BigDecimal salarioMinimo = new BigDecimal("1212.00"); // Define como BigDecimal diretamente

        for (Funcionario funcionario : funcionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println("O funcionário "+funcionario.getNome()+" Ganha "+salariosMinimos+" salários mínimos");
        }
    }
}
