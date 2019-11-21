var PessoaDao = require('../app/pessoa-dao');
var conexao = require('../config/custom-mssql');
const session = require('express-session');

var emailLogado = "vazio";
var codLogado;
var senhaLogada;
var pessoaDao = new PessoaDao(conexao, emailLogado);

function execSQL(sql, resposta) {
    global.conexao.request()
        .query(sql)
        .then(resultado => resposta.json(resultado.recordset))
        .catch(erro => resposta.json(erro));
}

module.exports = (app) => {

        app.post('/', function (req, res) {
            const doacao = req.body.doacao;
            const entidade = req.body.codDaEntidade;
            const qtd = req.body.quantidade;
           conexao.query(`select codigo from HPessoas where email='${emailLogado}'`, (err, result) => {
              codLogado = result.recordset[0].codigo;
            execSQL(`Insert into HDoacoes values (${codLogado},'${doacao}',${entidade},GETUTCDATE(),'N','${qtd}')`, res);
        });
        execSQL(`UPDATE HPessoas set qtdDoacoesFeitas=qtdDoacoesFeitas+1 where email = '${emailLogado}'`, res);
        execSQL(`UPDATE HEntidades set visualizacoes=visualizacoes+1 where codigo = '${entidade}'`, res);
        res.redirect('/#entidades');
    });


    app.get('/', function (req, res) {
        pessoaDao.lista(function (erro, resultados) {
            pessoaDao.listaDoacoes(function (erro, resultados2) {
                pessoaDao.informacoesSobreLogado(function(erro, resultados3){
                    res.render('paginas/home', {
                        lista: resultados["recordset"],
                        listaDoacoes: resultados2["recordset"],
                        informacoesSobreLogado: resultados3["recordset"]
                })
                })
            });

        });
    });

    app.get('/cadastro', function (req, resp) {
        resp.render("paginas/cadastro");
    });

    app.get('/pesquisar/:busc', function (req,resp){
        var busc = req.params.busc;
        execSQL("Busca_sp '"+busc+"'",resp);
    });

    app.post('/cadastro', function (req, resp) {
        const nome = req.body.nome.substring(0, 50);
        const email = req.body.email.substring(0, 50);
        const endereco = req.body.endereco.substring(0, 100);
        const cidade = req.body.cidade.substring(0, 40);
        const uf = req.body.uf.substring(0, 2);
        const senha = req.body.senha.substring(0, 15);
        const telefone = req.body.telefone.substring(0, 14);

        conexao.query(`SELECT * FROM HPessoas where email = '${email}'`, (err, result) => {
            if (result.rowsAffected == 0) {
                execSQL(`INSERT INTO HPessoas VALUES('${nome}','${email}','','','','${endereco}','${cidade}','${uf}','${senha}','${telefone}',0)`, resp);
                resp.render("paginas/login");
            } else
                resp.render("paginas/cadastro");
        });
    });

    app.get('/login', function (req, resp) {
        resp.render("paginas/login");
    });

     app.post('/login', function (req, resp) {
         var email = req.body.email.substring(0, 50);
         var senha = req.body.senha.substring(0, 15);
                     senhaLogada = senha;
                     emailLogado = email;
            pessoaDao = new PessoaDao(conexao, emailLogado);
                  pessoaDao.lista(function (erro, resultados) {
                    pessoaDao.listaDoacoes(function (erro, resultados2) {
                        pessoaDao.informacoesSobreLogado(function(erro, resultados3){
                            resp.render('paginas/home', {
                                lista: resultados["recordset"],
                                listaDoacoes: resultados2["recordset"],
                                informacoesSobreLogado: resultados3["recordset"]
                        })
                    });
                });
                });
     });

     app.get('/usuario', function (req, resp) {
        pessoaDao = new PessoaDao(conexao, emailLogado);
        pessoaDao.listaDeDoacoesFeitas(function (erro, resultados) {
            pessoaDao.informacoesSobreLogado(function(erro, resultados2){
            console.log(resultados);
                  resp.render('paginas/usuario', { 
                      listaDeDoacoesFeitas: resultados["recordset"],
                      informacoesSobreLogado: resultados2["recordset"]
                    });
                });
            });  
        });
           
}
