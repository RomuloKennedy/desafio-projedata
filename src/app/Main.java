package app;

import service.FuncionarioService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        FuncionarioService service = new FuncionarioService();
        // Tabela de funcionários
        List<String> tabelaDeFuncionarios = Arrays.asList(
                "Maria;18/10/2000;2009.44;Operador",
                "João;12/05/1990;2284.38;Operador",
                "Caio;02/05/1961;9836.14;Coordenador",
                "Miguel;14/10/1988;19119.88;Diretor",
                "Alice;05/01/1995;2234.68;Recepcionista",
                "Heitor;19/11/1999;1582.72;Operador",
                "Arthur;31/03/1993;4071.84;Contador",
                "Laura;08/07/1994;3017.45;Gerente",
                "Heloísa;24/05/2003;1606.85;Eletricista",
                "Helena;02/09/1996;2799.93;Gerente"
        );

        // 3.1 Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        service.inserirFuncionarios(tabelaDeFuncionarios);

        // 3.2 Remover o funcionário “João” da lista.
        service.removerFuncionarioPorNome("João");

        // 3.3 Imprimir todos os funcionários com todas suas informações, sendo que:
        /*
         * informação de data deve ser exibido no formato dd/mm/aaaa;
         * informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
         * */
        service.imprimirFuncionarios();

        // 3.4 Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        service.atualizarSalario(BigDecimal.valueOf(10.0));

        // 3.5 Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        service.agruparFuncionariosPorFuncao();

        // 3.6 Imprimir os funcionários, agrupados por função.
        service.imprimirFuncionariosAgrupadosPorFuncao();

        // 3.8 Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        service.imprimirFuncionariosPorMesDeAniversario(List.of(10,12));

        // 3.9 Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        service.imprimirFuncionarioMaiorIdade();

        // 3.10 Imprimir a lista de funcionários por ordem alfabética.
        service.imprimirOrdenadosPorNome();

        // 3.11 Imprimir o total dos salários dos funcionários..
        service.imprimirTotalSalarioFuncionarios();

        // 3.12 Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        service.imprimirSalarioMInimoPorFuncionario();
    }
}
