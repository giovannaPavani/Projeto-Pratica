var PessoaDao = require('../app/pessoa-dao');
var EntidadeDao = require('../app/entidade-dao');
var conexao = require('../config/custom-mssql');
const session = require('express-session');
var senhaLocal;
var emailLogado = "vazio";
var pessoaDao = new PessoaDao(conexao, emailLogado);

var sess;

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
            var codLogado = result.recordset[0].codigo;
            execSQL(`Insert into HDoacoes values (${codLogado},'${doacao}',${entidade},GETUTCDATE(),'N','${qtd}')`, res);
        });

        execSQL(`UPDATE HPessoas set qtdDoacoesFeitas=qtdDoacoesFeitas+1 where email = '${emailLogado}'`, res);
        execSQL(`UPDATE HEntidades set visualizacoes=visualizacoes+1 where codigo = '${entidade}'`, res);

        res.redirect('/#entidades');
    });


    app.get('/', function (req, res) {

        sess = req.session;
        sess.email;
        sess.senha;
        sess.codigo;

        pessoaDao.lista(function (erro, resultados) {
            pessoaDao.listaDoacoes(function (erro, resultados2) {
                pessoaDao.informacoesSobreLogado(function (erro, resultados3) {
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
        if (typeof sess.email == "undefined") {

            resp.render("paginas/cadastro");
        }
        else {
            resp.redirect("/#");
        }

    });

    app.get('/pesquisar/:busc', function (req, resp) {
        var busc = req.params.busc;
        execSQL("Busca_sp '" + busc + "'", resp);
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
        sess = req.session;
        if (typeof sess.email == "undefined") {
            resp.render("paginas/login");
        }
        else {
            resp.redirect("/");
        }
    });

    app.post('/login', function (req, resp) {
        sess = req.session;

         emailLogado = req.body.email.substring(0, 50);
         senhaLocal = req.body.senha.substring(0, 15);

        pessoaDao = new PessoaDao(conexao, emailLogado);
        pessoaDao.buscarPorEmail(emailLogado, function (erro, resultados) {

            if (erro){
                console.log("erro no login");
            }          
            else if (resultados.recordset.length != 0 && resultados.recordset[0].senha == senhaLocal) {
                sess.email = emailLogado;
                sess.senha = senhaLocal;
                emailLogado = sess.email;

                pessoaDao.lista(function (erro, resultados) {
                    pessoaDao.listaDoacoes(function (erro, resultados2) {
                        pessoaDao.informacoesSobreLogado(function (erro, resultados3) {
                            resp.render('paginas/home', {
                                lista: resultados["recordset"],
                                listaDoacoes: resultados2["recordset"],
                                informacoesSobreLogado: resultados3["recordset"]
                            })
                        })
                    });
                });
            }
            else {
                resp.redirect('/login');
            }
        });
    });

    app.get('/sair',(req,res) => {
        req.session.destroy((err) => {
        if(err) {
            return console.log(err);
        }
            emailLogado = "vazio";
            senhaLocal = undefined;
            pessoaDao = new PessoaDao(conexao, emailLogado);
            pessoaDao.lista(function (erro, resultados) {
                pessoaDao.listaDoacoes(function (erro, resultados2) {
                    pessoaDao.informacoesSobreLogado(function (erro, resultados3) {
                        res.render('paginas/home', {
                            lista: resultados["recordset"],
                            listaDoacoes: resultados2["recordset"],
                            informacoesSobreLogado: resultados3["recordset"]
                        })
                    })
                });
            });     
        });
    });

    app.get('/usuario', function (req, resp) {
        pessoaDao = new PessoaDao(conexao, emailLogado);
        pessoaDao.listaDeDoacoesFeitas(function (erro, resultados) {
            pessoaDao.informacoesSobreLogado(function (erro, resultados2) {
                console.log(resultados);
                resp.render('paginas/usuario', {
                    listaDeDoacoesFeitas: resultados["recordset"],
                    informacoesSobreLogado: resultados2["recordset"]
                });
            });
        });
    });

    app.post('/usuario', function (req, resp) {
            const nome = req.body.nome.substring(0, 50);
            const email = req.body.email.substring(0, 50);
            const endereco = req.body.endereco.substring(0, 100);
            const cidade = req.body.cidade.substring(0, 40);
            const uf = req.body.uf.substring(0, 2);
            const telefone = req.body.telefone.substring(0, 14);
    
            pessoaDao = new PessoaDao(conexao, emailLogado);
            pessoaDao.buscarPorEmail(emailLogado, function (erro, resultados) {
                if (erro){
                    console.log("Erro para Atualizar");
                }   
            console.log(emailLogado);
            console.log(email);
                    if(emailLogado != email){
                        conexao.query(`SELECT * FROM HPessoas where email = '${email}'`, (err, result) => {
                            console.log(result);
                            if (result.rowsAffected == 0) { 
                                execSQL(`update HPessoas set email='${email}',nome='${nome}',endereco='${endereco}',telefone='${telefone}',cidade='${cidade}',UF='${uf}' where email = '${emailLogado}'`, resp);
                               emailLogado = email; 
                               resp.redirect('/usuario');
                            } else
                           console.log("erjkljkljkljlterar");
                        });
                    }
                    else{
                        execSQL(`update HPessoas set nome='${nome}',endereco='${endereco}',telefone='${telefone}',cidade='${cidade}',UF='${uf}' where email = '${email}'`, resp);
                        resp.redirect('/usuario');
                    }
                });
        });
}