package service;

import model.Pessoa;

import java.time.LocalDate;

public class CadastroService {

    /**
     * Ao realizar o cadastro, a senha deve:
     *  - ter o minimo de 6 caracateres e o máximo de 12.
     *  - conter letras.
     *  - conter numeros.
     *
     * @param senha A senha que a pessoa está tentando cadastrar
     * @return retorna True se a senha atender as condições citadas e False caso não atenda.
     */
    public Boolean verificarSeASenhaEValida(String senha){

        Boolean status = Boolean.FALSE;

        if(senha.length() < 6 || senha.length() > 12)
            return Boolean.FALSE;

        for(int i = 0 ; i < senha.length() ; i++){
            if(Character.isAlphabetic(senha.charAt(i))){
                status = Boolean.TRUE;
                break;
            }
        }

        for(int i = 0 ; i < senha.length() ; i++){
            if(Character.isDigit(senha.charAt(i))){
                status = Boolean.TRUE;
                break;
            }else{
                status = Boolean.FALSE;
            }
        }

        return status;

    }


    /**
     * Ao realizar o cadastro, a pessoa deve ser maior de 18 anos.
     *
     * @param dataDeNascimento A data de nascimento da pessoa que está sendo cadastrada
     * @return retorna True se a pessoa for maior de 18 anos e False se a pessoa for menor de 18 anos.
     */
    public Boolean verificarSeEMaiorDeDezoitoAnos(LocalDate dataDeNascimento){

        LocalDate dataAtual = LocalDate.now();

        Integer idade = dataAtual.getYear() - dataDeNascimento.getYear();

        if(dataDeNascimento.getDayOfYear() > dataAtual.getDayOfYear())
        {
            idade--;
        }

        return idade >= 18;
    }

    /**
     * Para ser gerada uma chave para o usuário trocar de senha, deve haver uma combinação entre o nome e o sobrenome:
     * - a chave deve ser composta com uma letra do nome seguida de uma letra do sobrenome.
     * - caso uma palavra seja maior que a outra, a palavra chave deve ser completada com o restante da maior palavra, por exemplo:
     * - nome: maria
     * - sobrenome: cristina
     * - resultado: mcarriisatina
     *
     * @param pessoa A pessoa a quem está sendo gerada a chave.
     * @return retorna a String gerada a partir do nome e sobrenome.
     */
    public String gerarPalavraChaveParaTrocaDeSenha(Pessoa pessoa){

        Integer tamanhoDaMaiorPalavra = 0;
        StringBuilder palavraChave = new StringBuilder();

        if(pessoa.getSobrenome().length() > pessoa.getNome().length()){
            tamanhoDaMaiorPalavra = pessoa.getSobrenome().length();
        }else{
            tamanhoDaMaiorPalavra = pessoa.getNome().length();
        }

        for(Integer i = 0 ; i < tamanhoDaMaiorPalavra; i++){

            if(pessoa.getNome().length() > i){
                palavraChave.append(pessoa.getNome().charAt(i));
            }

            if(pessoa.getSobrenome().length() > i){
                palavraChave.append(pessoa.getSobrenome().charAt(i));
            }

        }

        return palavraChave.toString();
    }

    public String realizarCadastroDePessoa(Pessoa pessoa){

        if(!verificarSeASenhaEValida(pessoa.getSenha())){
            return "Senha inválida.";
        }
        if(!verificarSeEMaiorDeDezoitoAnos(pessoa.getDataDeNascimento())){
            return "Falha ao cadastrar, pessoa menor de 18 anos.";
        }

        return "Cadastro realizado com sucesso.";
    }

}
