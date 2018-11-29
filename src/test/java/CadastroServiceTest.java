import model.Pessoa;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.CadastroService;

import java.time.LocalDate;

public class CadastroServiceTest {

    private CadastroService cadastroService;

    @Before
    public void setUp(){
        this.cadastroService = new CadastroService();
    }

    @Test
    public void verificaSeQuandoEnviarUmaSenhaInvalidaRetornaFalse(){

        Pessoa pessoa = new Pessoa.Builder().comSenha("senhaInvalida").build();

        Assert.assertFalse(this.cadastroService.verificarSeASenhaEValida(pessoa.getSenha()));
    }

    @Test
    public void verificarSeQuandoEnviarUmaSenhaValidaRetornaTrue(){
        Pessoa pessoa = new Pessoa.Builder().comSenha("53nh4v4l1d4").build();

        Assert.assertTrue(this.cadastroService.verificarSeASenhaEValida(pessoa.getSenha()));
    }

    @Test
    public void verificarSeQuandoEnviarSenhaDeTamanhoErradoRetornaFalse(){
        Pessoa pessoa = new Pessoa.Builder().comSenha("s3nh").build();

        Assert.assertFalse(this.cadastroService.verificarSeASenhaEValida(pessoa.getSenha()));
    }

    @Test
    public void verificarSeQuandoAPessoaForMenorDeDezoitoAnosRetornaFalse(){

        Pessoa pessoa = new Pessoa.Builder().comDataDeNascimento(LocalDate.of(2001,8,20)).build();

        Assert.assertFalse(this.cadastroService.verificarSeEMaiorDeDezoitoAnos(pessoa.getDataDeNascimento()));
    }

    @Test
    public void verificarSeQuandoAPessoaForMaiorDeDezoitoAnosRetornaTrue(){

        Pessoa pessoa = new Pessoa.Builder().comDataDeNascimento(LocalDate.of(1996,8,20)).build();

        Assert.assertTrue(this.cadastroService.verificarSeEMaiorDeDezoitoAnos(pessoa.getDataDeNascimento()));
    }

    @Test
    public void verificarSeQuandoAPessoaNaoFezAniversarioEsseAnoRetornaFalse(){

        Pessoa pessoa = new Pessoa.Builder().comDataDeNascimento(LocalDate.of(2000,12,20)).build();

        Assert.assertFalse(this.cadastroService.verificarSeEMaiorDeDezoitoAnos(pessoa.getDataDeNascimento()));
    }

    @Test
    public void verificarSeAPalavraChaveGeradaEstaCorreta(){

        Pessoa pessoa = new Pessoa.Builder().comNome("joao").comSobrenome("pereira").build();

        Assert.assertEquals("jpoearoeira" , this.cadastroService.gerarPalavraChaveParaTrocaDeSenha(pessoa));
    }

    @Test
    public void verificarSeOCadastroFoiBemSucedido(){
        Pessoa pessoa = new Pessoa.Builder().comLogin("meuLogin")
                .comNome("meuNome")
                .comSenha("53nh4v4l1d4")
                .comDataDeNascimento(LocalDate.of(1996,8,20))
                .comSobrenome("meuSobrenome").build();

        Assert.assertEquals("Cadastro realizado com sucesso." , this.cadastroService.realizarCadastroDePessoa(pessoa));
    }

    @Test
    public void verificarCadastroComSenhaErrada(){
        Pessoa pessoa = new Pessoa.Builder().comLogin("meuLogin")
                .comNome("meuNome")
                .comSenha("53n")
                .comDataDeNascimento(LocalDate.of(1996,8,20))
                .comSobrenome("meuSobrenome").build();

        Assert.assertEquals("Senha inválida." , this.cadastroService.realizarCadastroDePessoa(pessoa));
    }

    @Test
    public void verificarCadastroComMenorDeDezoitoAnos(){
        Pessoa pessoa = new Pessoa.Builder().comLogin("meuLogin")
                .comNome("meuNome")
                .comSenha("53nh4v4l1d4")
                .comDataDeNascimento(LocalDate.of(2004,8,20))
                .comSobrenome("meuSobrenome").build();

        Assert.assertEquals("Falha ao cadastrar, pessoa menor de 18 anos." , this.cadastroService.realizarCadastroDePessoa(pessoa));
    }

}
